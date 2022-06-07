package vn.edu.hcmuaf.fit.controller.client;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "product", value = "/product")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class ProductController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/client/product.jsp").forward(request, response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
        // Get part
        // Part part = request.getPart("image");
        // Get filename
        //String filename = part.getSubmittedFileName();
        // Setup path
        //String path = getFolderUpload().getAbsolutePath() + File.separator + System.currentTimeMillis() + "-" + filename;
        // Write file
        //part.write(path);
    }

    public File getFolderUpload() {
        File folderUpload = new File(System.getProperty("user.home") + "/Furniture Selling/images/product");
        if (!folderUpload.exists()) {
            if (folderUpload.mkdirs()) return folderUpload;
        }
        return folderUpload;
    }
}
