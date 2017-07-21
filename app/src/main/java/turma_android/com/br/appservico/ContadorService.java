package turma_android.com.br.appservico;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

public class ContadorService extends Service implements Contador {
    private ContadorThread thread;

    @Override
    public void onCreate() {
        Log.i("CONTADOR_SERVICE", "onCreate");

        thread = new ContadorThread();
        thread.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
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
        return new ContadorBinder(this);
    }

    public class ContadorBinder extends Binder {
        private Contador contador;

        public ContadorBinder(Contador contador) {
            this.contador = contador;
        }

        public Contador getContador() {
            return contador;
        }
    }

    @Override
    public int getValor() {
        if(thread != null) {
            return thread.contador;
        }

        return 0;
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
