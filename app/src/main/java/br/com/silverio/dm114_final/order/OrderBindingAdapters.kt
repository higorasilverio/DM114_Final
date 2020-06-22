package br.com.silverio.dm114_final.order

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.silverio.dm114_final.persistence.OrderPersistence
import java.text.DateFormat
import java.text.SimpleDateFormat

@BindingAdapter("orderId")
fun bindOrderId(txtOrderId: TextView, orderId: Int?) {
    orderId?.let {
        txtOrderId.text = "$orderId"
    }
}
@BindingAdapter("ordersList")
fun bindOrdersList(recyclerView: RecyclerView, orders: List<OrderPersistence>?) {
    val adapter = recyclerView.adapter as OrderAdapter
    adapter.submitList(orders)
}

@BindingAdapter("productPrice")
fun bindProductPrice(txtProductPrice: TextView, productPrice: Double?) {
    productPrice?.let {
        val price = "$ " + "%.2f".format(productPrice)
        txtProductPrice.text = price
    }
}

@BindingAdapter("date")
fun bindDate(txtDate: TextView, date: Long?) {
    date?.let {
        val simple: DateFormat = SimpleDateFormat("dd-MMM-yyyy HH:mm")
        txtDate.text = simple.format(date)
    }
}

@BindingAdapter("identification")
fun bindIdentification(txtIdentification: TextView, identification: Int?) {
    identification?.let {
        txtIdentification.text = "$identification"
    }
}