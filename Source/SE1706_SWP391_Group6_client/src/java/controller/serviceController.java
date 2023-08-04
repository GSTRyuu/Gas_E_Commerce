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
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import model.Category;
import model.Order;
import model.Product;
import model.Service;
import model.ServiceItem;

/**
 *
 * @author Dell
 */
public class serviceController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        Service s = new Service();
        ServiceItem si = new ServiceItem();
        String act = req.getParameter("act");
        if (act.equals("search")) {
            String serial = req.getParameter("serial");
            s.getExpDate(serial);
            if (s.getWarrantyExp() != null) {
                String[] sub = s.getWarrantyExp().split(" ");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDateTime now = LocalDateTime.now();
                String tmp = dtf.format(now);
                int cmp = tmp.compareToIgnoreCase(sub[0]);
//                System.out.println("day: " + sub[0] + ", time: " + sub[1] + ", tmp:" + tmp);
//                System.out.println("cmp: " + cmp);
                if (cmp < 0) {
                    out.println("<p>\n"
                            + "Your product warranty will expire on "
                            + "<span style=\"color: red; font-size: larger\">" + sub[0] + "</span> at "
                            + "<span style=\"color: red; font-size: larger\">" + sub[1] + "</span></p>"
                            + "<p>All services except warranty extension are "
                            + "<span style=\"color: red\">free of charge</span>.</p>");
                } else if (cmp > 0) {
                    out.println("<p>\n"
                            + "Your product warranty has expired on "
                            + "<span style=\"color: red; font-size: larger\">" + sub[0] + "</span> at "
                            + "<span style=\"color: red; font-size: larger\">" + sub[1] + "</span></p>"
                            + "<p>All services are "
                            + "<span style=\"color: red\">of charge</span>.</p>");
                } else {
                    dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                    now = LocalDateTime.now();
                    tmp = dtf.format(now);
                    cmp = tmp.compareTo(sub[1]);
                    if (cmp < 0) {
                        out.println("<p>\n"
                                + "Your product warranty will expire "
                                + "<span style=\"color: red; font-size: larger\">today</span> at "
                                + "<span style=\"color: red; font-size: larger\">" + sub[1] + "</span></p>"
                                + "<p>All services except warranty extension are "
                                + "<span style=\"color: red\">free of charge</span>.</p>");
                    } else if (cmp > 0) {
                        out.println("<p>\n"
                                + "Your product warranty has expired "
                                + "<span style=\"color: red; font-size: larger\">today</span> at "
                                + "<span style=\"color: red; font-size: larger\">" + sub[1] + "</span></p>"
                                + "<p>All services are "
                                + "<span style=\"color: red\">of charge</span>.</p>");
                    }
                }
            } else {
                out.println("<p style=\"color: red\">*The product with provided serial cannot be found.</p>");
            }
        } else if (act.equals("show")) {
            String ofType = req.getParameter("ofType");
            s.getExpDate(ofType);
            List<Service> sList = s.getListServiceByType(ofType);
            out.println("<table cellspacing=\"0\" class=\"shop_table cart\" border=\"1\" style=\"width: 100%; height: fit-content\">\n"
                    + "<thead>\n"
                    + "<tr>\n"
                    + "<th>Name</th>\n"
                    + "<th>Description</th>\n"
                    + "<th>Option</th>\n"
                    + "</tr>\n"
                    + "</thead>\n"
                    + "<tbody>\n");
            for (Service s1 : sList) {
                out.println("<tbody>\n"
                        + "<tr>\n"
                        + "<td>" + s1.getName() + "</td>\n"
                        + "<td>" + s1.getContent() + "</td>\n"
                        + "<td>\n"
                        + "<form action=\"service\" method=\"post\">"
                        + "<input name=\"act\" value=\"request\" hidden/>"
                        + "<input name=\"requestCode\" value=\"" + s1.getCode() + "\" hidden/>"
                        + "<button style=\"width: fit-content\" type=\"submit\">Request</button>\n"
                        + "</form>"
                        + "</td>\n"
                        + "</tr>\n");
            }
            out.println("</tbody>\n"
                    + "</table>"
                    + "<button style=\"width: fit-content\" type=\"submit\" onclick=\"showLess()\">Show less</button>");
        } else if (act.equals("request")) {
            String requestCode = req.getParameter("requestCode");
            Order o = new Order();
            s.getServiceDetail(requestCode);
            req.setAttribute("service", s);
            req.setAttribute("coList", o.getListCountry());
            req.getRequestDispatcher("serviceRequest.jsp").forward(req, resp);
        } else if (act.equals("checkSerial")) {
            Locale vie = new Locale("vi", "VN");
            //Currency vnd = Currency.getInstance(vie);
            NumberFormat vndFormat = NumberFormat.getCurrencyInstance(vie);

            String input = req.getParameter("inputSerial");
            String code = req.getParameter("serviceCode");
            //s.getExpDate(input);
            Product p = si.getProductInfo(input);
            if (p.getPid() != 0) {
                s.getRequestDetail(code, input);
                out.println("<table cellspacing=\"0\" class=\"shop_table cart\">\n"
                        + "<thead>\n"
                        + "<tr>\n"
                        + "<th class=\"product-name\">Name</th>\n"
                        + "<th class=\"product-price\">Model</th>\n"
                        + "<th class=\"product-price\">Image</th>\n"
                        + "<th class=\"product-price\">Warranty Expire</th>\n"
                        + "</tr>\n"
                        + "</thead>\n"
                        + "<tbody>\n"
                        + "<tr class=\"cart_item\">\n"
                        + "<td class=\"product-name\">\n"
                        + p.getPname() + "\n"
                        + "</td>\n"
                        + "<td class=\"product-name\">\n"
                        + p.getModel() + "\n"
                        + "</td>\n"
                        + "\n"
                        + "<td>"
                        + "<img\n"
                        + "width=\"145\"\n"
                        + "height=\"145\"\n"
                        + "alt=\"poster_1_up\"\n"
                        + "class=\"shop_thumbnail\"\n"
                        + "src=\"" + p.getImg().get(0) + "\"\n"
                        + "/>\n"
                        + "</td>\n"
                        + "<td class=\"product-name\">\n"
                        + s.getWarrantyExp() + "\n"
                        + "</td>\n"
                        + "\n"
                        + "</tr>\n"
                        + "</tbody>\n"
                        + "</table>"
                        + "<h3 id=\"order_review_heading\">Service</h3>"
                        + "<table class=\"shop_table\">\n"
                        + "<tfoot>\n"
                        + "<tr class=\"cart-subtotal\">\n"
                        + "<th>" + s.getName() + "</th>\n"
                        + "<td>\n"
                        + "<span class=\"amount\">\n"
                        + vndFormat.format(s.getPrice())
                        + "</span>\n"
                        + "</td>\n"
                        + "</tr>\n"
                        + "</tfoot>\n"
                        + "</table>");
            } else {
                out.println("<p style=\"color: red; margin: 5px\">*Product serial cannot be found and you cannot proceed requesting service.</p>");
            }
        } else if (act.equals("submitRequest")) {
            String serial = req.getParameter("serial");
            String code = req.getParameter("serviceCode");
            Product p = si.getProductInfo(serial);
            if (p.getPid() != 0) {
                HttpSession session = req.getSession();
                String firstname = req.getParameter("firstname");
                String lastname = req.getParameter("lastname");
                String mobile = req.getParameter("mobile");
                String line1 = req.getParameter("line1");
                String line2 = req.getParameter("line2");
                String city = req.getParameter("city");
                String province = req.getParameter("province");
                int countryId = Integer.parseInt(req.getParameter("country"));
                String content = req.getParameter("content");
                double price = Double.parseDouble(req.getParameter("price"));
                si.requestSevice(code, serial, firstname, lastname, mobile, price, line1, line2, city, province, countryId, content);
                session.setAttribute("functionToast", "showToast('success','Resquest service successfully. We will contact to you soon.')");
                resp.sendRedirect("service");
            } else {
                Order o = new Order();
                s.getServiceDetail(code);
                req.setAttribute("deny", "deny");
                req.setAttribute("service", s);
                req.setAttribute("coList", o.getListCountry());
                req.getRequestDispatcher("serviceRequest.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Category c = new Category();
        Service s = new Service();
        req.setAttribute("cList", c.getListCategory());
        req.setAttribute("tList", s.getListType());
        req.setAttribute("sList", s.getListService());
        req.getRequestDispatcher("service.jsp").forward(req, resp);
    }
}
