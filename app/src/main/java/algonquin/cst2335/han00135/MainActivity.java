package algonquin.cst2335.han00135;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Switch aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.flagView);
        aSwitch = findViewById(R.id.spin_switch);

        aSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
//                RotateAnimation animation = new RotateAnimation(0, 360);
                RotateAnimation animation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                animation.setDuration(5000);
                animation.setRepeatCount(Animation.INFINITE);
                animation.setInterpolator(new LinearInterpolator());

                imageView.startAnimation(animation);
            } else {
                imageView.clearAnimation();
            }
        });
    }
}