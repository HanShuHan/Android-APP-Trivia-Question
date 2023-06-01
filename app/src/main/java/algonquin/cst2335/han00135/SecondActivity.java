package algonquin.cst2335.han00135;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // From previous page
        Intent previousPage = getIntent();
        String emailAddress = previousPage.getStringExtra("emailAddress");
        // Shared Preferences
        SharedPreferences prefs = getSharedPreferences("myData", MODE_PRIVATE);
        // Widgets
        TextView welcomeTextView = findViewById(R.id.welcomeText); // Welcome message
        ImageView cameraImg = findViewById(R.id.cameraImage); // A camera-like image
        Button chgPicBtn = findViewById(R.id.changePicBtn); // The CHANGE PICTURE button
        EditText phoneNum = findViewById(R.id.phoneNumber); // Phone number

        // Welcome Message
        welcomeTextView.setText("Welcome Back " + emailAddress);
        // Sets history phone number
        phoneNum.setText(prefs.getString("phoneNum", ""));
        // Loads Image File
        File file = new File(getFilesDir(), "image.png");
        if (file.exists()) {
            Bitmap imgBitmap = BitmapFactory.decodeFile(getFilesDir().getAbsolutePath() + "/image.png");
            cameraImg.setImageBitmap(imgBitmap);
        }

        // Adds the call button the functionality of on-click dialing phone call
        Button callBtn = findViewById(R.id.callBtn); // CALL BUTTON
        callBtn.setOnClickListener(v -> {
            Intent call = new Intent(Intent.ACTION_DIAL);
            call.setData(Uri.parse("tel:" + phoneNum.getText().toString()));
            startActivity(call);
        });
        // Register Camera Intent Activity Launcher
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        ActivityResultLauncher<Intent> cameraLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent resultData = result.getData();
                        Bitmap bitmap = resultData.getParcelableExtra("data");
                        cameraImg.setImageBitmap(bitmap);
                    }
                });
        // Sets the change picture button the functionality of on-click camera activity launch
        chgPicBtn.setOnClickListener(v -> {
            cameraLauncher.launch(cameraIntent);
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Saves the phone number
        SharedPreferences.Editor editor = getSharedPreferences("myData", MODE_PRIVATE).edit();
        EditText phoneNum = findViewById(R.id.phoneNumber); // Phone number
        editor.putString("phoneNum", phoneNum.getText().toString());
        editor.apply();
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Saves Image File
        ImageView cameraImg = findViewById(R.id.cameraImage);
        try (FileOutputStream fOS = openFileOutput("image.png", Context.MODE_PRIVATE)) { // Throws fileNotFoundException
            Bitmap bitmap = ((BitmapDrawable) cameraImg.getDrawable()).getBitmap();
            if (isCompress(fOS, bitmap)) {
                fOS.flush(); // Throws IOException
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isCompress(FileOutputStream fOS, Bitmap bitmap) {
        return bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOS);
    }

}