package algonquin.cst2335.han00135.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import algonquin.cst2335.han00135.data.MainViewModel;
import algonquin.cst2335.han00135.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;
    private MainViewModel mainModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // View Binding
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        // View Objects
        final TextView myTextView = mainBinding.myTextView;
        final Button myButton = mainBinding.myButton;
        final EditText myEditText = mainBinding.myEditText;
        // Sets ContentView
        setContentView(mainBinding.getRoot());

        // Gets View Model
        mainModel = new ViewModelProvider(this).get(MainViewModel.class);

        // Sets TextView content every time generated
        myTextView.setText("Your edit text has: " + mainModel.editStr.getValue());
        // Sets myButton OnClickListener
        myButton.setOnClickListener(v -> {
            String editText = myEditText.getText().toString();
            mainModel.editStr.postValue(editText);
        });
        // When the data editStr is changed...
        mainModel.editStr.observe(this, s -> myTextView.setText("Your edit text has: " + s));
    }
}