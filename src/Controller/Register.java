package Controller;

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
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean added = false;

        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmpassword = request.getParameter("confirmpassword");

        int code = -1;
        if (!username.isEmpty() && !password.isEmpty()) {
            if (password.equals(confirmpassword)) {
                //Generate salt and hash password
                byte[] salt = Hash.getSalt();
                String hpass = util.asHex(Hash.hashPassword(password.toCharArray(), salt, 1000, 256));

                UserDB udb = new UserDB();
                if (udb.getUser(username,null) == null) { //Check if user exists
                    //Add user to database
                    added = udb.addUser(username, hpass, util.asHex(salt));
                } else {
                    code = 1;
                }

                if (added) {
                    code = 0;
                }
            } else {
                code = 2;
            }
        } else {
            code = 3;
        }

        JsonObject obj = new JsonObject();
        obj.addProperty("Code", code);

        response.setContentType("application/json");
        out.println(obj);
    }
}
