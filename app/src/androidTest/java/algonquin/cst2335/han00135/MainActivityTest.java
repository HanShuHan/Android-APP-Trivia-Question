package algonquin.cst2335.han00135;

/*
 * Filename: MainActivityTest.java
 * Student Name: Shu Han Han
 * Student Number: 041060762
 * Course & Section #: 23S_CST2335_031
 * Assignment: Lab #5
 * Date: June 07, 2023
 * Professor: Eric Torunski
 * Declaration: This is my own original work and is free from Plagiarism.
 */

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * The application's main activity test class.
 *
 * @author Shu Han Han
 * @version 1.0
 * @since 20.0.1
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    /**
     * The activity scenario rule.
     */
    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    /**
     * Tests when the password meets all the requirements.
     */
    @Test
    public void passwordMeetsRequirementTest() {
//        ViewInteraction passwordEditText = onView(withId(R.id.password));
//        passwordEditText.perform(replaceText("1aD#"), closeSoftKeyboard());

        // Login button
//        ViewInteraction loginButton = onView(withId(R.id.get_forecast_button));
//        loginButton.perform(click());

//        ViewInteraction passwordTextView = onView(withId(R.id.passwordMessage));
//        passwordTextView.check(matches(withText("Your password meets the requirements!")));
    }
}
