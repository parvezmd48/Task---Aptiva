package com.example.aptivatask.ui.homepage

import javax.inject.Inject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aptivatask.models.ListingItemResponse
import com.example.aptivatask.respository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel
class HomepageViewModel @Inject constructor(var repo: Repository) : ViewModel() {
    val movieList = MutableLiveData<ListingItemResponse>()
    val errorMessage = MutableLiveData<String>()

    init {
        viewModelScope.launch {
            repo.getListings(
                movieList,
                errorMessage
            )
        }

    }
}