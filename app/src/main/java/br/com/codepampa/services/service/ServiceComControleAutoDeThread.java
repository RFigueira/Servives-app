package br.com.codepampa.services.service;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import br.com.codepampa.services.controller.MainActivity;
import br.com.codepampa.services.util.NotificationUtil;


public class ServiceComControleAutoDeThread extends IntentService {

    private static final String TAG = "Service um";
    public static final String MEU_INTENT_SERVICE = "MeuIntentService de id = ";
    public static final String EXECUTANDO = " executando ... ";
    public static final String TICKER = "Ticker";
    public static final String EXPERIMENTO_2 = "Experimento 2";
    public static final String O_SERVIÇO_DE_ID = "O Serviço de id = ";
    public static final String FINALIZADO = " foi finalizado!";

    private boolean running = false;
    private int  numeroNotificacao;


    public ServiceComControleAutoDeThread() {
        super("ServiceComControleAutoDeThread");
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        running = true;
        numeroNotificacao++;
        int count = 0;
        while (running && count < 10){
            Log.d(TAG,  + numeroNotificacao + EXECUTANDO + ++count);
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                Log.e(TAG, e.getMessage());
            }
        }
        Log.d(TAG, MEU_INTENT_SERVICE + numeroNotificacao + FINALIZADO);
        NotificationUtil.notify(
                getApplicationContext(),
                numeroNotificacao,
                new Intent(getApplicationContext(), MainActivity.class),
                TICKER,
                EXPERIMENTO_2,
                O_SERVIÇO_DE_ID + numeroNotificacao + FINALIZADO);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        running = false; //para a thread
    }
}
