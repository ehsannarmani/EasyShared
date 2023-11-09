package com.ehsannarmani.easyshared

import android.content.Context
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class DataDelegation<T : Any>(context: Context, val key: String? = null, val defaultValue: T) :
    ReadWriteProperty<Any, T> {

    private val sharedPreferences by lazy {
        context.getSharedPreferences("easy_shared", Context.MODE_PRIVATE)
    }

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        val result = when (defaultValue) {
            is String -> {
                sharedPreferences.getString(key ?: property.name, defaultValue) ?: defaultValue
            }
            is Int -> {
                sharedPreferences.getInt(key ?: property.name, defaultValue)
            }
            is Boolean -> {
                sharedPreferences.getBoolean(key ?: property.name, defaultValue)
            }
            is Float -> {
                sharedPreferences.getFloat(key ?: property.name, defaultValue)
            }
            is Long -> {
                sharedPreferences.getLong(key ?: property.name, defaultValue)
            }
            else -> error("used type not supported")
        }
        return result as T
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        when (defaultValue) {
            is String -> {
                sharedPreferences.edit().putString(key ?: property.name, value as String)
            }
            is Int -> {
                sharedPreferences.edit().putInt(key ?: property.name, value as Int)
            }
            is Boolean -> {
                sharedPreferences.edit().putBoolean(key ?: property.name, value as Boolean)
            }
            is Float -> {
                sharedPreferences.edit().putFloat(key ?: property.name, value as Float)
            }
            is Long -> {
                sharedPreferences.edit().putLong(key ?: property.name, value as Long)
            }
            else -> error("used type not supported")
        }.apply()
    }
}
inline fun <reified T:Any>Context.savable(name: String? = null, defaultValue:T? = null): DataDelegation<T> {
    var dv = defaultValue
    if (dv == null){
        dv = when(T::class){
            String::class-> ""
            Int::class -> 0
            Boolean::class -> false
            Float::class -> 0f
            Long::class -> 0L
            else-> error("used type not supported")
        } as T
    }
    return DataDelegation(context = this, key = name,defaultValue = dv)
}

fun Context.savableInt(name:String? = null,defaultValue:Int = 0):DataDelegation<Int>{
    return DataDelegation(context = this, key = name,defaultValue = defaultValue)
}
fun Context.savableString(name:String? = null,defaultValue:String = ""):DataDelegation<String>{
    return DataDelegation(context = this, key = name,defaultValue = defaultValue)
}
fun Context.savableLong(name:String? = null,defaultValue:Long = 0):DataDelegation<Long>{
    return DataDelegation(context = this, key = name,defaultValue = defaultValue)
}
fun Context.savableBoolean(name:String? = null,defaultValue:Boolean = false):DataDelegation<Boolean>{
    return DataDelegation(context = this, key = name,defaultValue = defaultValue)
}
fun Context.savableFloat(name:String? = null,defaultValue:Float = 0f):DataDelegation<Float>{
    return DataDelegation(context = this, key = name,defaultValue = defaultValue)
}


