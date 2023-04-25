package devandroid.thierry.appgaseta.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import devandroid.thierry.appgaseta.R;
import devandroid.thierry.appgaseta.controller.CombustivelController;
import devandroid.thierry.appgaseta.model.Combustivel;
import devandroid.thierry.appgaseta.util.UtilGasEta;

public class GasEtaActivity extends AppCompatActivity {

    CombustivelController controller;

    Combustivel gasolina;
    Combustivel etanol;


    EditText editTextGasolina;
    EditText editTextEtanol;

    TextView txtResultado;

    Button buttonCalcular;
    Button buttonLimpar;
    Button buttonSalvar;
    Button buttonFinalizar;

    double precoGasolina;
    double precoEtanol;
    String recomendacao;

    List<Combustivel> dados;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = new CombustivelController(GasEtaActivity.this);

        dados = controller.getListaDeDados();

        Combustivel objAlteracao = dados.get(1);

        objAlteracao.setNomeCombustivel("Gasosaaaa");
        objAlteracao.setPrecoCombustivel(3.33);
        objAlteracao.setRecomendacao("Abastecer com gasosaa");

        controller.alterar(objAlteracao);

        controller.deletar(2);

        setContentView(R.layout.activity_gaseta);

        editTextGasolina = findViewById(R.id.editTextGasolina);
        editTextEtanol = findViewById(R.id.editTextEtanol);

        buttonCalcular = findViewById(R.id.buttonCalcular);
        buttonFinalizar = findViewById(R.id.buttonFinalizar);
        buttonLimpar = findViewById(R.id.buttonLimpar);
        buttonSalvar = findViewById(R.id.buttonSalvar);

        txtResultado = findViewById(R.id.txtRsultado);

        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isDadosOK = true;

                if(TextUtils.isEmpty(editTextEtanol.getText()) ){
                    editTextEtanol.setError("Tu é cego ?");
                    editTextEtanol.requestFocus();
                    isDadosOK = false;

                }
                if(TextUtils.isEmpty(editTextGasolina.getText())){
                    editTextGasolina.setError("Tu é cego ?");
                    editTextGasolina.requestFocus();
                    isDadosOK = false;
                }

                if(isDadosOK){

                    precoEtanol = Double.parseDouble(editTextEtanol.getText().toString());
                    precoGasolina = Double.parseDouble(editTextGasolina.getText().toString());

                    recomendacao = UtilGasEta.calculaMelhorOpcao(precoGasolina, precoEtanol);

                    txtResultado.setText(recomendacao);

                    buttonSalvar.setEnabled(true);
                }

            }
        });

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                etanol = new Combustivel();
                gasolina = new Combustivel();

                gasolina.setNomeCombustivel("Gasolina");
                gasolina.setPrecoCombustivel(precoGasolina);
                gasolina.setRecomendacao(UtilGasEta.calculaMelhorOpcao(precoGasolina, precoEtanol));


                etanol.setNomeCombustivel("Etanol");
                etanol.setPrecoCombustivel(precoEtanol);
                etanol.setRecomendacao(UtilGasEta.calculaMelhorOpcao(precoGasolina, precoEtanol));

                controller.salvarPreferencias(gasolina);
                controller.salvarPreferencias(etanol);

            }
        });

        buttonFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(GasEtaActivity.this, "Volte Sempre", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        buttonLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editTextGasolina.setText("");
                editTextEtanol.setText("");

                controller.limparPreferencias();

                buttonSalvar.setEnabled(false);
            }
        });

        Toast.makeText(GasEtaActivity.this, UtilGasEta.calculaMelhorOpcao(3.15, 1.2), Toast.LENGTH_LONG).show();
    }
}
