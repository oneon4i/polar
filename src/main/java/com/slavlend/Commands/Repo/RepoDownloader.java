package com.slavlend.Commands.Repo;

import java.io.*;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/*
Довнлоадер репозиториев
 */
public class RepoDownloader {
    // скачивание
    public static void download(String repoUrl, String path) throws IOException {
        // выводим сообщение о скачивании
        System.out.println("\uD83D\uDD04 Downloading repo...");
        // скачиваем архив
        try (BufferedInputStream in = new BufferedInputStream(new URL(repoUrl).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(path)) {
            byte dataBuffer[] = new byte[8128];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 8128)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                System.out.println("🔃 Data (bytes): " + String.valueOf(bytesRead));
            }
        } catch (IOException e) {
            throw e;
        }
        // выводим сообщение об удачном скачивании
        System.out.println("🛸 Repo downloaded successfully.");
        System.out.println("👽 Unzipping...");
        // распаковываем архив
        try (var file = new ZipFile(path)) {
            var entries = file.entries();
            var uncompressedDirectory = new File(file.getName()).getParent() + File.separator;
            while (entries.hasMoreElements()) {
                var entry = entries.nextElement();
                if (entry.isDirectory()) {
                    unzipProcessFolder(uncompressedDirectory, entry);
                } else {
                    try {
                        unzipProcessFile(file, uncompressedDirectory, entry);
                    }
                    catch (Exception e) {

                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Выводим сообщение об успешной разархивации
        System.out.println("💡 Unzip success.");
        System.out.println("🚨 Pkg installed!");
    }

    // распаковка папки
    private static void unzipProcessFolder(String uncompressedDirectory, ZipEntry entry) {
        // создание директории
        var newDirectory = uncompressedDirectory + entry.getName();
        var directory = new File(newDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    // распаковка файла
    private static void unzipProcessFile(ZipFile file, String uncompressedDirectory, ZipEntry entry) throws IOException {
        // запись в файл
        try (
            InputStream is = file.getInputStream(entry);
            BufferedInputStream bis = new BufferedInputStream(is)
        ) {
            String uncompressedFileName = uncompressedDirectory + entry.getName();
            try (
                    FileOutputStream outputStream = new FileOutputStream(uncompressedFileName);
                    var bufferedOutputStream = new BufferedOutputStream(outputStream)
            ) {
                while (bis.available() > 0) {
                    bufferedOutputStream.write(bis.read());
                }
            }
        }
    }
}
