package Controller;

import Model.Shop;
import Model.ShopDB;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Leyond on 28/1/2017.
 */
public class Shops extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String c = request.getParameter("category");

        ShopDB sdb = new ShopDB();
        ArrayList<Shop> shops = sdb.getShops(c);

        JsonObject obj = new JsonObject();
        for (Shop s : shops) {
            JsonArray array = new JsonArray();
            String name = s.getName();
            String contact = s.getContact();
            String postalcode = s.getPostalcode();
            String unitno = s.getUnitno();
            String category = s.getCategory();
            String description = s.getDescription();

            if (description == null) {
                description = "";
            }

            array.add(contact);
            array.add(postalcode);
            array.add(unitno);
            array.add(category);
            array.add(description);

            obj.add(name, array);
        }

        response.setContentType("application/json");
        out.println(obj);
    }
}
