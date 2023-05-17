package algonquin.cst2335.han00135;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import algonquin.cst2335.han00135.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // View Binding
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        // View Objects
        final TextView myTextView = mainBinding.myTextView;
        final Button myButton = mainBinding.myButton;
        final EditText myEditText = mainBinding.myEditText;

        // Sets myButton OnClickListener
        myButton.setOnClickListener(v -> {
            String editStr = myEditText.getText().toString();
            myTextView.setText("Your edit text has: " + editStr);
        });
    }
}