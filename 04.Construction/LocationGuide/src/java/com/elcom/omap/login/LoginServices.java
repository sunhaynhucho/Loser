/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.omap.login;

import com.elcom.omap.common.Constant;
import com.elcom.omap.common.Md5;
import com.elcom.omap.common.OmapHistoryLogic;
import com.elcom.omap.common.OmapSessionUtils;
import com.elcom.omap.quantrihethong.QuanTriHeThongDataLogic;
import com.elcom.omap.util.OmapUtils;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author KiemPQ-PC
 */
public class LoginServices extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(Constant.LOGGER_NAME);

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String options = "";
            if (request.getParameter("options") != null) {
                options = request.getParameter("options");
            }

            if ("Login".equals(options)) {
                String userName = request.getParameter("userName");
                String passWord = request.getParameter("password");
                logger.info("Dang nhap voi ten dang nhap " + userName);
                if (userName == null || passWord == null) {
                    JSONObject jObj = new JSONObject();
                    jObj.put("code", "1");
                    jObj.put("detail", "Sai Tài khoản/Mật khẩu. Bạn hãy thử lại!");
                    out.println(jObj);
                    out.flush();
                } else {
                    OmapUser user = LoginDAO.getUserByUserName(userName);
                    if (user == null) {
                        if (userName.equals("admin")) {
                            QuanTriHeThongDataLogic.addUser("admin", Md5.getMd5Digest("omapadmin"), "Phạm Quang Kiếm", "kiempq@gmail.com", "127.0.0.1", "0988783880", "Đông Anh - Hà Nội", "0","0","");
                        }
                        JSONObject jObj = new JSONObject();
                        jObj.put("code", "1");
                        jObj.put("detail", "Tên đăng nhập không tồn tại. Bạn hãy thử lại!");
                        out.println(jObj);
                        out.flush();
                        return;
                    }
                    if (user.getUserPass().equals(passWord)) {
                        OmapHistoryLogic.insertUserHistory(Constant.ACTION_LOGIN, "0", userName, "Đăng nhập", OmapUtils.getClientIpAddr(request));
                        OmapSessionUtils.setOmapUserUserName(request, user);
                        JSONObject jObj = new JSONObject();
                        jObj.put("code", "0");
                        jObj.put("detail", "");
                        out.println(jObj);
                        out.flush();
                        logger.info("Dang nhap thanh cong voi ten dang nhap " + userName);
                    } else {
                        JSONObject jObj = new JSONObject();
                        jObj.put("code", "1");
                        jObj.put("detail", "Sai Tài khoản/Mật khẩu. Bạn hãy thử lại!");
                        out.println(jObj);
                        out.flush();
                        logger.info("Dang nhap sai ten danh nhap hoac mat khau voi ten dang nhap " + userName);
                    }

                }
            } else if ("EditUser".equals(options)) {
                OmapUser omapUser = OmapSessionUtils.getOmapUserUserName(request);
                if (omapUser == null) {
                    OmapUtils.showErrSessionTimeOut(out);
                    return;
                }
                String userId = request.getParameter("userid");
                String fullName = request.getParameter("fullname");
                String tenDangNhap = request.getParameter("username");
                String imageUrl = request.getParameter("imageurl");
                String phoneNumber = request.getParameter("phonenumber");
                String email = request.getParameter("email");
                String ipaddress = request.getParameter("ipaddress");
                String address = request.getParameter("address");
                String checkip = request.getParameter("checkip");
                String password = request.getParameter("password");
//                if (password != null && password.equals("") == false) {
//                    password = Md5.getMd5Digest(password);
//                }
               
                String passwordNew = request.getParameter("passwordnew");
//                if (passwordNew != null && passwordNew != "") {
//                    passwordNew = Md5.getMd5Digest(passwordNew);
//                }

                if (!password.equals(omapUser.getUserPass())) {
                    logger.info(tenDangNhap + "==> Sua nguoi dung khong thanh cong. Ten dang nhap khong dung");
                    JSONObject jObj = new JSONObject();
                    jObj.put("code", "1");
                    jObj.put("detail", "Sửa người dùng không thành công. Mật khẩu cũ không đúng");
                    out.print(jObj);
                    out.flush();
                    return;
                }
                boolean result = QuanTriHeThongDataLogic.editUserImage(userId, userId, passwordNew, fullName, email, ipaddress, phoneNumber, address, checkip, imageUrl);
                if (result) {
                    logger.info(tenDangNhap + "==> Sua nguoi dung thanh cong.");
                    OmapHistoryLogic.insertUserHistory(Constant.ACTION_EDITUSER, "0", tenDangNhap, "Sửa user", OmapUtils.getClientIpAddr(request));
                    JSONObject jObj = new JSONObject();
                    jObj.put("code", "0");
                    jObj.put("detail", "Sửa người dùng thành công");
                    out.print(jObj);
                    out.flush();
                } else {
                    OmapHistoryLogic.insertUserHistory(Constant.ACTION_EDITUSER, "1", tenDangNhap, "Sửa user", OmapUtils.getClientIpAddr(request));
                    logger.info(tenDangNhap + "==> Sua nguoi dung khong thanh cong. Do loi update database");
                    JSONObject jObj = new JSONObject();
                    jObj.put("code", "1");
                    jObj.put("detail", "Sửa người dùng không thành công");
                    out.print(jObj);
                    out.flush();
                }
            }

        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
