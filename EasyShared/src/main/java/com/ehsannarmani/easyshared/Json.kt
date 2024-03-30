package com.ehsannarmani.easyshared

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Json {

    private var gson:Gson? = null
    fun initialize(){
        if (gson == null) gson = Gson()
    }

    fun <T>T.serialize():String{
        initialize()
        return gson!!.toJson(this)
    }
    inline fun <reified T>String.deserialize():T{
        initialize()

        return Gson().fromJson(this,T::class.java)
    }
}