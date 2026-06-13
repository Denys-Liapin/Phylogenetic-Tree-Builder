package com.bioinformatics.analyzer.phylogen;

public class NewickRecord {

    private final String newickString;

    public NewickRecord(String newickString){
        this.newickString = newickString;
    }

    public String getNewickString(){
        return newickString;
    }
}
