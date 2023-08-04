/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import model.Discount;
import model.Product;

/**
 *
 * @author Nam
 */
public class discountUpdateController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("updateDiscountCode");
        if (code != null) {
            Discount d = new Discount();
            Discount updateDiscount = d.getDiscountByCode(code);

            if (d.getProductDiscountByCode(code) != null) {
                Discount updateProductDiscount = d.getProductDiscountByCode(code);
                req.setAttribute("updateFromDate", convertDateTimeFormat(updateProductDiscount.getFromDate()));
                req.setAttribute("updateToDate", convertDateTimeFormat(updateProductDiscount.getToDate()));
                req.setAttribute("updateProductDiscount", updateProductDiscount);

                String models = "";
                List<Integer> ids = d.getListProductIdByDiscountCode(code);
                for (Integer id : ids) {
                    String tmpMod = d.getProductModelByProductId(id);
                    models += " " + tmpMod;
                }          
                req.setAttribute("updateModels", models);
                boolean checkUpdate = true;
                boolean checkUpdateTo = true;
                LocalDate localDate1 = LocalDate.parse(convertDateTimeFormat(updateProductDiscount.getFromDate()));
                LocalDate localDate2 = LocalDate.parse(convertDateTimeFormat(updateProductDiscount.getToDate()));

                LocalDate currentDate = LocalDate.now();
                int result = localDate1.compareTo(currentDate);
                int result1 = localDate2.compareTo(currentDate);

                if (result <= 0) {
                    checkUpdate = false;
                }

                if (result1 <= 0) {
                    checkUpdateTo = false;
                }
                req.setAttribute("checkUpdate", checkUpdate);
                req.setAttribute("checkUpdateTo", checkUpdateTo);

            } else {
                req.setAttribute("checkUpdate", false);
            }
            req.setAttribute("updateDiscount", updateDiscount);
        }
        Product p = new Product();
        List<Product> ps =  p.getListProduct();
        HttpSession s = req.getSession();
        s.setAttribute("products", ps);
        req.getRequestDispatcher("discountDetailManagement.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //delete discount here
        HttpSession s = req.getSession();
        String code = req.getParameter("discountCode");
        Discount d = new Discount();
        Discount selectDiscount = d.getDiscountByCode(code);
        String type = selectDiscount.getType();
        if (type.equalsIgnoreCase("User")) {
            s.setAttribute("functionToast", "showToast('error','User discount can not be deleted!')");
            resp.sendRedirect("discount");
        }
        if (type.equalsIgnoreCase("Product")) {
            LocalDate localDate1 = LocalDate.parse(convertDateTimeFormat(d.getProductDiscountByCode(code).getFromDate()));
            LocalDate currentDate = LocalDate.now();
            int result = localDate1.compareTo(currentDate);

            if (result <= 0) {
                s.setAttribute("functionToast", "showToast('error','This product discount has been in use! (Can not delete)')");
                resp.sendRedirect("discount");
            } else {
                d.deleteProductDiscount(code);
                d.deleteDiscount(code);
                s.setAttribute("functionToast", "showToast('success','Delete discount successfully!')");
                resp.sendRedirect("discount");
            }
        }
//        else if (type.equalsIgnoreCase("User")) {
//            d.deleteUserDiscount(code);
//        }
    }

//    public static void main(String[] args) {
//
//        Discount d = new Discount();
//        Discount selectDiscount = d.getDiscountByCode("ICT18362342");
//        System.out.println(selectDiscount.getFromDate());
//    }
    private static String convertDateTimeFormat(String inputDateTime) {
        if (inputDateTime == null) {
            return null;
        } else {
            DateFormat inputFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
            DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                java.util.Date date = inputFormat.parse(inputDateTime);
                return outputFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
