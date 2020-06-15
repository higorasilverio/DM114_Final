package br.com.silverio.dm114_final.productdetail

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import br.com.silverio.dm114_final.R
import br.com.silverio.dm114_final.databinding.FragmentProductDetailBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig

class ProductDetailFragment : Fragment() {
    private var productCode: String? = null

    private lateinit var binding: FragmentProductDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentProductDetailBinding.inflate(inflater)

        binding.setLifecycleOwner(this)
        //Fetch the product code and create the ViewModel here

        productCode = ProductDetailFragmentArgs.fromBundle(arguments!!).productCode
        val productDetailViewModelFactory = ProductDetailViewModelFactory(productCode)
        binding.productDetailViewModel = ViewModelProviders.of(
            this, productDetailViewModelFactory).get(ProductDetailViewModel::class.java)
        val remoteConfig = Firebase.remoteConfig
        setHasOptionsMenu(remoteConfig.getBoolean("delete_detail_view"))
        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.product_details_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_product -> {
                binding.productDetailViewModel?.deleteProduct()
                val firebaseAnalytics = FirebaseAnalytics.getInstance(this.context!!)
                val bundle = Bundle()
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, productCode)
                firebaseAnalytics.logEvent("delete_item", bundle)
                findNavController().popBackStack()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}