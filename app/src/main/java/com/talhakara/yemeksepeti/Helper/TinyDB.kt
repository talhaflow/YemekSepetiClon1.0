package com.talhakara.yemeksepeti.Helper

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.preference.PreferenceManager
import android.text.TextUtils
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.talhakara.yemeksepeti.Domain.FoodDomain
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class TinyDB(private val appContext: Context) {
     val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(appContext)
    private var DEFAULT_APP_IMAGE_DATA_DIRECTORY: String = ""
    private var lastImagePath: String = ""

    fun getImage(path: String): Bitmap? {
        var bitmapFromPath: Bitmap? = null
        try {
            bitmapFromPath = BitmapFactory.decodeFile(path)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return bitmapFromPath
    }

    fun putImage(theFolder: String, theImageName: String, theBitmap: Bitmap): String {
        if (theFolder == null || theImageName == null || theBitmap == null)
            return ""

        this.DEFAULT_APP_IMAGE_DATA_DIRECTORY = theFolder
        val mFullPath = setupFullPath(theImageName)

        if (!mFullPath.isEmpty()) {
            lastImagePath = mFullPath
            saveBitmap(mFullPath, theBitmap)
        }
        return mFullPath
    }

    fun putImageWithFullPath(fullPath: String, theBitmap: Bitmap): Boolean {
        return !(fullPath == null || theBitmap == null) && saveBitmap(fullPath, theBitmap)
    }

    private fun setupFullPath(imageName: String): String {
        val mFolder = File(
            Environment.getExternalStorageDirectory(),
            DEFAULT_APP_IMAGE_DATA_DIRECTORY
        )

        if (isExternalStorageReadable() && isExternalStorageWritable() && !mFolder.exists()) {
            if (!mFolder.mkdirs()) {
                Log.e("ERROR", "Failed to setup folder")
                return ""
            }
        }
        return "${mFolder.path}/$imageName"
    }

    fun putString(key: String, value: String) {
        checkForNullKey(key)
        checkForNullValue(value)
        preferences.edit().putString(key, value).apply()
    }


    fun putListString(key: String, stringList: ArrayList<String>) {
        checkForNullKey(key)
        val myStringList = stringList.toTypedArray()
        preferences.edit().putString(key, TextUtils.join("‚‗‚", myStringList)).apply()
    }

    fun putBoolean(key: String, value: Boolean) {
        checkForNullKey(key)
        preferences.edit().putBoolean(key, value).apply()
    }


    fun putObject(key: String, obj: Any) {
        val gson = Gson()
        putString(key, gson.toJson(obj))
    }

    fun putListObject(key: String, objArray: ArrayList<FoodDomain>) {
        checkForNullKey(key)
        val gson = Gson()
        val objStrings = ArrayList<String>()
        for (obj in objArray) {
            objStrings.add(gson.toJson(obj))
        }
        putListString(key, objStrings)
    }

    fun remove(key: String) {
        preferences.edit().remove(key).apply()
    }

    fun deleteImage(path: String): Boolean {
        return File(path).delete()
    }

    fun clear() {
        preferences.edit().clear().apply()
    }

    fun getAll(): Map<String, *> {
        return preferences.all
    }

    fun registerOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {
        preferences.registerOnSharedPreferenceChangeListener(listener)
    }

    fun unregisterOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {
        preferences.unregisterOnSharedPreferenceChangeListener(listener)
    }

    inline fun <reified T> getListObject(key: String, clazz: Class<T>): ArrayList<T> {
        val listString = preferences.getString(key, null)
        val gson = Gson()
        val listFood = ArrayList<T>()

        if (listString != null) {
            val jsonArray = JsonParser.parseString(listString).asJsonArray
            for (jsonElement in jsonArray) {
                val food = gson.fromJson(jsonElement, clazz)
                listFood.add(food)
            }
        }

        return listFood
    }


    companion object {
        fun isExternalStorageWritable(): Boolean {
            return Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()
        }

        fun isExternalStorageReadable(): Boolean {
            val state = Environment.getExternalStorageState()
            return Environment.MEDIA_MOUNTED == state ||
                    Environment.MEDIA_MOUNTED_READ_ONLY == state
        }
    }

    fun checkForNullKey(key: String) {
        if (key == null) {
            throw NullPointerException()
        }
    }

    fun checkForNullValue(value: String) {
        if (value == null) {
            throw NullPointerException()
        }
    }

    private fun saveBitmap(filePath: String, bitmap: Bitmap): Boolean {
        return try {
            val stream = FileOutputStream(filePath)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            stream.flush()
            stream.close()
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }

}


