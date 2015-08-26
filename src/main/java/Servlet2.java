import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

class ValidPlayerMove {

    char[][] board;
    ArrayList<EmptySquare> emptySpaces;
    int row;
    int col;

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

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}

@WebServlet(name = "Servlet2")
public class Servlet2 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int depthLimit = 1;

        char clr = request.getParameter("clr").charAt(0);

        Type boardType = new TypeToken<char[][]>() {}.getType();
        char[][] board = new Gson().fromJson(request.getParameter("board"), boardType);

        Type emptySpacesType = new TypeToken<ArrayList<EmptySquare>>() {}.getType();
        ArrayList<EmptySquare> emptySpaces = new Gson().fromJson(request.getParameter("emptySpaces"), emptySpacesType);

        BoardState bs = new BoardState(board, emptySpaces);

        GameEngine.bestPlacement(bs, GameEngine.otherPlayer(clr), GameEngine.otherPlayer(clr), 'a', depthLimit, true);

        ValidPlayerMove validPlayerMove = new ValidPlayerMove();
        validPlayerMove.setBoard(bs.getBoard());
        validPlayerMove.setEmptySpaces(bs.getEmptySpaces());

        String json = new Gson().toJson(validPlayerMove);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
