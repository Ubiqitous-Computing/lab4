
package com.example.lab4;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.InputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class RecipeListFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public RecipeListFragment() {
        // Required empty public constructor
    }

    public static RecipeListFragment newInstance(String param1, String param2) {
        RecipeListFragment fragment = new RecipeListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    // Creates Fragment's user interface from XML layout
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Step 1: Load the XML layout
        View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);

        // Step 2: Find ListView
        ListView recipeListView = view.findViewById(R.id.recipeListView);

        // Step 3: Create recipe names
        String[] recipes = {
                "Pancakes",
                "Omelette",
                "Rice",
                "Ham & Cheese Toastie",
                "Mash Potatoes"
        };

        // Step 4: Create ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_list_item_1,
                recipes
        );

        // Step 5: Display recipes in ListView
        recipeListView.setAdapter(adapter);

        return view;
    }

    // Grab recipeList.xml
    private String loadJSONFromAsset() {
        try {
            InputStream inputStream = requireContext().getAssets().open("recipes.json");

            byte[] buffer = new byte[inputStream.available()];

            inputStream.read(buffer);
            inputStream.close();

            return new String(buffer, StandardCharsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

        }
    }
}
