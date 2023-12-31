package org.example;

import java.nio.file.Path;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;


public class FileIo {


   public static boolean checkIfExists(String filePath) {
        Path path = Path.of(filePath);
        return Files.exists(path);
    }


    public static void createDirectory(String dirPath) {
        if (!checkIfExists(dirPath)) {
            Path path = Path.of(dirPath);
            try {
                Files.createDirectory(path);
                System.out.println("Created directory: " + dirPath);
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
                exception.printStackTrace();
            }

        } else {
            System.out.println("Directory " + dirPath + " already exists.");
        }
    }

  
    public static void writeToFile(String filePath, String data) {
        if (checkIfExists(filePath)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                writer.write(data);
                writer.newLine();
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
                exception.printStackTrace();
            }
        } else {
            System.out.println("File " + filePath + " does not exist.");
        }
    }


    public static int countLines(String filePath) {
        if (checkIfExists(filePath)) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                int count = 0;
                while (reader.readLine() != null) {
                    count++;
                }
                return count;
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
                exception.printStackTrace();
                return -1;
            }
        }
        System.out.println("File " + filePath + " does not exist");
        return -1;
    }


    public static void deleteFile(String filePath) {
        if (checkIfExists(filePath)) {
            Path path = Path.of(filePath);
            try {
                Files.delete(path);
                System.out.println("Deleted file: " + filePath);
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
                exception.printStackTrace();
            }
        } else {
            System.out.println("File " + filePath + " does not exist.");
        }
    }

 
    public static void createFile(String filePath) {
        if (!checkIfExists(filePath)) {
            Path path = Path.of(filePath);
            try {
                Files.createFile(path);
                System.out.println("Created file: " + filePath);
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
                exception.printStackTrace();
            }
        } else {
            System.out.println("File " + filePath + " already exists.");
        }
    }

  
 
    public static void listFiles(String dirPath) {
        Path path = Path.of(dirPath);
        if (checkIfExists(dirPath)) {
            if (Files.isDirectory(path)) {
                try {
                    Files.list(path).forEach(System.out::println);
                } catch (IOException exception) {
                    System.out.println(exception.getMessage());
                    exception.printStackTrace();
                }
            } else {
                System.out.println(dirPath + " is not a directory.");
            }
        } else {
            System.out.println("Directory " + dirPath + " does not exist.");
        }
    }

   
    public static void listFilesWithExtension(String dirPath, String extention) {
        Path path = Path.of(dirPath);
        if (checkIfExists(dirPath)) {
            if (Files.isDirectory(path)) {
                try {
                    Files.newDirectoryStream(path,
                                    filepath -> filepath.toFile().isFile() && filepath.toString().endsWith(extention))
                            .forEach(System.out::println);
                } catch (IOException exception) {
                    System.out.println(exception.getMessage());
                    exception.printStackTrace();
                }
            } else {
                System.out.println(dirPath + " is not a directory.");
            }
        } else {
            System.out.println("Directory " + dirPath + " does not exist.");
        }
    }
}
