package com.bioinformatics.analyzer.alignment;
import com.bioinformatics.analyzer.model.FastaRecord;
import com.bioinformatics.analyzer.model.KmerProfile;

import java.util.HashMap;
import java.util.List;

public class EazyDistanceChecker {

    public KmerProfile seqReader(List<FastaRecord> records) {
        FastaRecord thisSample = records.get(0);
        String thisSampleSeq = thisSample.getSequence();
        String codon;
        int codonCount = 0;
        int startIndex = 0;
        int endIndex = 3;
        HashMap<String, Integer> thisCodonAndQuantity = new HashMap<>();

        while (endIndex <= thisSampleSeq.length()) {
            codon = thisSampleSeq.substring(startIndex, endIndex);
            codonCount = thisCodonAndQuantity.getOrDefault(codon, 0);

            thisCodonAndQuantity.put(codon, codonCount+1);

            startIndex++;
            endIndex++;
        }

        return new KmerProfile(thisCodonAndQuantity);

    }
}
