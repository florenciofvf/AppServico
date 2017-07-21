package turma_android.com.br.appservico;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Contador contador;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        textView = (TextView) findViewById(R.id.textView);

        new THREAD().start();
    }

    public void iniciarServico(View view) {
        Intent it = new Intent(this, ContadorService.class);
        startService(it);
    }

    public void pararServico(View view) {
        Intent it = new Intent(this, ContadorService.class);
        stopService(it);
    }

    public void vincularServico(View view) {
        Intent it = new Intent(this, ContadorService.class);
        bindService(it, serviceConnection,
                                Service.BIND_AUTO_CREATE);
    }

    private Conexao serviceConnection = new Conexao();

    private class Conexao implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            ContadorService.ContadorBinder binder =
                    (ContadorService.ContadorBinder) iBinder;

            contador = binder.getContador();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i("TESTE", componentName.toString());
            contador = null;
        }
    }

    public void desvincularServico(View view) {
        unbindService(serviceConnection);
    }

    private class THREAD extends Thread {
        @Override
        public void run() {
            while (true) {
                if(contador != null) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText("" + contador.getValor());
                        }
                    });
                }

                try {
                    Thread.sleep(1000);
                } catch(Exception e) {}
            }
        }
    }
}
