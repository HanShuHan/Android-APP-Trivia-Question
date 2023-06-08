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

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * The application's main activity.
 *
 * @author Shu Han Han
 * @version 1.0
 * @since 20.0.1
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Tells you to insert the password.
     */
    private TextView passwordMessage;

    /**
     * The login button.
     */
    private Button loginButton;

    /**
     * The user input password.
     */
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Widgets
        passwordMessage = findViewById(R.id.passwordMessage);
        loginButton = findViewById(R.id.loginButton);
        password = findViewById(R.id.password);

        // Sets the login button's on-click password validation
        loginButton.setOnClickListener(v -> {
            String pwd = password.getText().toString(); // Password

            List<String> errorMessages = checkPasswordComplexity(pwd);
            // Checks if the password meets the requirements
            if (errorMessages.isEmpty()) {
                passwordMessage.setText("Your password meets the requirements!");
            } else {
                passwordMessage.setText("You shall not pass");
                // Builds the toast message
                StringBuilder toastMessage = new StringBuilder("The password lacks:\n\n");
                for (int i = 0; i < errorMessages.size(); i++) {
                    toastMessage.append(i + 1).append(". ")
                            .append(errorMessages.get(i))
                            .append("\n");
                }
                Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * Returns the invalid password messages of not meeting the requirements.
     *
     * @param password the user input password
     * @return the password invalid messages of not meeting the requirements in a list
     */
    private List<String> checkPasswordComplexity(String password) {
        final List<String> errorMessages = new ArrayList<>(); // Erro
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;
        int correctCount = 0;

        // Checks the password by iterating the letters
        for (int i = 0; i < password.length() && correctCount < 4; i++) {
            final char c = password.charAt(i); // The current being checked letter
            final String specialChars = "#$%^&*!@?";

            // Checks if the character is a uppercase until finds one
            if (!hasUppercase) {
                if (Character.isUpperCase(c)) {
                    hasUppercase = true;
                    correctCount++;
                }
            }
            // Checks if the character is a lowercase until finds one
            if (!hasLowercase) {
                if (Character.isLowerCase(c)) {
                    hasLowercase = true;
                    correctCount++;
                }
            }
            // Checks if the character is a digit until finds one
            if (!hasDigit) {
                if (Character.isDigit(c)) {
                    hasDigit = true;
                    correctCount++;
                }
            }
            // Checks if the character is a special character until finds one
            if (!hasSpecialChar) {
                String c1 = Character.toString(c);
                if (specialChars.contains(c1)) {
                    hasSpecialChar = true;
                    correctCount++;
                }
            }
        }
        // Adds the invalid password messages of not meeting the requirements into a list
        if (!hasUppercase) {
            errorMessages.add("An uppercase letter.");
        }
        if (!hasLowercase) {
            errorMessages.add("A lowercase letter.");
        }
        if (!hasDigit) {
            errorMessages.add("A digital number.");
        }
        if (!hasSpecialChar) {
            errorMessages.add("A special character.");
        }
        return errorMessages;
    }

}