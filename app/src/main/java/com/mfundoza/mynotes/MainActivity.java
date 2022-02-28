package com.mfundoza.mynotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.mfundoza.mynotes.adapters.NoteAdapter;
import com.mfundoza.mynotes.databinding.ActivityMainBinding;
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

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Note selectedNote = adapter.getNoteAt(viewHolder.getBindingAdapterPosition());
                mainViewModel.delete(selectedNote);

                // Todo Make Snackbar that will acknowledge deletion and offer to undo
                Snackbar.make(binding.mainConstraintLayout, "Note Deleted!", Snackbar.LENGTH_LONG)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                mainViewModel.insert(selectedNote);
                            }
                        })
                        .show();
            }
        }).attachToRecyclerView(binding.rcyNotes);

        binding.fabAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddNoteActivity.class));
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        // Todo check the number of notes before declaring note saved
        Snackbar.make(binding.mainConstraintLayout, "Note saved!", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itmDeleteAll:
                List<Note> temp = mainViewModel.getAllNotes().getValue();
                Note[] allNotes = temp.toArray(new Note[temp.size()]);
                mainViewModel.deleteAllNotes();
                Snackbar.make(binding.mainConstraintLayout, "All notes deleted!", Snackbar.LENGTH_LONG)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // Todo fix this
                                mainViewModel.undoDeleteAll(allNotes);
                            }
                        })
                        .show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}