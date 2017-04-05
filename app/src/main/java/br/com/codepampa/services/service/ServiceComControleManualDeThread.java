package br.com.codepampa.services.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import br.com.codepampa.services.controller.MainActivity;
import br.com.codepampa.services.util.NotificationUtil;


public class ServiceComControleManualDeThread extends Service {

    private static final String SERVIÇO_INICIADO_PELO_COM_ID = "Serviço iniciado pelo método onStartCommand(), com id = ";
    private static final String TAG = "Service um";
    private static final String MENSAGE_CREATE = "Criando nosso servico";
    public static final String SERVIÇO_DESTRUÍDO = "Serviço destruído no onDestroy()";
    public static final String MEU_SERVICE = "MeuService ";
    public static final String EXECUTANDO = " executando ... ";

    private boolean running = false;
    private int  numeroServico;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, MENSAGE_CREATE);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        numeroServico = startId;
        Log.d(TAG, SERVIÇO_INICIADO_PELO_COM_ID + startId);
        running = true;
        new Task().start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        running = false; // faz parar a thread
        Log.d(TAG, SERVIÇO_DESTRUÍDO);
    }

    private class Task extends Thread{
        int count = 0;
        public void run(){
            int id = numeroServico;
            try {
                while (running && count < 10){
                    Thread.sleep(1500);
                    Log.d(TAG, MEU_SERVICE + id + EXECUTANDO + ++count);
                }
                NotificationUtil.notify(
                        getApplicationContext(), //contexto da app
                        id, //id do service, fornecendo id para a notification
                        new Intent(getApplicationContext(), MainActivity.class), //a intent
                        "Ticker", //o ticker da notification
                        "Experimento 1", //o título da notification
                        "O Serviço de id = " + id + " foi finalizado!"); //o texto da notification
            }catch (InterruptedException e){
                Log.e(TAG, e.getMessage(), e);
            }finally {
                stopSelf();
                numeroServico = id;
            }
        }
    }



}
