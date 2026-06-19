# Phylogenetic-Tree-Builder

A Java-based bioinformatics tool for parsing multi-FASTA files, aligning DNA sequences, and generating distance matrices. Features a clean, extensible OOP architecture with custom parsers and algorithmic strategies, tested on *Camellia sinensis* cold-tolerance genes (DREB/CBF) from NCBI.

---

## Features

* **Robust FASTA Parsing:** A custom-built, highly resilient parser that sanitizes molecular data on the fly, eliminating whitespaces, blank lines, and Newick-invalid characters from NCBI headers.
* **K-mer Profile Generation:** Computes $k$-mer frequency profiles for highly accurate sequence composition analysis.
* **Distance Matrix Calculation:** Builds genetic distance matrices between biological samples utilizing cosine similarity metrics.
* **UPGMA Phylogenetic Inference:** An autonomous implementation of the Unweighted Pair Group Method with Arithmetic Mean (UPGMA) algorithm for automated, step-by-step phylogenetic tree construction.
* **Standard Export:** Saves the final tree topology into the universally recognized **Newick format (.nwk)**, ensuring 100% compatibility with global bioinformatics suites (iTOL, MEGA, Phylo.io).
* **Interactive CLI:** A user-friendly Command Line Interface equipped with structural exception handling against I/O failures and file-system errors.

---

## How to Run

### Prerequisites
* **Java JDK 17** or higher.
* Any modern IDE or Maven/Gradle for alternative manual builds.

### Quick Console Launch (Standard Java Compiler)

```bash
javac -d out src/Main.java src/com/bioinformatics/analyzer/*/*.java
java -cp out Main
```

Once the molecular pipeline finishes calculation, the application enters an interactive loop awaiting your input:
* `show` — Displays the sanitized sequence headers/taxons directly in the console.
* `save` — Exports the calculated phylogenetic tree into a Newick file (the app will prompt you for a filename, e.g., `tree.nwk`).
* `exit` — Terminates the application session.

<details>
<summary>

Start of work

</summary>

<img width="814" height="340" alt="image" src="https://github.com/user-attachments/assets/cd05054b-5396-43f8-b78d-0fe92605f80e" />

</details>

<details>
<summary>

`show` command

</summary>

<img width="1007" height="466" alt="image" src="https://github.com/user-attachments/assets/944fcfbc-a4c4-4944-bacd-e18d985b3d11" />

</details>
---

## Results & Validation

The tool has been successfully validated using real-world molecular data: cold-inducible genes from the tea plant (**Camellia sinensis**) extracted from the NCBI GenBank database. 

The pipeline flawlessly processed the sequences and reconstructed the evolutionary topology into a valid Newick string:

<details>
<summary>

Newick string

</summary>

```text
((>KC702795.1 Camellia sinensis CBF-like protein mRNA complete cds,>XM_028200075.2 PREDICTED: Camellia sinensis dehydration-responsive element-binding protein 1D _LOC114260025_ mRNA),(>XM_082881196.1 PREDICTED: Camellia sinensis dehydration-responsive element-binding protein 1A-like _LOC114308387_ mRNA,>MH165878.1 Camellia sinensis var. sinensis CBF5/DREB protein _DREB_ mRNA complete cds));
```

</details>

<details>
<summary>

Tree Validation via Phylo.io

</summary>

<img width="919" height="174" alt="image" src="https://github.com/user-attachments/assets/3a24102c-78a0-4efb-89c2-59bede0a39fb" />

</details>
