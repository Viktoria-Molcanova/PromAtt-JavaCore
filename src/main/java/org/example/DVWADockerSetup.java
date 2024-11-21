
package org.example;

import java.io.*;
import java.nio.file.*;
import java.util.Arrays;

public class DVWADockerSetup {

    public static void setupDVWADocker() {

        String[] commands = {
                "docker pull vulnerables/web-dvwa", // образ DVWA
                "docker run --rm -d -p 80:80 --name dvwa vulnerables/web-dvwa", // Запуск контейнера
                "docker exec -it dvwa /bin/bash -c 'sleep 5; /usr/bin/mysql_install_db'", // Инициализация базы данных
                "docker exec -it dvwa /bin/bash -c 'sleep 5; php init.php'" // Инициализация DVWA
        };


        for (String command : commands) {
            try {
                Process process = Runtime.getRuntime().exec(command);
                process.waitFor(); 
                System.out.println("Успешно выполнена команда: " + command);
            } catch (IOException | InterruptedException e) {
                System.err.println("Ошибка при выполнении команды: " + command + " - " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        setupDVWADocker();
    }
}
