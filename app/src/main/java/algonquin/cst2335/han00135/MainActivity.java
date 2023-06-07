package algonquin.cst2335.han00135;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Shu Han Han
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    /**
     *
     */
    protected TextView myTextView;

    /**
     *
     */
    protected Button button1;

    /**
     *
     */
    protected EditText theEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTextView = findViewById(R.id.myText);
        button1 = findViewById(R.id.button1);
        theEditText = findViewById(R.id.editText);

        button1.setOnClickListener(v -> {
            String input = theEditText.getText().toString();
            if (checkInput(input, '=')) {
                myTextView.setText("Equal sign found");
            } else {
                myTextView.setText("No equal sign found");
            }
        });
    }

    /**
     * @param input
     * @param toLookFor
     */
    boolean checkInput(@NonNull String input, @NonNull char toLookFor) {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == toLookFor) {
                return true;
            }
        }
        return false;
    }
}