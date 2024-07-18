package com.nathanhaze.aavia.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nathanhaze.aavia.ui.HappyList

class HomeViewModel : ViewModel() {


    private val _lastHappyWord= MutableLiveData<String>("")

    val lastHappyWord: LiveData<String>
        get() = _lastHappyWord


    private val _list = MutableLiveData<ArrayList<String>>(HappyList.getArrayList())//.apply {
      //  value = HappyList.getArrayList()
  //  }
    val happyList: LiveData<ArrayList<String>>
          get() = _list

    fun updateHappyList(happyValue: String) {
        HappyList.addHappyValue(happyValue)
        _lastHappyWord.postValue(happyValue)
        val list = HappyList.getArrayList()
        _list.postValue(list)
    }
}