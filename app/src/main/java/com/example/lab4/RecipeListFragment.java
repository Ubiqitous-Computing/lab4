
package com.example.lab4;

import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class RecipeListFragment extends Fragment {

    public RecipeListFragment() {
        // Required empty public constructor
    }

    // Creates Fragment's user interface from XML layout
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Step 1: Load the XML layout
        View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);

        // Step 2: Find ListView
        ListView recipeListView = view.findViewById(R.id.recipeListView);

        // Step 3: Create a list to store recipe names from JSON
        ArrayList<String> recipes = new ArrayList<>();

        try {
            // Step a: Get the recipes array using RecipeData
            JSONArray recipesArray = RecipeData.getRecipes(requireContext());

            // Step b: Loop through each recipe
            for (int i = 0; i < recipesArray.length(); i++) {

                JSONObject recipe = recipesArray.getJSONObject(i);

                // Step c: Extract the recipe name
                String name = recipe.getString("name");

                // Step d: Add the name to our list
                recipes.add(name);
            }

        } catch (JSONException | IOException e) {
            Log.e("RecipeListFragment", "Error loading recipes", e);

            Toast.makeText(requireContext(),
                    "Could not load recipes",
                    Toast.LENGTH_SHORT).show();
        }

        // Step 4: Create ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_list_item_1,
                recipes
        );

        // Step 5: Display recipes in ListView
        recipeListView.setAdapter(adapter);

        // Step 6: Detect which recipe was clicked
        recipeListView.setOnItemClickListener((parent, view1, position, id) -> {

            // Create the detail Fragment
            RecipeDetailFragment detailFragment = new RecipeDetailFragment();

            // Store the selected recipe position
            Bundle args = new Bundle();
            args.putInt("recipePosition", position);

            // Pass the position to the Fragment
            detailFragment.setArguments(args);

            // Display the detail Fragment
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, detailFragment)
                    .addToBackStack(null)
                    .commit();
        });

        return view;
    }
}
