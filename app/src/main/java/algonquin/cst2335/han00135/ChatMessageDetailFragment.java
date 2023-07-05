package algonquin.cst2335.han00135;

/*
 * Filename: DetailsFragment.java
 * Student Name: Shu Han Han
 * Student Number: 041060762
 * Course & Section #: 23S_CST2335_031
 * Assignment: Final Project
 * Date: July 04, 2023
 * Professor: Eric Torunski
 * Declaration: This is my own original work and is free from Plagiarism.
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import algonquin.cst2335.han00135.databinding.ChatMessageDetailLayoutBinding;
import algonquin.cst2335.han00135.entity.ChatMessage;

/**
 * Todo:
 *
 * @author Shu Han Han
 * @version 1.0
 * @since 20.0.1
 */
public class ChatMessageDetailFragment extends Fragment {

    private final ChatMessage selectedMessage;

    public ChatMessageDetailFragment(ChatMessage message) {
        selectedMessage = message;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        ChatMessageDetailLayoutBinding binding = ChatMessageDetailLayoutBinding.inflate(inflater, container, false);
        binding.selectedChatMessageText.setText(selectedMessage.getMessage());
        binding.selectedChatMessageTimeText.setText(selectedMessage.getTimeSent());
        binding.selectedChatMessageIsSentText.setText(String.valueOf(selectedMessage.isSentButton()));
        binding.selectedChatMessageDatabaseIdText.setText("Id: " + selectedMessage.getId());
        return binding.getRoot();
    }
}
