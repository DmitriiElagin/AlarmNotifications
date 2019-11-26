package dmitry.elagin.alarmnotifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.content.Context.NOTIFICATION_SERVICE;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);

        Notification notification=new NotificationCompat.Builder(context,"myChannel")

                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setContentTitle("Уведомление от AlarmManager")
                .setContentText("Периодическое уведомление, созданное AlarmManager")
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.cat_icon))
                .build();

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel= new NotificationChannel("myChannel","channelName",NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(777,notification);

        String time=new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
        Log.d("happy","AlarmManager - "+time);

        MediaPlayer.create(context, R.raw.sound).start();



    }
}
