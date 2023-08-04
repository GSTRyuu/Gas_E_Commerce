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
 * @author Admin
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class productImageController extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contentType = req.getContentType();
        if (contentType != null && contentType.contains("multipart/form-data")) {
            try {
                Collection<Part> parts = req.getParts();

                for (Part part : parts) {
                    String imageName = "image_" + System.currentTimeMillis() + ".jpg";
                    if (imageName != null && !imageName.isEmpty()) {
                        String uploadPath = getServletContext().getRealPath("") + "/images/Products";
                        String uploadPath1 = getServletContext().getRealPath("") + "../../web/images/Products";
                        String uploadPath2 = getServletContext().getRealPath("") + "../../../SE1706_SWP391_Group6_client/web/images/Products";
                        String uploadPath3 = getServletContext().getRealPath("") + "../../../SE1706_SWP391_Group6_client/build/web/images/Products";
                        
                        part.write(uploadPath + File.separator + imageName);
                        part.write(uploadPath1 + File.separator + imageName);
                        part.write(uploadPath2 + File.separator + imageName);
                        part.write(uploadPath3 + File.separator + imageName);
                        
                        // Trả về URL của ảnh đã tải lên
                        String imageUrl = req.getContextPath() + "/images/Products/" + imageName;
                        resp.setContentType("application/json");
                        resp.getWriter().write("{\"location\": \"" + imageUrl + "\"}");
                        return;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
            }
        }

        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        resp.getWriter().write("{\"error\": \"No file data found\"}"); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    public static String extractImageSrc(String html) {
        String src = null;
        int startIndex = html.indexOf("src=\"");
        if (startIndex != -1) {
            startIndex += "src=\"".length();
            int endIndex = html.indexOf("\"", startIndex);
            if (endIndex != -1) {
                src = html.substring(startIndex, endIndex);
            }
        }
        return src;
    }
}
