package algonquin.cst2335.han00135;

import android.app.Activity;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {


    private static boolean compress(FileOutputStream fOS, Bitmap bitmap) {
        return bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOS);
    }

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

        // Load Image File
        File file = new File(getFilesDir(), "image.png");
        if (file.exists()) {
            Bitmap imgBitmap = BitmapFactory.decodeFile(getFilesDir().getAbsolutePath() + "/image.png");
            cameraImg.setImageBitmap(imgBitmap);
        }

        // Preferences
        SharedPreferences prefs = getSharedPreferences("myData", Context.MODE_PRIVATE);
        phoneNum.setText(prefs.getString("phoneNum", ""));

        // Welcome EmailAddress
        welcomeTextView.setText("Welcome Back " + emailAddress);
        callBtn.setOnClickListener(v -> {
            Intent call = new Intent(Intent.ACTION_DIAL);
            call.setData(Uri.parse("tel:" + phoneNum.getText().toString()));
            startActivity(call);
        });

        // Change picture button onclick: ActivityResultLauncher
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        ActivityResultLauncher<Intent> cameraResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        Bitmap bitmap = data.getParcelableExtra("data");
                        cameraImg.setImageBitmap(bitmap);
                    }
                });
        chgPicBtn.setOnClickListener(v -> {
            cameraResult.launch(cameraIntent);
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences prefs = getSharedPreferences("myData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        TextView phoneNum = findViewById(R.id.phoneNum);
        editor.putString("phoneNum", phoneNum.getText().toString());
        editor.apply();
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Save Image File
        ImageView cameraImg = findViewById(R.id.cameraImg);
        try (FileOutputStream fOS = openFileOutput("image.png", Context.MODE_PRIVATE)) {
            Bitmap bitmap = ((BitmapDrawable) cameraImg.getDrawable()).getBitmap();
            compress(fOS, bitmap);
            fOS.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}