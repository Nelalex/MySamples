package com.nelalexxx.room

import androidx.room.Entity
import androidx.room.PrimaryKey

 // Mapping for Room(sqlite this data class as a database(entity)
 @Entity
data class Contact (
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    // we put default parameters always in the bottom
    @PrimaryKey(autoGenerate = true) // before id + we can autogenerate unique values
    val id: Int = 0 // nullable or 0 cuz we can delete contact to dont have an errors

){
}