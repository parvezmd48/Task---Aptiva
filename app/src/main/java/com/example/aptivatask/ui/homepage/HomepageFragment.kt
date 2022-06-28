package com.example.aptivatask.ui.homepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.aptivatask.databinding.FragmentHomepageBinding
import com.example.aptivatask.models.ListingItem
import com.example.aptivatask.models.ListingItemResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomepageFragment : Fragment() {
    lateinit var homePageViewModel: HomepageViewModel
    lateinit var binding: FragmentHomepageBinding
    private val adapter: HomePageListingAdapter by lazy {
        HomePageListingAdapter(
            requireContext(),
            ListingItemResponse(emptyList<ListingItem>())
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homePageViewModel = ViewModelProvider(requireActivity()).get(HomepageViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomepageBinding.inflate(inflater, container, false)
        initViews()
        return binding.root
    }

    private fun initViews() {
        val llm = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        binding.listingsList.layoutManager = llm
        binding.listingsList.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homePageViewModel.movieList.observe(viewLifecycleOwner, Observer {
            binding.progressBar.visibility = GONE
            adapter.setListingData(it)
        })
        setOnclickListeners()
    }

    fun setOnclickListeners() {
        adapter.setonListingItemClickListener {
            val action =
                HomepageFragmentDirections.actionHomepageFragmentToListingItemDetailsPage(it)
            findNavController().navigate(action)
        }
    }
}