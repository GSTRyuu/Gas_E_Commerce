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
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import model.Product;

/**
 *
 * @author Admin
 */
@MultipartConfig
public class productStockManageController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InputStream inputStream = req.getPart("inputFile").getInputStream();
        Product p = new Product();
        // Create a scanner to read the file contents
        Scanner scanner = new Scanner(inputStream);

        // Loop through the file lines and write them to the output file
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            try {
                String[] parts = line.split(";");
                if (p.checkStockProduct(parts[0])) {
                    continue;
                }
                int id = p.getProductIDByModel(parts[1]);
                if (id == 0) {
                    continue;
                }
                p.addStockProduct(parts[0], id);
            } catch (Exception e) {
                req.setAttribute("mess", "Invalid file format");
                req.setAttribute("p", p.getProductByID(Integer.parseInt(req.getParameter("id"))));
                req.getRequestDispatcher("productStockManage.jsp").forward(req, resp);
                return;
            }
        }
        resp.sendRedirect("productManage");
    }

}
