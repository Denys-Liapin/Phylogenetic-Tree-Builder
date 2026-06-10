package com.bioinformatics.analyzer.model;

import java.util.HashMap;

public class KmerProfile {
    private final HashMap<String, Integer> codonAndQuantity;

    public KmerProfile(HashMap<String, Integer> codonAndQuantity){
        this.codonAndQuantity = codonAndQuantity;
    }

    public HashMap<String, Integer> getCodonAndQuantity() {
        return codonAndQuantity;
    }
}
