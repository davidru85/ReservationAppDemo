package com.ruizurraca.reservationappdemo.classes.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ruizurraca.reservationappdemo.classes.data.models.Bookings
import com.ruizurraca.reservationappdemo.databinding.ItemClassBinding

class ClassesAdapter : RecyclerView.Adapter<ClassesAdapter.ClassHolder>() {

    var listener: ClassClickListener? = null
    private var bookings = mutableListOf<Bookings>()

    fun fillData(bookings: List<Bookings>) {
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
        fun bind(currentClass: Bookings) {
            binding.clCurrentClass.setOnClickListener {
                listener?.classClick(currentClass)
            }
            binding.name.text = "${currentClass.className} ${currentClass.time}"
        }
    }
}

interface ClassClickListener {
    fun classClick(currentClass: Bookings)
}