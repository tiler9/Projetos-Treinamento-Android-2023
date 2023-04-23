package devandroid.thierry.appgaseta.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import devandroid.thierry.appgaseta.R;
import devandroid.thierry.appgaseta.database.GasEtaDB;

public class SplashActivity extends AppCompatActivity {

    public static final int TIMEOUTSPLASH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        trocarTelaSplash();
    }

    private void trocarTelaSplash() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                GasEtaDB db = new GasEtaDB(SplashActivity.this);

                Intent telaPrincipal = new Intent(SplashActivity.this, GasEtaActivity.class);

                startActivity(telaPrincipal);
                finish();
            }
        }, TIMEOUTSPLASH);
    }


}