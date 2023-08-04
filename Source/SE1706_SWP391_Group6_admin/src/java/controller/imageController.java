/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 *
 * @author Nam
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class imageController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String contentType = request.getContentType();
        if (contentType != null && contentType.contains("multipart/form-data")) {
            try {
                Collection<Part> parts = request.getParts();

                for (Part part : parts) {
                    String imageName = "image_" + System.currentTimeMillis() + ".jpg";
                    if (imageName != null && !imageName.isEmpty()) {
                        String uploadPath = getServletContext().getRealPath("") + "/images/News";
                        String uploadPath1 = getServletContext().getRealPath("") + "../../web/images/News";
                        String uploadPath2 = getServletContext().getRealPath("") + "../../../SE1706_SWP391_Group6_client/web/images/News";
                        String uploadPath3 = getServletContext().getRealPath("") + "../../../SE1706_SWP391_Group6_client/build/web/images/News";
                        
                        part.write(uploadPath + File.separator + imageName);
                        part.write(uploadPath1 + File.separator + imageName);
                        part.write(uploadPath2 + File.separator + imageName);
                        part.write(uploadPath3 + File.separator + imageName);
                        
                        // Trả về URL của ảnh đã tải lên
                        String imageUrl = request.getContextPath() + "/images/News/" + imageName;
                        response.setContentType("application/json");
                        response.getWriter().write("{\"location\": \"" + imageUrl + "\"}");
                        return;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
            }
        }

        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().write("{\"error\": \"No file data found\"}");
    }

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] elements = contentDisposition.split(";");

        for (String element : elements) {
            if (element.trim().startsWith("filename")) {
                return element.substring(element.indexOf('=') + 1).trim().replace("\"", "");
            }
        }

        return null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
