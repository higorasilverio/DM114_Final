package br.com.silverio.dm114_final.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.silverio.dm114_final.network.Product
import br.com.silverio.dm114_final.databinding.ItemProductBinding

class ProductAdapter
    (val onProductClickListener: ProductClickListener):
    ListAdapter<Product, ProductAdapter.ProductViewHolder>(ProductDiff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ProductViewHolder {
        return ProductViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.context)))
    }
    override fun onBindViewHolder(holder: ProductAdapter.ProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product)
        holder.itemView.setOnClickListener {
            onProductClickListener.onClick(product)
        }
    }
    class ProductViewHolder(private var binding: ItemProductBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.product = product
            binding.executePendingBindings()
        }
    }
    companion object ProductDiff : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return ((oldItem.id == newItem.id)
                    && (oldItem.name.equals(newItem.name))
                    && (oldItem.code.equals(newItem.code))
                    && (oldItem.price == newItem.price))
        }
    }
    class ProductClickListener(val clickListener: (product: Product) -> Unit) {
        fun onClick(product: Product) = clickListener(product)
    }

}