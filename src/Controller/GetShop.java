package Controller;

import Model.Shop;
import Model.ShopDB;
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
public class GetShop extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String n = request.getParameter("name");

        ShopDB sdb = new ShopDB();
        Shop shop = sdb.getShop(n);

        if (shop != null) {
            String name = shop.getName();
            String contact = shop.getContact();
            String postalcode = shop.getPostalcode();
            String unitno = shop.getUnitno();
            String category = shop.getCategory();
            String description = shop.getDescription();

            if (description == null) {
                description = "";
            }

            JsonObject obj = new JsonObject();
            obj.addProperty("Name", name);
            obj.addProperty("Contact", contact);
            obj.addProperty("Postalcode", postalcode);
            obj.addProperty("Unitno", unitno);
            obj.addProperty("Category", category);
            obj.addProperty("Description", description);

            response.setContentType("application/json");
            out.println(obj);
        }
    }
}
