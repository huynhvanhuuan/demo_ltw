package vn.edu.hcmuaf.fit.controller.client;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.net.URLDecoder;
import java.nio.file.Files;

import static vn.edu.hcmuaf.fit.constant.FileConstant.PRODUCT_IMAGE_FOLDER;
import static vn.edu.hcmuaf.fit.constant.FileConstant.USER_IMAGE_FOLDER;

@WebServlet(name = "image", value = "/image/*")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 100) // 100MB
public class ImageController extends HttpServlet {
    private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404
            return;
        }

        String[] pathParts = pathInfo.split("/");
        switch (pathParts[1]) {
            case "product":
                getProductImage(response, pathParts[2]);
                break;
            case "user":
                getUserImage(response, pathParts[2]);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404
                break;
        }
    }

    private void getUserImage(HttpServletResponse response, String imageName) throws IOException {
        File file = new File(USER_IMAGE_FOLDER, URLDecoder.decode(imageName, "UTF-8"));

        if (!file.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404
            return;
        }

        String contentType = getServletContext().getMimeType(file.getName());

        if (contentType == null) contentType = "application/octet-stream";

        response.reset();
        response.setBufferSize(DEFAULT_BUFFER_SIZE);
        response.setContentType(contentType);
        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

        try (BufferedInputStream input = new BufferedInputStream(Files.newInputStream(file.toPath()), DEFAULT_BUFFER_SIZE);
             BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE)){

            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
        }
    }

    private void getProductImage(HttpServletResponse response, String imageName) throws IOException {
        File file = new File(PRODUCT_IMAGE_FOLDER, URLDecoder.decode(imageName, "UTF-8"));

        if (!file.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404
            return;
        }

        String contentType = getServletContext().getMimeType(file.getName());

        if (contentType == null) contentType = "application/octet-stream";

        response.reset();
        response.setBufferSize(DEFAULT_BUFFER_SIZE);
        response.setContentType(contentType);
        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

        try (BufferedInputStream input = new BufferedInputStream(Files.newInputStream(file.toPath()), DEFAULT_BUFFER_SIZE);
             BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE)){

            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
        }
    }
}
