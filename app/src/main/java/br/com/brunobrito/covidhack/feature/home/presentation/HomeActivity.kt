package br.com.brunobrito.covidhack.feature.home.presentation

import CasesCovid19
import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import br.com.brunobrito.covidhack.R
import br.com.brunobrito.covidhack.databinding.HomeActivityBinding
import br.com.brunobrito.covidhack.feature.heart.MedidorActivity
import br.com.brunobrito.covidhack.feature.home.domain.HomeContract
import br.com.brunobrito.covidhack.feature.home.presentation.HomePresenter.Companion.CACHE_CASES_NOW
import br.com.brunobrito.covidhack.feature.home.presentation.adapter.CasesCovide19ItemAdapter
import br.com.brunobrito.covidhack.feature.home.presentation.adapter.StoriesItemAdapter
import br.com.brunobrito.covidhack.platform.base.BaseActivity
import br.com.brunobrito.covidhack.platform.extension.*
import br.com.brunobrito.data.source.local.findCacheByName
import com.ahmadrosid.svgloader.SvgLoader
import com.google.firebase.analytics.FirebaseAnalytics
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.home_activity.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class HomeActivity : BaseActivity(), HomeContract.IView, SwipeRefreshLayout.OnRefreshListener {
    private val presenter: HomePresenter by inject { parametersOf(this) }
    private lateinit var adapter: CasesCovide19ItemAdapter
    private lateinit var adapterStories: StoriesItemAdapter
    private lateinit var binding: HomeActivityBinding
    private var mFirebaseAnalytics: FirebaseAnalytics? = null

    companion object {
        fun newInstance(activity: AppCompatActivity) {
            activity.startActivity(Intent(activity, HomeActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.home_activity)
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        configBind()
        configTheme()
        filterFactory()
        presenter.attachView("Brazil")
    }

    @SuppressLint("CheckResult")
    private fun configBind() {
        binding.swipeRefreshIssues.setOnRefreshListener(this)
        binding.presenter = presenter
        binding.swipeRefreshIssues.setBackgroundColor(Color.BLACK)
        val c1 = resources.getColor(R.color.fab)
        val c2 = resources.getColor(R.color.fab2)
        val c3 = resources.getColor(R.color.fab3)
        binding.swipeRefreshIssues.setColorSchemeColors(c1, c2, c3)
        hideSearchView()

        binding.tvMedidor.setOnClickListener {
            RxPermissions(this)
                .request(Manifest.permission.CAMERA) // ask single or multiple permission once
                .subscribe { granted ->
                    if (granted) {
                        // All requested permissions are granted
                        MedidorActivity.newInstance(this)

                    } else {
                        // At least one permission is denied

                    }
                }

        }
    }

    fun ArrayList<Int>.valueConverted(): Int {
        var qt = this.size
        var sum = this.sum()
        return sum / qt
    }

    override fun onRefresh() {
        presenter.attachView("Brazil")
    }

    override fun showSearchView() {

        etFilter.visibility = View.GONE
        setStatusBarColor(R.color.colorSearchBg)
        statusBarLightIcons()
        linearSearch.expand(150) {
            etFilter.visibility = View.VISIBLE
            etFilter.fadeIn(100) {}
            etFilter.requestFocus()
        }
    }

    override fun hideSearchView() {

        if (etFilter.visibility == View.VISIBLE) {
            invokeAdapter(findCacheByName(CACHE_CASES_NOW) ?: listOf())
            etFilter.fadeOut(100) {
                linearSearch.collapse(150) {
                    statusBarLightIcons()
                    setStatusBarColor(R.color.colorSearchBg)
                    etFilter.visibility = View.GONE
                }
            }
        }
    }

    private fun filterFactory() {
        etFilter.onKeyText {
            presenter.filter(it)
        }
    }

    private fun configTheme() {
        setStatusThemeColor(R.color.colorAccent, false)
    }

    override fun invokeAdapter(list: List<CasesCovid19>) {
        adapter = CasesCovide19ItemAdapter(this, list)
        rvIssues.adapter = adapter
        rvIssues.layoutAnimation = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation)
        adapter.notifyDataSetChanged()
        rvIssues.scheduleLayoutAnimation()
    }

    @SuppressLint("SetTextI18n")
    override fun invokeView(nowData: List<CasesCovid19>, lastData: List<CasesCovid19>) {
        val layoutManager = GridLayoutManager(this, 1)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rvIssues.layoutManager = layoutManager

        txtData.setText(
            "Ultimo relatório do governo: " + nowData[0].datetime?.toLongDate() + ""
        )

        invokeAdapter(nowData)
        notifyButton2.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:xpto.covid19@gmail.com")
            }
            startActivity(Intent.createChooser(emailIntent, "Feedback"))
        }
    }

    fun invokeAdapterWorld(nowData: List<CasesCovid19>, lastData: List<CasesCovid19>) {
        var casesNow = 0
        var suspectsNow = 0
        var deathsNow = 0
        var refusesNow = 0

        try {
            deathsNow = nowData.sumBy { it.deaths!! } - lastData.sumBy { it.deaths!! }
        } catch (e: Exception) {

        }

        adapterStories = StoriesItemAdapter(
            this, listOf(
//                Triple("Confirmados", casesNow ?: 0, R.color.confirmados),
//                Triple("Recuperados", refusesNow ?: 0, R.color.recuperados),
//                Triple("Suspeitos", suspectsNow ?: 0, R.color.amarelo),
                Triple("Mortes por COVID-19 nas últimas 24 horas", deathsNow ?: 0, R.color.mortes)
            )
        )
        rvStories.adapter = adapterStories
        rvStories.layoutAnimation =
            AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation)
        adapterStories.notifyDataSetChanged()
        rvStories.scheduleLayoutAnimation()
    }

    override fun invokeStories(nowData: List<CasesCovid19>, lastData: List<CasesCovid19>) {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        rvStories.layoutManager = layoutManager
        invokeAdapterWorld(nowData, lastData)
    }

    override fun showLoading() {
        swipeRefreshIssues.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefreshIssues.isRefreshing = false
    }

    override fun showApiError() {
        logger.e(getString(R.string.api_error))
        onError(rvIssues, getString(R.string.api_error))
    }

    override fun showConnectionError() {
        logger.e(getString(R.string.internet_error))
        onInternetError(rvIssues, getString(R.string.internet_error)) {
            presenter.attachView("Brazil")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
        SvgLoader.pluck().close()

    }
}
