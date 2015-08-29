import java.util.ArrayList;

/**
 * Created by root on 8/26/15.
 */
public class GameEngine {

    public static boolean validate(char[][] board, char player, int x, int y, boolean capture) {
        boolean captured1 = false, captured2 = false, captured3 = false, captured4 = false, captured5 = false, captured6 = false, captured7 = false, captured8 = false;
        char opponent = otherPlayer(player);

        //check if space is empty and coordinates are within bounds
        if (board[x][y] == 'x') {

            //determine if adjacent piece belongs to opponent
            if (x< 6 && board[x + 1][y] == opponent)
                captured1 = capture(board, x, y, 1, player, capture); //down

            if (x<6 && y<6 && board[x + 1][y + 1] == opponent)
                captured2 = capture(board, x, y, 5, player, capture); //diagonalDownRight

            if (y<6 && board[x][y + 1] == opponent)
                captured3 = capture(board, x, y, 3, player, capture); //right

            if (x>1 && board[x - 1][y] == opponent)
                captured4 = capture(board, x, y, 0, player, capture); //up

            if (y>1 && board[x][y - 1] == opponent)
                captured5 = capture(board, x, y, 2, player, capture); //left

            if (x>1 && y>1 && board[x - 1][y - 1] == opponent)
                captured6 = capture(board, x, y, 6, player, capture); //diagonalUpLeft

            if (x>1 && y<6 && board[x - 1][y + 1] == opponent)
                captured7 = capture(board, x, y, 4, player, capture); //diagonalUpRight

            if (x<6 && y>1 && board[x + 1][y - 1] == opponent)
                captured8 = capture(board, x, y, 7, player, capture); //diagonalDownLeft

        }

        return captured1 || captured2 || captured3 || captured4 || captured5 || captured6 || captured7 || captured8;
    }

    public static boolean capture(char[][] board, int x, int y, int direction, char col, boolean capture) {

        switch (direction) {

            //up
            case 0:
                for (int i = x - 2; i >= 0; i--) {
                    if (board[i][y] == col) {
                        if (capture) {
                            for (int j = x - 1; j > i; j--) {
                                board[j][y] = col;
                            }
                        }
                        return true;
                    } else if (board[i][y] == '_' || board[i][y] == 'x')
                        return false;
                }
                break;

            //down
            case 1:
                for (int i = x + 2; i < 8; i++) {
                    if (board[i][y] == col) {
                        if (capture) {
                            for (int j = x + 1; j < i; j++) {
                                board[j][y] = col;
                            }
                        }
                        return true;
                    } else if (board[i][y] == '_' || board[i][y] == 'x')
                        return false;
                }
                break;

            //left
            case 2:
                for (int i = y - 2; i >= 0; i--) {
                    if (board[x][i] == col) {
                        if (capture) {
                            for (int j = y - 1; j > i; j--) {
                                board[x][j] = col;
                            }
                        }
                        return true;
                    } else if (board[x][i] == '_'|| board[x][i] == 'x')
                        return false;
                }
                break;

            //right
            case 3:
                for (int i = y + 2; i < 8; i++) {
                    if (board[x][i] == col) {
                        if (capture) {
                            for (int j = y + 1; j < i; j++) {
                                board[x][j] = col;
                            }
                        }
                        return true;
                    } else if (board[x][i] == '_' || board[x][i] == 'x')
                        return false;
                }
                break;

            //diagonalUpRight
            case 4:
                for (int i = x - 2, j = y + 2; i >= 0 && j < 8; i--, j++) {
                    if (board[i][j] == col) {
                        if (capture) {
                            for (int k = x - 1, l = y + 1; k > i && l < j; k--, l++) {
                                board[k][l] = col;
                            }
                        }
                        return true;
                    } else if (board[i][j] == '_'|| board[i][j] == 'x')
                        return false;
                }
                break;

            //diagonalDownRight
            case 5:
                for (int i = x + 2, j = y + 2; i < 8 && j < 8; i++, j++) {
                    if (board[i][j] == col) {
                        if (capture) {
                            for (int k = x + 1, l = y + 1; k < i && l < j; k++, l++) {
                                board[k][l] = col;
                            }
                        }
                        return true;
                    } else if (board[i][j] == '_'|| board[i][j] == 'x')
                        return false;
                }
                break;

            //diagonalUpLeft
            case 6:
                for (int i = x - 2, j = y - 2; i >= 0 && j >= 0; i--, j--) {
                    if (board[i][j] == col) {
                        if (capture) {
                            for (int k = x - 1, l = y - 1; k > i && l > j; k--, l--) {
                                board[k][l] = col;
                            }
                        }
                        return true;
                    } else if (board[i][j] == '_'|| board[i][j] == 'x')
                        return false;
                }
                break;

            //diagonalDownLeft
            case 7:
                for (int i = x + 2, j = y - 2; i < 8 && j >= 0; i++, j--) {
                    if (board[i][j] == col) {
                        if (capture) {
                            for (int k = x + 1, l = y - 1; k < i && l > j; k++, l--) {
                                board[k][l] = col;
                            }
                        }
                        return true;
                    } else if (board[i][j] == '_'|| board[i][j] == 'x')
                        return false;
                }
                break;

        }
        return false;
    }

    public static int squareValue(char[][] board, int Y, int X) {

        //Corner squares worth 100 points
        if (X == 0 && Y == 0 || X == 0 && Y == 7 || X == 7 && Y == 0 || X == 7 && Y == 7) {
            return 100;
        }

        //Spaces adjacent to corners are BAD - 1 point
        if (X == 0 && Y == 1 || X == 1 && Y == 0 || X == 1 && Y == 1  //Top left corner adjacent
                || X == 6 && Y == 0 || X == 7 && Y == 1 || X == 6 && Y == 1  //Bottom left
                || X == 0 && Y == 6 || X == 1 && Y == 7 || X == 1 && Y == 6  //Top Right
                || X == 6 && Y == 7 || X == 7 && Y == 6 || X == 6 && Y == 6) {//Bottom right

            return -5;
        }

        //Sides:
        //Top, bottom, left, right
        if (X == 0 || X == 7 || Y == 0 || Y == 7) {
            return 20; //edges second best - 20 points
        }

        //everything else is (10 - #of empty adjacent spots) points
        int score = 2; //10;
        if (board[X + 1][Y] == 'x') score--;
        if (board[X + 1][Y + 1] == 'x') score--;
        if (board[X][Y + 1] == 'x') score--;
        if (board[X - 1][Y] == 'x') score--;
        if (board[X - 1][Y - 1] == 'x') score--;
        if (board[X][Y - 1] == 'x') score--;
        if (board[X + 1][Y - 1] == 'x') score--;
        if (board[X - 1][Y + 1] == 'x') score--;

        //!!!READ!!!
        //later, consider the number of valid spaces the opponent has -- minimize those, maximize your own
        //to do this, you can use countValidSpaces() and add a parameter to this method for player color
        //!!!READ!!!

        return score;
    }

    public static int evaluateBoard(char[][] board, char player){
        int score = 0;

        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if(board[i][j] == player){
                    score += squareValue(board, i, j);
                }
                if(board[i][j] == otherPlayer(player)){
                    score -= squareValue(board, i, j);
                }
            }
        }

        return score;
    }

    public static char otherPlayer(char player){
        if(player == 'B'){
            return 'W';
        }
        else{
            return 'B';
        }
    }

    public static int bestPlacement(BoardState bs, char player, char compCol, char Alphabeta, int depth, boolean first){

        int bestX = 0;
        int bestY = 0;
        boolean hasValidMoves = false;

        //base case
        if (depth == 0){
            int eval = evaluateBoard(bs.board, player);
            if(player == compCol)
                return eval;
            else
                return -eval;
        }

        else if(bs.getEmptySpaces().size() == 0){

            //This condition is only here in case something (or the opponent), messes up
            if(first){
                System.out.println("pass");
                return 0;
            }
            return evaluateBoard(bs.board, player);
        }

        else{

            if(Alphabeta == 'a'){
                int maxscore = -1000000;
                for (int i = 0; i < bs.getEmptySpaces().size(); i++) {

                    int currX = bs.getEmptySpaces().get(i).getX();
                    int currY = bs.getEmptySpaces().get(i).getY();
                    if(validate(bs.getBoard(), player, currX, currY, false)){

                        hasValidMoves = true;
                        BoardState copy = bs.copy();
                        EmptySquare newMove = new EmptySquare(currX, currY);
                        validate(copy.getBoard(), player, currX, currY, true);
                        copy.getBoard()[currX][currY] = player;
                        editEmptyList(copy, newMove); //I dont care about or understand the adjacent square score it returns, I just need the board state to be updated

                        int recursion = bestPlacement(copy, otherPlayer(player), compCol, 'b', depth - 1, false);
                        if(recursion > maxscore){
                            maxscore = recursion;
                            bestX = currX;
                            bestY = currY;
                        }
                    }

                }

                if(first){
                    if(!hasValidMoves){
                        System.out.println("pass");
                        return 0;
                    }

                    else{
                        validate(bs.getBoard(), player, bestX, bestY, true);
                        bs.getBoard()[bestX][bestY] = player;
                        EmptySquare newMove = new EmptySquare(bestX, bestY);
                        editEmptyList(bs, newMove);

                        System.out.println(); //For readability, comment out later
                        System.out.println(bestY + " " + bestX);
                    }
                }

                return maxscore;
            }

            if(Alphabeta == 'b'){
                int minscore = 1000000;

                for (int i = 0; i < bs.getEmptySpaces().size(); i++) {
                    int currX = bs.getEmptySpaces().get(i).getX();
                    int currY = bs.getEmptySpaces().get(i).getY();
                    if(validate(bs.getBoard(), player, currX, currY, false)){
                        BoardState copy = bs.copy();
                        validate(copy.getBoard(), player, currX, currY, true);
                        copy.getBoard()[currX][currY] = player;
                        EmptySquare newMove = new EmptySquare(currX, currY);
                        editEmptyList(copy, newMove); //I don't care about or understand the adjacent square score it returns, I just need the board state to be updated

                        int recursion = bestPlacement(copy, otherPlayer(player), compCol, 'a', depth - 1, false);
                        if(recursion < minscore){
                            minscore = recursion;
                        }
                    }

                }

                return minscore;
            }

        }

        return 0;
    }

    public static boolean noValidMoves(BoardState bs, char col) {
        for (int i = 0; i < bs.getEmptySpaces().size(); i++) {
            if (validate(bs.getBoard(), col, bs.getEmptySpaces().get(i).getX(), bs.getEmptySpaces().get(i).getY(), false))
                return false;
        }
        return true;
    }

    public static int countValidMoves(BoardState bs, char col) {
        int numValidMoves = 0;
        for (int i = 0; i < bs.getEmptySpaces().size(); i++) {
            if (validate(bs.getBoard(), col, bs.getEmptySpaces().get(i).getX(), bs.getEmptySpaces().get(i).getY(), false))
                numValidMoves++;
        }
        return numValidMoves;
    }

    public static void editEmptyList(BoardState bs, EmptySquare E) {
        int x = E.getX();
        int y = E.getY();

        //Find E in ArrayList and remove
        for (int i = 0; i < bs.getEmptySpaces().size(); i++) {
            if (bs.getEmptySpaces().get(i).getX() == x && bs.getEmptySpaces().get(i).getY() == y) {
                bs.getEmptySpaces().remove(i);
                break;
            }
        }

        //Add empty spaces around E
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if ((i != x || j != y) && i >= 0 && j >= 0 && j < 8 && i < 8) {
                    if (bs.getBoard()[i][j] == '_') {
                        bs.getEmptySpaces().add(new EmptySquare(i, j));
                        bs.getBoard()[i][j] = 'x';
                    }
                }
            }
        }
    }

}

class EmptySquare{
    int X;
    int Y;

    EmptySquare(int X, int Y){
        this.X = X;
        this.Y = Y;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public EmptySquare clone(){
        return new EmptySquare(this.getX(),this.getY());
    }

}

class BoardState{
    char [][] board;
    ArrayList<EmptySquare> emptySpaces;

    public BoardState(char[][] board, ArrayList<EmptySquare> emptySpaces) {
        this.board = board;
        this.emptySpaces = emptySpaces;
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    public ArrayList<EmptySquare> getEmptySpaces() {
        return emptySpaces;
    }

    public void setEmptySpaces(ArrayList<EmptySquare> emptySpaces) {
        this.emptySpaces = emptySpaces;
    }

    public BoardState copy(){

        //more efficient 2D array deep copy (as opposed to using clone() or a double for loop)
        char [][] newBoard = new char[8][];
        for(int i = 0; i < 8; i++)
        {
            char[] row = this.getBoard()[i];
            newBoard[i] = new char[8];
            System.arraycopy(row, 0, newBoard[i], 0, 8);
        }

        //deep copy of ArrayList
        ArrayList<EmptySquare> newEmptySpaces = new ArrayList<EmptySquare>();
        for(EmptySquare i : this.getEmptySpaces()) {
            newEmptySpaces.add(i.clone());
        }

        //return new board state
        BoardState newBoardState = new BoardState(newBoard, newEmptySpaces);
        return newBoardState;
    }
}
