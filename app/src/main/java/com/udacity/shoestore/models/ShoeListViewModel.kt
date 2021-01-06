package com.udacity.shoestore.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeListViewModel: ViewModel() {
    val shoeList = MutableLiveData<MutableList<Shoe>>()

    init {
        shoeList.value = ArrayList()

        addShoe("Nike", 45.0, "Nike", "Air Jordan")
    }

    fun addShoe( name: String,  size: Double,  company: String,  description: String) {
        var shoe:Shoe= Shoe(name, size, company, description)

        shoeList.value?.add(shoe)
        shoeList.value = shoeList.value
    }

    fun addShoe(shoe: Shoe) {
        shoeList.value?.add(shoe)
        shoeList.value = shoeList.value
    }
}