package com.example.project;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SecondLayout extends AppCompatActivity {

    public SecondLayout() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        TextView textView = findViewById(R.id.text_second_layout);
        TextView textTitle = findViewById(R.id.text_title);
        String textUsername = getIntent().getStringExtra("username");

        String message = "Hello! We are glad to have on our journey.";
        textView.setText(message);
        textTitle.setText("Our project");

        ImageView backIcon = findViewById(R.id.back_icon);
        ImageView menuIcon = findViewById(R.id.menu_icon);
        ImageView userIcon = findViewById(R.id.user_icon);

        BottomNavigationView bnv;
        bnv = findViewById(R.id.bnv);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //At this point, we are doing the same thing that is done for menu.xml selections.
                //if we had a onOptionsItemSelect method for a menu.xml, we could just use it.
                int id = item.getItemId();
                if (id == R.id.nav_main) {
                    Toast.makeText(SecondLayout.this, "You are on the main page.",
                            Toast.LENGTH_SHORT).show();
                    return true;
                } else if (id == R.id.nav_custom) {
                    startActivity(new Intent(SecondLayout.this, ThirdLayout.class));
                    return true;
                }
                return false;
            }
        });

        userIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String popupMessage = "Hello " + textUsername + " !";
                openPopup(v, popupMessage);
            }
        });

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SecondLayout.this, "You clicked on exit button." +
                                "Goodbye!",
                        Toast.LENGTH_SHORT).show();

                String message = "Goodbye! Have a nice day!";
                String CHANNEL_ID = "";
                NotificationManager notificationManager = (NotificationManager)
                        getSystemService(Context.NOTIFICATION_SERVICE);

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    CHANNEL_ID = "my_channel_02";
                    CharSequence name = "my_channel";
                    int importance = NotificationManager.IMPORTANCE_HIGH;
                    NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name,
                            importance);
                    mChannel.enableVibration(true);
                    mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200,
                            400});
                    mChannel.setShowBadge(false);
                    notificationManager.createNotificationChannel(mChannel);
                }

                NotificationCompat.Builder builder = new NotificationCompat.Builder(
                        SecondLayout.this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_baseline_message_24)
                        .setContentTitle("New notification")
                        .setContentText(message)
                        .setAutoCancel(true);

                Intent intent = new Intent(SecondLayout.this,
                        LogoutNotification.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("message", message);

                PendingIntent pendingIntent = PendingIntent.getActivity(SecondLayout.this,
                        0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);

                notificationManager.notify(0, builder.build());

                returnToMainActivity();
            }
        });

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SecondLayout.this, "You clicked on menu icon",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void returnToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_menu, menu);
        return true;
    }

    public void openPopup(View view, String popupMessage) {
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup, null);

        TextView textView1 = popupView.findViewById(R.id.popup_text);
        textView1.setText(popupMessage);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.showAsDropDown(view, -5, 0);

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }

}
