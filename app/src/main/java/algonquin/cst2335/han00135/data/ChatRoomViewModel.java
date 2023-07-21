package algonquin.cst2335.han00135.data;

/*
 * Filename: ChatRoomViewModel.java
 * Student Name: Shu Han Han
 * Student Number: 041060762
 * Course & Section #: 23S_CST2335_031
 * Assignment: Lab #5
 * Date: June 13, 2023
 * Professor: Eric Torunski
 * Declaration: This is my own original work and is free from Plagiarism.
 */

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import algonquin.cst2335.han00135.entity.ChatMessage;

/**
 * Todo:
 *
 * @author Shu Han Han
 * @version 1.0
 * @see
 * @since 20.0.1
 */
public class ChatRoomViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<ChatMessage>> chatMessages;
    private final MutableLiveData<ChatMessage> chatMessage;
    private final MutableLiveData<Integer> selectedRow;

    public ChatRoomViewModel() {
        chatMessages = new MutableLiveData<>();
        chatMessage = new MutableLiveData<>();
        selectedRow = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<ChatMessage>> getChatMessages() {
        return chatMessages;
    }

    public MutableLiveData<ChatMessage> getChatMessage() {
        return chatMessage;
    }

    public MutableLiveData<Integer> getSelectedRow() {
        return selectedRow;
    }
}