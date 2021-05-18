package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RadioButtons extends AppCompatActivity {

    RadioButton red, green, black, blue, white;
    String selectedColor;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radio_buttons);

        red = (RadioButton) findViewById(R.id.red);
        green = (RadioButton) findViewById(R.id.green);
        black = (RadioButton) findViewById(R.id.black);
        blue = (RadioButton) findViewById(R.id.blue);
        white = (RadioButton) findViewById(R.id.white);
        submit = (Button) findViewById(R.id.submitButton);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (red.isChecked()) {
                    selectedColor = red.getText().toString();
                } else if (blue.isChecked()) {
                    selectedColor = blue.getText().toString();
                } else if (green.isChecked()) {
                    selectedColor = green.getText().toString();
                } else if (white.isChecked()) {
                    selectedColor = white.getText().toString();
                } else if (black.isChecked()) {
                    selectedColor = black.getText().toString();
                }
                Toast.makeText(getApplicationContext(), selectedColor, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(RadioButtons.this, ThirdLayout.class);
                intent.putExtra("color", "Your favorite color is " + selectedColor);
                startActivity(intent);
            }
        });
    }
}