package com.ehsannarmani.easyshared

import android.app.Person
import android.content.Context
import com.ehsannarmani.easyshared.Json.deserialize
import com.ehsannarmani.easyshared.Json.serialize
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class DataDelegation<T : Any>(
    context: Context,
    val key: String? = null,
    val defaultValue: T,
    val handleObjectDeserialization:(String)->T,
) :
    ReadWriteProperty<Any, T> {

    private val sharedPreferences by lazy {
        context.getSharedPreferences("easy_shared", Context.MODE_PRIVATE)
    }

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        val keyToRestore = key ?: property.name
        val result = when (defaultValue) {
            is String -> {
                sharedPreferences.getString(keyToRestore, defaultValue) ?: defaultValue
            }
            is Int -> {
                sharedPreferences.getInt(keyToRestore, defaultValue)
            }
            is Boolean -> {
                sharedPreferences.getBoolean(keyToRestore, defaultValue)
            }
            is Float -> {
                sharedPreferences.getFloat(keyToRestore, defaultValue)
            }
            is Long -> {
                sharedPreferences.getLong(keyToRestore, defaultValue)
            }
            else ->{
                handleObjectDeserialization(sharedPreferences.getString(keyToRestore,defaultValue.serialize()) ?: defaultValue.serialize())
            }
        }
        return result as T
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        val editor = sharedPreferences.edit()
        val keyToSave = key ?: property.name
        when (defaultValue) {
            is String -> {
                editor.putString(keyToSave, value as String)
            }
            is Int -> {
                editor.putInt(keyToSave, value as Int)
            }
            is Boolean -> {
                editor.putBoolean(keyToSave, value as Boolean)
            }
            is Float -> {
                editor.putFloat(keyToSave, value as Float)
            }
            is Long -> {
                editor.putLong(keyToSave, value as Long)
            }
            else -> {
                editor.putString(keyToSave,value.serialize())
            }
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
            else-> error("You should pass default parameter when you are using savable delegation on objects")
        } as T
    }
    return DataDelegation(
        context = this,
        key = name,
        defaultValue = dv,
        handleObjectDeserialization = {
            it.deserialize()
        }
    )
}

fun Context.savableInt(name:String? = null,defaultValue:Int = 0):DataDelegation<Int>{
    return DataDelegation(
        context = this,
        key = name,
        defaultValue = defaultValue,
        handleObjectDeserialization = {0}
    )
}
fun Context.savableString(name:String? = null,defaultValue:String = ""):DataDelegation<String>{
    return DataDelegation(
        context = this,
        key = name,
        defaultValue = defaultValue,
        handleObjectDeserialization = {""}
    )
}
fun Context.savableLong(name:String? = null,defaultValue:Long = 0):DataDelegation<Long>{
    return DataDelegation(
        context = this,
        key = name,
        defaultValue = defaultValue,
        handleObjectDeserialization = {0}
    )
}
fun Context.savableBoolean(name:String? = null,defaultValue:Boolean = false):DataDelegation<Boolean>{
    return DataDelegation(
        context = this,
        key = name,
        defaultValue = defaultValue,
        handleObjectDeserialization = {false}
    )
}
fun Context.savableFloat(name:String? = null,defaultValue:Float = 0f):DataDelegation<Float>{
    return DataDelegation(
        context = this,
        key = name,
        defaultValue = defaultValue,
        handleObjectDeserialization = { 0f },
    )
}


