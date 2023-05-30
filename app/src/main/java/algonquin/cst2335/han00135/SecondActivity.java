package algonquin.cst2335.han00135;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Previous Page.
        Intent previousPage = getIntent();
        String emailAddress = previousPage.getStringExtra("emailAddress");
        //
        TextView welcomeTextView = findViewById(R.id.welcomeMsg);
        Button callBtn = findViewById(R.id.callBtn);
        EditText phoneNum = findViewById(R.id.phoneNum);

        welcomeTextView.setText("Welcome Back " + emailAddress);
        callBtn.setOnClickListener(v -> {
            Intent call = new Intent(Intent.ACTION_DIAL);
            call.setData(Uri.parse("tel:" + phoneNum.getText().toString()));
            startActivity(call);
        });
    }
}