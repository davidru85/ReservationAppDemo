package com.ruizurraca.reservationappdemo.box.presentation

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ruizurraca.reservationappdemo.box.presentation.models.BoxResponseModel
import com.ruizurraca.reservationappdemo.databinding.ItemBoxBinding

class BoxesAdapter : RecyclerView.Adapter<BoxesAdapter.BoxHolder>() {

    companion object {
        const val TAG = "ClassesAdapter"
    }

    var listener: BoxesClickListener? = null
    private var boxesList = mutableListOf<BoxResponseModel>()

    fun fillData(boxes: List<BoxResponseModel>) {
        this.boxesList = boxes.toMutableList()
        notifyDataSetChanged()
    }

    fun empty() {
        boxesList = mutableListOf()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BoxHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBoxBinding.inflate(inflater, parent, false)
        return BoxHolder(binding, parent.context)
    }

    override fun getItemCount() = boxesList.size

    override fun onBindViewHolder(holder: BoxHolder, position: Int) {
        boxesList.get(position).let { currentBox ->
            holder.bind(currentBox)
        }
    }

    inner class BoxHolder(
        itemBoxBinding: ItemBoxBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(itemBoxBinding.root) {
        private val binding = itemBoxBinding
        fun bind(currentBox: BoxResponseModel) {
            Log.d(TAG, "bind: $currentBox")
            binding.clCurrentBox.setOnClickListener {
                listener?.boxClick(currentBox)
            }
            binding.tvName.text = currentBox.title
            Glide.with(context).load(currentBox.photo).into(binding.ivPhoto)
        }
    }
}

interface BoxesClickListener {
    fun boxClick(boxResponseModel: BoxResponseModel)
}
