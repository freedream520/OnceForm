package wangs;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by WangS on 2017/2/26.
 */
public class DoFormServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String username = request.getParameter("username");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean b = isTokenValid(request);
        if (!b) {
            System.out.println("请不要重复提交表单");
            return;
        }

        request.getSession().removeAttribute("token");

        System.out.println("向数据库中注册用户：" + username);
    }

    private boolean isTokenValid(HttpServletRequest request) {
        String client_token = request.getParameter("token");
        if (client_token == null)
            return false;

        String server_token = (String) request.getSession().getAttribute("token");
        if (server_token == null)
            return false;

        if (!client_token.equals(server_token))
            return false;


        return true;
    }
}

