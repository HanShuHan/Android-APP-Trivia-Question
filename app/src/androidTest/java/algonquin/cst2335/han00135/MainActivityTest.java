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

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;
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
        ViewInteraction passwordEditText = onView(withId(R.id.password));
        passwordEditText.perform(replaceText("1aD#"), closeSoftKeyboard());

        // Login button
        ViewInteraction loginButton = onView(withId(R.id.loginButton));
        loginButton.perform(click());

        ViewInteraction passwordTextView = onView(withId(R.id.passwordMessage));
        passwordTextView.check(matches(withText("Your password meets the requirements!")));
    }

    /**
     * Tests when the password is missing a uppercase letter.
     */
    @Test
    public void findPasswordMissingUppercaseTest() {
        ViewInteraction passwordEditText = onView(withId(R.id.password));
        passwordEditText.perform(replaceText("1a?"), closeSoftKeyboard());

        // Login button
        ViewInteraction loginButton = onView(withId(R.id.loginButton));
        loginButton.perform(click());

        ViewInteraction passwordTextView = onView(withId(R.id.passwordMessage));
        passwordTextView.check(matches(withText("You shall not pass")));
    }

    /**
     * Tests when the password is missing a lowercase letter.
     */
    @Test
    public void findPasswordMissingLowercaseTest() {
        ViewInteraction passwordEditText = onView(withId(R.id.password));
        passwordEditText.perform(replaceText("1A!"), closeSoftKeyboard());

        // Login button
        ViewInteraction loginButton = onView(withId(R.id.loginButton));
        loginButton.perform(click());

        ViewInteraction passwordTextView = onView(withId(R.id.passwordMessage));
        passwordTextView.check(matches(withText("You shall not pass")));
    }

    /**
     * Tests when the password is missing a number letter.
     */
    @Test
    public void findPasswordMissingNumberTest() {
        ViewInteraction passwordEditText = onView(withId(R.id.password));
        passwordEditText.perform(replaceText("aC@"), closeSoftKeyboard());

        // Login button
        ViewInteraction loginButton = onView(withId(R.id.loginButton));
        loginButton.perform(click());

        ViewInteraction passwordTextView = onView(withId(R.id.passwordMessage));
        passwordTextView.check(matches(withText("You shall not pass")));
    }

    /**
     * Tests when the password is missing a special character.
     */
    @Test
    public void findPasswordMissingSpecialCharacterTest() {
        ViewInteraction passwordEditText = onView(withId(R.id.password));
        passwordEditText.perform(replaceText("0Cd"), closeSoftKeyboard());

        // Login button
        ViewInteraction loginButton = onView(withId(R.id.loginButton));
        loginButton.perform(click());

        ViewInteraction passwordTextView = onView(withId(R.id.passwordMessage));
        passwordTextView.check(matches(withText("You shall not pass")));
    }
}
