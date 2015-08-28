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

class SkipTurn {

    boolean valid;
    
    public boolean isValid() {

        return valid;
    }

    public void setValid(boolean valid) {

        this.valid = valid;
    }
}

@WebServlet(name = "Servlet3")
public class Servlet3 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        char clr = request.getParameter("clr").charAt(0);

        Type listType = new TypeToken<char[][]>() {}.getType();
        char[][] board = new Gson().fromJson(request.getParameter("board"), listType);

        Type emptySpacesType = new TypeToken<ArrayList<EmptySquare>>() {}.getType();
        ArrayList<EmptySquare> emptySpaces = new Gson().fromJson(request.getParameter("emptySpaces"), emptySpacesType);

        SkipTurn skipTurn = new SkipTurn();

        skipTurn.setValid(GameEngine.noValidMoves(new BoardState(board, emptySpaces), clr));

        String json = new Gson().toJson(skipTurn);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
