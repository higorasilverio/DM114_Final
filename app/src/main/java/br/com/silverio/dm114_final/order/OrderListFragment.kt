package br.com.silverio.dm114_final.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView.VERTICAL


class OrdersListFragment : Fragment() {

    private val orderListViewModel: OrderListViewModel by lazy {
        ViewModelProviders.of(this).get(OrderListViewModel::class.java)
    }

    override fun onCreateView
                (inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = FragmentOrdersListBinding.inflate(inflater)

        binding.setLifecycleOwner(this)
        binding.orderListViewModel= orderListViewModel

        val itemDecor = DividerItemDecoration(getContext(), VERTICAL);
        binding.rcvOrders.addItemDecoration(itemDecor);

        binding.rcvPOrders.adapter =
            OrderAdapter(OrderAdapter.OrderClickListener {
               this.findNavController()
                    .navigate(
                        OrdersListFragmentDirections.actionShowOrderDetail(
                            it.code
                        )
                    )
            })

        binding.ordersRefresh.setOnRefreshListener {
            orderListViewModel.refreshOrders()
            binding.ordersRefresh.isRefreshing = false
        }
        return binding.root
    }

}