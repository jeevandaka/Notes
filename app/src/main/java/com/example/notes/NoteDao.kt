package com.example.notes

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) //suspend used for run in background thread because insert and delete IO operation which are very slow
    suspend fun insert(note: Notes)
    @Delete
    suspend fun delete(note: Notes)
    @Query("Select * from notes_table order by id ASC")
    fun getAllNotes(): LiveData<List<Notes>> // live data is kind of wrapper which continuously observes data

}