package com.example.lab4;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {

            getSupportFragmentManager(). // Gets What is responsible for Fragments
                    beginTransaction(). // Starts Fragment Operation
                    replace(R.id.fragment_container, new RecipeListFragment()) // Places fragment inside container,
                    // Also creates an instance of our fragment
                    .commit(); // Submits the transaction
        }

    }
}