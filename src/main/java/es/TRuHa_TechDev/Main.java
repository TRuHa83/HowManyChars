package es.TRuHa_TechDev;


public class Main {
    public static void main(String[] args) {
        HowManyRepeat hmr = new HowManyRepeat(
                "repetir.txt",
                "destino.txt",
                "error.log");

        hmr.processData();

    }
}
