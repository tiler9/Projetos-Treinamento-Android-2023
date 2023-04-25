package devandroid.thierry.appgaseta.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import devandroid.thierry.appgaseta.model.Combustivel;

public class GasEtaDB extends SQLiteOpenHelper {

    private static final String DB_NAME = "gaseta.db";
    private static final int DB_VERSION = 1;

    Cursor cursor;

    SQLiteDatabase db; //aqui encontram-se os métodos


    public GasEtaDB(Context context){
        super(context, DB_NAME, null, DB_VERSION);

        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlTabelaCombustivel = "CREATE TABLE Combustivel (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nomeDoCombustivel TEXT, " +
                "precoDoCombustivel REAL, " +
                "recomendacao TEXT)";

        db.execSQL(sqlTabelaCombustivel);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }


    public void salvarObjeto(String tabela, ContentValues dados){

        db.insert(tabela, null, dados);

    }

    public List<Combustivel> listarDados(){

        List<Combustivel> lista = new ArrayList<>();

        Combustivel registro;

        String query = "select * from Combustivel";

        cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){

            do{

                registro = new Combustivel();

                registro.setId(cursor.getInt(0)); //i seria o índice da coluna
                registro.setNomeCombustivel(cursor.getString(1));
                registro.setPrecoCombustivel(cursor.getDouble(2));
                registro.setRecomendacao(cursor.getString(3));

                lista.add(registro);

            }while (cursor.moveToNext());

        }else{

        }

        return lista;

    }


    public void alterarObjeto(String tabela, ContentValues dados){

        int id = dados.getAsInteger("id");

        db.update(tabela, dados, "id = ?", new String[]{Integer.toString(id)});


    }

    public void deletarObjeto(String tabela, int id){

        db.delete(tabela, "id = ?", new String[]{Integer.toString(id)});


    }
}
