package com.ruizurraca.reservationappdemo.classes.presentation

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.ruizurraca.reservationappdemo.classes.presentation.models.BookingsModel
import com.ruizurraca.reservationappdemo.databinding.ItemClassBinding

class ClassesAdapter : RecyclerView.Adapter<ClassesAdapter.ClassHolder>() {

    companion object {
        const val TAG = "ClassesAdapter"
    }

    var listener: ClassClickListener? = null
    private var bookings = mutableListOf<BookingsModel>()

    fun fillData(bookings: List<BookingsModel>) {
        this.bookings = bookings.toMutableList()
        notifyDataSetChanged()
    }

    fun empty() {
        bookings = mutableListOf()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ClassHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemClassBinding.inflate(inflater, parent, false)
        return ClassHolder(binding, parent.context)
    }

    override fun getItemCount() = bookings.size

    override fun onBindViewHolder(holder: ClassHolder, position: Int) {
        bookings.get(position).let { currentClass ->
            holder.bind(currentClass)
        }
    }

    inner class ClassHolder(
        itemClassBinding: ItemClassBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(itemClassBinding.root) {
        private val binding = itemClassBinding
        fun bind(currentClass: BookingsModel) {
            Log.d(TAG, "bind: $currentClass")
            binding.clCurrentClass.setOnClickListener {
                listener?.classClick(currentClass)
            }
            binding.tvTime.text = "${currentClass.time}"
            binding.tvName.text = "${currentClass.className}"
            binding.tvBooked.isVisible = currentClass.isBooked()
        }
    }
}

interface ClassClickListener {
    fun classClick(currentClass: BookingsModel)
}
