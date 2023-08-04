package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.Constants;
import model.User;
import model.UserGoogleDto;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author fpt
 */
public class loginGGController extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        String accessToken = getToken(code);
        UserGoogleDto user = getUserInfo(accessToken);
        User u = new User();
        HttpSession session = req.getSession();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        if (!u.checkExistGmailUser(user.getId())) {
            u.signUp(user.getGiven_name(), "", user.getEmail(), "", user.getId(), dateFormat.format(date));
            String ten = user.getGiven_name();
            User n = u.getLogin("", user.getId());
            u.updateLastLogin(dateFormat.format(date), n.getId());
            u.updateLastLogOut(null, n.getId());
            session.setAttribute("acc", u.getUserById(n.getId()));
            session.setAttribute("ten", ten);
            
        } else {
            String ten = user.getGiven_name();
            User n = u.getLoginGmail(user.getId());
            u.updateLastLogin(dateFormat.format(date), n.getId());
            u.updateLastLogOut(null, n.getId());
            session.setAttribute("acc", u.getUserById(n.getId()));
            session.setAttribute("ten", ten);
            
        }
        session.setAttribute("checkGG", "testGG");
        resp.sendRedirect("home");
    }
    
    public static String getToken(String code) throws ClientProtocolException, IOException {
        // call api to get token
        String response = Request.Post(Constants.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", Constants.GOOGLE_CLIENT_ID)
                        .add("client_secret", Constants.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", Constants.GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", Constants.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();
        
        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }
    
    public static UserGoogleDto getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        
        UserGoogleDto googlePojo = new Gson().fromJson(response, UserGoogleDto.class);
        
        return googlePojo;
    }
    
}
