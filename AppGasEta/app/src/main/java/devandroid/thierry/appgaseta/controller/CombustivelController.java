package devandroid.thierry.appgaseta.controller;

import android.content.SharedPreferences;

import devandroid.thierry.appgaseta.model.Combustivel;
import devandroid.thierry.appgaseta.view.GasEtaActivity;

public class CombustivelController {

    SharedPreferences preferences;
    SharedPreferences.Editor sharedEditor;

    public static final String NOME_PREFERENCIAS = "pref_gaseta";

    public CombustivelController(GasEtaActivity activity){

        preferences = activity.getSharedPreferences(NOME_PREFERENCIAS, 0);
        sharedEditor = preferences.edit();

    }

    public void salvarPreferencias(Combustivel combustivel){

        sharedEditor.putString("combustivel", combustivel.getNomeCombustivel());
        sharedEditor.putFloat("precoCombustivel", (float) combustivel.getPrecoCombustivel());
        sharedEditor.putString("recomendacao", combustivel.getRecomendacao());

        sharedEditor.apply();

    }

    public void limparPreferencias(){

        sharedEditor.clear();
        sharedEditor.apply();

    }

}
