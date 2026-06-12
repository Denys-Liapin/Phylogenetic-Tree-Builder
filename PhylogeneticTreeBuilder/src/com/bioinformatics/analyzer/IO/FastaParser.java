package com.bioinformatics.analyzer.IO;
import java.io.*;
import java.util.ArrayList;

public class FastaParser {
    private String inputFileName = "file.txt";
    ArrayList<String> DNA_header = new ArrayList<>();
    ArrayList<String> DNA_sequence = new ArrayList<>();

    public void parse(String inputFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            String line;
            StringBuilder ThisDNA = new StringBuilder();
            int i = 0;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(">")) {
                    DNA_header.add(line);
                    i++;
                } else {
                    ThisDNA.append(line);
                    DNA_sequence.add(i, ThisDNA);
                }
            }

        }
    }

}
