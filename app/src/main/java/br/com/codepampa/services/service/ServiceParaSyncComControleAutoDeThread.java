package br.com.codepampa.services.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import br.com.codepampa.services.controller.MainActivity;
import br.com.codepampa.services.util.NotificationUtil;


public class ServiceParaSyncComControleAutoDeThread extends IntentService {

    private static final String TAG = "Service tres";
    private static final int MAX = 20 ;
    public static final String SIMULA_SYNC_SENDO_EXECUTANDO = "Simula sync sendo executando... ";
    public static final String EXPERIMENTO_3 = "Experimento 3";
    public static final String DADOS_SINCRONIZADOS = "Dados sincronizados.";

    private boolean running = false;
    private int  numeroNotificacao;


    public ServiceParaSyncComControleAutoDeThread() {
        super("ServiceParaSyncComControleAutoDeThread");
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        running = true; //a flag recebe true, indicando que o Service inicicou
        simularSync(); //chama um método para realizar algum trabalho pesado no serviço
        Log.d(TAG, "Fim do processamento de Experimento3_Service.");
        //e cria uma Notification para avisar o usuário que o serviço finalizou
        Intent it = new Intent(ServiceParaSyncComControleAutoDeThread.this, MainActivity.class); // poderia usar o getApplicationContext() para pegar contexto
        NotificationUtil.notify(ServiceParaSyncComControleAutoDeThread.this, 1, it, EXPERIMENTO_3, EXPERIMENTO_3, DADOS_SINCRONIZADOS);
    }

    public void simularSync() {
        int count = 0;
        while(running && count < MAX){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Log.e(TAG, e.getMessage(), e);
            }
            Log.d(TAG, SIMULA_SYNC_SENDO_EXECUTANDO + (count + 1) );
            count++;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        running = false; //para a thread
    }
}
