package com.example.puppyapp

import android.os.Bundle
import androidx.navigation.NavType
import com.example.puppyapp.data.Puppy
import com.google.gson.Gson

class AssetParamType: NavType<Puppy>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Puppy? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Puppy {
        return Gson().fromJson(value, Puppy::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Puppy) {
        bundle.putParcelable(key, value)
    }
}