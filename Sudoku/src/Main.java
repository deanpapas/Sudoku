import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {


        puzzle unsolvedpuzzle = new puzzle(puzzleReader("lib/puzzle_1"));

        System.out.println("===========================");
        System.out.println("      Current Puzzle");
        System.out.println("===========================");
        unsolvedpuzzle.printPuzzle();
        System.out.println("===========================");

        System.out.println("      Solved Puzzle");
        System.out.println("===========================");
        puzzleSolver(unsolvedpuzzle).printPuzzle();;
        System.out.println("===========================");

    }

    private static int[][] puzzleReader(String address){

        try {

            Scanner sc = new Scanner(new BufferedReader(new FileReader(address)));

            //Define Puzzle Array
            int rows = 9;
            int columns = 9;
            int[][] pArray = new int[rows][columns];
    
                while (sc.hasNextLine()) {
    
                    for (int i = 0; i < pArray.length; i++) {
                        String[] line = sc.nextLine().trim().split("");
                        for (int j = 0; j < line.length; j++) {
                            pArray[i][j] = Integer.parseInt(line[j]);
                        }
                    }
                }

            return pArray;

            //File Not Found Message
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!");
            System.exit(1);
            return null;
        }

    }

    private static puzzle puzzleSolver(puzzle unsolvedpuzzle){
       
        int [][] uArray = unsolvedpuzzle.getPuzzle();

        for (int i = 0; i < uArray.length; i++){
            System.out.println(uArray[0][i]);
        }

        
        puzzle solvedPuzzle = new puzzle(null);
       

        return solvedPuzzle;
    }

    private static boolean checkRow(int[][] puzzle, int row){


        for (int i = 0; i < puzzle.length; i++){

        }
        return true;
    }
}
