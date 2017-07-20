package turma_android.com.br.appservico;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ContadorService extends Service {
    private ContadorThread thread;

    @Override
    public void onCreate() {
        Log.i("CONTADOR_SERVICE", "onCreate");

        thread = new ContadorThread();
        thread.start();
    }

    @Override
    public void onDestroy() {
        Log.i("CONTADOR_SERVICE", "onDestroy");

        if(thread != null) {
            thread.ativo = false;
        }

        thread = null;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class ContadorThread extends Thread {
        private boolean ativo = true;
        private int contador;

        @Override
        public void run() {
            while(ativo) {
                contador++;
                Log.i("CONTADOR_SERVICE", "" + contador);
                try {
                    Thread.sleep(1000);
                } catch(Exception e) {}
            }
        }
    }
}
