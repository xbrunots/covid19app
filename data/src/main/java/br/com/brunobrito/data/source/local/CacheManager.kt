package br.com.brunobrito.data.source.local

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import br.com.brunobrito.data.R
import br.com.brunobrito.data.platform.context.GlobalContext
import br.com.brunobrito.data.source.local.sp.SharedPreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


inline fun <reified T : Any> findCacheByModel(): T? {
    return try {
        val fooType: Type = object : TypeToken<T>() {}.type
        val cache: String? = SharedPreferenceManager.get(T::class.qualifiedName.toString())
        return Gson().fromJson(cache, fooType)
    } catch (e: Exception) {
        Log.e(
            GlobalContext.context().getString(R.string.tag_cache_error),
            GlobalContext.context().getString(R.string.cache_error).plus(T::class.qualifiedName)
        )
        return null
    }
}

inline fun <reified T : Any> findCacheByName(name: String): T? {
    return try {
        val fooType: Type = object : TypeToken<T>() {}.type
        val cache: String? = SharedPreferenceManager.get(name)
        return Gson().fromJson(cache, fooType)
    } catch (e: Exception) {
        Log.e(
            GlobalContext.context().getString(R.string.tag_cache_error),
            GlobalContext.context().getString(R.string.cache_error).plus(name)
        )
        null
    }
}

inline fun <reified T : Any> cache(data: Any) {
    try {
        SharedPreferenceManager.set(T::class.qualifiedName.toString(), Gson().toJson(data))
    } catch (e: Exception) {
        Log.e(
            GlobalContext.context().getString(R.string.tag_cache_error),
            GlobalContext.context().getString(R.string.cache_error).plus(T::class.qualifiedName)
        )
    }
}

fun cache(name: String, data: Any) {
    SharedPreferenceManager.set(name, Gson().toJson(data))
}