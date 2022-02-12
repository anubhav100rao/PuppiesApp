package com.example.puppyapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Puppy(
    val id: Int = 100,
    val title: String = "Default Puppy",
    val sex: String = "Male",
    val age: Int = 4,
    val description: String = "This puppy is very cute",
    val puppyImageId: Int = 0
) : Parcelable


