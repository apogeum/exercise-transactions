package com.ripple.trustline;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileBasedTransactionsLog {

    private final String fileName;

    public FileBasedTransactionsLog(@Value("${trustline.logfile}") String name){
        this.fileName = name;
        File f = new File(fileName);
        try {
            f.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Transaction> loadTransactions(){
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            return lines.map(l -> l.split(" ")).map(columns -> Transaction.build(columns[0], columns[1])).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void appendTransaction(Transaction t) {
        try (FileOutputStream log = new FileOutputStream(fileName, true)) {
            log.write(t.logLine().getBytes());
            log.write(System.getProperty("line.separator").getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
