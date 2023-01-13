import java.util.Arrays;

public class puzzle {
    
    int [][] Array;

    public puzzle(int[][] pArray) {
        this.Array = pArray;
    }

    public int[][] getPuzzle() {
        return Array;
    }

    public void setPuzzle(int[][] pArray) {
        this.Array = pArray;
    }

    public void printPuzzle(){
        try {
            for (int i = 0; i < Array.length; i++){
                System.out.println(Arrays.toString(Array[i]));
            }
        } catch (NullPointerException e){
            System.out.println("Empty Puzzle!");
        }

    }
}
