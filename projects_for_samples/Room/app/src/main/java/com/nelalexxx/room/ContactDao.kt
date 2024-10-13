package com.nelalexxx.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

//its like viewModel
@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) // its insert + update mixed in one / We can add parameter for annotation
    // to deal with conflicts when we have 2 objects with the same id
    suspend fun upsertContact(contact: Contact)


//    @Delete
//    suspend fun deleteContact(contact: Contact)

    //Query - zapros

    @Query("SELECT * FROM contact ORDER BY firstName ASC") // simple sql query
    fun getContactsOrderedByFirstName(): Flow<List<Contact>>
// Flow its an objects that allows async update for coroutines with any change with his own methods


    @Query("SELECT * FROM contact ORDER BY lastName ASC")
    fun getContactsOrderedByLastName(): Flow<List<Contact>>

    @Query("SELECT * FROM contact ORDER BY phoneNumber ASC")
    fun getContactsOrderedByPhoneNumber(): Flow<List<Contact>>


}