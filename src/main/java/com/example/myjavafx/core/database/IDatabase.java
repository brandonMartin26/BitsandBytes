package com.example.myjavafx.core.database;

import java.io.IOException;
import java.util.List;

public interface IDatabase {
    void writeRecord(String record) throws IOException;
    void writeRecords(List<String> records) throws IOException;

    List<String> readRecords() throws IOException;

    void updateRecord(String recordId, String newRecord) throws IOException;

    void deleteRecord(String recordId) throws IOException;


}
