package devandroid.thierry.applistacurso.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import devandroid.thierry.applistacurso.R;
import devandroid.thierry.applistacurso.controller.PessoaController;
import devandroid.thierry.applistacurso.model.Pessoa;

public class MainActivity extends AppCompatActivity {

    Pessoa pessoa;

    PessoaController controller;

    SharedPreferences preferences;
    public static final String NOME_PREFERENCIAS = "prefListaVip";

    EditText editTextTelefone;
    EditText editTextNomeCurso;
    EditText editTextSobrenome;
    EditText editTextPrimeiroNome;

    Button buttonFinalizar;
    Button buttonSalvar;
    Button buttonLimpar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pessoa = new Pessoa();

        controller = new PessoaController();

        preferences = getSharedPreferences(NOME_PREFERENCIAS, 0);
        SharedPreferences.Editor listaVip = preferences.edit();

        pessoa.setPrimeiroNome("Thierry");
        pessoa.setSobrenome("Araújo");
        pessoa.setNomeDoCurso("Kotlin");
        pessoa.setTelefoneContato("(92) 98152-0445");

        editTextTelefone = findViewById(R.id.editTextTelefone);
        editTextNomeCurso = findViewById(R.id.editTextNomeCurso);
        editTextSobrenome = findViewById(R.id.editTextSobrenome);
        editTextPrimeiroNome = findViewById(R.id.editTextPrimeiroNome);

        buttonFinalizar = findViewById(R.id.buttonFinalizar);
        buttonSalvar = findViewById(R.id.buttonSalvar);
        buttonLimpar = findViewById(R.id.buttonLimpar);

        editTextNomeCurso.setText(pessoa.getNomeDoCurso());
        editTextPrimeiroNome.setText(pessoa.getPrimeiroNome());
        editTextSobrenome.setText(pessoa.getSobrenome());
        editTextTelefone.setText(pessoa.getTelefoneContato());

        buttonLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextPrimeiroNome.setText("");
                editTextSobrenome.setText("");
                editTextTelefone.setText("");
                editTextNomeCurso.setText("");
            }
        });

        buttonFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Volte Sempre", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pessoa.setPrimeiroNome(editTextPrimeiroNome.getText().toString());
                pessoa.setSobrenome(editTextSobrenome.getText().toString());
                pessoa.setNomeDoCurso(editTextNomeCurso.getText().toString());
                pessoa.setTelefoneContato(editTextTelefone.getText().toString());

                Toast.makeText(MainActivity.this, "Salvo" + pessoa.toString(), Toast.LENGTH_SHORT).show();

                listaVip.putString("primeiroNome", pessoa.getPrimeiroNome());
                listaVip.putString("sobreNome", pessoa.getSobrenome());
                listaVip.putString("nomeCurso", pessoa.getNomeDoCurso());
                listaVip.putString("telefoneContato", pessoa.getTelefoneContato());

                listaVip.apply(); //salvando arquivo
                controller.salvar(pessoa);

            }
        });
    }

}