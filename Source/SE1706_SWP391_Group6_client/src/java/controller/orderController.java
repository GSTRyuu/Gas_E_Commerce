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
import model.Category;
import model.News;
import model.OrderItem;
import model.Order;
import model.Product;
import model.User;

/**
 *
 * @author Dell
 */
public class orderController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String action = req.getParameter("action");
        if (session.getAttribute("acc") == null) {
            if (action.equals("add")) {
                session.setAttribute("functionToast", "showToast('warning','Please sign in to add item to cart.')");
            }
            resp.sendRedirect("signIn.jsp");
        } else {
            User u = (User) session.getAttribute("acc");
            Product p = new Product();
            OrderItem oi = new OrderItem();
            Order o = new Order();
            if (action.equals("apply")) {
                boolean bool = o.applyDiscount(Integer.parseInt(req.getParameter("applyId")), req.getParameter("code"));
                if (!bool) {
                    req.setAttribute("wrong", "wrong");
                }
                o.getCheckoutDetail(u.getId());
                req.setAttribute("coList", o.getListCountry());
                req.setAttribute("ckDetail", o);
                req.setAttribute("aList", o.getAddressList(u.getId()));
                req.setAttribute("oiList", oi.getListOrderItem(o.getOrderId()));
                req.getRequestDispatcher("checkout.jsp").forward(req, resp);
            } else if (action.equals("updateCountry")) {
                int countryId = Integer.parseInt(req.getParameter("country"));
                int orderId = Integer.parseInt(req.getParameter("orderId"));
                o.updateTotal(orderId, countryId);
                o.getCheckoutDetail(u.getId());
                req.setAttribute("coList", o.getListCountry());
                req.setAttribute("ckDetail", o);
                req.setAttribute("aList", o.getAddressList(u.getId()));
                req.setAttribute("oiList", oi.getListOrderItem(o.getOrderId()));
                req.getRequestDispatcher("checkout.jsp").forward(req, resp);
            } else if (action.equals("updateRecent")) {
                int orderId = Integer.parseInt(req.getParameter("orderId"));
                if (req.getParameter("recentAddress") != null) {
                    String recentAddress = req.getParameter("recentAddress");
                    o.updateRecent(orderId, recentAddress);
                }
                o.getCheckoutDetail(u.getId());
                req.setAttribute("coList", o.getListCountry());
                req.setAttribute("ckDetail", o);
                req.setAttribute("aList", o.getAddressList(u.getId()));
                req.setAttribute("oiList", oi.getListOrderItem(o.getOrderId()));
                req.getRequestDispatcher("checkout.jsp").forward(req, resp);
            } else if (action.equals("checkout")) {
                if (u.getMobile().length() == 0) {
                    session.setAttribute("functionToast", "showToast('warning','Please provide mobile phone to checkout.')");
                    resp.sendRedirect("userDetail.jsp");
                } else {
                    if (req.getParameterValues("cartIds[]") == null) {
                        req.setAttribute("missing", "missing");
                        session.setAttribute("functionToast", "showToast('warning','No items selected.')");
                        resp.sendRedirect("order/cart");
                    } else {
                        String[] cartIds = req.getParameterValues("cartIds[]");
                        if (!o.checkout(u.getId(), cartIds)) {
                            resp.sendRedirect("order/cart");
                        } else {
                            o.getCheckoutDetail(u.getId());
                            req.setAttribute("coList", o.getListCountry());
                            req.setAttribute("ckDetail", o);
                            req.setAttribute("aList", o.getAddressList(u.getId()));
                            req.setAttribute("oiList", oi.getListOrderItem(o.getOrderId()));
                            req.getRequestDispatcher("checkout.jsp").forward(req, resp);
                        }
                    }
                }
            } else if (action.equals("delete")) {
                String[] cartIds = req.getParameterValues("cartIds[]");
                if (cartIds != null) {
                    oi.deleteOrderItems(cartIds);
                } else {
                    session.setAttribute("functionToast", "showToast('warning','No items selected.')");
                }
                resp.sendRedirect("order/cart");
            } else if (action.equals("add")) {
                int proId = Integer.parseInt(req.getParameter("proId"));
                int qty = Integer.parseInt(req.getParameter("qty"));
                oi.addOrderItem(u.getId(), proId, qty);
                resp.sendRedirect("order/cart");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String act, vnpayParams;
        int tmp = 0;
        if (!req.getPathInfo().contains("&")) {
            act = req.getPathInfo().substring(1);
        } else {
            String[] params = req.getPathInfo().substring(1).split("&");
            act = params[0];
            if (!params[1].contains("&")) {
                tmp = Integer.parseInt(params[1]);
            } else {
                vnpayParams = params[1];
            }
        }
        if (session.getAttribute("acc") == null) {
            if (act.equals("add")) {
                session.setAttribute("functionToast", "showToast('warning','Please sign in to add item to cart.')");
            }
            if (act.equals("cart")) {
                session.setAttribute("functionToast", "showToast('warning','Please sign in to open cart.')");
            }
            if (act.equals("history")) {
                session.setAttribute("functionToast", "showToast('warning','Please sign in to open purchase history.')");
            }
            resp.sendRedirect("http://localhost:9999/SE1706_SWP391_Group6_client/signIn.jsp");
        } else {
            Product p = new Product();
            OrderItem oi = new OrderItem();
            Order o = new Order();
            Category c = new Category();
            News n = new News();
            User u = (User) session.getAttribute("acc");
            if (act.equals("drop") || act.equals("add") || act.equals("remove") || act.equals("cart")) {
                if (act.equals("drop")) {
                    o.dropOrder(u.getId());
                } else if (act.equals("add")) {
                    oi.addOrderItem(u.getId(), tmp, 1);
                } else if (act.equals("remove")) {
                    oi.deleteSingleOrderItem(tmp);
                }
                o.getCart(u.getId());
                req.setAttribute("defendNews", n.getListNews());
                req.setAttribute("cList", c.getListCategory());
                req.setAttribute("oiList", oi.getListOrderItem(o.getOrderId()));
                req.setAttribute("pList", p.getListProduct());
                req.getRequestDispatcher("../cart.jsp").forward(req, resp);
            } else if (act.equals("history")) {
                req.setAttribute("defendNews", n.getListNews());
                req.setAttribute("cList", c.getListCategory());
                req.setAttribute("pList", p.getListProduct());
                req.setAttribute("oList", o.getListOrder(u.getId()));
                req.getRequestDispatcher("../orderHistory.jsp").forward(req, resp);
            } else if (act.equals("detail")) {
                //int orderId = Integer.parseInt(req.getParameter("orderId"));
                o.getOrderDetail(tmp);
                req.setAttribute("o", o);
                req.setAttribute("oiList", oi.getListOrderItem(tmp));
                req.getRequestDispatcher("../orderDetail.jsp").forward(req, resp);
            } else if (act.equals("cancel")) {
                //int orderId = Integer.parseInt(req.getParameter("orderId"));
                o.cancelOrder(tmp);
                req.setAttribute("defendNews", n.getListNews());
                req.setAttribute("cList", c.getListCategory());
                req.setAttribute("pList", p.getListProduct());
                req.setAttribute("oList", o.getListOrder(u.getId()));
                req.getRequestDispatcher("../orderHistory.jsp").forward(req, resp);
            } else if (act.equals("placeOrder")) {
                int orderId = Integer.parseInt(session.getAttribute("orderId").toString());
                String firstname = session.getAttribute("firstname").toString();
                String lastname = session.getAttribute("lastname").toString();
                String mobile = session.getAttribute("mobile").toString();
                String line1 = session.getAttribute("line1").toString();
                String line2 = session.getAttribute("line2").toString();
                String city = session.getAttribute("city").toString();
                String province = session.getAttribute("province").toString();
                String content = session.getAttribute("content").toString();
                String paymentMethod = session.getAttribute("paymentMethod").toString();
                o.placeOrder(orderId, firstname, lastname, mobile, line1, line2, city, province, content, paymentMethod);
                req.setAttribute("defendNews", n.getListNews());
                req.setAttribute("cList", c.getListCategory());
                req.setAttribute("pList", p.getListProduct());
                req.setAttribute("oList", o.getListOrder(u.getId()));
                req.getRequestDispatcher("../orderHistory.jsp").forward(req, resp);
            } else if (act.equals("placeOrderByVnpay")) {
                String transactionStatus = req.getParameter("vnp_TransactionStatus");
                if (transactionStatus.equals("00")) {
                    int orderId = Integer.parseInt(session.getAttribute("orderId").toString());
                    String firstname = session.getAttribute("firstname").toString();
                    String lastname = session.getAttribute("lastname").toString();
                    String mobile = session.getAttribute("mobile").toString();
                    String line1 = session.getAttribute("line1").toString();
                    String line2 = session.getAttribute("line2").toString();
                    String city = session.getAttribute("city").toString();
                    String province = session.getAttribute("province").toString();
                    String content = session.getAttribute("content").toString();
                    String paymentMethod = session.getAttribute("paymentMethod").toString();
                    o.placeOrder(orderId, firstname, lastname, mobile, line1, line2, city, province, content, paymentMethod);
                    req.setAttribute("defendNews", n.getListNews());
                    req.setAttribute("cList", c.getListCategory());
                    req.setAttribute("pList", p.getListProduct());
                    req.setAttribute("oList", o.getListOrder(u.getId()));
                    req.getRequestDispatcher("../orderHistory.jsp").forward(req, resp);
                } else {
                    o.dropOrder(u.getId());
                    o.getCart(u.getId());
                    req.setAttribute("defendNews", n.getListNews());
                    req.setAttribute("cList", c.getListCategory());
                    req.setAttribute("oiList", oi.getListOrderItem(o.getOrderId()));
                    req.setAttribute("pList", p.getListProduct());
                    req.getRequestDispatcher("../cart.jsp").forward(req, resp);
                }
            }
        }
    }
}
