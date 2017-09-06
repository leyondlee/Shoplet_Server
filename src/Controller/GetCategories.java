package Controller;

import Model.Category;
import Model.CategoryDB;
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
public class GetCategories extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String spinner = request.getParameter("spinner");

        PrintWriter out = response.getWriter();

        CategoryDB cdb = new CategoryDB();
        ArrayList<Category> categories = cdb.getAllCategories();

        JsonObject obj = new JsonObject();

        JsonArray array = new JsonArray();
        if (!(spinner != null && spinner.equals("1"))) {
            array.add("All Categories");
        }

        for (Category c : categories) {
            String name = c.getName();

            array.add(name);
        }

        obj.add("Categories", array);

        response.setContentType("application/json");
        out.println(obj);
    }
}
