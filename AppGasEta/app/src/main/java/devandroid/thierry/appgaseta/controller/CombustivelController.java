package devandroid.thierry.appgaseta.controller;

import android.content.ContentValues;
import android.content.SharedPreferences;

import java.util.List;

import devandroid.thierry.appgaseta.database.GasEtaDB;
import devandroid.thierry.appgaseta.model.Combustivel;
import devandroid.thierry.appgaseta.view.GasEtaActivity;

public class CombustivelController extends GasEtaDB {

    SharedPreferences preferences;
    SharedPreferences.Editor sharedEditor;

    public static final String NOME_PREFERENCIAS = "pref_gaseta";

    public CombustivelController(GasEtaActivity activity){

        super(activity); //pega o contexto do GasEtaActivity

        preferences = activity.getSharedPreferences(NOME_PREFERENCIAS, 0);
        sharedEditor = preferences.edit();

    }

    public void salvarPreferencias(Combustivel combustivel){

        ContentValues dados = new ContentValues();

        sharedEditor.putString("combustivel", combustivel.getNomeCombustivel());
        sharedEditor.putFloat("precoCombustivel", (float) combustivel.getPrecoCombustivel());
        sharedEditor.putString("recomendacao", combustivel.getRecomendacao());

        sharedEditor.apply();

        dados.put("nomeDoCombustivel", combustivel.getNomeCombustivel());
        dados.put("precoDoCombustivel", combustivel.getPrecoCombustivel());
        dados.put("recomendacao", combustivel.getRecomendacao());

        salvarObjeto("Combustivel", dados);

    }

    public void limparPreferencias(){

        sharedEditor.clear();
        sharedEditor.apply();

    }

    public List<Combustivel> getListaDeDados(){

        return listarDados();

    }

    public void alterar(Combustivel combustivel){

        ContentValues dados = new ContentValues();

        dados.put("id", combustivel.getId());
        dados.put("nomeDoCombustivel", combustivel.getNomeCombustivel());
        dados.put("precoDoCombustivel", combustivel.getPrecoCombustivel());
        dados.put("recomendacao", combustivel.getRecomendacao());

        alterarObjeto("Combustivel", dados);
    }

    public void deletar(int id){

        deletarObjeto("Combustivel", id);
    }

}
