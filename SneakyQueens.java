// Rhami Thrower
// COP 3503, Spring 2023
// Rh270888
// Due Date: January 22, 2023

// Imports Bellow
import java.util.ArrayList;

// Classes Bellow
public class SneakyQueens 
{
    public static boolean allTheQueensAreSafe(ArrayList<String> coordinateStrings, int boardSize)
    {
        // variables
        int numPieces = coordinateStrings.size();
        int downDiagnalSpot = 0;
        int upDiagnalSpot = 0;
        int colSpot = 0;
        int rowSpot = 0;
        // used for storing updated coordinates on given pieces 
        int[] rowPieces = new int[numPieces];
        int[] colPieces = new int[numPieces];
        // used to determine if a piece can attack another piece
        int[] rowBoard = new int[boardSize];
        int[] colBoard = new int[boardSize]; 
        int[] downDiagnal = new int[2*boardSize-1];
        int[] upDiagnal = new int[2*boardSize-1];

        // populate rowpiece and colpiece
        updatedCord(rowPieces, colPieces, coordinateStrings);

        // populates row and column list
        for (int i = 0; i < boardSize; i++)
        {
            rowBoard[i] = 0;
            colBoard[i] = 0;
        }
        //populates Diagnal lists
        for (int i = 0; i <2*boardSize-1; i++)
        {
            downDiagnal[i] = 0;
            upDiagnal[i] = 0;
        }

        // check all possiable attack angles
        for (int i = 0; i < numPieces; i++)
        {
            downDiagnalSpot = rowPieces[i] - colPieces[i] + (boardSize-1);
            upDiagnalSpot = rowPieces[i] + colPieces[i] - 2;
            rowSpot =rowPieces[i]- 1;
            colSpot =colPieces[i]- 1;
            rowBoard[rowSpot]++;
            colBoard[colSpot]++;
            downDiagnal[rowPieces[i] - colPieces[i] + (boardSize-1)]++;
            upDiagnal[rowPieces[i] + colPieces[i] - 2]++;
            // Long If bellow to test all spaces
            if (rowBoard[rowSpot] >= 2 || colBoard[colSpot] >= 2 || upDiagnal[upDiagnalSpot] >= 2 || downDiagnal[downDiagnalSpot] >=2) 
            {
                //Queen can ATTACK!!!!!!
                return false;
            }
        }
        return true;
    }

    public static double difficultyRating()
    {
        return 3.2;
    }

    public static double hoursSpent()
    {
        return 13.5;
    }

    // extra methods bellow
    public static void updatedCord(int[] row, int[] col, ArrayList<String> coordinateStrings)
    {
        // variables
        int lastLetter = 0; // last letter in string
        int numPieces = coordinateStrings.size();

        for (int i = 0; i < numPieces; i++)
        {
            // finds lastLetter
            for (int j = 0; j < 4; j++)
            {
                if(coordinateStrings.get(i).charAt(j) >= 'a' && coordinateStrings.get(i).charAt(j) <= 'z')
                {
                    lastLetter = j;
                }
                else break;
            }

            //converts col to int value along side row and update arrays in allTheQueensAreSafe function
            if (lastLetter == 3)
            {
                // four letters
                col[i] = (26 * 26 * 26 * (coordinateStrings.get(i).charAt(lastLetter - 3) - 96))
                + (26 * 26 * (coordinateStrings.get(i).charAt(lastLetter-2) - 96)) 
                + (26 * (coordinateStrings.get(i).charAt(lastLetter-1) - 96))
                + (coordinateStrings.get(i).charAt(lastLetter) - 96);
                row[i] = Integer.parseInt(coordinateStrings.get(i).substring(lastLetter + 1));
            }
            else if (lastLetter == 2)
            {
                // three letters
                col[i] = (26 * 26 * (coordinateStrings.get(i).charAt(lastLetter -2) - 96))
                + (26 * (coordinateStrings.get(i).charAt(lastLetter-1) - 96))
                + (coordinateStrings.get(i).charAt(lastLetter) - 96);
                row[i] = Integer.parseInt(coordinateStrings.get(i).substring(lastLetter + 1));
            }
            else if (lastLetter == 1)
            {
                // two letters
                col[i] = (26 * (coordinateStrings.get(i).charAt(lastLetter - 1) - 96))
                + (coordinateStrings.get(i).charAt(lastLetter) - 96);
                row[i] = Integer.parseInt(coordinateStrings.get(i).substring(lastLetter + 1));
            }
            else
            {
                // one letter
                col[i] = (coordinateStrings.get(i).charAt(lastLetter) - 96);
                row[i] = Integer.parseInt(coordinateStrings.get(i).substring(lastLetter + 1));
            }
        }
    }
}