package algonquin.cst2335.han00135;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.w(TAG, "In onCreate() - Loading Widgets"); // Log

        // Widgets
        Button loginBtn = findViewById(R.id.loginBtn); // Login Button
        EditText emailEditText = findViewById(R.id.emailInput); // Email Address

        // Sets history email address
        SharedPreferences prefs = getSharedPreferences("myData", MODE_PRIVATE);
        emailEditText.setText(prefs.getString("emailAddress", ""));

        // Sets login button's on-click listener
        loginBtn.setOnClickListener(v -> {
            // Next Page
            Intent nextPage = new Intent(this, SecondActivity.class);
            nextPage.putExtra("emailAddress", emailEditText.getText().toString());
            startActivity(nextPage);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.w(TAG, "The application is now visible on screen.");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.w(TAG, "The application is now responding to user input.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w(TAG, "The application no longer responds to user input.");

        // Saves the email address into Shared Preferences
        SharedPreferences.Editor editor = getSharedPreferences("myData", MODE_PRIVATE).edit();
        EditText emailEditText = findViewById(R.id.emailInput); // Email Address
        editor.putString("emailAddress", emailEditText.getText().toString());
        editor.commit();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w(TAG, "The application is no longer visible.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w(TAG, "Any memory used by the application is freed.");
    }
}