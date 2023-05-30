package algonquin.cst2335.han00135;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    public static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.w(TAG, "In onCreate() - Loading Widgets");

        // Adds login button function to the second Activity.
        Button loginBtn = findViewById(R.id.loginBtn);
        EditText emailEditText = findViewById(R.id.emailInput);
        loginBtn.setOnClickListener(v -> {
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