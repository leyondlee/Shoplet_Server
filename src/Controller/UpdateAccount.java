package Controller;

import Model.User;
import Model.UserDB;
import Util.Hash;
import Util.util;
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
public class UpdateAccount extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String currentpassword = request.getParameter("currentpassword");
        String newpassword = request.getParameter("newpassword");
        String confirmnewpassword = request.getParameter("confirmnewpassword");

        int code = -1;

        UserDB udb = new UserDB();
        User user = udb.getUser(username,password);
        if (user != null) {
            if (user.getPassword().equals(util.hash(currentpassword,user.getSalt()))) {
                if (!newpassword.isEmpty()) {
                    if (newpassword.equals(confirmnewpassword)) {
                        byte[] salt = Hash.getSalt();
                        user.setPassword(util.hash(newpassword, salt));
                        user.setSalt(salt);

                        boolean result = udb.updateUser(user);
                        if (result) {
                            code = 0;
                        }
                    } else {
                        code = 1;
                    }
                } else {
                    code = 2;
                }
            } else {
                code = 3;
            }
        }

        JsonObject obj = new JsonObject();
        obj.addProperty("Code", code);

        response.setContentType("application/json");
        out.println(obj);
    }
}
