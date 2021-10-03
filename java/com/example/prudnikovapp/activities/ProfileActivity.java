package com.example.prudnikovapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prudnikovapp.R;
import com.example.prudnikovapp.models.User;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Bundle bundle = getIntent().getExtras();

        User user = bundle.getParcelable(User.class.getSimpleName());
        ImageView imageView = (ImageView) findViewById(R.id.avatar);
        TextView name = (TextView) findViewById(R.id.name);
        TextView age = (TextView) findViewById(R.id.age);
        imageView.setImageResource(R.drawable.pidor);
        name.setText(user.getName());
        age.setText(user.getAge() + " лет деду");

    }
}