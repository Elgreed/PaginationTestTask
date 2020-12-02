package ru.elgreed.testtask.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.elgreed.testtask.R
import ru.elgreed.testtask.databinding.NumberItemBinding

class NumbersAdapter(var context: Context) : PagedListAdapter<Long, NumbersAdapter.ViewHolder>(DIFF_CALLBACK) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = NumberItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCurrentListChanged(previousList: PagedList<Long>?, currentList: PagedList<Long>?) {
        super.onCurrentListChanged(previousList, currentList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(var binding: NumberItemBinding) : RecyclerView.ViewHolder(binding.root)
    {
        private fun containerColor()
        {
            val row = (adapterPosition / 2)
            val column = adapterPosition % 2

            if((row+column) % 2 == 0)
            {
                binding.numberContainer.setBackgroundColor(ContextCompat.getColor(context, R.color.grey))
                binding.numberTextView.setTextColor(ContextCompat.getColor(context, R.color.white))
            }
            else
            {
                binding.numberContainer.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
                binding.numberTextView.setTextColor(ContextCompat.getColor(context, R.color.grey))
            }
        }
        fun bind(number: Long?)
        {
            binding.numberTextView.text = number.toString()
            containerColor()
        }
    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<Long>() {
            override fun areItemsTheSame(oldItem: Long, newItem: Long): Boolean {
               return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Long, newItem: Long): Boolean {
                return oldItem == newItem
            }
        }
    }
}