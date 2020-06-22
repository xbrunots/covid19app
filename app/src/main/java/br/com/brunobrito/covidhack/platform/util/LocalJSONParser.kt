package br.com.brunobrito.covidhack.platform.util

import androidx.appcompat.app.AppCompatActivity
import br.com.brunobrito.covidhack.feature.home.data.service.model.WorldData
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream

class LocalJSONParser {
    companion object {
        fun inputStreamToString(inputStream: InputStream): String {
            try {
                val bytes = ByteArray(inputStream.available())
                inputStream.read(bytes, 0, bytes.size)
                return String(bytes)
            } catch (e: IOException) {
                return ""
            }
        }
    }
}

inline fun <reified T> AppCompatActivity.getJsonFromAssets(jsonFileName: String): T {
    val myJson = LocalJSONParser.inputStreamToString(this.assets.open(jsonFileName))
    return Gson().fromJson(myJson, T::class.java)
}


val gson = GsonHelper().gson

fun AppCompatActivity.getStoriesCountry() : WorldData {
    val json = LocalJSONParser.inputStreamToString(this.assets.open("all.json"))
    return gson.fromJson(json, WorldData::class.java)
}