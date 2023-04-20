package devandroid.thierry.applistacurso.controller;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import devandroid.thierry.applistacurso.model.Pessoa;
import devandroid.thierry.applistacurso.view.MainActivity;

public class PessoaController {

    public SharedPreferences preferences;
    public static final String NOME_PREFERENCIAS = "prefListaVip";

    public SharedPreferences.Editor listaVip;

    public PessoaController(MainActivity mainActivity){

        //get sharedpreferences faz parte de uma biblioteca context,
        // então do main activy é preciso passar o contexto para o construtor
        preferences = mainActivity.getSharedPreferences(NOME_PREFERENCIAS, 0);

        listaVip = preferences.edit();
    }

    @NonNull
    @Override
    public String toString() {

        Log.d("PessoaController", "método ToString do COntrollador");

        return super.toString();
    }

    public void salvar(Pessoa pessoa){

        listaVip.putString("primeiroNome", pessoa.getPrimeiroNome());
        listaVip.putString("sobreNome", pessoa.getSobrenome());
        listaVip.putString("nomeCurso", pessoa.getNomeDoCurso());
        listaVip.putString("telefoneContato", pessoa.getTelefoneContato());

        listaVip.apply(); //salvando arquivo

        Log.d("PessoaController", "Salvo " + pessoa.toString());

    }

    public void limpar(){

        listaVip.clear();
        listaVip.apply();
    }

    public Pessoa buscar(Pessoa pessoa){

        pessoa.setPrimeiroNome(preferences.getString("primeiroNome", "Nada escrito"));
        pessoa.setSobrenome(preferences.getString("sobreNome", "Nada escrito"));
        pessoa.setNomeDoCurso(preferences.getString("nomeCurso", "Nada escrito"));
        pessoa.setTelefoneContato(preferences.getString("telefoneContato", "Nada escrito"));

        return pessoa;
    }

}
