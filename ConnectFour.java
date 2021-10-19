import java.util.*;
public class ConnectFour {
    public static void printBoard(char[][] array)
    {
    	//prints the board as given by the 2d array
        for (int i = 0; i < array.length; i++)
        {
            for (int j = 0; j < array[i].length; j++)
            {
                System.out.print(array[i][j]);
            }
            System.out.print("\n");
        }
    }
//hello, i made an edit here:)
    public static void initializeBoard(char[][] array)
    {
    	// initializes the board.
        char dash = 45;
        for (int i = 0; i < array.length; i++)
        {
            for (int j = 0; j < array[i].length; j++)
            {
            	//sets all elements of the array as dashes and prints it out
                array[i][j] = dash;
                System.out.print(array[i][j]);
            }
            System.out.print("\n");
        }
    }
    
    public static int insertChip(char[][]array, int col, char chipType)
    {
    	//inserts the chip into the array at the lowest possible spot
        int out = -1;
        //iterates through array
        for (int i = 0; i < array.length; i++)
        {
        	//drops the piece above tthe current piece if they exist
            if ((array[i][col] == 120) || (array[i][col] == 111))
            {
                array[i - 1][col] = chipType;
                out = array.length - i;
                break;
            }
        }
        //if no pieces are already in the column, drops the piece at the very bottom
        if (out == -1)
        {
            array[array.length - 1][col] = chipType;
            out = 0;
        }
        return out;
    }

    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType)
    {
        int check = 0;
        boolean out = false;

        for (int i = 0; i < array.length; i++)
        {
        	//checks if there are 4 of the same chip type in a column
            if (array[i][col] == chipType)
            {
                check++;
                if (check == 4)
                {
                    out = true;
                    break;
                }
            }
        }
        check = 0;
        for (int j = 0; j < array[col].length; j++)
        {
        	//checks if there are 4 of the same chip type in a row
            if (array[array.length - row - 1][j] == chipType)
            {
                check++;
                if (check == 4)
                {
                    out = true;
                    break;
                }
            }
        }
        return out;
    }

    public static void main(String[] args) {
    	//initialize variables
        Scanner scnr = new Scanner(System.in);
        int height;
        int length;
        char player1 = 120;
        char player2 = 111;
        
        //ask for the size of the board
        System.out.println("What would you like the height of the board to be? ");
        height = scnr.nextInt();
        System.out.println("What would you like the length of the board to be? ");
        length = scnr.nextInt();

        //print out player 1 and player 2 chip types
        char[][] array = new char[height][length];
        initializeBoard(array);
        System.out.println("");
        System.out.println("Player 1: " + player1);
        System.out.println("Player 2: " + player2);
        System.out.println("");
        
        boolean run = true;
        int col;
        int row;
        int count = 0;

        while (run)
        {
        	//places player 1's chip and subsequently prints the new board out
            System.out.println("Player 1: Which column would you like to choose? ");
            col = scnr.nextInt();
            row = insertChip(array, col, player1);
            printBoard(array);
            System.out.println("");
            //checks if the chip just inserted would cause a win
            if (checkIfWinner(array, col, row, player1) == true)
            {
                System.out.println("Player 1 won the game!");
                break;
            }

            //places player 2's chip and subsequently prints the new board out
            System.out.println("Player 2: Which column would you like to choose? ");
            col = scnr.nextInt();
            row = insertChip(array, col, player2);
            printBoard(array);
            System.out.println("");
            //checks if the chip just inserted would cause player 2 to win
            if (checkIfWinner(array, col, row, player2) == true)
            {
                System.out.println("Player 2 won the game!");
                break;
            }

            //checks that if the board is filled, the game is ended as a draw
            count = 0;
            for (int i = 0; i < array[0].length; i++)
            {
                if (array[0][i] != 45)
                {
                    count++;
                    if (count == array.length)
                    {
                        run = false;
                        System.out.println("Draw. Nobody wins.");
                        break;
                    }
                }
            }
        }
    }
}
