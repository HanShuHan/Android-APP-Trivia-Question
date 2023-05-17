package algonquin.cst2335.han00135.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

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
        // Sets ContentView
        setContentView(mainBinding.getRoot());

        // View Objects
        final TextView myTextView = mainBinding.myTextView;
        final Button myButton = mainBinding.myButton;
        final EditText myEditText = mainBinding.myEditText;
        final CheckBox myCheckBox = mainBinding.myCheckBox;
        final Switch mySwitch = mainBinding.mySwitch;
        final RadioButton myRadioBtn = mainBinding.myRadioBtn;
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

        // Lab 2-5: Compound Buttons
        myCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> mainModel.isChecked.postValue(isChecked));
        mySwitch.setOnCheckedChangeListener((buttonView, isChecked) -> mainModel.isChecked.postValue(isChecked));
        myRadioBtn.setOnCheckedChangeListener((buttonView, isChecked) -> mainModel.isChecked.postValue(isChecked));
        // ViewModel isChecked observer
        mainModel.isChecked.observe(this, isChecked -> {
            myCheckBox.setChecked(isChecked);
            mySwitch.setChecked(isChecked);
            myRadioBtn.setChecked(isChecked);
            Toast.makeText(getApplicationContext(), "The value is now: " + isChecked, Toast.LENGTH_SHORT).show();
        });
    }
}