package com.bioinformatics.analyzer.model;
import java.util.HashMap;

public class KmerPercentProfile {
    private final HashMap<String, Double> codonAndPercent;

    public KmerPercentProfile(HashMap<String, Double> codonAndPercent){
        this.codonAndPercent = codonAndPercent;
    }

    public HashMap<String, Double> getCodonAndPercent() {
        return codonAndPercent;
    }
}