package turma_android.com.br.appservico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
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
    }

    public void desvincularServico(View view) {
    }
}
