package com.alpamedev.styleshop.ui.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.alpamedev.domain.Product
import com.alpamedev.styleshop.databinding.FragmentProductsBinding
import com.alpamedev.styleshop.ui.viewmodels.MainViewModel
import com.alpamedev.styleshop.ui.adapters.ProductItemAdapter
import com.alpamedev.styleshop.ui.listeners.OnProductListener
import com.alpamedev.styleshop.ui.viewmodels.ProductsViewModel

class ProductsFragment : Fragment(), OnProductListener {
    private lateinit var binding: FragmentProductsBinding
    private val parentViewModel: MainViewModel by activityViewModels()
    private val productsViewModel: ProductsViewModel by activityViewModels()
    private lateinit var productItemAdapter: ProductItemAdapter
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        setUpViewModel()
        setUpRecyclerView()
        setUpObservers()
    }

    private fun setUpViewModel() {
        binding.viewmodel = productsViewModel
    }

    private fun setUpRecyclerView() {
        productItemAdapter = ProductItemAdapter(this)
        binding.rvProduct.apply {
            adapter = productItemAdapter
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(),2)
        }
    }

    private fun setUpObservers() {
        parentViewModel.products.observe(viewLifecycleOwner) {
            productItemAdapter.updateList(it)
        }
        productsViewModel.text.observe(viewLifecycleOwner) {
            val data = if (it.isNotEmpty()) {
                parentViewModel.products.value?.filter { p ->
                    p.title.contains(it, true)
                } ?: listOf()
            } else{
                parentViewModel.products.value ?: listOf()
            }
            productItemAdapter.updateList(data)
        }
        productsViewModel.isSortClicked.observe(viewLifecycleOwner) {
            if (it) {
                productItemAdapter.sortList()
            }
        }
    }

    override fun onClick(product: Product) {
        val action = ProductsFragmentDirections.actionProductsFragmentToDetailFragment(product.id)
        navController.navigate(action)
    }
}