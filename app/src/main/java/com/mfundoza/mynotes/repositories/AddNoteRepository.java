package com.mfundoza.mynotes.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.mfundoza.mynotes.daos.NoteDao;
import com.mfundoza.mynotes.models.Note;
import com.mfundoza.mynotes.roomdatabase.NoteDatabase;

public class AddNoteRepository {
    private NoteDao noteDao;

    public AddNoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
    }

    public void insert(Note note) {
        new AddNoteRepository.InsertNoteAsyncTask(noteDao).execute(note);
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        private  InsertNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert((notes[0]));
            return null;
        }
    }
}
