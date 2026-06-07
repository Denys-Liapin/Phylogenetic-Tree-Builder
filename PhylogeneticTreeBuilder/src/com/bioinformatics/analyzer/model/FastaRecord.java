package com.bioinformatics.analyzer.model;

public class FastaRecord {
    private final String header;
    private final String sequence;

    public FastaRecord(String header, String sequence) {
        this.header = header;
        this.sequence = sequence.toUpperCase().replaceAll("\\s+", "");
    }

    public String getHeader() {
        return header;
    }

    public String getSequence() {
        return sequence;
    }



}
