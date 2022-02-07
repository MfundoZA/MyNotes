package com.mfundoza.mynotes.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mfundoza.mynotes.models.Note;
import com.mfundoza.mynotes.repositories.MainRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private MainRepository repository;
    private LiveData<List<Note>> allNotes;

    public MainViewModel(@NonNull Application application) {
        super(application);

        repository = new MainRepository(application);
        allNotes = repository.getAllNotes();
    }

    public void insert(Note note) {
        repository.insert(note);
    }

    public void update(Note note) {
        repository.update(note);
    }

    public void delete(Note note) {
        repository.delete(note);
    }

    public void deleteAllNotes () {
        repository.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}
