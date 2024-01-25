package com.alpamedev.styleshop.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.alpamedev.domain.Product
import com.alpamedev.styleshop.R
import com.alpamedev.styleshop.databinding.FragmentProductsBinding
import com.alpamedev.styleshop.ui.adapters.ProductItemAdapter
import com.alpamedev.styleshop.ui.listeners.OnProductListener

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProductsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductsFragment : Fragment(), OnProductListener {
    private lateinit var binding: FragmentProductsBinding
    private val parentViewModel: MainViewModel by activityViewModels()
    private lateinit var productItemAdapter: ProductItemAdapter
    private lateinit var navController: NavController
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        setUpViews()
        setUpObservers()
    }

    private fun setUpViews() {
        binding.tieSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val data = if (s.toString().isNotEmpty()) {
                    parentViewModel.products.value?.filter {
                        it.title.contains(s.toString(), true)
                    } ?: listOf()
                } else{
                    parentViewModel.products.value ?: listOf()
                }
                productItemAdapter.updateList(data)
            }

            override fun afterTextChanged(s: Editable?) { }
        })

        binding.ibSort.setOnClickListener {
            productItemAdapter.sortList()
        }

        setUpRecyclerView()
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
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProductsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProductsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(product: Product) {
        val action = ProductsFragmentDirections.actionProductsFragmentToDetailFragment(product.id)
        navController.navigate(action)
    }
}