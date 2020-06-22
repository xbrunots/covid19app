package br.com.brunobrito.data.source.local.sp

import android.content.Context.MODE_PRIVATE
import br.com.brunobrito.data.platform.context.GlobalContext

class SharedPreferenceManager {
    companion object {
        const val SP_CACHE = "SP_CACHE"
        var pref = GlobalContext.context().getSharedPreferences(SP_CACHE, MODE_PRIVATE)
        var editor = pref.edit()

        fun set(key: String, data: String) {
            if (get(key) != null) delete(key)
            editor.putString(key, data)
            editor.commit()
        }

        fun get(key: String): String? {
            return pref.getString(key, null)
        }

        fun delete(key: String) {
            editor.remove(key)
            editor.commit()
        }
    }
}