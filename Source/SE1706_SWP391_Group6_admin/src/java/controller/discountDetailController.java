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

/**
 *
 * @author Nam
 */
public class discountDetailController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("submit").equalsIgnoreCase("add")) {
            HttpSession s = req.getSession();
            String code = req.getParameter("code");
            String name = req.getParameter("name");
            Double amount = Double.parseDouble(req.getParameter("amount"));
            String description = req.getParameter("description");
            String type = req.getParameter("type");

            Discount d = new Discount();
            List<Discount> dis = d.getListDiscount();

            boolean checkCode = true;
            boolean checkAmount = true;
            String error = "";
            for (Discount di : dis) {
                if (di.getCode().equals(code)) {
                    checkCode = false;
                    error += "showToast('error','The code is exist!');";
                }
            }
            if (amount > 100 || amount < 0) {
                checkAmount = false;
                error += "showToast('error','The amount is out of range!');";
            }

            if (checkCode && checkAmount) {
                if (type.equalsIgnoreCase("Product")) {
                    String productModel = req.getParameter("proModel");
                    String fromDate = req.getParameter("fromDate");
                    String toDate = req.getParameter("toDate");

                    String[] models = req.getParameterValues("models[]");

                    String insideError = "";
                    boolean modelsNull = true;
                    if (models == null) {
                        modelsNull = false;
                        insideError += "showToast('info','Product models should not be empty');";
                    }

//                    String[] listModels = splitString(productModel);
//                    if (!checkUniqueElements(listModels)) {
//                        checkModel = false;
//                        error += "showToast('error','Some models are duplicates!');";
//                    }
//                    if (!d.checkgetProductModelExist(listModels)) {
//                        checkModel = false;
//                        error += "showToast('error','Models is not like the syntax or some of them are not exist!');";
//                    }
//                    int productId = d.getProductIdByProductModel(productModel);
//                    if (productId == -1) {
//                        checkModel = false;
//                        s.setAttribute("errorModel", "Product model not found!");
//                    }
                    boolean checkDate = true;
                    boolean checkExistModel = true;
                    LocalDate localDate1 = LocalDate.parse(fromDate);
                    LocalDate localDate2 = LocalDate.parse(toDate);
                    int result = localDate1.compareTo(localDate2);
                    if (result >= 0) {
                        checkDate = false;
                        insideError += "showToast('error','To must be after from!');";
                    }

                    for (String model : models) {
                        if (d.checkAddProductModelOnActivateWithDiscount(model, localDate1.toString(), localDate2.toString())) {
                            insideError += "showToast('warning','Some model(s) you choose already on discount at this period. Try another one!');";
                            checkExistModel = false;
                            break;
                        }
                    }

                    if (checkDate && modelsNull && checkExistModel) {
                        d.addDiscount(code, name, amount, description, type);
                        for (String listModel : models) {
                            int proId = d.getProductIdByProductModel(listModel);
                            d.addProductDiscount(proId, code, fromDate, toDate);
                        }
                        s.setAttribute("functionToast", "showToast('success','Add discount successfully!')");

                        //d.addProductDiscount(productId, code, fromDate, toDate);
                        resp.sendRedirect("discount");
                    } else {

                        req.setAttribute("code", code);
                        req.setAttribute("name", name);
                        req.setAttribute("amount", amount);
                        req.setAttribute("description", description);
                        req.setAttribute("proModel", productModel);
                        req.setAttribute("fromDate", fromDate);
                        req.setAttribute("toDate", toDate);
                        s.setAttribute("functionToast", insideError);

                        req.getRequestDispatcher("discountDetailManagement.jsp").forward(req, resp);
                    }
                } else {
//                int number = Integer.parseInt(req.getParameter("number"));
//                int userid = Integer.parseInt(req.getParameter("userId"));
                    d.addDiscount(code, name, amount, description, type);
//                d.addUserDiscount(userid, code, number);
                    s.setAttribute("functionToast", "showToast('success','Add discount successfully!')");
                    //req.getRequestDispatcher("discount").forward(req, resp);
                    resp.sendRedirect("discount");
                }
            } else {
                req.setAttribute("code", code);
                req.setAttribute("name", name);
                req.setAttribute("amount", amount);
                req.setAttribute("description", description);
                s.setAttribute("functionToast", error);

                req.getRequestDispatcher("discountDetailManagement.jsp").forward(req, resp);
            }
        } else if (req.getParameter("submit").equalsIgnoreCase("update")) {

            String code = req.getParameter("code");
            String name = req.getParameter("name");
            Double amount = Double.parseDouble(req.getParameter("amount"));
            String description = req.getParameter("description");

            HttpSession s = req.getSession();
            Discount d = new Discount();
            Discount thisDiscount = d.getDiscountByCode(code);
            boolean checkAmount = true;
            if (amount > 100 || amount < 0) {
                checkAmount = false;
            }
            if (checkAmount) {
                if (d.getProductDiscountByCode(code) != null) {
                    String fromDate = req.getParameter("fromDate");
                    String toDate = req.getParameter("toDate");

                    String[] models = req.getParameterValues("models[]");

                    boolean checkDate = true;
                    boolean checkExistModel = true;
                    String error = "";
                    LocalDate localDate1 = LocalDate.parse(fromDate);
                    LocalDate localDate2 = LocalDate.parse(toDate);

                    System.out.println(checkExistModel);
                    int result = localDate1.compareTo(localDate2);
                    if (result >= 0) {
                        checkDate = false;
                        error += "showToast('error','To must be after from!');";
                    }

                    boolean modelsNull = true;
                    if (models == null) {
                        modelsNull = false;
                        error += "showToast('info','Product models should not be empty');";
                    } else {
                        for (String model : models) {
                            if (d.checkUpdateProductModelOnActivateWithDiscount(model, localDate1.toString(), localDate2.toString(), code)) {
                                error += "showToast('warning','Some model(s) you choose already on active at this period. Try another one!');";
                                checkExistModel = false;
                                break;
                            }
                        }
                    }
                    String canUpdate = req.getParameter("canUpdate");
                    if (canUpdate.equals("0")) {
                        modelsNull = true;
                    }
                    if (checkDate && modelsNull && checkExistModel) {
                        if (canUpdate.equals("1")) {
                            d.deleteProductDiscount(code);
                            for (String listModel : models) {
                                int proId = d.getProductIdByProductModel(listModel);
                                d.addProductDiscount(proId, code, fromDate, toDate);
                            }
                        }
                    } else {
                        Discount updateProductDiscount = d.getProductDiscountByCode(code);
                        req.setAttribute("updateProductModel", d.getProductModelByProductId(updateProductDiscount.getProductId()));
                        List<String> updateModels = d.getListProductModelByProductDiscountCode(updateProductDiscount.getCode());
                        String listModels = "";
                        for (String updateModel : updateModels) {
                            listModels += " " + updateModel;
                        }
                        req.setAttribute("updateModels", listModels);

                        req.setAttribute("updateProductDiscount", updateProductDiscount);

                        req.setAttribute("updateFromDate", convertDateTimeFormat(updateProductDiscount.getFromDate()));
                        req.setAttribute("updateToDate", convertDateTimeFormat(updateProductDiscount.getToDate()));
                        req.setAttribute("updateDiscount", thisDiscount);
                        req.setAttribute("checkUpdate", true);

                        s.setAttribute("functionToast", error);

                        req.getRequestDispatcher("discountDetailManagement.jsp").forward(req, resp);
                        return;
                    }
                }
                d.updateDiscount(name, amount, description, code);
                s.setAttribute("functionToast", "showToast('success','Update discount successfully!')");
                resp.sendRedirect("discount");
            } else {
                if (d.getProductDiscountByCode(code) != null) {
                    Discount updateProductDiscount = d.getProductDiscountByCode(code);
                    //req.setAttribute("updateProductModel", d.getProductModelByProductId(updateProductDiscount.getProductId()));
                    req.setAttribute("updateProductModel", req.getParameter("proModel"));
                    req.setAttribute("updateProductDiscount", updateProductDiscount);
                    req.setAttribute("updateFromDate", convertDateTimeFormat(updateProductDiscount.getFromDate()));
                    req.setAttribute("updateToDate", convertDateTimeFormat(updateProductDiscount.getToDate()));
                }
                req.setAttribute("updateDiscount", thisDiscount);
                s.setAttribute("functionToast", "showToast('error','The amount is out of range!')");
                req.getRequestDispatcher("discountDetailManagement.jsp").forward(req, resp);
            }
        } else {
            resp.sendRedirect("discount");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    private String convertDateTimeFormat(String inputDateTime) {
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
