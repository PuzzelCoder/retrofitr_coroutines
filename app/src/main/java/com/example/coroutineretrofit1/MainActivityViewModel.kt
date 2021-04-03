package com.example.coroutineretrofit1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutineretrofit1.model.RecylerList
import com.example.coroutineretrofit1.network.RetrofitInstance
import com.example.coroutineretrofit1.network.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel:ViewModel() {
     var recyclerListLiveData: MutableLiveData<RecylerList>

    init {
        recyclerListLiveData= MutableLiveData()
    }
    fun getRecyclerListObservers():LiveData<RecylerList>{
        return recyclerListLiveData
    }

    fun makeApiCall(searchString: String) {
        viewModelScope.launch(Dispatchers.IO) {
          val myRetrofit=  RetrofitInstance.getRetroInstance().create(RetrofitService::class.java)
           val response= myRetrofit.getDataFromAPI(searchString)
            recyclerListLiveData.postValue(response)
        }
    }
}