package com.bioinformatics.analyzer.model;
import java.util.List;

public class DistanceMatrix {
    private List<String> sampleNames;
    private double[][] matrix;

    public DistanceMatrix(List<String> sampleNames, double[][] matrix){
        this.sampleNames = sampleNames;
        this.matrix = matrix;
    }
    public List<String> getSampleNames(){
        return sampleNames;
    }
    public double[][] getMatrix(){
        return matrix;
    }
}
