package com.bioinformatics.analyzer.IO;
import com.bioinformatics.analyzer.phylogen.NewickRecord;
import java.util.Stack;

public class TreeShower {

    public void show(NewickRecord record){
        String newickString = record.getNewickString();
        String vertically = "\u2502"; // "│"
        String horizontally = "\u2500"; // "─"
        String downRight = "\u2514"; // "└"
        String topRight = "\u250C"; // "┌"
        String verticallyConnection = "\u251C"; // "├"
        Stack<Character> stack = new Stack<>();
        StringBuilder sampleName = new StringBuilder();

        for (int i = 0; i < newickString.length(); i++){
            char thisSymbol = newickString.charAt(i);

            if (thisSymbol == '('){
                stack.push(thisSymbol);
            }
            else if (thisSymbol == ','){
                System.out.print("\u251C");
                for (int j = 0; j < stack.size(); j++){
                    System.out.print("\u2500");
                }
                System.out.println("\u2500" + sampleName);
                sampleName.setLength(0);
            }
            else if (thisSymbol == ')'){
                System.out.print("\u251C");
                for (int j = 0; j < stack.size(); j++){
                    System.out.print("\u2500");
                }
                System.out.println("\u2500" + sampleName);
                sampleName.setLength(0);
                stack.pop();
            }
            else {
                sampleName.append(thisSymbol);
            }

        }
    }
}
