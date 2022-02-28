package com.mfundoza.mynotes.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.mfundoza.mynotes.models.Note;
import com.mfundoza.mynotes.repositories.AddNoteRepository;
import com.mfundoza.mynotes.repositories.MainRepository;

public class AddNoteViewModel extends AndroidViewModel {

    private AddNoteRepository repository;

    public AddNoteViewModel(@NonNull Application application) {
        super(application);

        repository = new AddNoteRepository(application);

    }

    public void insert(Note note) {
        repository.insert(note);
    }
}
