package br.com.silverio.dm114_final.product

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.silverio.dm114_final.persistence.Product

@BindingAdapter("productsList")
fun bindProductsList(recyclerView: RecyclerView, products: List<Product>?) {
    val adapter = recyclerView.adapter as ProductAdapter
    adapter.submitList(products)
}

@BindingAdapter("productPrice")
fun bindProductPrice(txtProductPrice: TextView, productPrice: Double?) {
    productPrice?.let {
        val price = "$ " + "%.2f".format(productPrice)
        txtProductPrice.text = price
    }
}