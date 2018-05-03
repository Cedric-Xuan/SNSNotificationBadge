package xyz.anynotification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private int i=1;
    private int MQTT_IM_NOTIFICATION_ID=1007;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button show=(Button)findViewById(R.id.show);
        Button hide=(Button)findViewById(R.id.hide);


        String content = 2 + "个联系人发了" + i + "条消息";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext());
        builder.setSmallIcon(R.drawable.chat_notify_icon);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setTicker("收到" + i + "条消息");
        builder.setWhen(System.currentTimeMillis());
        Intent intent = new Intent(getBaseContext(), MainActivity.class);

        intent.setAction(getApplicationContext().getPackageName());
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pi = PendingIntent.getActivity(getBaseContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pi);
        builder.setContentTitle(getResources().getText(R.string.app_name));
        builder.setContentText(content);

        final Notification n = builder.build();
        int defaults = Notification.DEFAULT_LIGHTS;

        defaults |= Notification.DEFAULT_SOUND;

        defaults |= Notification.DEFAULT_VIBRATE;


        n.defaults = defaults;
        n.flags = Notification.FLAG_SHOW_LIGHTS | Notification.FLAG_AUTO_CANCEL;






        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DifferentNotifications.showBubble(getBaseContext(),n,MQTT_IM_NOTIFICATION_ID, i++);
            }
        });

        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DifferentNotifications.hideBubble(getBaseContext(),n,MQTT_IM_NOTIFICATION_ID);
                i=1;
            }
        });









    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("0.0","ReStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("0.0","onStop");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("0.0","onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.e("0.0","Resume");
    }
}
