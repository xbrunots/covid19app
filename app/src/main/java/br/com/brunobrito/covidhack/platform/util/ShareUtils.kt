package br.com.brunobrito.covidhack.platform.util

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.tbruyelle.rxpermissions2.RxPermissions


class ShareUtils {

    @SuppressLint("CheckResult")
    fun share(activity: AppCompatActivity, view: View) {
//            val intent2 = Intent()
//            intent2.action = Intent.ACTION_SEND
//            intent2.type = "text/plain"
//            var dataVova = data.datetime?.toLongDate()
//            var conteudo =
//                "Olhe como andam os casos acumulados de Covid-19 em " + data.state + " até a data de " + dataVova + "\n" +
//                        "casos: " + data.cases + "\n" +
//                        "mortes: " + data.deaths + "\n" +
//                        "suspeitos :" + data.suspects + "\n\n" +
//                        "Faça sua parte, evite sair de casa e se sair use mascara!\n"
//            intent2.putExtra(Intent.EXTRA_TEXT, conteudo)
//            activity.startActivity(Intent.createChooser(intent2, "Compartilhar"))


        RxPermissions(activity)
            .request(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            .subscribe { granted ->
                if (granted) {
                    activity.takeScreenshot(view)
                }
            }

    }

    fun getBitmapFromView(view: View): Bitmap? {
        val returnedBitmap =
            Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(returnedBitmap)
        val bgDrawable = view.background
        if (bgDrawable != null) bgDrawable.draw(canvas) else canvas.drawColor(Color.BLACK)
        view.draw(canvas)
        return returnedBitmap
    }

    fun AppCompatActivity.takeScreenshot(v1: View) {

        val mBitmap = getBitmapFromView(v1)

        val path = MediaStore.Images.Media.insertImage(
            contentResolver,
            mBitmap, "Design", null
        )

        val uri: Uri = Uri.parse(path)

        val share = Intent(Intent.ACTION_SEND)
        share.type = "image/*"
        share.putExtra(Intent.EXTRA_STREAM, uri)
        share.putExtra(Intent.EXTRA_TEXT, "Ultimas noticias sobre o Covid-19")
        startActivity(Intent.createChooser(share, "Compartilhar"))
    }
}