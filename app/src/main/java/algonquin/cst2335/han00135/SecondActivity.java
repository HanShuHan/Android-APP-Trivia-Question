package algonquin.cst2335.han00135;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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
        ImageView cameraImg = findViewById(R.id.cameraImg);
        Button chgPicBtn = findViewById(R.id.chgPicBtn);

        welcomeTextView.setText("Welcome Back " + emailAddress);
        callBtn.setOnClickListener(v -> {
            Intent call = new Intent(Intent.ACTION_DIAL);
            call.setData(Uri.parse("tel:" + phoneNum.getText().toString()));
            startActivity(call);
        });

        cameraImg.setOnClickListener(v -> {
            Intent takePhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivity(takePhoto);
        });

        // Change picture button onclick: ActivityResultLauncher
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        chgPicBtn.setOnClickListener(v -> {
            ActivityResultLauncher<Intent> cameraResult = registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            Bitmap bitmap = data.getParcelableExtra("data");
                            cameraImg.setImageBitmap(bitmap);
                        }
                    });
            cameraResult.launch(cameraIntent);
        });
    }
}