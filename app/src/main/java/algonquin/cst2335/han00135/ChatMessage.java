package algonquin.cst2335.han00135;

/*
 * Filename: ChatMessage.java
 * Student Name: Shu Han Han
 * Student Number: 041060762
 * Course & Section #: 23S_CST2335_031
 * Assignment: Lab #5
 * Date: June 20, 2023
 * Professor: Eric Torunski
 * Declaration: This is my own original work and is free from Plagiarism.
 */

/**
 * Todo:
 *
 * @author Shu Han Han
 * @version 1.0
 * @see
 * @since 20.0.1
 */
public class ChatMessage {
    private String message;
    private String timeSent;
    private boolean isSentButton;

    public ChatMessage() {
    }

    public ChatMessage(String message, String timeSent, boolean isSentButton) {
        this.message = message;
        this.timeSent = timeSent;
        this.isSentButton = isSentButton;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(String timeSent) {
        this.timeSent = timeSent;
    }

    public boolean isSentButton() {
        return isSentButton;
    }

    public void setSentButton(boolean sentButton) {
        isSentButton = sentButton;
    }
}
