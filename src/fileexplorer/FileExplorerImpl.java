package fileexplorer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileExplorerImpl implements FileExplorer {

    @Override
    public String readFileContent(String name) {
        String text = "";

        try {
            System.err.println(name);
            File myObj = new File(name);
            try (Scanner myReader = new Scanner(myObj)) {
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();

                    text = text + data + "\n";
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nu exista fila.");
            return "Nu exista fila.";
            
        }

        return text;
    }

    @Override
    public String listAllFiles() {
        String text = "";

        try (Stream<Path> paths = Files.list(Paths.get(""))) {

            StringBuilder sb = new StringBuilder();

            paths.filter(Files::isRegularFile)
                    .map(x -> x.toString())
                    .forEach(s -> sb.append(s).append('\n'));

            text = sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return text;
    }

    @Override
    public String deleteFile(String name) {
        String text = "";

        File file = new File(name);

        if (file.delete()) {
            text = "Fila a fost stearsa.";
            System.out.println("Fila a fost stearsa.");
        } else {
            text = "Nu exista fila. ";
            System.out.println("Nu exista fila.");
        }

        return text;
    }

    @Override
    public String createFile(String name) {
        String text = "";

        try {
            File myObj = new File(name);
            if (myObj.createNewFile()) {
                System.out.println("Fila a fost creata: " + myObj.getName());
                text = "Fila a fost creata: " + myObj.getName();
            } else {
                System.out.println("Fila exista deja.");
                text = "Fila exista deja.";
            }
        } catch (IOException e) {
            System.out.println("A aparut o eroare.");
            text = "A aparut o eroare.";
            e.printStackTrace();
        }

        return text;
    }

    @Override
    public String fileWrite(String name, String message) {
        String text = "";


        try {
            Files.write(Paths.get(name), message.getBytes(), StandardOpenOption.APPEND);
            text = "Textul a fost adaugat.";
        } catch (IOException e) {
            
            text = "Nu exista fila.";
        }
        return text;
    }
    
    
    @Override
    public String fileCreateWrite(String name, String message) {
        String text = "";

        String context = null;
        context = message;
        File file = new File(name);
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            fr.write(context);
            text = "Textul a fost adaugat.";
        } catch (IOException e) {
             text="Nu exista fila.";
            e.printStackTrace();
           
        } finally {
          
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return text;
        }
      
    }
    
}
