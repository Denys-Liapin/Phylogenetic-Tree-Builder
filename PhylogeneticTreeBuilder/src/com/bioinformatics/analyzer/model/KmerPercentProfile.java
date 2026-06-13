package com.bioinformatics.analyzer.model;
import java.util.HashMap;

public class KmerPercentProfile {
    private final HashMap<String, Double> codonAndPercent;
    private final String sampleName;

    public KmerPercentProfile(HashMap<String, Double> codonAndPercent, String sampleName){
        this.codonAndPercent = codonAndPercent;
        this.sampleName = sampleName;
    }

    public HashMap<String, Double> getCodonAndPercent() {
        return codonAndPercent;
    }

    public String getSampleName(){
        return sampleName;
    }
}