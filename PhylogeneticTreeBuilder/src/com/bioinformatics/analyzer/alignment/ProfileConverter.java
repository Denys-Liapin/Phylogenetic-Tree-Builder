package com.bioinformatics.analyzer.alignment;
import com.bioinformatics.analyzer.model.KmerPercentProfile;
import com.bioinformatics.analyzer.model.KmerProfile;
import java.util.HashMap;

public class ProfileConverter {

    public KmerPercentProfile Converter(KmerProfile rawProfile){
        double total = 0.0;
        String sampleName = rawProfile.getSampleName();
        HashMap<String, Integer> rawMap = rawProfile.getCodonAndQuantity();
        HashMap<String, Double> percentMap = new HashMap<>();

        for (String key : rawMap.keySet()){
            total = total + rawMap.get(key);
        }

        for (String key : rawMap.keySet()){
            percentMap.put(key, rawMap.get(key) / total * 100);
        }

        return new KmerPercentProfile(percentMap, sampleName);
    }
}
