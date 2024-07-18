package com.nathanhaze.aavia.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nathanhaze.aavia.ui.HappyList

class HomeViewModel : ViewModel() {

    private val _lastHappyWord = MutableLiveData<String>(HappyList.getLastHappyWord())

    val lastHappyWord: LiveData<String>
        get() = _lastHappyWord


    private val _list = MutableLiveData(HappyList.getArrayList())//.apply {

    val happyList: LiveData<ArrayList<String>>
        get() = _list

    fun updateHappyList(happyValue: String) {
        if (happyValue.isBlank()) {
            return
        }
        _lastHappyWord.postValue(happyValue)
        HappyList.saveLastHappyWord(happyValue)
        _list.value?.let {
            it.add(it.size - 1, happyValue)
            _list.postValue(it)
        }
    }

    fun saveList() {
        happyList.value?.let { HappyList.saveArrayList(it) }
    }
}