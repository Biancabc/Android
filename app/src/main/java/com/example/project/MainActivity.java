package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements UsernameDialog.DialogListener {

    public TextView textUsername = null;
    private ImageView open;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        open = findViewById(R.id.open_img);
        TextView textTitle = findViewById(R.id.text_title);
        textUsername = findViewById(R.id.text_view_username);

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }

    public void openDialog() {
        UsernameDialog usernameDialog = new UsernameDialog();
        usernameDialog.show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public void applyText(View view, String username) {
        textUsername.setText(username);
        String message = "Hello " + username + " welcome to our new android application!";

        String CHANNEL_ID = "my_channel";
        NotificationManager notificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            CHANNEL_ID = "my_channel_01";
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
                MainActivity.this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_message_24)
                .setContentTitle("New notification")
                .setContentText(message)
                .setAutoCancel(true);

        Intent intent = new Intent(MainActivity.this,
                Notification.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("message", message);

        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,
                0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        notificationManager.notify(0, builder.build());
        openActivity2(username);
    }

    public void openActivity2(String username) {
        Intent intent = new Intent(this, SecondLayout.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

}