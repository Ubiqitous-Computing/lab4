
package com.example.lab4;

import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.IOException;

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
            // Step 4: Get the recipes array using RecipeData
            JSONArray recipesArray = RecipeData.getRecipes(requireContext());

            // Step 5: Get the selected recipe
            JSONObject selectedRecipe = recipesArray.getJSONObject(position);

            // Step 6: Extract the recipe name
            String name = selectedRecipe.getString("name");

            // Step 7: Extract the ingredients
            JSONArray ingredientsArray = selectedRecipe.getJSONArray("ingredients");

            StringBuilder ingredients = new StringBuilder("Ingredients:\n");

            for (int i = 0; i < ingredientsArray.length(); i++) {
                ingredients.append("• ")
                        .append(ingredientsArray.getString(i))
                        .append("\n");
            }

            // Step 8: Extract the cooking method
            String method = selectedRecipe.getString("method");

            // Step 9: Display the recipe information
            recipeName.setText(name);
            recipeIngredients.setText(ingredients.toString());
            recipeMethod.setText("Method:\n" + method);

        } catch (IOException | JSONException e) {
            Log.e("RecipeDetailFragment", "Error loading recipe details", e);

            Toast.makeText(requireContext(),
                    "Could not load recipe details",
                    Toast.LENGTH_SHORT).show();
        }

        return view;
    }
}
