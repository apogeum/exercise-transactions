package com.ripple.trustline;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import static org.junit.Assert.*;

public class FileBasedTransactionsLogTest {


    @Test
    public void shouldWriteAndRead() throws FileNotFoundException {
        String fileName = "test.log";
        cleanFile(fileName);
        FileBasedTransactionsLog l = new FileBasedTransactionsLog(fileName);
        l.appendTransaction(Transaction.build("111", "100"));
        l.appendTransaction(Transaction.build("112", "50"));
        List<Transaction> txs = l.loadTransactions();
        assertEquals(2, txs.size());
        assertEquals("111", txs.get(0).id);
        assertEquals(Dollars.amount(100), txs.get(0).amount);
        assertEquals("112", txs.get(1).id);
        assertEquals(Dollars.amount(50), txs.get(1).amount);
        removeFile(fileName);
    }

    private void removeFile(String fileName) {
        File f = new File(fileName);
        f.delete();
    }

    private void cleanFile(String filename) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(filename);
        writer.print("");
        writer.close();
    }
}
