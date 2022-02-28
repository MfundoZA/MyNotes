package com.mfundoza.mynotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.mfundoza.mynotes.databinding.ActivityAddNoteBinding;
import com.mfundoza.mynotes.models.Note;
import com.mfundoza.mynotes.viewmodels.AddNoteViewModel;

public class AddNoteActivity extends AppCompatActivity {

    private ActivityAddNoteBinding binding;
    private AddNoteViewModel addNoteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.nmbPriority.setMinValue(1);
        binding.nmbPriority.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Note");

        binding.btnSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNote();
            }
        });

        addNoteViewModel = new ViewModelProvider(this).get(AddNoteViewModel.class);
    }

    private void saveNote() {
        String title = binding.edtTitle.getText().toString();
        String description = binding.edtDescription.getText().toString();
        int priority = binding.nmbPriority.getValue();

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Snackbar.make(binding.getRoot(), "Please insert a title and description", Snackbar.LENGTH_SHORT).show();

            return;
        }

        Note newNote = new Note(title, description, priority);
        addNoteViewModel.insert(newNote);
        finish();
    }
}