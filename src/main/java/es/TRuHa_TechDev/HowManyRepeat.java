package es.TRuHa_TechDev;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


/**
 * <p>Utilidad para procesar un archivo de texto plano.</p>
 * <br>
 * <p><b>CONSTRUCTOR</b></p>
 * <p>HowManyRepeat hmr = new HowManyRepeat("repetir.txt","destino.txt","error.txt");</p>
 * <p>---</p>
 * <p> - repetir.txt: Destinado a almacenar los valores a repetir</p>
 * <p> - destino.txt: Destinado para almacenar los valores repetidos</p>
 * <p> - error.txt: Destinado para almacenar un listado de error</p>
 * <br>
 * <p><b>MÉTODO</b></p>
 * <p>processData()</p>
 * <p>---</p>
 * <p>- Procesa el archivo indicado en busco de repeticiones.</p>
 * <p>- Almacena todas las repeticiones indicadas en el archivo.</p>
 * <p>- Genera un informe de errores.</p>
 * <br>
 * <p>Autor: Sergio Trujillo de la Nuez</p>
 * <p>GitHub: <a href="https://github.com/TRuHa83">TRuHa83</a></p>
 * <p>Repositorio: <a href="https://github.com/TRuHa83/HowManyRepeat">HowManyRepeats</a></p>
 */
public class HowManyRepeat {
    private final ArrayList<String> bufferData = new ArrayList<>();
    private final ArrayList<String> bufferError = new ArrayList<>();
    private final ArrayList<String> data = new ArrayList<>();
    private final String workpath = System.getProperty("user.dir");
    private final String separator = File.separator;
    private final String fileRepeat;
    private final String fileDestiny;
    private final String fileError;

    // Configuración por defecto
    public HowManyRepeat() {
        this.fileRepeat = "repetir.txt";
        this.fileDestiny = "destino.txt";
        this.fileError = "error.txt";
    }

    // Configuración personalizada
    public HowManyRepeat(String fileRepeat, String fileDestiny, String fileError) {
        this.fileRepeat = fileRepeat;
        this.fileDestiny = fileDestiny;
        this.fileError = fileError;
    }

    // Generar archivo a procesar de forma aleatoria
    private void genFile() {
        int[] combined = new int[3]; // Conjunto de una línea en array
        int[] character = {33, 127}; // Caracteres imprimibles
        int[] numbers = {1, 101};    // Números del 1 al 100
        int[] letter = {65, 91};     // Caracteres de la A a la Z
        int aleatorio;
        String line;

        Random random = new Random();
        for (int i=0; i < random.nextInt(100000); i++) {
            combined[0] = random.nextInt(numbers[0], numbers[1]);
            combined[1] = 59;
            combined[2] = random.nextInt(letter[0], letter[1]);


            aleatorio = random.nextInt(11);
            if ( aleatorio == 0) {
                combined[aleatorio] = random.nextInt(character[0], character[1]);
                line = Character.toString(combined[0]) + Character.toString(combined[1]) + Character.toString(combined[2]);

            } else if (aleatorio < 3) {
                combined[aleatorio] = random.nextInt(character[0], character[1]);
                line = combined[0] + Character.toString(combined[1]) + Character.toString(combined[2]);

            } else {
                line = combined[0] + Character.toString(combined[1]) + Character.toString(combined[2]);

            }

            data.add(line);
        }

        writeData(data, fileRepeat);
    }

    // Cuando no se encuentra el archivo a procesar
    private void fileNotFound(){
        Scanner input = new Scanner(System.in);

        System.out.print("[?] Desea generar un archivo de prueba? [S/N] ");
        String option = input.nextLine().toLowerCase();

        if (option.equals("s")) {
            System.out.println("[S] Gerenando " + fileRepeat + " de forma aleatoria");
            genFile();

        } else {
            System.out.println("[N] Hasta pronto ;)\n");
            System.exit(0);
        }
    }

    // Leer el contenido de un archivo
    private void readData() {
        try {
            String pathfile = workpath + separator + fileRepeat;
            BufferedReader buffer = new BufferedReader(new FileReader(pathfile));

            String line;
            while ((line = buffer.readLine()) != null) {
                data.add(line);
            }

        } catch (IOException e) {
            System.out.println("[!] Archivo a procesar no disponible");
            fileNotFound();
        }
    }

    // Almacenar datos en un archivo
    private void writeData(ArrayList<String> data, String route) {
        try {
            String pathfile = workpath + separator + route;
            BufferedWriter buffer = new BufferedWriter(new FileWriter(pathfile));

            for (String line : data) {
                buffer.write(line);
                buffer.newLine();
                buffer.flush();
            }

        } catch (IOException e) {}
    }

    // Validar un carácter
    private boolean validate(String letter) {
        int chart = letter.charAt(0);
        return 64 < chart && 91 > chart;
    }

    // Imprime líneas de informe
    private void printInform(int dataSize, int countCorrect, int countError) {
        String resume;
        String error = "";
        String correct = "";

        if (dataSize > 1) {
            resume = "[+] Datos procesados correctamente";

            if (countCorrect > 0) {
                correct = String.format("[✔] Se han procesado un total de %s lineas\n", countCorrect);
            }

            if (countError > 0) {
                resume = resume.replace("+", "!") + " con errores";
                error = String.format("[✘] Se han encontrado %s problemas al procesar\n", countError);

            }

        } else {
            resume = String.format("[!] Archivo %s en blanco\n[✘] Nada que procesar", fileRepeat);

        }

        System.out.println(resume);
        System.out.print(error);
        System.out.print(correct);

    }

    // Procesamiento de datos
    public void processData() {
        System.out.println("[+] Procesando datos...");
        readData();

        int[] countData = {0, 0};
        int countLine = 1;

        for (String value : data) {
            String[] part = value.split(";");

            if (part.length > 1) {
                try {
                    int rep = Integer.parseInt(part[0]);

                    String line = "";
                    if (validate(part[1].toUpperCase())) {
                        for (int i = 0; i < rep; i++) {
                            line += part[1];
                        }

                        bufferData.add(line);
                        countData[0] ++;

                    } else {
                        String text = String.format("LINEA %s: Detectado carácter %s inválido", countLine, part[1]);
                        bufferError.add(text);
                        countData[1] ++;

                    }

                } catch (NumberFormatException e) {
                    String text = String.format("LINEA %s: Número de repeticiones incorrecto", countLine);
                    bufferError.add(text);
                    countData[1] ++;


                }

            } else {
                String text = String.format("LINEA %s: Detectado un separador incorrecto", countLine);
                bufferError.add(text);
                countData[1] ++;

            }

        countLine++;

        }

        writeData(bufferData, fileDestiny);
        writeData(bufferError, fileError);

        printInform(data.size(), countData[0], countData[1]);
        System.out.println("[+] Procesamiento terminado");
    }
}