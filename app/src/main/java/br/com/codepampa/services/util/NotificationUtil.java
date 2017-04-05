package br.com.codepampa.services.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import br.com.codepampa.services.R;

/**
 * Created by rfreitas on 04/04/17.
 */

public class NotificationUtil {

    private static final String TAG = "NotificationUtil";

    public static void notify(Context context, int id, Intent intent, String contenTicker, String contentTitle, String contentText) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Intent para disparar o broadcast
        PendingIntent p = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Cria a notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(p)
                .setTicker(contenTicker)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setSmallIcon(R.drawable.notification_icon_background)
                .setAutoCancel(true);

        // Dispara a notification
        Notification n = builder.build();
        manager.notify(id, n);

        Log.d(TAG,"Notification criada com sucesso");

        //obtém o Uri do RingTone (do tipo Notificações) e faz ele tocar
        Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION); //obtém o Uri padrão do toque de notificações
        Ringtone toque = RingtoneManager.getRingtone(context, som); //obtém a instância do RingTone
        toque.play(); //manda tocar
        Log.d("TesteNotification", "Uri = " + som);
    }
}
