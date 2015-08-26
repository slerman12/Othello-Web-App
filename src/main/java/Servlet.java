import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class ComputerMove {

    String clr;
    int row;
    int col;

    public String getClr() {
        return clr;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setClr(String clr) {
        this.clr = clr;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }
}

class ValidPlayerMove {

    boolean valid;
    String clr;
    int row;
    int col;

    public boolean isValid() {
        return valid;
    }

    public String getClr() {
        return clr;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public void setClr(String clr) {
        this.clr = clr;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }
}

@WebServlet(name = "Servlet")
public class Servlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rowOriginal = request.getParameter("row");
        int rowOrig = Integer.parseInt(rowOriginal);

        String colOriginal = request.getParameter("col");
        int colOrig = Integer.parseInt(colOriginal);

        ValidPlayerMove validPlayerMove = new ValidPlayerMove();
        validPlayerMove.setClr("B");
        validPlayerMove.setCol(colOrig);
        validPlayerMove.setRow(rowOrig);
        validPlayerMove.setValid(true);

        String json = new Gson().toJson(validPlayerMove);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> list = new ArrayList<String>();
        list.add("item1");
        list.add("item2");
        list.add("item3");
        String json = new Gson().toJson(list);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
