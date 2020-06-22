package br.com.brunobrito.covidhack.feature.home.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.brunobrito.covidhack.R
import br.com.brunobrito.covidhack.platform.extension.setStatusThemeColor
import br.com.brunobrito.covidhack.platform.extension.showInAdapter


class StoriesItemAdapter(
    private var context: AppCompatActivity,
    private var countryList: List<Triple<String, Int, Int>>
) :
    RecyclerView.Adapter<StoriesItemAdapter.ViewHolder>() {

    lateinit var view: View

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val countryList = countryList[position]

        holder.value.showInAdapter()
        holder.description.showInAdapter()

        holder.value.text = countryList.second.toString()
        holder.description.text = countryList.first
        holder.cardMain.setCardBackgroundColor(ContextCompat.getColor(context, countryList.third))

        view.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    internal fun getItem(id: Int): Triple<String, Int, Int> {
        return countryList[id]
    }

    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        view = mInflater.inflate(R.layout.stories, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        internal var value: TextView = itemView.findViewById(R.id.tvValue)
        internal var description: TextView = itemView.findViewById(R.id.tvDescription)
        internal var cardMain: CardView = itemView.findViewById(R.id.cardMain)
    }
}
