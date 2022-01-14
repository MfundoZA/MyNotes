package com.mfundoza.mynotes.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mfundoza.mynotes.models.Note;

import java.util.List;

@Dao
public interface NoteDao {
    // Create note
    @Insert
    public void insert(Note note);

    // Read all notes
    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    public LiveData<List<Note>> getAllNotes();

    // Update note
    @Update
    public void update(Note note);

    // Delete note
    @Delete
    public void delete(Note note);

    // Delete multiple Notes
    @Query("DELETE FROM note_table")
    public void deleteAllNotes();
}
