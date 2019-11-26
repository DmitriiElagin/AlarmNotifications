package dmitry.elagin.alarmnotifications;

import androidx.appcompat.app.AppCompatActivity;


import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private AlarmManager alarmManager;


    private static final int ALARM_ID=333;
    private PendingIntent pendingIntent;
    private boolean alarmEnabled=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);

        pendingIntent=PendingIntent.getBroadcast(getApplicationContext(),ALARM_ID,
                new Intent(getApplicationContext(),AlarmReceiver.class),PendingIntent.FLAG_UPDATE_CURRENT);
    }



    public void onSwitchClick(View view) {
        if(alarmEnabled) {
            alarmManager.cancel(pendingIntent);
            alarmEnabled=false;
        }

        else {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,1000,AlarmManager.INTERVAL_FIFTEEN_MINUTES,pendingIntent);

            alarmEnabled=true;
        }
    }
}
