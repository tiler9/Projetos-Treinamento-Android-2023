package devandroid.thierry.applistacurso.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import devandroid.thierry.applistacurso.R;
import devandroid.thierry.applistacurso.controller.CursoController;
import devandroid.thierry.applistacurso.controller.PessoaController;
import devandroid.thierry.applistacurso.model.Pessoa;

public class MainActivity extends AppCompatActivity {

    Pessoa pessoa;
    List<String> nomesDeCursos;

    PessoaController controller;

    CursoController cursoController;

    EditText editTextTelefone;
    EditText editTextNomeCurso;
    EditText editTextSobrenome;
    EditText editTextPrimeiroNome;

    Button buttonFinalizar;
    Button buttonSalvar;
    Button buttonLimpar;

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        pessoa = new Pessoa();

        controller = new PessoaController(MainActivity.this);
        controller.buscar(pessoa);

        cursoController = new CursoController();
        nomesDeCursos = cursoController.dadosParaSpinner();

        Log.d("MainActivity","");

        editTextTelefone = findViewById(R.id.editTextTelefone);
        editTextNomeCurso = findViewById(R.id.editTextNomeCurso);
        editTextSobrenome = findViewById(R.id.editTextSobrenome);
        editTextPrimeiroNome = findViewById(R.id.editTextPrimeiroNome);
        spinner = findViewById(R.id.txtListaSpinner);

        editTextPrimeiroNome.setText(pessoa.getPrimeiroNome());
        editTextSobrenome.setText(pessoa.getSobrenome());
        editTextNomeCurso.setText(pessoa.getNomeDoCurso());
        editTextTelefone.setText(pessoa.getTelefoneContato());

        buttonFinalizar = findViewById(R.id.buttonFinalizar);
        buttonSalvar = findViewById(R.id.buttonSalvar);
        buttonLimpar = findViewById(R.id.buttonLimpar);

        editTextNomeCurso.setText(pessoa.getNomeDoCurso());
        editTextPrimeiroNome.setText(pessoa.getPrimeiroNome());
        editTextSobrenome.setText(pessoa.getSobrenome());
        editTextTelefone.setText(pessoa.getTelefoneContato());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cursoController.dadosParaSpinner());

        adapter.setDropDownViewResource(android.R.layout.select_dialog_item);

        spinner.setAdapter(adapter);

        buttonLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextPrimeiroNome.setText("");
                editTextSobrenome.setText("");
                editTextTelefone.setText("");
                editTextNomeCurso.setText("");

                controller.limpar();
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

                controller.salvar(pessoa);
                Log.d("MainActivity", "click botão salvr");
            }
        });
    }

}