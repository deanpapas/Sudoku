import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    private static int PUZZLE_SIZE = 9;
    static int recursions = 0;

    public static void main(String[] args) throws Exception {

        Path puzzlepath = Paths.get("lib/Extreme_Puzzle");
        int[][] puzzle = puzzleReader(puzzlepath);

        System.out.println("===========================");
        System.out.println("      Selected Puzzle");
        System.out.println("===========================");
        printPuzzle(puzzle);
        System.out.println("===========================");

        System.out.println("      Solved Puzzle");
        System.out.println("===========================");
        if (solvePuzzle(puzzle)) {
            printPuzzle(puzzle);
            System.out.println("===========================");
            System.out.println("This puzzle took " + recursions + " recursions to solve!");
        } else {
            System.out.println("Impossible Puzzle");
        }
        System.out.println("===========================");

    }

    private static void printPuzzle(int[][] puzzle) {
        for (int row = 0; row < puzzle.length; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("-----------");
            }
            for (int column = 0; column < puzzle.length; column++) {
                if (column % 3 == 0 && column != 0) {
                    System.out.print("|");
                }
                System.out.print(puzzle[row][column]);
            }
            System.out.println();
        }
    }

    private static int[][] puzzleReader(Path address) throws IOException {

        try {

            String[] puzzleSplitString = Files.readString(address).split("(?!^)");

            // Define Puzzle Array
            int[][] pArray = new int[PUZZLE_SIZE][PUZZLE_SIZE];

            for (int i = 0; i < pArray.length; i++) {
                for (int j = 0; j < pArray.length; j++) {
                    pArray[i][j] = Integer.parseInt(puzzleSplitString[(i * pArray.length) + j]);
                }
            }

            return pArray;

            // File Not Found Message
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!");
            System.exit(1);
            return null;
        }

    }

    private static boolean checkRow(int[][] puzzle, int row, int number) {
        for (int i = 0; i < PUZZLE_SIZE; i++) {
            if (puzzle[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkColumn(int[][] puzzle, int column, int number) {
        for (int i = 0; i < PUZZLE_SIZE; i++) {
            if (puzzle[i][column] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkGrid(int[][] puzzle, int row, int column, int number) {

        // Find top left of local grid
        int localGridRow = row - row % 3;
        int localGridColumn = column - column % 3;

        for (int i = localGridRow; i < localGridRow + 3; i++) {
            for (int j = localGridColumn; j < localGridColumn + 3; j++) {
                if (puzzle[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkGuess(int[][] puzzle, int row, int column, int number) {
        return !checkRow(puzzle, row, number)
                && !checkColumn(puzzle, column, number)
                && !checkGrid(puzzle, row, column, number);
    }

    private static boolean solvePuzzle(int[][] inputpuzzle) {

        for (int row = 0; row < PUZZLE_SIZE; row++) {
            for (int column = 0; column < PUZZLE_SIZE; column++) {
                if (inputpuzzle[row][column] == 0) {
                    for (int guess = 1; guess <= PUZZLE_SIZE; guess++) {
                        if (checkGuess(inputpuzzle, row, column, guess)) {
                            inputpuzzle[row][column] = guess;

                            if (solvePuzzle(inputpuzzle)) {
                                return true;
                            } else {
                                inputpuzzle[row][column] = 0;
                            }

                        }
                    }
                    recursions++;
                    return false;
                }
            }
        }
        return true;
    }
}
