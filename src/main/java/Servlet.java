import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;

class ValidPlayerMove {

    char[][] board;
    boolean valid;

    public char[][] getBoard() {

        return board;
    }

    public void setBoard(char[][] board) {

        this.board = board;
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

        ValidPlayerMove validPlayerMove = new ValidPlayerMove();
        validPlayerMove.setBoard(board);

        if(GameEngine.validate(validPlayerMove.getBoard(), clr, row, col, true)) {
            validPlayerMove.setValid(true);
            validPlayerMove.getBoard()[row][col] = clr;
        }
        else{
            validPlayerMove.setValid(false);
        }

        String json = new Gson().toJson(validPlayerMove);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
