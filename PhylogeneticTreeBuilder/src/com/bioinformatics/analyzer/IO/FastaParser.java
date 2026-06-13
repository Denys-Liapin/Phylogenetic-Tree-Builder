package com.bioinformatics.analyzer.IO;
import com.bioinformatics.analyzer.model.FastaRecord;

import java.io.*;
import java.util.ArrayList;

public class FastaParser {
    private String inputFileName = "file.txt";

    public ArrayList<FastaRecord> parse(String inputFileName) {
        ArrayList<FastaRecord> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            String line;
            StringBuilder ThisDNA = new StringBuilder();
            String thisHeader = null;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(">")) {
                    if (thisHeader != null) {
                        records.add(new FastaRecord(thisHeader, ThisDNA.toString()));
                        ThisDNA.setLength(0);
                    }
                    thisHeader = line;
                    i++;
                } else {
                    ThisDNA.append(line);
                }
            }
            records.add(new FastaRecord(thisHeader, ThisDNA.toString()));
        }
        return records;
    }

}
