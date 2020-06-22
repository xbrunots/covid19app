package br.com.brunobrito.covidhack.feature.heart

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.text.Html
import android.text.Spanned
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.brunobrito.covidhack.R
import br.com.brunobrito.covidhack.platform.extension.setStatusBarColor
import br.com.brunobrito.covidhack.platform.extension.setStatusThemeColor
import br.com.brunobrito.covidhack.platform.extension.statusBarLightIcons
import com.example.flatdialoglibrary.dialog.FlatDialog
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_medidor.*


class MedidorActivity : AppCompatActivity() {
    companion object {
        fun newInstance(activity: AppCompatActivity) {
            activity.startActivity(Intent(activity, MedidorActivity::class.java))
        }
    }

    var lista = arrayListOf<Int>()

    @SuppressLint("CheckResult", "MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medidor)
        lista.clear()
        setStatusBarColor(R.color.colorSearchBg)
        statusBarLightIcons()
        setStatusThemeColor(R.color.colorSearchBg, false)
        progress.visibility = View.INVISIBLE

        tvReset.setOnClickListener {
            if (tvReset.text == "PARAR") {
                tvReset.setText("INICIAR")
                HeartRateOmeter().stop(this)
                lista.clear()
                //textView3.text = "0"
                animation_view2.visibility = View.INVISIBLE
                animation_view3.visibility = View.INVISIBLE
                // tvCheckText.setText("")
                tvCheckText.setTextColor(resources.getColor(R.color.colorWhite))
                animate_load.visibility = View.INVISIBLE
                endLess()

            } else {
                tvReset.setText("PARAR")
                textView3.text = ""
                animate_load.visibility = View.VISIBLE
                monitor()
            }
        }

        tvClose.setOnClickListener {
            finish()
        }

        val flatDialog = FlatDialog(this@MedidorActivity)
        flatDialog.setTitle("Atenção")
            .setBackgroundColor(resources.getColor(R.color.progressFundoOK))
            .setSubtitle(getString(R.string.medicao))
            .setFirstButtonText("OK, ENTENDI")
            .setFirstButtonColor(resources.getColor(R.color.fab2))
            .withFirstButtonListner {
                flatDialog.dismiss()
            }
            .show()
    }

    fun String.convertToHTML(): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
        } else {
            @Suppress("DEPRECATION")
            Html.fromHtml(this)
        }
    }

    fun check(num: Int) {
        Handler().postDelayed({
            if (num == lista.size) {
                endLess()
            }
        }, 5000)
    }

    fun endLess() {
        tvReset.setText("INICIAR")
        var v = textView3.text
        lista.clear()
        HeartRateOmeter().stop(this)
        animation_view2.visibility = View.INVISIBLE
        animation_view3.visibility = View.INVISIBLE
        textView3.text = v
        progress.visibility = View.INVISIBLE
    }

    @SuppressLint("CheckResult", "MissingPermission")
    fun monitor() {
        var flatDialogRisk = FlatDialog(this@MedidorActivity)
        HeartRateOmeter()
            .bpmUpdates(this, preview.holder)
            .subscribe(
                {
                    if (tvReset.text == "PARAR") {
                        var media = lista.valueConverted().toString()
                        Log.i("BBB", it.type.name)
                        Log.i("BBB", it.value.toString() + " -> " + media)
                        lista.add(it.value)
                        textView3.text = lista.valueConverted().toString()
                        animation_view2.visibility = View.VISIBLE
                        animation_view3.visibility = View.VISIBLE

                        animate_load.visibility = View.INVISIBLE
                        progress.visibility = View.VISIBLE
                        if (media.toInt() > 99) {
                            tvCheckText.setText("GRUPO DE RISCO")
                            tvCheckText.setTextColor(resources.getColor(R.color.mortetITULO))

                            flatDialogRisk.setTitle("Atenção")
                                .setBackgroundColor(resources.getColor(R.color.progressFundoNOK))
                                .setSubtitle(getString(R.string.risk_group))
                                .setFirstButtonText("DISQUE SAUDE")
                                .setFirstButtonColor(resources.getColor(R.color.colorLikedForegra))
                                .setSecondButtonText("OK, ENTENDI")
                                .setSecondButtonColor(resources.getColor(R.color.progressFundoNOK))
                                .withFirstButtonListner {
                                    RxPermissions(this@MedidorActivity)
                                        .request(Manifest.permission.CALL_PHONE) // ask single or multiple permission once
                                        .subscribe { granted ->
                                            if (granted) {
                                                val callIntent = Intent(Intent.ACTION_CALL)
                                                callIntent.data = Uri.parse("tel:136")
                                                startActivity(callIntent)
                                            }
                                        }
                                }
                                .withSecondButtonListner {
                                    flatDialogRisk.dismiss()
                                }
                                .show()

                        } else {
                            flatDialogRisk.dismiss()
                            tvCheckText.setText("NORMAL")
                            tvCheckText.setTextColor(resources.getColor(R.color.colorWhite))
                        }
                        check(lista.size)
                    }
                },
                Throwable::printStackTrace
            )
    }

    fun ArrayList<Int>.valueConverted(): Int {
        var qt = this.size
        var sum = this.sum()
        return if (sum < 2) {
            0
        } else {
            sum / qt
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        lista.clear()
        HeartRateOmeter().stop(this)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        lista.clear()
        HeartRateOmeter().stop(this)
        finish()
    }
}