
package com.example.lab4;

import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.InputStream;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

public class RecipeDetailFragment extends Fragment {

    public RecipeDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Step 1: Load the XML layout
        View view = inflater.inflate(
                R.layout.fragment_recipe_detail, container, false
        );

        // Step 2: Find the TextViews
        TextView recipeName = view.findViewById(R.id.recipeName);
        TextView recipeIngredients = view.findViewById(R.id.recipeIngredients);
        TextView recipeMethod = view.findViewById(R.id.recipeMethod);

        // Step 3: Get the selected recipe position
        int position = 0;

        if (getArguments() != null) {
            position = getArguments().getInt("recipePosition", 0);
        }

        try {
            // Step 4: Open recipes.json
            InputStream inputStream = requireContext()
                    .getAssets()
                    .open("recipes.json");

            // Step 5: Read the JSON file
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            inputStream.close();

            // Step 6: Convert file contents into a String
            String jsonString = outputStream.toString(StandardCharsets.UTF_8.name());

            // Step 7: Convert String into JSONObject
            JSONObject jsonObject = new JSONObject(jsonString);

            // Step 8: Get the recipes array
            JSONArray recipesArray = jsonObject.getJSONArray("recipes");

            // Step 9: Get the selected recipe
            JSONObject selectedRecipe = recipesArray.getJSONObject(position);

        } catch (IOException | JSONException e) {
            Log.e("RecipeDetailFragment", "Error reading recipe", e);
        }

        return view;
    }
}
