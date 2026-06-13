package com.bioinformatics.analyzer.alignment;
import com.bioinformatics.analyzer.model.DistanceMatrix;
import com.bioinformatics.analyzer.model.KmerPercentProfile;

import java.util.*;

public class DistanceCalculator {

    public DistanceMatrix buildMatrix(List<KmerPercentProfile> profiles){
        ArrayList<String> samplesNames = new ArrayList<>();
        double[][] distanceMatrix = new double[profiles.size()][profiles.size()];
        for (int i = 0; i < profiles.size(); i++) {
            KmerPercentProfile profile = profiles.get(i);
            samplesNames.add(profile.getSampleName());
        }

        for (int i = 0; i < profiles.size(); i++) {
            for (int j = i + 1; j < profiles.size(); j++) {
                KmerPercentProfile firstSample = profiles.get(i);
                KmerPercentProfile secondSample = profiles.get(j);

                double distance = calculateDistance(firstSample, secondSample);
                distanceMatrix[i][j] = distance;
                distanceMatrix[j][i] = distance;
            }
        }
        return new DistanceMatrix(samplesNames, distanceMatrix);
    }

    private double calculateDistance(KmerPercentProfile firstSample, KmerPercentProfile secondSample){
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        double vectSize = 0.0;
        double similarity = 0.0;
        double distance = 0.0;
        HashMap<String, Double> mapA = firstSample.getCodonAndPercent();
        HashMap<String, Double> mapB = secondSample.getCodonAndPercent();

        for (String codon : mapA.keySet()) {
            double prsA = mapA.get(codon);
            double prsB = mapB.getOrDefault(codon, 0.0);

            dotProduct = dotProduct + prsA * prsB;
            normA = normA + prsA * prsA;
            normB = normB + prsB * prsB;
        }
        vectSize = Math.sqrt(normA) * Math.sqrt(normB);
        similarity = dotProduct / vectSize;
        distance = 1.0 - similarity;

        return distance;
    }
}