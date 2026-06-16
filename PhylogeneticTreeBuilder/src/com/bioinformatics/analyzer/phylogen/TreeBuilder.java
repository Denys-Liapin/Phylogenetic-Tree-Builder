package com.bioinformatics.analyzer.phylogen;
import com.bioinformatics.analyzer.model.DistanceMatrix;
import java.util.ArrayList;
import java.util.List;

public class TreeBuilder {

    public NewickRecord buildTree (DistanceMatrix thisMatrix){
        List<String> sampleNames = new ArrayList<>(thisMatrix.getSampleNames());
        double[][] oldMatrix = thisMatrix.getMatrix();
        double[][] newMatrix = null;
        String thisTaxas = null;
        int minRow = -1;
        int minCol = -1;

        while (oldMatrix.length > 1) {
            double minDistance = Double.MAX_VALUE;
            for (int i = 0; i < sampleNames.size(); i++) {
                for (int j = 0; j < i; j++) {
                    if (oldMatrix[i][j] < minDistance) {
                        minDistance = oldMatrix[i][j];
                        minRow = i;
                        minCol = j;
                    }
                }
            }
            thisTaxas = "(" + sampleNames.get(minRow) + "," + sampleNames.get(minCol) + ")";
            sampleNames.set(minCol, thisTaxas);
            sampleNames.remove(minRow);

            newMatrix = new double[oldMatrix.length - 1][oldMatrix.length - 1];
            for (int i = 0; i < sampleNames.size(); i++){
                for (int j = 0; j < i; j++){
                    int other = (i == minCol) ? j : i;
                    int oldOther = (other >= minRow) ? other + 1 : other;
                    int oldI = (i >= minRow) ? i + 1 : i;
                    int oldJ = (j >= minRow) ? j + 1 : j;

                    if (i == minCol || j == minCol){
                        newMatrix[i][j] = (oldMatrix[oldOther][minRow] + oldMatrix[oldOther][minCol]) / 2.0;
                    }
                    else if(i >= minRow){
                        newMatrix[i][j] = oldMatrix[oldI][oldJ];
                    }
                    else if(i < minRow){
                        newMatrix[i][j] = oldMatrix[oldI][oldJ];
                    }
                }
            }
        }
        return new NewickRecord(sampleNames.get(0) + ";");
    }
}
