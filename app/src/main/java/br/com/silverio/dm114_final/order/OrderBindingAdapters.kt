package br.com.silverio.dm114_final.order

import android.widget.TextView
import androidx.databinding.BindingAdapter
@BindingAdapter("orderId")
fun bindOrderId(txtOrderId: TextView, orderId: Int?) {
    orderId?.let {
        txtOrderId.text = "$orderId"
    }
}