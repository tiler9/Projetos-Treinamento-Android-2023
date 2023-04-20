package devandroid.thierry.applistacurso.controller;

import android.util.Log;

import androidx.annotation.NonNull;

import devandroid.thierry.applistacurso.model.Pessoa;

public class PessoaController {

    @NonNull
    @Override
    public String toString() {

        Log.d("PessoaController", "método ToString do COntrollador");

        return super.toString();
    }

    public void salvar(Pessoa pessoa){

        Log.d("PessoaController", "Salvo " + pessoa.toString());

    }
}
