package com.mm.todo.Database

import androidx.lifecycle.LiveData
import com.mm.todo.Models.Note

class NotesRepository(private val noteDao: NoteDao) {
    val allNotes : LiveData<List<Note>> = noteDao.getAllNotes()
    suspend fun insert(note: Note){
        noteDao.insert(note)

    }
    suspend fun delete(note: Note){
        noteDao.delete(note)
    }
    suspend fun update(note: Note){
        noteDao.update(note.id,note.title,note.note)
    }
}