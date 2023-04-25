package devandroid.thierry.applistacurso.model;

public class Curso {


    String nomeDoCursoDesejado;

    public Curso(String nomeDoCurso) {
        this.nomeDoCursoDesejado = nomeDoCurso;
    }

    public String getNomeDoCursoDesejado() {
        return nomeDoCursoDesejado;
    }

    public void setNomeDoCursoDesejado(String nomeDoCursoDesejado) {
        this.nomeDoCursoDesejado = nomeDoCursoDesejado;
    }
}
