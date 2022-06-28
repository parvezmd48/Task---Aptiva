package com.example.aptivatask.ui.detailsPage

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.aptivatask.databinding.FragmentListingItemDetailsPageBinding

class ListingItemDetailsPage : Fragment() {
private val args by navArgs<ListingItemDetailsPageArgs>()

  private lateinit var binding :FragmentListingItemDetailsPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentListingItemDetailsPageBinding.inflate(inflater, container, false)
        showProgressBar()
        bindData()
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            // Handle the back button event
            findNavController().popBackStack()
        }
        return binding.root
    }

    private fun bindData() {
        binding.itemName.text = args.item.name
        binding.itemPrice.text = args.item.price
        Glide.with(binding.itemImgage.context)
            .load(args.item.imageUrls.get(0))
            .into(binding.itemImgage)

    }
   fun showProgressBar(){
        Handler(Looper.myLooper()!!).postDelayed(
            Runnable { binding.progressBar.setVisibility(View.GONE) },
            2000
        )
    }

}