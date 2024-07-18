package com.nathanhaze.aavia.ui

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object HappyList {

    private const val HAPPY_LIST = "happy"
    private const val LAST_WORD = "last word"

    private var sharedPreferences: SharedPreferences? = null

    private var defaultHappyList = "[\"Family\",\"Friends\",\"Work\",\"Other..\"]"

    fun setup(context: Context) {
        sharedPreferences = context.getSharedPreferences("local_shared", MODE_PRIVATE)
    }

    private val gson = Gson()

    fun saveArrayList(list: ArrayList<String>) {
        val json = gson.toJson(list)
        sharedPreferences?.edit()?.putString(HAPPY_LIST, json)?.apply()
    }

    fun getArrayList(): ArrayList<String> {
        val json = sharedPreferences?.getString(HAPPY_LIST, defaultHappyList)
        val type = object : TypeToken<ArrayList<String>>() {}.type
        return gson.fromJson(json, type) ?: ArrayList()
    }

    fun addHappyValue(key: String) {
        val happyList = getArrayList()
        happyList.add(happyList.size - 2, key)
        saveArrayList(happyList)
    }

    fun saveLastHappyWord(word: String) {
        sharedPreferences?.edit()?.putString(LAST_WORD, word)?.apply()
    }

    fun getLastHappyWord(): String? {
        return sharedPreferences?.getString(LAST_WORD, "")
    }


}