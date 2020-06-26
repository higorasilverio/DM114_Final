package br.com.silverio.dm114_final.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.silverio.dm114_final.databinding.ItemOrderBinding
import br.com.silverio.dm114_final.persistence.OrderPersistence

class OrderAdapter
    (val onOrderClickListener: OrderClickListener):
    ListAdapter<OrderPersistence, OrderAdapter.OrderViewHolder>(
        OrderDiff
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(
            ItemOrderBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: OrderAdapter.OrderViewHolder, position: Int) {
        val order = getItem(position)
        holder.bind(order)
        holder.itemView.setOnClickListener {
            onOrderClickListener.onClick(order)
        }
    }

    class OrderViewHolder(private var binding: ItemOrderBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(order: OrderPersistence) {
            binding.orderPersistence = order
            binding.executePendingBindings()
        }
    }

    companion object OrderDiff : DiffUtil.ItemCallback<OrderPersistence>() {
        override fun areItemsTheSame(oldItem: OrderPersistence, newItem: OrderPersistence): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: OrderPersistence, newItem: OrderPersistence): Boolean {
            return ((oldItem.id == newItem.id)
                    && (oldItem.name.equals(newItem.name))
                    && (oldItem.code.equals(newItem.code))
                    && (oldItem.price == newItem.price))
        }
    }

    class OrderClickListener(val clickListener: (order: OrderPersistence) -> Unit) {
        fun onClick(order: OrderPersistence) = clickListener(order)
    }

}