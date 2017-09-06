package Controller;

import Model.CategoryDB;
import Model.ShopDB;
import Model.User;
import Model.UserDB;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;

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
public class AddShop extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String contact = request.getParameter("contact");
        String postalcode = request.getParameter("postalcode");
        String unitno = request.getParameter("unitno");
        String category = request.getParameter("category");
        String description = request.getParameter("description");
        String encodedimage = request.getParameter("image");

        if (encodedimage.equals("")) {
            encodedimage = null;
        }

        int code = -1;

        UserDB udb = new UserDB();
        User user = udb.getUser(username,password);
        if (user != null) {
            if (contact.length() == 8 && StringUtils.isNumeric(contact)) {
                if (postalcode.length() > 0 && postalcode.length() <= 6 && StringUtils.isNumeric(postalcode)) {
                    if (unitno.length() > 5 && unitno.length() <= 7) {
                        if (name.length() > 0 && (new CategoryDB()).getCategory(category) != null) {
                            ShopDB sdb = new ShopDB();
                            if (sdb.getShop(name) == null) {
                                boolean result = sdb.addShop(name, contact, postalcode, unitno, category, description, encodedimage);

                                if (result) {
                                    code = 0;
                                } else {
                                    code = 1;
                                }
                            }
                        }
                    }
                }
            }
        }

        JsonObject obj = new JsonObject();
        obj.addProperty("Code", code);

        response.setContentType("application/json");
        out.println(obj);
    }
}
