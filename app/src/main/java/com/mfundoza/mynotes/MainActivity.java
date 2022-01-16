package com.mfundoza.mynotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import com.mfundoza.mynotes.models.Note;
import com.mfundoza.mynotes.viewmodels.NoteViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                // Update our RecyclerView
                Snackbar.make(findViewById(R.id.main_constraint_layout), "OnChange", Snackbar.LENGTH_LONG).show();
            }
        });
    }
}