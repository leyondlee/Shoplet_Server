package Controller;

import Model.User;
import Model.UserDB;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Leyond on 28/1/2017.
 */
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean login = false;

        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDB udb = new UserDB();
        User user = udb.getUser(username,password);
        if (user != null) {
            login = true;
        }

        JsonObject obj = new JsonObject();
        obj.addProperty("Success", login);

        response.setContentType("application/json");
        out.println(obj);
    }
}
