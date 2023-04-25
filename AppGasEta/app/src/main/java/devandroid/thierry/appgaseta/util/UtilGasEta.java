package devandroid.thierry.appgaseta.util;

public class UtilGasEta {

    public UtilGasEta(){

    }


    public static String calculaMelhorOpcao(double precoGasosa, double precoEtanol){

        double precoIdeal = precoGasosa * 0.7;

        if(precoEtanol <= precoIdeal)
            return "Melhor abastecer com etanol";
        else
            return "Melhor abastecer com gasosa";

    }
}
