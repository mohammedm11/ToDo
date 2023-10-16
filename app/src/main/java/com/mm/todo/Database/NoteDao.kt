package com.mm.todo.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mm.todo.Models.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)
    @Delete
    suspend fun delete(note: Note)
    @Query("Select * from note_table order by id ASC")
    fun getAllNotes():LiveData<List<Note>>
    @Query("UPDATE note_table set title = :title,note = :note WHERE id = :id")
    suspend fun update(id : Int?,title : String?,note :String?)
}