
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

import java.io.InputStream;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
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
            // Step a: Read the JSON file
            String jsonString = loadJSONFromAsset();

            if (jsonString == null) {
                Log.e("RecipeListFragment", "JSON file could not be loaded");
                return view;
            }


            // Step b: Convert String into JSONObject
            JSONObject jsonObject = new JSONObject(jsonString);

            // Step c: Get the recipes array
            JSONArray recipesArray = jsonObject.getJSONArray("recipes");

            // Step d: Loop through each recipe
            for (int i = 0; i < recipesArray.length(); i++) {

                JSONObject recipe = recipesArray.getJSONObject(i);

                // Step e: Extract the recipe name
                String name = recipe.getString("name");

                // Step f: Add the name to our list
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

    // Read recipes.json from the assets folder
    private String loadJSONFromAsset() throws IOException {

        try (InputStream inputStream =
                     requireContext().getAssets().open("recipes.json");

             ByteArrayOutputStream outputStream =
                     new ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            return outputStream.toString(StandardCharsets.UTF_8.name());
        }
    }
}
