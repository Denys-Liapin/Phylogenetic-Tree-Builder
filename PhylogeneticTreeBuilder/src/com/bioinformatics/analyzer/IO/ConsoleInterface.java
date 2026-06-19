package com.bioinformatics.analyzer.IO;
import com.bioinformatics.analyzer.alignment.DistanceCalculator;
import com.bioinformatics.analyzer.alignment.EazyDistanceChecker;
import com.bioinformatics.analyzer.alignment.ProfileConverter;
import com.bioinformatics.analyzer.model.DistanceMatrix;
import com.bioinformatics.analyzer.model.FastaRecord;
import com.bioinformatics.analyzer.model.KmerPercentProfile;
import com.bioinformatics.analyzer.model.KmerProfile;
import com.bioinformatics.analyzer.phylogen.NewickRecord;
import com.bioinformatics.analyzer.phylogen.TreeBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class ConsoleInterface {
    Scanner scanner = new Scanner(System.in);
    String filePath = null;

    private void printLogo() {
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_RESET = "\u001B[00m";

        System.out.println(ANSI_GREEN);
        System.out.println("   ____  __           __      ______                 ____        _ __     __           ");
        System.out.println("  / __ \\/ /_  __  __ / /___  /_  __/_____ ___  ___  / __ )__  __(_) /____/ /__  _____ ");
        System.out.println(" / /_/ / __ \\/ / / // // __ \\  / /  / ___// _ \\/ _ \\/ __  / / / / / / __  / _ \\/ ___/");
        System.out.println("/ ____/ / / / /_/ // // /_/ / / /  / /   /  __/  __/ /_/ / /_/ / / / /_/ /  __/ /    ");
        System.out.println("/_/   /_/ /_/\\__, //_/ \\____/ /_/  /_/    \\___/\\___/_____/\\__,_/_/_/\\__,_/\\___/_/     ");
        System.out.println("            /____/                                                                   ");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println(ANSI_RESET);
    }

    public void run() {
        printLogo();
        if (filePath == null) {
            System.out.println("Please enter the file path.");
            filePath = scanner.nextLine().trim();
        }

        List<KmerPercentProfile> percentProfiles = new ArrayList<>();

        try {
            FastaParser parser = new FastaParser();
            ArrayList<FastaRecord> records = parser.parse(filePath);
            if (records.isEmpty()) {
                System.out.println("Error: FASTA file is empty!");
                return;
            }

            EazyDistanceChecker distanceChecker = new EazyDistanceChecker();
            ProfileConverter converter = new ProfileConverter();

            for (FastaRecord record : records) {
                if (record.getHeader() == null || record.getSequence().isEmpty()) {
                    continue;
                }
                KmerProfile rawProfile = distanceChecker.seqReader(record);
                KmerPercentProfile percentProfile = converter.Converter(rawProfile);
                percentProfiles.add(percentProfile);
            }

            DistanceCalculator distanceCalculator = new DistanceCalculator();
            DistanceMatrix matrix = distanceCalculator.buildMatrix(percentProfiles);
            TreeBuilder treeBuilder = new TreeBuilder();
            NewickRecord newickRecord = treeBuilder.buildTree(matrix);


            System.out.println("To save a file with the newick format, type \"save\"");
            System.out.println("To display phylogenetic proximity, type \"show\"");
            System.out.println("To finish type \"exit\" ");

            while (true) {
                String userCommand = scanner.nextLine().trim().toLowerCase();

                if (userCommand.equals("save")) {
                    System.out.print("Enter output file name (e.g., tree_result.nwk): ");
                    String outputFileName = scanner.nextLine().trim();

                    if (outputFileName.isEmpty()) {
                        System.out.println("File name cannot be empty!");
                    } else {
                        String newickContent = newickRecord.getNewickString();
                        Files.writeString(Paths.get(outputFileName), newickContent);
                        System.out.println("Success! Tree saved to: " + Paths.get(outputFileName).toAbsolutePath());
                    }
                }
                else if (userCommand.equals("show")) {
                    TreeShower shower = new TreeShower();
                    shower.show(newickRecord);
                } else if (userCommand.equals("exit")) {
                    break;
                } else {
                    System.out.println("Unknown command!");
                }
            }
        }
        catch (IOException e) {
            System.out.println("Failed to process or save file due to I/O error: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Unexpected pipeline error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
