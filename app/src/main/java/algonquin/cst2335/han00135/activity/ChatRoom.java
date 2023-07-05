package algonquin.cst2335.han00135.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executors;

import algonquin.cst2335.han00135.ChatMessageDetailFragment;
import algonquin.cst2335.han00135.R;
import algonquin.cst2335.han00135.dao.ChatMessageDAO;
import algonquin.cst2335.han00135.data.ChatRoomViewModel;
import algonquin.cst2335.han00135.database.ChatMessageDatabase;
import algonquin.cst2335.han00135.databinding.ActivityChatRoomBinding;
import algonquin.cst2335.han00135.databinding.ReceiveMessageBinding;
import algonquin.cst2335.han00135.databinding.SentMessageBinding;
import algonquin.cst2335.han00135.entity.ChatMessage;

public class ChatRoom extends AppCompatActivity {

    private ActivityChatRoomBinding binding;
    private ChatRoomViewModel chatRoomModel;
    private ArrayList<ChatMessage> chatMessages;
    private RecyclerView.Adapter<MyRowHolder> myAdapter;
    private ChatMessageDAO chatMessageDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // ChatMessage DAO
        chatMessageDAO = Room.databaseBuilder(getApplicationContext(), ChatMessageDatabase.class, "database-name").build()
                .chatMessageDAO();

        // ChatRoom View Model
        chatRoomModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);
        chatMessages = chatRoomModel.getChatMessages().getValue();
        if (chatMessages == null) {
            chatMessages = new ArrayList<>();
            chatRoomModel.getChatMessages().postValue(chatMessages);
            Executors.newSingleThreadExecutor().execute(() -> {
                chatMessages.addAll(chatMessageDAO.getAllChatMessages());
//                runOnUiThread(() -> {
//                });
            });
        }
        // Sets the RecycleView Adapter
        setAdapter();

        //
        chatRoomModel.getChatMessage().observe(this, selectedChatMessage -> {
            ChatMessageDetailFragment detailFragment = new ChatMessageDetailFragment(selectedChatMessage);
            getSupportFragmentManager().beginTransaction().replace(R.id.selected_chat_message_fragment, detailFragment).addToBackStack(null).commit();
        });

        // Send Button
        setSentButton();
        // Receive Button
        setReceiveButton();
    }

    private void setReceiveButton() {
        binding.receiveButton.setOnClickListener(v -> {
            ChatMessage chatMessage = new ChatMessage();
            String textInput = binding.textInput.getText().toString();
            String time = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a").format(new Date());
            chatMessage.setMessage(textInput);
            chatMessage.setTimeSent(time);
            chatMessage.setSentButton(false);

            chatMessages.add(chatMessage);
            Executors.newSingleThreadExecutor().execute(() -> {
                chatMessage.setId(chatMessageDAO.insert(chatMessage));
            });
//            chatRoomModel.getChatMessages().postValue(chatMessages);
            myAdapter.notifyItemInserted(chatMessages.size() - 1);
//            myAdapter.notifyDataSetChanged();

            binding.textInput.setText("");
        });
    }

    private void setSentButton() {
        binding.sendButton.setOnClickListener(v -> {
            ChatMessage chatMessage = new ChatMessage();
            String textInput = binding.textInput.getText().toString();
            String time = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a").format(new Date());
            chatMessage.setMessage(textInput);
            chatMessage.setTimeSent(time);
            chatMessage.setSentButton(true);

            chatMessages.add(chatMessage);
            Executors.newSingleThreadExecutor().execute(() -> {
                chatMessage.setId(chatMessageDAO.insert(chatMessage));
            });
//            chatRoomModel.getChatMessages().postValue(chatMessages);
            myAdapter.notifyItemInserted(chatMessages.size() - 1);
//            myAdapter.notifyDataSetChanged();

            binding.textInput.setText("");
        });
    }

    private void setAdapter() {
        binding.recycleView.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                if (viewType == 0) {
                    SentMessageBinding messageBinding = SentMessageBinding.inflate(getLayoutInflater(), parent, false);
                    return new MyRowHolder(messageBinding.getRoot());
                } else {
                    ReceiveMessageBinding messageBinding = ReceiveMessageBinding.inflate(getLayoutInflater(), parent, false);
                    return new MyRowHolder(messageBinding.getRoot());
                }
            }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                ChatMessage chatMessage = chatMessages.get(position);

                holder.message.setText(chatMessage.getMessage());
                holder.time.setText(chatMessage.getTimeSent());
            }

            @Override
            public int getItemCount() {
                return chatMessages.size();
            }

            @Override
            public int getItemViewType(int position) {
                boolean isSentButton = chatMessages.get(position).isSentButton();
                return isSentButton ? 0 : 1;
            }
        });
        // Sets LayoutManager
        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));
    }

    public class MyRowHolder extends RecyclerView.ViewHolder {

        private final TextView message;
        private final TextView time;

        public MyRowHolder(@NonNull View itemView) {
            super(itemView);

            message = itemView.findViewById(R.id.message);
            time = itemView.findViewById(R.id.time);

            // Sets delete dialog
            itemView.setOnClickListener(v -> {
                ChatMessage selectedMessage = chatMessages.get(getAbsoluteAdapterPosition());
                chatRoomModel.getChatMessage().postValue(selectedMessage);

//                int position = getAbsoluteAdapterPosition();
//                ChatMessage chatMessage = chatMessages.get(position);
//
//                new AlertDialog.Builder(ChatRoom.this)
//                        .setTitle("Warning: ")
//                        .setMessage("Do you want to delete the message: " + chatMessage.getMessage())
//                        .setNegativeButton("No", (dialog, which) -> {
//                        })
//                        .setPositiveButton("Yes", (dialog, which) -> {
//                            // Deletes the chatMessage in the Database
//                            Executors.newSingleThreadExecutor().execute(() -> {
//                                chatMessageDAO.deleteMessage(chatMessage);
//                            });
//                            chatMessages.remove(position);
//                            myAdapter.notifyItemRemoved(position);
//
//                            Snackbar.make(message, "You deleted message #" + (position + 1), Snackbar.LENGTH_LONG)
//                                    .setAction("UNDO", v1 -> {
//                                        // Re-inserts the chatMessage into the Database
//                                        Executors.newSingleThreadExecutor().execute(() -> {
//                                            chatMessageDAO.insert(chatMessage);
//                                        });
//                                        chatMessages.add(position, chatMessage);
//                                        myAdapter.notifyItemInserted(position);
//                                    })
//                                    .show();
//                        })
//                        .create().show();
            });
        }
    }

}