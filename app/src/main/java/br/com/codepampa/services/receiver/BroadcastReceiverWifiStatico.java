package br.com.codepampa.services.receiver;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import br.com.codepampa.services.service.ServiceParaSyncComControleAutoDeThread;



public class BroadcastReceiverWifiStatico extends BroadcastReceiver {

    public static final String BROADCAST_WI_FI_CHANGE_RECEBIDO = "Broadcast Wi-fi change recebido.";
    public static final String NETWORK_AVAILABLE_YES = "Network Available, YES";
    public static final String OBJETO_WI_FI = "objeto wi-fi = ";
    private String TAG = "Broadcast receiver";


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, BROADCAST_WI_FI_CHANGE_RECEBIDO);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (isConnected(context)) {
            Log.d(TAG, NETWORK_AVAILABLE_YES);
            context.startService(new Intent(context, ServiceParaSyncComControleAutoDeThread.class));
        }

    }

    private boolean isConnected(Context context){
        //obtém o serviço do SO
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        Log.d(TAG, OBJETO_WI_FI + networkInfo);

        return networkInfo != null && networkInfo.isConnected();
    }
}
