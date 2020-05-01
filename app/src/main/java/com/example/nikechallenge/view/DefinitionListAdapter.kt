package com.example.nikechallenge.view

import com.example.nikechallenge.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nikechallenge.model.DefinitionObject
import com.example.nikechallenge.model.DefinitionResponse

class DefinitionListAdapter : RecyclerView.Adapter<DefinitionListAdapter.DefinitionViewHolder>() {

    var dataSet: DefinitionResponse? = null

    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DefinitionViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.definition_item_layout,
                parent,
                false)
        )

    override fun getItemCount() = dataSet?.list?.size?: 0

    override fun onBindViewHolder(holder: DefinitionViewHolder, position: Int) {
        dataSet?.list?.let {
            holder.onBind(it[position])
        }
    }

    class DefinitionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvDescriptionItem: TextView = itemView.findViewById(R.id.tv_definition)
        private val tvThumbsUpNumber: TextView = itemView.findViewById(R.id.tv_upvotes)
        private val tvThumbsDownNumber: TextView = itemView.findViewById(R.id.tv_downvotes)

        fun onBind(dataItem: DefinitionObject) {
            tvDescriptionItem.text = dataItem.definition
            tvThumbsDownNumber.text = dataItem.thumbs_down.toString()
            tvThumbsUpNumber.text = dataItem.thumbs_up.toString()
        }
    }
}