package br.com.codepampa.services.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import br.com.codepampa.services.R;
import br.com.codepampa.services.service.ServiceComControleAutoDeThread;
import br.com.codepampa.services.service.ServiceComControleManualDeThread;

public class ExperimentoDois extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experimento_dois);

        findViewById(R.id.button_start_service_experimento_dois).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(new Intent(ExperimentoDois.this, ServiceComControleAutoDeThread.class));
            }
        });
        findViewById(R.id.button_stop_service_experimento_dois).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(ExperimentoDois.this, ServiceComControleAutoDeThread.class));
            }
        });
    }
}
