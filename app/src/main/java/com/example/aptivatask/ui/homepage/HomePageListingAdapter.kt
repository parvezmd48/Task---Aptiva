package com.example.aptivatask.ui.homepage


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aptivatask.databinding.ListingItemViewBinding
import com.example.aptivatask.models.ListingItem
import com.example.aptivatask.models.ListingItemResponse

class HomePageListingAdapter(val context: Context, private var listingsList: ListingItemResponse) :
    RecyclerView.Adapter<MainViewHolder>() {
    fun setListingData(listingslist: ListingItemResponse) {
        this.listingsList = listingslist
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflator = LayoutInflater.from(context)
        val binding = ListingItemViewBinding.inflate(inflator, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val listing = listingsList.results.get(position)
        holder.binding.itemName.text = listing.name
        Glide.with(holder.itemView.context)
            .load(listing.imageUrlsThumbnails.get(0))
            .into(holder.binding.itemImgage)

        holder.binding.root.setOnClickListener {
            onListingItemClickListener?.let {
                it(listing)
            }
        }

    }

    override fun getItemCount(): Int {
        return listingsList.results.size
    }

    private var onListingItemClickListener: ((item: ListingItem) -> Unit)? = null
    fun setonListingItemClickListener(listener: (item: ListingItem) -> Unit) {
        this.onListingItemClickListener = listener
    }
}

class MainViewHolder(val binding: ListingItemViewBinding) : RecyclerView.ViewHolder(binding.root)
