package br.com.brunobrito.covidhack.feature.home.presentation.adapter

import CasesCovid19
import android.annotation.SuppressLint
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import br.com.brunobrito.covidhack.R
import br.com.brunobrito.covidhack.feature.detail.DetailBottomSheet
import br.com.brunobrito.covidhack.feature.home.presentation.HomeActivity
import br.com.brunobrito.covidhack.platform.base.BaseActivity
import br.com.brunobrito.covidhack.platform.extension.incrementNumber
import br.com.brunobrito.covidhack.platform.extension.showInAdapter
import br.com.brunobrito.covidhack.platform.extension.toShortDate
import br.com.brunobrito.covidhack.platform.util.ShareUtils


class CasesCovide19ItemAdapter(
    private var context: AppCompatActivity,
    private var countryList: List<CasesCovid19>
) :
    RecyclerView.Adapter<CasesCovide19ItemAdapter.ViewHolder>() {

    lateinit var view: View

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val casesCovid19 = countryList[position]


        holder.value.showInAdapter()
        holder.date.showInAdapter()
        holder.description.showInAdapter()

        holder.tvConfirmados.showInAdapter()
        holder.tvSuspeitos.showInAdapter()
        holder.tvMortos.showInAdapter()
        holder.tvRecuperados.showInAdapter()

        holder.tvConfirmados.incrementNumber(casesCovid19.cases ?: 0)
        holder.tvSuspeitos.incrementNumber(casesCovid19.suspects ?: 0)
        holder.tvMortos.incrementNumber(casesCovid19.deaths ?: 0)
        holder.tvRecuperados.incrementNumber(casesCovid19.refuses ?: 0)

        holder.date.text = casesCovid19.datetime?.toShortDate()
        holder.value.text = casesCovid19.state.toString()
        holder.description.text = casesCovid19.deaths.toString()

        view.setOnClickListener {
            casesCovid19.getDetails()
        }

        view.setOnLongClickListener {
            val popup = PopupMenu(context, holder.value, Gravity.RIGHT)
            popup.inflate(R.menu.menu_long_click)
            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.share -> {
                        ShareUtils().share(context as BaseActivity, view)
                        true
                    }
                    else -> false
                }
            }
            popup.show()
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    internal fun getItem(id: Int): CasesCovid19 {
        return countryList[id]
    }

    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        view = mInflater.inflate(R.layout.issue_layout_item, parent, false)
        return ViewHolder(view)
    }

    fun CasesCovid19.getDetails() {
        DetailBottomSheet.newInstance(
            (context as HomeActivity),
            "Por dentro de " + this.state,
            this
        )
    }

    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        internal var value: TextView = itemView.findViewById(R.id.tvName)
        internal var description: TextView = itemView.findViewById(R.id.tvDescription)
        internal var date: TextView = itemView.findViewById(R.id.tvDateTime)
        internal var cardMain: CardView = itemView.findViewById(R.id.cardMain)

        internal var tvConfirmados: TextView = itemView.findViewById(R.id.tvConfirmados)
        internal var tvSuspeitos: TextView = itemView.findViewById(R.id.tvSuspeitos)
        internal var tvMortos: TextView = itemView.findViewById(R.id.tvMortos)
        internal var tvRecuperados: TextView = itemView.findViewById(R.id.tvRecuperados)
    }
}
