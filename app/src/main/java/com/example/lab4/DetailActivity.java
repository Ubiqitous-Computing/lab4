
package com.example.lab4;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Step 1: Load the XML layout
        setContentView(R.layout.activity_detail);

        // Step 2: Get the selected recipe position
        int position = getIntent().getIntExtra("recipePosition", 0);

        // Step 3: Only add Fragment when Activity is first created
        if (savedInstanceState == null) {

            // Step 4: Create RecipeDetailFragment
            RecipeDetailFragment detailFragment =
                    new RecipeDetailFragment();

            // Step 5: Pass the selected recipe position
            Bundle args = new Bundle();
            args.putInt("recipePosition", position);
            detailFragment.setArguments(args);

            // Step 6: Display RecipeDetailFragment
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.detail_fragment_container, detailFragment)
                    .commit();
        }
    }
}
