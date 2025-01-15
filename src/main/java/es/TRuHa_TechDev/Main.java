package es.TRuHa_TechDev;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final String workpath = System.getProperty("user.dir") + "/";
    private static final String fileRepeat = "repetir.txt";
    private static final String fileDestiny = "destino.txt";
    private static final String fileError = "error.txt";

    public static String Read(String filename) {
        String text = "";

        try {
            FileReader file = new FileReader(workpath + filename);

            int chart = file.read();

            while (chart != -1) {
                char letter = (char) chart;
                text += letter;

                chart = file.read();
            }

        } catch (IOException _) {}

        return text;
    }

    public static void Write() {

    }

    public static void main(String[] args) {
        String repeat = Read(fileRepeat);

        if (repeat.isEmpty()) {
            Scanner input = new Scanner(System.in);

            System.out.println("[!] El archivo está vacío");
            System.out.print("[?] ¿Generar aleatorio? [S/N]");
            String option = input.nextLine().toLowerCase();

            if (option.equals("s")) {
                System.out.println("[S] Generando archivo...");

            } else  {
                System.out.println("[N] Se necesitan datos para continuar :(");
                System.exit(0);
            }

        } else {
            System.out.println(repeat);

        }

        System.out.println("Continuando...");
    }
}
