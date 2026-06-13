package com.bioinformatics.analyzer.model;

import java.util.HashMap;

public class KmerProfile {
    private final HashMap<String, Integer> codonAndQuantity;
    private final String sampleName;

    public KmerProfile(HashMap<String, Integer> codonAndQuantity, String sampleName){
        this.codonAndQuantity = codonAndQuantity;
        this.sampleName = sampleName;
    }

    public HashMap<String, Integer> getCodonAndQuantity() {
        return codonAndQuantity;
    }

    public String getSampleName(){
        return sampleName;
    }
}
