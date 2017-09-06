package Controller;

import Model.Shop;
import Model.ShopDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Leyond on 28/1/2017.
 */
public class GetShopImage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");

        ShopDB sdb = new ShopDB();
        Shop shop = sdb.getShop(name);
        if (shop != null) {
            byte[] bytes = shop.getImage();
            if (bytes != null) {
                response.setContentType("image/png");
                OutputStream os = response.getOutputStream();
                os.write(bytes);
                os.flush();
                os.close();
            }
        }
    }
}
