package br.com.brunobrito.covidhack.feature.detail

import CasesCovid19
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import br.com.brunobrito.covidhack.R
import br.com.brunobrito.covidhack.feature.home.presentation.HomePresenter
import br.com.brunobrito.covidhack.platform.extension.incrementNumber
import br.com.brunobrito.covidhack.platform.extension.toLongDate
import br.com.brunobrito.covidhack.platform.util.IntentUtils
import br.com.brunobrito.covidhack.platform.util.ShareUtils
import br.com.brunobrito.data.source.local.findCacheByName
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.layout_bottom_detail.view.*


class DetailBottomSheet : BottomSheetDialogFragment() {

    private lateinit var counterData: CasesCovid19
    private lateinit var mActivity: AppCompatActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return bindView(inflater.inflate(R.layout.layout_bottom_detail, container, false))
    }


    @SuppressLint("SetTextI18n")
    private fun bindView(view: View) = view.apply {

        var counterDataLastDay: CasesCovid19? = null
        val countryDataLastDayList =
            findCacheByName<List<CasesCovid19>>(HomePresenter.CACHE_CASES_LAST)?.filter {
                it.uf == counterData.uf
            } ?: listOf<CasesCovid19>()
        if (countryDataLastDayList.isNotEmpty()) {
            counterDataLastDay = countryDataLastDayList[0]
        }

        if (counterDataLastDay != null) {

            this.tvCasesLast.incrementNumber(
                counterData.cases!! - counterDataLastDay.cases!!
            )
            this.tvSuspeitosLast.incrementNumber(
                counterData.suspects!! - counterDataLastDay.suspects!!
            )
            this.tvMortesLast.incrementNumber(
                counterData.deaths!! - counterDataLastDay.deaths!!
            )
            this.tvRefusesLast.incrementNumber(
                counterData.refuses!! - counterDataLastDay.refuses!!
            )
        } else {
            View.GONE.apply {
                tvCasesLast.rootView.visibility = this
                tvSuspeitosLast.rootView.visibility = this
                tvMortesLast.rootView.visibility = this
                tvRefusesLast.rootView.visibility = this
            }
        }

        this.tvCases.incrementNumber(counterData.cases ?: 0)
        this.tvSuspeitos.incrementNumber(counterData.suspects ?: 0)
        this.tvMortes.incrementNumber(counterData.deaths ?: 0)
        this.tvRefuses.incrementNumber(counterData.refuses ?: 0)


        this.ibShare.setOnClickListener {
            ShareUtils().share(mActivity, view)
        }

        this.ibClose.setOnClickListener {
            dismiss()
        }

        this.btnOpenWeb.setOnClickListener {
            IntentUtils.openWebPage(mActivity, context.getString(R.string.site_covid_gov))
        }

        this.tvDate.text = counterData.datetime?.toLongDate()
        this.tvUserName.text = counterData.state

    }

    companion object {
        fun newInstance(activity: AppCompatActivity, tag: String, data: CasesCovid19) {
            DetailBottomSheet().apply {
                counterData = data
                mActivity = activity
            }.show(activity.supportFragmentManager, tag)
        }
    }
}