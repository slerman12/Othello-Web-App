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

    public static char otherPlayer(char player){
        if(player == 'B'){
            return 'W';
        }
        else{
            return 'B';
        }
    }

}
