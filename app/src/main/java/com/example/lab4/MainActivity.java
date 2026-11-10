package com.example.lab4;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load xml file
        setContentView(R.layout.activity_main);

        // Checks if device is in landscape mode
        boolean isLandscape = findViewById(R.id.detail_container) != null;

        // Only add fragments when activity is first created
        if(savedInstanceState == null) {

            // Display RecipeListFragment
            getSupportFragmentManager(). // Gets What is responsible for Fragments
                    beginTransaction(). // Starts Fragment Operation
                    replace(R.id.fragment_container, new RecipeListFragment()) // Places fragment inside container,
                    // Also creates an instance of our fragment
                    .commit(); // Submits the transaction

            if(isLandscape) {
                RecipeDetailFragment detailFragment = new RecipeDetailFragment();

                //Display first recipe by defailt
                Bundle args = new Bundle();
                args.putInt("recipePosition", 0);
                detailFragment.setArguments(args);

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.detail_container, detailFragment)
                        .commit();
            }
        }
    }
}