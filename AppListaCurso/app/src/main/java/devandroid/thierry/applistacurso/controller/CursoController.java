package devandroid.thierry.applistacurso.controller;

import java.util.ArrayList;
import java.util.List;

import devandroid.thierry.applistacurso.model.Curso;

public class CursoController {

    private List listCursos;

    public List getListaDeCursos(){

        listCursos = new ArrayList<Curso>();

        listCursos.add(new Curso("Java"));
        listCursos.add(new Curso("Python"));
        listCursos.add(new Curso("C++"));
        listCursos.add(new Curso("C#"));

        return listCursos;
    }

    public ArrayList<String> dadosParaSpinner(){

        ArrayList<String> dados = new ArrayList<>();

        for(int i = 0; i < getListaDeCursos().size(); i++){

            Curso objeto = (Curso) getListaDeCursos().get(i);
            dados.add(objeto.getNomeDoCursoDesejado());

        }

        return dados;

    }

}
