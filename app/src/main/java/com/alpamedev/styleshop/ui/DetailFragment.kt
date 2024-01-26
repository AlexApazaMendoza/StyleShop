package com.alpamedev.styleshop.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.alpamedev.styleshop.R
import com.alpamedev.styleshop.databinding.FragmentDetailBinding
import com.alpamedev.styleshop.ui.adapters.ProductCarouselAdapter
import com.alpamedev.styleshop.ui.adapters.ProductItemAdapter
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val parentViewModel: MainViewModel by activityViewModels()
    private lateinit var carouselAdapter: ProductCarouselAdapter
    private lateinit var navController: NavController
    val args: DetailFragmentArgs by navArgs()
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
        binding = FragmentDetailBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        setUpCarousel()
        val idProduct = args.idProduct
        setUpView(idProduct)
    }

    private fun setUpCarousel() {
        carouselAdapter = ProductCarouselAdapter()
        binding.rvCarousel.apply {
            layoutManager = CarouselLayoutManager()
            adapter = carouselAdapter
        }
        val snapHelper = CarouselSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvCarousel)
    }

    private fun setUpView(idProduct: Int?) {
        idProduct?.let {
            parentViewModel.getProductById(it)?.let { p ->
                binding.product = p
                carouselAdapter.updateList(p.images)
            }
        }
        binding.ivReturn.setOnClickListener {
            navController.popBackStack()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}