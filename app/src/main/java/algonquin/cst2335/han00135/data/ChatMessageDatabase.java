package algonquin.cst2335.han00135.data;

/*
 * Filename: ChatMessageDatabase.java
 * Student Name: Shu Han Han
 * Student Number: 041060762
 * Course & Section #: 23S_CST2335_031
 * Assignment: Lab #5
 * Date: June 21, 2023
 * Professor: Eric Torunski
 * Declaration: This is my own original work and is free from Plagiarism.
 */

import androidx.room.Database;
import androidx.room.RoomDatabase;

import algonquin.cst2335.han00135.ChatMessage;
import algonquin.cst2335.han00135.ChatMessageDAO;

/**
 * Todo:
 *
 * @author Shu Han Han
 * @version 1.0
 * @see
 * @since 20.0.1
 */
@Database(entities = ChatMessage.class, version = 1)
public abstract class ChatMessageDatabase extends RoomDatabase {
    public abstract ChatMessageDAO chatMessageDAO();
}
