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
                String trimmedLine = line.trim();

                if (trimmedLine.isEmpty()) {
                    continue;
                }
                if (trimmedLine.startsWith(">")) {
                    if (thisHeader != null) {
                        records.add(new FastaRecord(thisHeader, ThisDNA.toString()));
                        ThisDNA.setLength(0);
                    }
                    thisHeader = trimmedLine.replace(",", "").replace("(", "_").replace(")", "_");;
                    i++;
                } else {
                    ThisDNA.append(trimmedLine);
                }
            }
            records.add(new FastaRecord(thisHeader, ThisDNA.toString()));
        }
        catch (FileNotFoundException e) {
            System.out.println("Parser error: The file '" + inputFileName + "' was not found.");
        }
        catch (IOException e) {
            System.out.println("Parser error: I/O mistake while reading lines: " + e.getMessage());
        }
        return records;
    }

}
