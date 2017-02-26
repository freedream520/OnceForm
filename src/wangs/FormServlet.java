package wangs;

import sun.misc.BASE64Encoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * Created by WangS on 2017/2/26.
 * 产生表单
 */
@WebServlet(name = "FormServlet")
public class FormServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //产生随机数（表单号）
        TokenProcessor tp = TokenProcessor.getInstance();
        String token = tp.generateToken();

        request.getSession().setAttribute("token", token);

        request.getRequestDispatcher("form.jsp").forward(request, response);
    }
}

class TokenProcessor {  //令牌
    private TokenProcessor() {

    }

    private static final TokenProcessor instance = new TokenProcessor();

    public static TokenProcessor getInstance() {
        return instance;
    }

    public String generateToken() {
        String token = System.currentTimeMillis() + new Random().nextInt() + "";

        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] md5 = digest.digest(token.getBytes());

            //base64编码
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(md5);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
