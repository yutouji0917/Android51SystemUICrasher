
package com.example.android51notificationtest;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_crash_android_22).setOnClickListener(this);
        findViewById(R.id.btn_not_crash_android_22).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_crash_android_22:
                showNotification(true);
                break;
            case R.id.btn_not_crash_android_22:
                showNotification(false);
                break;
            default:
                break;
        }
    }

    private void showNotification(boolean crash) {
        String title = "title";
        String description = "description";
        Notification notification = buildNotification(title, description, crash, true);
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(0, notification);
    }

    private Notification buildNotification(String title, String description, boolean crash, boolean withLargeIcon) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this).setDefaults(0)
                .setSound(null).setTicker(title).setContentTitle(title).setContentText(description);
        if (withLargeIcon) {
            Bitmap bitmap = getLargeIconBitmap();
            builder.setLargeIcon(bitmap);
        }
        if (!crash) {
            builder.setSmallIcon(R.drawable.notification_icon);
        }
        builder.setAutoCancel(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
            bigTextStyle.setBigContentTitle(title);
            bigTextStyle.bigText(description);
            builder.setStyle(bigTextStyle);
        }
        Notification notification = builder.build();
        if (crash) {
            notification.icon = R.drawable.notification_icon;
        }
        return notification;
    }

    private Bitmap getLargeIconBitmap() {
        return BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_browser);
    }
}
