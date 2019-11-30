package be.mikedhoore.realstation.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import be.mikedhoore.realstation.R;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        TextView tv = findViewById(R.id.textViewInfo);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
