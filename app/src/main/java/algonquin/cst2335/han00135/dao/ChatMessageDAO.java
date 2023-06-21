package algonquin.cst2335.han00135.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import algonquin.cst2335.han00135.entity.ChatMessage;

/*
 * Filename: ChatMessageDAO.java
 * Student Name: Shu Han Han
 * Student Number: 041060762
 * Course & Section #: 23S_CST2335_031
 * Assignment: Lab #5
 * Date: June 21, 2023
 * Professor: Eric Torunski
 * Declaration: This is my own original work and is free from Plagiarism.
 */
@Dao
public interface ChatMessageDAO {

    @Insert
    long insert(ChatMessage message);

    @Query("SELECT * FROM ChatMessage;")
    List<ChatMessage> getAllChatMessages();

    @Update
    int updateMessage(ChatMessage message);

    @Delete
    int deleteMessage(ChatMessage message);
}
