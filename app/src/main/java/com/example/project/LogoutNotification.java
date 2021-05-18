package com.example.project;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LogoutNotification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logout_notif);

        TextView textView = findViewById(R.id.text_view_logout_notif);

        String message = getIntent().getStringExtra("message");
        textView.setText(message);
    }
}