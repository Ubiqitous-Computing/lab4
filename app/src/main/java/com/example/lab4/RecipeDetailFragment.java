
package com.example.lab4;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

        // Step 3: Display temporary data
        recipeName.setText("Pancakes");
        recipeIngredients.setText("Ingredients:\nFlour\nMilk\nEggs");
        recipeMethod.setText("Method:\nMix ingredients and cook in a pan.");

        return view;
    }
}
