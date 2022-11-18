package com.example.myjavafx.core.database;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TextDatabase implements IDatabase {
    /***
     * Path to .txt file acting as database file.
     */
    private final String dbFilePath;

    public TextDatabase(String dbFilePath) {
        this.dbFilePath = dbFilePath;
    }

    @Override
    public void writeRecord(String record) throws IOException {
        writeRecord(record, true);
    }

    private void writeRecord(String record, boolean append) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.dbFilePath, append));
        writer.append(record);
        writer.newLine();
        writer.close();
    }

    @Override
    public void writeRecords(List<String> records) throws IOException {
        writeRecords(records, true);
    }

    private void writeRecords(List<String> records, boolean append) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.dbFilePath, append));
        for (String record : records) {
            writer.append(record);
            writer.newLine();
        }
        writer.close();
    }

    @Override
    public List<String> readRecords() throws IOException {
        Scanner scanner = new Scanner(Paths.get(this.dbFilePath), StandardCharsets.UTF_8);
        String data = scanner.useDelimiter("\\A").next();
        scanner.close();
        return Arrays.asList(data.split("\n"));
    }

    @Override
    public void updateRecord(String recordId, String newRecord) throws IOException {
        List<String> records = readRecords();
        records.replaceAll(record -> record.trim().split(";")[0].contentEquals(recordId) ? newRecord : record);
        writeRecords(records, false);
    }


    @Override
    public void deleteRecord(String recordId) throws IOException {
        List<String> records = readRecords();
        List<String> updatedRecords = records.stream().filter(record -> !record.trim().split(";")[0].contentEquals(recordId)).toList();
        writeRecords(updatedRecords, false);
    }
}
