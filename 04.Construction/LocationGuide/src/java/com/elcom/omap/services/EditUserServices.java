/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.omap.services;

import com.elcom.omap.common.Constant;
import com.elcom.omap.common.OmapHistoryLogic;
import com.elcom.omap.common.OmapSessionUtils;
import com.elcom.omap.database.DBActions;
import com.elcom.omap.login.OmapUser;
import com.elcom.omap.quantrihethong.QuanTriHeThongDataLogic;
import com.elcom.omap.util.OmapDocUtils;
import com.elcom.omap.util.OmapUtils;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EditUserServices extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(EditUserServices.class);
    private static final long serialVersionUID = 1L;
    private static final int THRESHOLD_SIZE = 1024 * 1024 * 3; // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            String userId = "0";//request.getParameter("userid");
            String fullName = "0";//request.getParameter("fullname");
            String tenDangNhap = "0";//request.getParameter("username");
            String imageUrl = "0";//request.getParameter("imageurl");
            String phoneNumber = "0";//request.getParameter("phonenumber");
            String email = "0";//request.getParameter("email");
            String ipaddress = "0";//request.getParameter("ipaddress");
            String address = "0";//request.getParameter("address");
            String checkip = "0";//request.getParameter("checkip");
            String password = "0";//request.getParameter("password");
            String passwordNew = "0";//request.getParameter("passwordnew");
            String userName = OmapSessionUtils.getUserName(request);
            if (userName == null) {
                OmapUtils.showErrSessionTimeOut(out);
                return;
            }
            if (!ServletFileUpload.isMultipartContent(request)) {
                // if not, we stop here
                return;
            }

            // configures some settings
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(THRESHOLD_SIZE);
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(MAX_FILE_SIZE);
            upload.setSizeMax(REQUEST_SIZE);

            // constructs the directory path to store upload file
            String uploadPath = getServletContext().getRealPath("") + File.separator + Constant.UPLOAD_DIRECTORY_USER;
            System.out.println(uploadPath);
            String filePath = "";
            String content = "";
            String fileUpload = request.getContextPath() + "/" + Constant.UPLOAD_WEB_USER;
            // creates the directory if it does not exist
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            int workId = 0;
            String title = "";
            FileItem filesave = null;
            File storeFile = null;
            try {
                // parses the request's content to extract file data
                List formItems = upload.parseRequest(request);
                Iterator iter = formItems.iterator();

                // iterates over form's fields

                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    // processes only fields that are not form fields
                    if (!item.isFormField()) {
                        imageUrl = new File(item.getName()).getName();
                        if (imageUrl.length() > 0) {
                            filePath = uploadPath + imageUrl;
                            fileUpload += imageUrl;

                            storeFile = new File(filePath);
                            if (storeFile.exists()) {
                                JSONObject jObj = new JSONObject();
                                jObj.put("code", "1");
                                jObj.put("detail", "Tên file đã tồn tại. Bạn phải đổi sang tên file khác trước khi upload");
                                out.print(jObj);
                                out.flush();
                                return;
                            }
                            filesave = item;
                        }

                    } else {
                        if (item.getFieldName().equals("userid")) {
                            userId = item.getString("UTF-8");
                        }
                        if (item.getFieldName().equals("fullname")) {
                            fullName = item.getString("UTF-8");
                        }
                        if (item.getFieldName().equals("tenDangNhap")) {
                            tenDangNhap = item.getString();
                        }
                        if (item.getFieldName().equals("phoneNumber")) {
                            phoneNumber = item.getString();
                        }
                        if (item.getFieldName().equals("email")) {
                            email = item.getString();
                        }
                        if (item.getFieldName().equals("ipaddress")) {
                            ipaddress = item.getString();
                        }
                        if (item.getFieldName().equals("address")) {
                            address = item.getString();
                        }
                        if (item.getFieldName().equals("checkip")) {
                            checkip = item.getString();
                        }
                        if (item.getFieldName().equals("password")) {
                            password = item.getString();
                        }
                        if (item.getFieldName().equals("passwordNew")) {
                            passwordNew = item.getString();
                        }
                    }
                }
                OmapUser omapUser = OmapSessionUtils.getOmapUserUserName(request);
                if (!password.equals(omapUser.getUserPass())) {
                    logger.info(tenDangNhap + "==> Sua nguoi dung khong thanh cong. Ten dang nhap khong dung");
                    JSONObject jObj = new JSONObject();
                    jObj.put("code", "1");
                    jObj.put("detail", "Mật khẩu hiện tại không đúng");
                    out.print(jObj);
                    out.flush();
                    return;
                }
//                DBActions.addDocument(title, content, fileUpload, filePath, "", workId);
                boolean result = QuanTriHeThongDataLogic.editUserImage(userId, userName, passwordNew, fullName, email, ipaddress, phoneNumber, address, checkip, imageUrl);
                if (result) {
                    if (filesave != null && storeFile != null) {
                        filesave.write(storeFile);
                    }
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
            } catch (Exception ex) {
                logger.error("UploadFile()==>", ex);
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
