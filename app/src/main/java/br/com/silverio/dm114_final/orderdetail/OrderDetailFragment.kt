package br.com.silverio.dm114_final.orderdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import br.com.silverio.dm114_final.databinding.FragmentOrderDetailBinding

class OrderDetailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentOrderDetailBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        val productCode = OrderDetailFragmentArgs.fromBundle(arguments!!).productCode
        val OrderDetailViewModelFactory = OrderDetailViewModelFactory(productCode)
        binding.orderDetailViewModel = ViewModelProviders.of(
            this, OrderDetailViewModelFactory).get(OrderDetailViewModel::class.java)

        return binding.root
    }
}