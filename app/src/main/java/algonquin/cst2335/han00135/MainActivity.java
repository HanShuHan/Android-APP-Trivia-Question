package algonquin.cst2335.han00135;

/*
 * Filename: MainActivity.java
 * Student Name: Shu Han Han
 * Student Number: 041060762
 * Course & Section #: 23S_CST2335_031
 * Assignment: Lab #5
 * Date: June 07, 2023
 * Professor: Eric Torunski
 * Declaration: This is my own original work and is free from Plagiarism.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;

import algonquin.cst2335.han00135.databinding.ActivityMainBinding;

/**
 * The application's main activity.
 *
 * @author Shu Han Han
 * @version 1.0
 * @since 20.0.1
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //
        queue = Volley.newRequestQueue(this);

        binding.getForecastButton.setOnClickListener(v -> {
            final String city = binding.cityName.getText().toString().trim();
            final String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=7e943c97096a9784391a981c4d878b22&units=metric";
            if (!city.isEmpty()) {
                requestWeatherAPI(url);
            }
        });
    }

    private void requestWeatherAPI(@NonNull String url) {
        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    try {
                        final JSONArray weatherArray = response.getJSONArray("weather");
                        final JSONObject weatherArrayFirstObject = weatherArray.getJSONObject(0);
                        final String description = weatherArrayFirstObject.getString("description");
                        final String iconName = weatherArrayFirstObject.getString("icon");
                        final String imageURL = "http://openweathermap.org/img/w/" + iconName + ".png";
                        final JSONObject main = response.getJSONObject("main");
                        final double temp = main.getDouble("temp");
                        final double tempMax = main.getDouble("temp_max");
                        final double tempMin = main.getDouble("temp_min");
                        final double humidity = main.getInt("humidity");
                        final String imageFilePath = getFilesDir().getPath() + "/" + iconName + ".png";
                        final File imageFile = new File(imageFilePath);
                        final ImageRequest imageRequest;
                        final Bitmap bitmap;

                        runOnUiThread(() -> {
                            binding.temp.setText("The current temperature is " + temp);
                            binding.temp.setVisibility(View.VISIBLE);
                            binding.maxTemp.setText("The max temperature is " + tempMax);
                            binding.maxTemp.setVisibility(View.VISIBLE);
                            binding.minTemp.setText("The min temperature is " + tempMin);
                            binding.minTemp.setVisibility(View.VISIBLE);
                            binding.humidity.setText("The humidity is " + humidity);
                            binding.humidity.setVisibility(View.VISIBLE);
                            binding.description.setText(description);
                            binding.description.setVisibility(View.VISIBLE);
                        });

                        if (imageFile.exists()) {
                            bitmap = BitmapFactory.decodeFile(imageFilePath);
                            runOnUiThread(() -> binding.icon.setImageBitmap(bitmap));
                        } else {
                            requestWeatherImageAPI(imageURL);
                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                },
                error -> {
                });
        queue.add(request);
    }

    /**
     * @param imageURL
     */
    private void requestWeatherImageAPI(@NonNull String imageURL) {
        final int startIndex = imageURL.lastIndexOf("/") + 1;
        final String iconName = imageURL.substring(startIndex);
        final ImageRequest imageRequest = new ImageRequest(
                imageURL,
                bitmap -> {
                    runOnUiThread(() -> binding.icon.setImageBitmap(bitmap));
                    try {
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, openFileOutput(iconName, MODE_PRIVATE));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                },
                1024, 1024,
                ImageView.ScaleType.CENTER,
                null,
                error -> {
                });
        queue.add(imageRequest);
    }

}