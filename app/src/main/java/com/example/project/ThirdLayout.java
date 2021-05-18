package com.example.project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ThirdLayout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_layout);
        Button colors = findViewById(R.id.colors);
        BottomNavigationView bnv;
        TextView setColor = findViewById(R.id.color);
        bnv = findViewById(R.id.second_bnv);
        String color = getIntent().getStringExtra("color");
        setColor.setText(color);

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //At this point, we are doing the same thing that is done for menu.xml selections.
                //if we had a onOptionsItemSelect method for a menu.xml, we could just use it.
                int id = item.getItemId();
                if (id == R.id.nav_back) {
                    back();
                    return true;
                } else if (id == R.id.nav_about) {
                    Toast.makeText(ThirdLayout.this, "You are on the main menu.",
                            Toast.LENGTH_SHORT).show();
                    openPopup();
                    return true;
                }
                return false;
            }
        });

        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThirdLayout.this, RadioButtons.class);
                startActivity(intent);
            }
        });
    }

    public void back() {
        Intent intent = new Intent(this, SecondLayout.class);
        startActivity(intent);
    }

    public void getColor(String color) {
        if (color != null) {

        }

    }

    public void openPopup() {

        String popupMessage = "This is our amazing project in android. Hope you like it!";

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.third_layout, null);
        View popupView = inflater.inflate(R.layout.popup, null);

        TextView textView1 = popupView.findViewById(R.id.popup_text);
        textView1.setText(popupMessage);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }
}
