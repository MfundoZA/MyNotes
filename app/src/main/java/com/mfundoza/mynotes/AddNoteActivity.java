package com.mfundoza.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class AddNoteActivity extends AppCompatActivity {
    private AddNoteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = AddNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}