
package com.example.lab4;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class RecipeData {

    // Step 1: Read recipes.json from the assets folder
    public static String loadJSONFromAsset(Context context) throws IOException {

        try (InputStream inputStream =
                     context.getAssets().open("recipes.json");

             ByteArrayOutputStream outputStream =
                     new ByteArrayOutputStream()) {

            // Step 2: Read the file into a buffer
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            // Step 3: Convert the file contents into a String
            return outputStream.toString(StandardCharsets.UTF_8.name());
        }
    }

    // Step 4: Get the recipes array from JSON
    public static JSONArray getRecipes(Context context)
            throws IOException, JSONException {

        String jsonString = loadJSONFromAsset(context);

        JSONObject jsonObject = new JSONObject(jsonString);

        return jsonObject.getJSONArray("recipes");
    }
}
