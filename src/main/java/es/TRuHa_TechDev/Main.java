package es.TRuHa_TechDev;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.Random;
import java.util.Scanner;


public class Main {
    private static final String workpath = System.getProperty("user.dir");
    private static final String fileRepeat = "repetir.txt";
    private static final String fileDestiny = "destino.txt";
    private static final String fileError = "error.txt";

    public static void genRepeat() {
        System.out.println("[S] Generando datos...");

        Random ram = new Random();
        String text = "";

        int count = ram.nextInt(1, 20);
        for (int i = 1; i <= count; i++) {
            int num = ram.nextInt(1, 20);
            int let = ram.nextInt(65, 90);

            String line = String.format("%s;%s",num, (char) let);
            text += line + "\n";

            String symbol = "├";
            if (i == count) symbol = "└";

            System.out.printf(" %s[%s] %s\n", symbol, i, line);

        }

        Write(fileRepeat, text);

    }

    public static String Read(String filename) {
        String text = "";

        try {
            // para compatibilizar con sistemas Linux y Windows
            String pathfile = workpath + File.separator + filename;
            FileReader file = new FileReader(pathfile);

            int chart = file.read();

            while (chart != -1) {
                char letter = (char) chart;
                text += letter;

                chart = file.read();
            }

            file.close();

        } catch (IOException _) {}

        return text;
    }

    public static void Write(String filename, String data) {
        System.out.printf("Guardando datos en el archivo: %s\n", filename);
    }

    public static void main(String[] args) {
        String repeat = Read(fileRepeat);

        if (repeat.isEmpty()) {
            Scanner input = new Scanner(System.in);

            System.out.println("[!] El archivo está vacío o no existe");
            System.out.print("[?] ¿Generar aleatorio? [S/N] ");
            String option = input.nextLine().toLowerCase();

            if (option.equals("s")) {
                genRepeat();

            } else  {
                System.out.println("[N] Se necesitan datos para continuar :(");
                System.exit(1);
            }

        } else {
            System.out.println(repeat);

        }

        System.out.println("Continuando...");
    }
}
