package org.example;

import java.io.*;
import java.nio.file.*;
import java.util.Arrays;

public class BackupAndGameState {

    // Функция для резервного копирования файлов
    public static void backupFiles(String sourceDir) {
        File dir = new File(sourceDir);
        File backupDir = new File("./backup");

        // Создание директории для резервной копии, если она не существует
        if (!backupDir.exists()) {
            backupDir.mkdir();
        }

        // Получение всех файлов в директории
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    try {
                        // Копирование файла в директорию резервной копии
                        Files.copy(file.toPath(), Paths.get(backupDir.getPath(), file.getName()), StandardCopyOption.REPLACE_EXISTING);
                        System.out.println("Файл " + file.getName() + " успешно скопирован.");
                    } catch (IOException e) {
                        System.err.println("Ошибка при копировании файла " + file.getName() + ": " + e.getMessage());
                    }
                }
            }
        }
    }

    // Функция для записи состояния игры в файл
    public static void writeGameState(int[] gameState, String fileName) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName))) {
            for (int state : gameState) {
                dos.writeByte(state);
            }
            System.out.println("Состояние игры успешно записано в файл " + fileName);
        } catch (IOException e) {
            System.err.println("Ошибка при записи состояния игры: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        backupFiles("./source_directory");

        int[] gameState = {0, 1, 2, 1, 0, 0, 2, 1, 3};
        writeGameState(gameState, "gameState.dat");
    }
}