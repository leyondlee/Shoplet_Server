package Controller;

import Model.Category;
import Model.CategoryDB;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Leyond on 28/1/2017.
 */
public class GetCategoryImage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("category");

        byte[] bytes = null;
        if (name != null) {
            CategoryDB cdb = new CategoryDB();
            Category category = cdb.getCategory(name);
            if (category != null) {
                bytes = category.getImage();
                if (bytes == null) {
                    BufferedInputStream bis = new BufferedInputStream(getServletContext().getResourceAsStream("/images/category_placeholder.png"));
                    bytes = IOUtils.toByteArray(bis);
                    bis.close();
                }
            }
        } else {
            BufferedInputStream bis = new BufferedInputStream(getServletContext().getResourceAsStream("/images/store.png"));
            bytes = IOUtils.toByteArray(bis);
            bis.close();
        }

        if (bytes != null) {
            response.setContentType("image/png");
            OutputStream os = response.getOutputStream();
            os.write(bytes);
            os.flush();
            os.close();
        }
    }
}
