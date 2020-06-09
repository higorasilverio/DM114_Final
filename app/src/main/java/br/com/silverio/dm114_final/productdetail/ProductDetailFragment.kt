package br.com.silverio.dm114_final.productdetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import br.com.silverio.dm114_final.databinding.FragmentProductDetailBinding
import br.com.silverio.dm114_final.productdetail.ProductDetailFragmentArgs
import br.com.silverio.dm114_final.productdetail.ProductDetailViewModel
import br.com.silverio.dm114_final.productdetail.ProductDetailViewModelFactory

private const val TAG = "ProductDetailFragment"
class ProductDetailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.i(TAG, "Creating ProductDetailFragment")
        val binding = FragmentProductDetailBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        //Fetch the product code and create the ViewModel here

        val productCode = ProductDetailFragmentArgs.fromBundle(arguments!!).productCode
        val productDetailViewModelFactory = ProductDetailViewModelFactory(productCode)
        binding.productDetailViewModel = ViewModelProviders.of(
            this, productDetailViewModelFactory).get(ProductDetailViewModel::class.java)
        return binding.root
    }
}