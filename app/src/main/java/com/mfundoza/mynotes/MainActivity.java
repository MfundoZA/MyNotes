package com.mfundoza.mynotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.mfundoza.mynotes.adapters.NoteAdapter;
import com.mfundoza.mynotes.models.Note;
import com.mfundoza.mynotes.viewmodels.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.rcyNotes.setLayoutManager(new LinearLayoutManager(this));
        binding.rcyNotes.setHasFixedSize(true);

        NoteAdapter adapter = new NoteAdapter();
        binding.rcyNotes.setAdapter(adapter);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                // Update our RecyclerView
                adapter.setNotes(notes);

                //Snackbar.make(findViewById(R.id.main_constraint_layout), "OnChange", Snackbar.LENGTH_LONG).show();
            }
        });
    }
}