package com.alpamedev.styleshop.ui.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.alpamedev.styleshop.databinding.FragmentDetailBinding
import com.alpamedev.styleshop.ui.viewmodels.MainViewModel
import com.alpamedev.styleshop.ui.adapters.ProductCarouselAdapter
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val parentViewModel: MainViewModel by activityViewModels()
    private lateinit var carouselAdapter: ProductCarouselAdapter
    private lateinit var navController: NavController
    private val args: DetailFragmentArgs by navArgs()

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
}