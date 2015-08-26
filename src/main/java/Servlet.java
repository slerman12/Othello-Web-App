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

class PlayerMove {

    char[][] board;
    ArrayList<EmptySquare> emptySpaces;
    boolean valid;

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

    public boolean isValid() {

        return valid;
    }

    public void setValid(boolean valid) {

        this.valid = valid;
    }
}

@WebServlet(name = "Servlet")
public class Servlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int row = Integer.parseInt(request.getParameter("row"));
        int col = Integer.parseInt(request.getParameter("col"));
        char clr = request.getParameter("clr").charAt(0);

        Type listType = new TypeToken<char[][]>() {}.getType();
        char[][] board = new Gson().fromJson(request.getParameter("board"), listType);

        Type emptySpacesType = new TypeToken<ArrayList<EmptySquare>>() {}.getType();
        ArrayList<EmptySquare> emptySpaces = new Gson().fromJson(request.getParameter("emptySpaces"), emptySpacesType);

        PlayerMove playerMove = new PlayerMove();
        playerMove.setBoard(board);
        playerMove.setEmptySpaces(emptySpaces);

        if(GameEngine.validate(playerMove.getBoard(), clr, row, col, true)) {
            playerMove.setValid(true);
            playerMove.getBoard()[row][col] = clr;
            GameEngine.editEmptyList(new BoardState(board, emptySpaces), new EmptySquare(row, col));
        }
        else{
            playerMove.setValid(false);
        }

        String json = new Gson().toJson(playerMove);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
