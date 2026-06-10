package com.bioinformatics.analyzer.IO;
import com.bioinformatics.analyzer.model.FastaRecord;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FastaParser {

    public List<FastaRecord> parse(String inputFileName) throws IOException {
        List<FastaRecord> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            String line;
            StringBuilder thisDNA = new StringBuilder();
            String currentHeader = null;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(">")) {
                    if (currentHeader != null) {
                        records.add(new FastaRecord(currentHeader, thisDNA.toString()));
                        thisDNA.setLength(0);
                    }
                    currentHeader = line;
                } else {
                    thisDNA.append(line);
                }
            }
            if (currentHeader != null) {
                records.add(new FastaRecord(currentHeader, thisDNA.toString()));
            }
        }
        return records;
    }
}