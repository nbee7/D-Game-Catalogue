package com.submission.dicoding.core.data.source.local.room

import androidx.room.TypeConverter
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken

class PlatformConverter {
    @TypeConverter // note this annotation
    fun fromStringList(optionValues: List<String?>?): String? {
        if (optionValues == null) {
            return null
        }
        val gson = Gson()
        val type =
            object : TypeToken<List<String?>?>() {}.type
        return gson.toJson(optionValues, type)
    }

    @TypeConverter // note this annotation
    fun toStringList(optionValuesString: String?): List<String?>? {
        if (optionValuesString == null) {
            return null
        }
        val gson = Gson()
        val type =
            object : TypeToken<List<String?>?>() {}.type
        return gson.fromJson(optionValuesString, type)
    }
}