/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.omap.quantrihethong;

import com.elcom.omap.common.Constant;
import com.elcom.omap.common.Md5;
import com.elcom.omap.common.OmapHistoryLogic;
import com.elcom.omap.common.OmapSessionUtils;
import com.elcom.omap.util.OmapUtils;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author KiemPQ-PC
 */
public class QuanTriHeThongServices extends HttpServlet {

    private Logger logger = LoggerFactory.getLogger(Constant.LOGGER_NAME);

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
            String userName = OmapSessionUtils.getUserName(request);
            if (userName == null) {
                OmapUtils.showErrSessionTimeOut(out);
                return;
            }
            String functionName = request.getParameter("options");
            if (functionName == null) {
                return;
            }
            logger.info(userName + "==> Action: " + functionName);
            if (functionName.equals("GetListUser")) {
                getListUser(out, userName, request, response);
            } else if (functionName.equals("AddUser")) {
                addUser(out, userName, request, response);
            } else if (functionName.equals("XuatBaoCao")) {
                xuatBaoCao(out, userName, request, response);
            } else if (functionName.equals("EditUser")) {
                editUser(out, userName, request, response);
            } else if (functionName.equals("DeleteUser")) {
                deleteUser(out, userName, request, response);
            } else if (functionName.equals("GetListPages")) {
                getListPages(out, userName, request, response);
            } else if ("GetRoles".equals(functionName)) {
                getRoles(out, request, userName);
            } else if ("AddRole".equals(functionName)) {
                addRole(out, request, userName);

            } else if ("EditRole".equals(functionName)) {
                editRole(out, request, userName);

            } else if ("DeleteRole".equals(functionName)) {
                deleteRole(out, request, userName);
            } else if (functionName.equals("AddPage")) {
                String name = request.getParameter("name");
                String descrition = request.getParameter("descrition");
                String frendlyurl = request.getParameter("friendlyurl");
                String parentId = request.getParameter("parent");
                String pageType = request.getParameter("pagetype");
                boolean result = PageLogic.addPage(name, frendlyurl, parentId, descrition, pageType);
                if (result) {
                    JSONObject jObj = new JSONObject();
                    jObj.put("code", "0");
                    jObj.put("detail", "Thêm trang thành công");
                    out.print(jObj);
                    out.flush();
                } else {
                    JSONObject jObj = new JSONObject();
                    jObj.put("code", "1");
                    jObj.put("detail", "Thêm trang không thành công");
                    out.print(jObj);
                    out.flush();
                }
            } else if ("DeletePage".equals(functionName)) {
                String pageId = request.getParameter("pageId");
                boolean result = PageLogic.deletePage(pageId);
                if (result) {
                    JSONObject jObj = new JSONObject();
                    jObj.put("code", "0");
                    jObj.put("detail", "Xóa trang thành công");
                    out.print(jObj);
                    out.flush();
                } else {
                    JSONObject jObj = new JSONObject();
                    jObj.put("code", "1");
                    jObj.put("detail", "Xóa trang không thành công");
                    out.print(jObj);
                    out.flush();
                }
            } else if ("EditPage".equals(functionName)) {
                String pageId = request.getParameter("pageId");
                String name = request.getParameter("name");
                String descrition = request.getParameter("descrition");
                String frendlyurl = request.getParameter("friendlyurl");
                String parentId = request.getParameter("parent");
                String pageType = request.getParameter("pagetype");
                boolean result = PageLogic.editPage(pageId, name, frendlyurl, parentId, descrition, pageType);
                if (result) {
                    JSONObject jObj = new JSONObject();
                    jObj.put("code", "0");
                    jObj.put("detail", "Sửa trang thành công");
                    out.print(jObj);
                    out.flush();
                } else {
                    JSONObject jObj = new JSONObject();
                    jObj.put("code", "1");
                    jObj.put("detail", "Thêm trang không thành công");
                    out.print(jObj);
                    out.flush();
                }
            } else if (functionName.equals("GetPagesByRoleId")) {

                String roleId = request.getParameter("roleId");
                if (roleId != null) {
                    List listPage = RolePageLogic.getListPageByRoleId(roleId);
                    //logger.debug("Size page:" + listPage.size());
                    if (listPage != null) {
                        JSONArray arrayObj = new JSONArray();
                        for (Object o : listPage) {
                            Object[] obj = (Object[]) o;
                            JSONObject jObj = new JSONObject();
                            String pageId = String.valueOf(obj[0]);
                            jObj.put("pageId", pageId);
                            arrayObj.add(jObj);
                        }
                        out.print(arrayObj);
                        out.flush();
                    }
                }
            } else if (functionName.equals("SaveRolePages")) {
                String roleId = request.getParameter("roleId");
                String pageIds = request.getParameter("pageIds");
                if (roleId != null && pageIds != null) {

                    String[] arrPageIds = pageIds.split(",");
                    boolean result = true;
                    RolePageLogic.deleteRolePage(roleId);
                    for (String pageId : arrPageIds) {
                        if (!pageId.equals("")) {
                            if (!RolePageLogic.insertRolePage(pageId, roleId)) {
                                result = false;
                            }
                        }
                    }

                    if (result) {
                        OmapHistoryLogic.insertUserHistory(Constant.ACTION_QTHT_ROLEPAGE, "0", userName, "Cập nhật thành công. Vai trò:" + roleId + ". Chức năng:" + pageIds, OmapUtils.getClientIpAddr(request));
                        out.print("Cập nhật thành công");
                        out.flush();
                    } else {
                        // roleback lai du lieu
                        OmapHistoryLogic.insertUserHistory(Constant.ACTION_QTHT_ROLEPAGE, "1", userName, "Cập nhật không thành công. Vai trò:" + roleId + ". Chức năng:" + pageIds, OmapUtils.getClientIpAddr(request));
                        out.print("Cập nhật thành công");
                        out.flush();
                    }
                }
            } else if (functionName.equals("GetRolesByUserId")) {
                String userId = request.getParameter("userId");
                if (userId != null) {
                    List listPage = UserRoleLogic.getListRoleByUserId(userId);
                    //logger.debug("Size page:" + listPage.size());
                    if (listPage != null) {
                        JSONArray arrayObj = new JSONArray();
                        for (Object o : listPage) {
                            Object[] obj = (Object[]) o;
                            JSONObject jObj = new JSONObject();
                            String pageId = String.valueOf(obj[0]);
                            jObj.put("roleId", pageId);
                            arrayObj.add(jObj);
                        }
                        out.print(arrayObj);
                        out.flush();
                    }
                }
            } else if (functionName.equals("SaveUserRoles")) {
                String userId = request.getParameter("userId");
                String roleIds = request.getParameter("roleIds");
                if (userId != null && roleIds != null) {
                    String[] arrRoleIds = roleIds.split(",");
                    boolean result = true;
                    UserRoleLogic.deleteUserRoles(userId);
                    for (String roleId : arrRoleIds) {
                        if (!roleId.equals("")) {
                            if (!UserRoleLogic.insertUserRole(userId, roleId)) {
                                result = false;
                            }
                        }
                    }

                    if (result) {
                        OmapHistoryLogic.insertUserHistory(Constant.ACTION_QTHT_USERROLE, "0", userName, "Cập nhật thành công. Người dùng:" + userId + ". Vai trò:" + roleIds, OmapUtils.getClientIpAddr(request));
                        out.print("Cập nhật thành công");
                        out.flush();
                    } else {
                        OmapHistoryLogic.insertUserHistory(Constant.ACTION_QTHT_ROLEPAGE, "1", userName, "Cập nhật không thành công. Người dùng:" + userId + ". Vai trò:" + roleIds, OmapUtils.getClientIpAddr(request));
                        out.print("Cập nhật không thành công");
                        out.flush();
                    }
                }
            } else if (functionName.equals("GiamSatTruyNhapNguoiSuDung")) {
                giamSatTruyNhapNguoiSuDung(out, request, response, userName);
            } else if (functionName.equals("GetUserByParentId")) {
                getUserByParentId(out, request, response, userName);
            } else if (functionName.equals("SaveUserPosition")) {
                String userId = request.getParameter("userId");
                String roleIds = request.getParameter("posids");
                if (userId != null && roleIds != null) {
                    String[] arrRoleIds = roleIds.split(",");
                    boolean result = true;
                    UserRoleLogic.deleteUserPermision(userId);
                    for (String roleId : arrRoleIds) {
                        if (!roleId.equals("")) {
                            if (!UserRoleLogic.insertUserPermision(userId, roleId)) {
                                result = false;
                            }
                        }
                    }

                    if (result) {
                        OmapHistoryLogic.insertUserHistory(Constant.ACTION_QTHT_USERROLE, "0", userName, "Cập nhật thành công. Người dùng:" + userId + ". Vai trò:" + roleIds, OmapUtils.getClientIpAddr(request));
                        out.print("Cập nhật thành công");
                        out.flush();
                    } else {
                        OmapHistoryLogic.insertUserHistory(Constant.ACTION_QTHT_ROLEPAGE, "1", userName, "Cập nhật không thành công. Người dùng:" + userId + ". Vai trò:" + roleIds, OmapUtils.getClientIpAddr(request));
                        out.print("Cập nhật không thành công");
                        out.flush();
                    }
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

    private void getListUser(PrintWriter out, String userName, HttpServletRequest request, HttpServletResponse response) {
        String tenDangNhap = request.getParameter("tendangnhap");
        String pt = request.getParameter("postype");
        int iPosType = -1;
        if (pt != null) {
            iPosType = OmapUtils.parserInteger(pt);
        }
//        List lUser = QuanTriHeThongDataLogic.listUsers();
        List lUser = QuanTriHeThongDataLogic.listUsers(tenDangNhap);
        if (lUser != null && lUser.size() > 0) {
            JSONObject objAll = new JSONObject();
            JSONArray arrayObj = new JSONArray();
            objAll.put("total", lUser.size());
            for (Object o : lUser) {
                Object[] obj = (Object[]) o;
                JSONObject jObj = new JSONObject();
                String userid = String.valueOf(obj[0]);
                String username = String.valueOf(obj[1]);
                String userpass = String.valueOf(obj[2]);
                String fullname = String.valueOf(obj[3]);
                String email = String.valueOf(obj[4]);
                String ip = String.valueOf(obj[5]);
                String phonenumber = String.valueOf(obj[6]);
                String address = String.valueOf(obj[7]);
                String checkip = String.valueOf(obj[8]);
                String posname = OmapUtils.parserObjectToString(obj[10]);
                int postype = OmapUtils.parserInteger(obj[11]);

                if (phonenumber.equals("null")) {
                    phonenumber = "";
                }
                if (email.equals("null")) {
                    email = "";
                }
                if (address.equals("null")) {
                    address = "";
                }
                if (ip.equals("null")) {
                    ip = "";
                }
                if (checkip.equals("1")) {
                    checkip = "Kiểm tra";
                } else {
                    checkip = "Không kiểm tra";
                }

                jObj.put("userid", userid);
                jObj.put("username", username);
                jObj.put("userpass", userpass);
                jObj.put("fullname", fullname);
                jObj.put("email", email);
                jObj.put("ip", ip);
                jObj.put("phonenumber", phonenumber);
                jObj.put("address", address);
                jObj.put("checkip", checkip);
                jObj.put("posname", posname);
                jObj.put("postype", postype);
                if (postype > iPosType) {
                    arrayObj.add(jObj);
                }
            }
            objAll.put("rows", arrayObj);
            out.print(objAll);
            out.flush();
        } else {
            JSONObject objAll = new JSONObject();
            JSONArray arrayObj = new JSONArray();
            objAll.put("total", 0);
            objAll.put("rows", arrayObj);
            out.print(objAll);
            out.flush();
        }
    }

    private void addUser(PrintWriter out, String userName, HttpServletRequest request, HttpServletResponse response) {
        String fullName = request.getParameter("fullname");
        String tenDangNhap = request.getParameter("username");
        String password = request.getParameter("password");
        String phoneNumber = request.getParameter("phonenumber");
        String email = request.getParameter("email");
        String ipaddress = request.getParameter("ipaddress");
        String address = request.getParameter("address");
        String checkip = request.getParameter("checkip");
        String posId = request.getParameter("posid");
        String imgUrl = request.getParameter("urlImg");

        boolean isExitsUserName = QuanTriHeThongDataLogic.checkUserNameExits(tenDangNhap);
        if (isExitsUserName) {
            OmapHistoryLogic.insertUserHistory(Constant.ACTION_QTHT_USER, "1", userName, "Tên user đã tồn tại", OmapUtils.getClientIpAddr(request));
            logger.info(userName + "==> Tao user that bai{ErrorCode:1|Detail:Da ton tai ten user}");
            JSONObject jObj = new JSONObject();
            jObj.put("code", "1");
            jObj.put("detail", "Tên đăng nhập đã tồn tại");
            out.print(jObj);
            out.flush();
            return;
        }
        boolean result = QuanTriHeThongDataLogic.addUser(tenDangNhap, password, fullName, email, ipaddress, phoneNumber, address, checkip, posId, imgUrl);
        if (result) {
            OmapHistoryLogic.insertUserHistory(Constant.ACTION_QTHT_USER, "0", userName, "Tạo user " + tenDangNhap, OmapUtils.getClientIpAddr(request));
            JSONObject jObj = new JSONObject();
            jObj.put("code", "0");
            jObj.put("detail", "Thêm người dùng thành công");
            out.print(jObj);
            out.flush();
        } else {
            OmapHistoryLogic.insertUserHistory(Constant.ACTION_QTHT_USER, "1", userName, "Lỗi insert xuống db", OmapUtils.getClientIpAddr(request));
            JSONObject jObj = new JSONObject();
            jObj.put("code", "1");
            jObj.put("detail", "Thêm người dùng không thành công");
            out.print(jObj);
            out.flush();
        }
    }

    private void editUser(PrintWriter out, String userName, HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userid");
        String fullName = request.getParameter("fullname");
        String tenDangNhap = request.getParameter("username");
        String phoneNumber = request.getParameter("phonenumber");
        String email = request.getParameter("email");
        String ipaddress = request.getParameter("ipaddress");
        String address = request.getParameter("address");
        String checkip = request.getParameter("checkip");
        String password = request.getParameter("password");
        String posId = request.getParameter("posid");
        String imgUrl = request.getParameter("urlImg");

//        if (password != null && password.equals("") == false) {
//            password = Md5.getMd5Digest(password);
//        }

        boolean result = QuanTriHeThongDataLogic.editUser(userId, userName, password, fullName, email, ipaddress, phoneNumber, address, checkip, posId, imgUrl);
        if (result) {
            OmapHistoryLogic.insertUserHistory(Constant.ACTION_QTHT_USER, "0", userName, "Sửa user " + tenDangNhap, OmapUtils.getClientIpAddr(request));
            logger.info(userName + "==> Sua user thanh cong{ErrorCode:0|Detail:OK}");
            JSONObject jObj = new JSONObject();
            jObj.put("code", "0");
            jObj.put("detail", "Sửa người dùng thành công");
            out.print(jObj);
            out.flush();
        } else {
            OmapHistoryLogic.insertUserHistory(Constant.ACTION_QTHT_USER, "1", userName, "Sửa user " + tenDangNhap + ". Lỗi dưới DB", OmapUtils.getClientIpAddr(request));
            logger.info(userName + "==> Sua user that bai{ErrorCode:2|Detail:Loi update xuong db}");
            JSONObject jObj = new JSONObject();
            jObj.put("code", "1");
            jObj.put("detail", "Sửa người dùng không thành công");
            out.print(jObj);
            out.flush();
        }
    }

    private void deleteUser(PrintWriter out, String userName, HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userid");

        boolean result = QuanTriHeThongDataLogic.deleteUser(userId);
        if (result) {
            OmapHistoryLogic.insertUserHistory(Constant.ACTION_QTHT_USER, "0", userName, "Xóa user " + userId, OmapUtils.getClientIpAddr(request));
            logger.info(userName + "==> Xoa user thanh cong{ErrorCode:0|Detail:OK|UserId:" + userId + "}");
            JSONObject jObj = new JSONObject();
            jObj.put("code", "0");
            jObj.put("detail", "Xóa người dùng thành công");
            out.print(jObj);
            out.flush();
        } else {
            OmapHistoryLogic.insertUserHistory(Constant.ACTION_QTHT_USER, "1", userName, "Xóa user " + userId, OmapUtils.getClientIpAddr(request));
            logger.info(userName + "==> Xoa user that bai{ErrorCode:2|Detail:Loi delete xuong db|UserId:" + userId + "}");
            JSONObject jObj = new JSONObject();
            jObj.put("code", "1");
            jObj.put("detail", "Xóa người dùng không thành công");
            out.print(jObj);
            out.flush();
        }
    }

    private void getListPages(PrintWriter out, String userName, HttpServletRequest request, HttpServletResponse response) {

        List lPage = QuanTriHeThongDataLogic.getTreePages();

        if (lPage != null && lPage.size() > 0) {
            JSONArray arrayObj = new JSONArray();
            for (Object o : lPage) {
                Object[] obj = (Object[]) o;
                String id = String.valueOf(obj[0]);
                String name = String.valueOf(obj[2]);
                String parentId = String.valueOf(obj[1]);
                JSONObject jObj = new JSONObject();
                jObj.put("id", id);
                jObj.put("parentId", parentId);
                jObj.put("name", name);
                arrayObj.add(jObj);
            }
            out.print(arrayObj);
            out.flush();
        } else {
        }
    }

    private void getRoles(PrintWriter out, HttpServletRequest request, String userName) {
        List dsKhuyenMai = RoleLogic.getRoles();
        JSONObject objAll = new JSONObject();
        JSONArray arrayObj = new JSONArray();
        objAll.put("total", dsKhuyenMai.size());
        if (dsKhuyenMai != null && dsKhuyenMai.size() > 0) {
            for (Object o : dsKhuyenMai) {
                Object[] obj = (Object[]) o;
                JSONObject jObj = new JSONObject();
                String roleid = String.valueOf(obj[0]);
                String rolename = String.valueOf(obj[1]);
                if (rolename.equals("null")) {
                    rolename = "";
                }
                String descrition = String.valueOf(obj[2]);
                if (descrition.equals("null")) {
                    descrition = "";
                }
                jObj.put("roleid", roleid);
                jObj.put("rolename", rolename);
//                jObj.put("roletype", roletype);
                jObj.put("descrition", descrition);
                arrayObj.add(jObj);
            }

        }
        objAll.put("rows", arrayObj);
        out.print(objAll);
        out.flush();
    }

    private void addRole(PrintWriter out, HttpServletRequest request, String userName) {
        String name = request.getParameter("name");
        String descrition = request.getParameter("descrition");
        boolean isExistRole = RoleLogic.isExistRoleByName(name);
        if (isExistRole) {
            OmapHistoryLogic.insertUserHistory(Constant.ACTION_QTHT_ROLE, "1", userName, "Thêm vai trò: " + name + ". Tên vai trò đã tồn tại", OmapUtils.getClientIpAddr(request));
            JSONObject jObj = new JSONObject();
            jObj.put("code", "1");
            jObj.put("detail", "Thêm vai trò không thành công. Tên vai trò đã tồn tại");
            out.print(jObj);
            out.flush();
        } else {
            boolean result = RoleLogic.addRole(name, descrition);
            if (result) {
                OmapHistoryLogic.insertUserHistory(Constant.ACTION_QTHT_ROLE, "0", userName, "Thêm vai trò: " + name, OmapUtils.getClientIpAddr(request));
                JSONObject jObj = new JSONObject();
                jObj.put("code", "0");
                jObj.put("detail", "Thêm vai trò thành công");
                out.print(jObj);
                out.flush();
            } else {
                OmapHistoryLogic.insertUserHistory(Constant.ACTION_QTHT_ROLE, "1", userName, "Thêm vai trò: " + name + ". Lỗi logic dữ liệu", OmapUtils.getClientIpAddr(request));
                JSONObject jObj = new JSONObject();
                jObj.put("code", "1");
                jObj.put("detail", "Thêm vai trò không thành công. Lỗi logic dữ liệu");
                out.print(jObj);
                out.flush();
            }
        }
    }

    private void editRole(PrintWriter out, HttpServletRequest request, String userName) {
        String pageId = request.getParameter("roleId");
        String name = request.getParameter("name");
        String descrition = request.getParameter("descrition");
        boolean isExistRole = RoleLogic.isExistRoleByName(name, pageId);
        if (isExistRole) {
            OmapHistoryLogic.insertUserHistory(Constant.ACTION_QTHT_ROLE, "1", userName, "Sửa vai trò: " + pageId + ". Tên vai trò đã tồn tại", OmapUtils.getClientIpAddr(request));
            JSONObject jObj = new JSONObject();
            jObj.put("code", "1");
            jObj.put("detail", "Sửa vai trò không thành công. Tên vai trò đã tồn tại");
            out.print(jObj);
            out.flush();
        } else {
            boolean result = RoleLogic.editRole(pageId, name, descrition);
            if (result) {
                OmapHistoryLogic.insertUserHistory(Constant.ACTION_QTHT_ROLE, "0", userName, "Sửa vai trò: " + pageId, OmapUtils.getClientIpAddr(request));
                JSONObject jObj = new JSONObject();
                jObj.put("code", "0");
                jObj.put("detail", "Sửa vai trò thành công");
                out.print(jObj);
                out.flush();
            } else {
                OmapHistoryLogic.insertUserHistory(Constant.ACTION_QTHT_ROLE, "1", userName, "Sửa vai trò: " + pageId + ". Lỗi logic dữ liệu", OmapUtils.getClientIpAddr(request));
                JSONObject jObj = new JSONObject();
                jObj.put("code", "1");
                jObj.put("detail", "Sửa vai trò không thành công. Lỗi logic dữ liệu");
                out.print(jObj);
                out.flush();
            }
        }
    }

    private void deleteRole(PrintWriter out, HttpServletRequest request, String userName) {
        String roleId = request.getParameter("roleId");
        boolean result = RoleLogic.deleteRole(roleId);
        if (result) {
            OmapHistoryLogic.insertUserHistory(Constant.ACTION_QTHT_ROLE, "0", userName, "Xóa vai trò: " + roleId, OmapUtils.getClientIpAddr(request));
            JSONObject jObj = new JSONObject();
            jObj.put("code", "0");
            jObj.put("detail", "Xóa vai trò thành công");
            out.print(jObj);
            out.flush();
        } else {
            OmapHistoryLogic.insertUserHistory(Constant.ACTION_QTHT_ROLE, "1", userName, "Xóa vai trò: " + roleId + ". Lỗi logic dữ liệu", OmapUtils.getClientIpAddr(request));
            JSONObject jObj = new JSONObject();
            jObj.put("code", "1");
            jObj.put("detail", "Xóa vai trò thành công");
            out.print(jObj);
            out.flush();
        }
    }

    private void giamSatTruyNhapNguoiSuDung(PrintWriter out, HttpServletRequest request, HttpServletResponse response, String userName) {
        String tuNgay = request.getParameter("tungay");
        String denNgay = request.getParameter("denngay");
        String trungTam = request.getParameter("trungtam");
        String congViec = request.getParameter("conviec");
        String tenDangNhap = request.getParameter("username");
        String pagenumber = request.getParameter("pagenumber");
        String pagesize = request.getParameter("pagesize");
        int startRecord = 0;
        int endRecord = 10;
        if (pagenumber != null) {
            try {
                startRecord = (Integer.parseInt(pagenumber) - 1) * Integer.parseInt(pagesize);
                endRecord = startRecord + (Integer.parseInt(pagesize));
            } catch (Exception e) {
            }
        }
        String tKetThuc = "10";
        if (pagesize != null) {
            tKetThuc = String.valueOf(pagesize);
        }
        String tBatDau = String.valueOf(startRecord);


        int totalRow = QuanTriHeThongDataLogic.totalGiamSatTruyNhapNguoiSuDung(tuNgay, denNgay, trungTam, congViec, tenDangNhap);
        List lKetQua = QuanTriHeThongDataLogic.giamSatTruyNhapNguoiSuDung(tuNgay, denNgay, trungTam, congViec, tenDangNhap, tBatDau, tKetThuc);

        JSONObject objAll = new JSONObject();
        JSONArray arrayObj = new JSONArray();
        objAll.put("total", totalRow);
        if (lKetQua != null && lKetQua.size() > 0) {
            for (Object o : lKetQua) {
                Object[] obj = (Object[]) o;
                JSONObject jObj = new JSONObject();
                String action = String.valueOf(obj[0]);
                String result = String.valueOf(obj[1]);
                String time = String.valueOf(obj[2]);
                String username = String.valueOf(obj[3]);
                String userhis_id = String.valueOf(obj[4]);
                String action_detail = String.valueOf(obj[5]);
                String ip = String.valueOf(obj[6]);
//                String row_id = String.valueOf(obj[7]);

                if (result.equals("null")) {
                    result = "";
                } else if (result.equals("0")) {

                    result = "Thành công";
                } else if (result.equals("1")) {
                    result = "Thất bại";
                }

                jObj.put("rowid", 0);
                jObj.put("username", username);
                jObj.put("userhis_id", userhis_id);
                jObj.put("congviecthuchien", action);
                jObj.put("thoigian", time);
                jObj.put("chitietcongviec", action_detail);
                jObj.put("ketqua", result);
                jObj.put("diachiip", ip);
                arrayObj.add(jObj);
            }

        }
        objAll.put("rows", arrayObj);
        out.print(objAll);
        out.flush();
    }

    private void getUserByParentId(PrintWriter out, HttpServletRequest request, HttpServletResponse response, String userName) {
        String tenDangNhap = request.getParameter("userId");
//        List lUser = QuanTriHeThongDataLogic.listUsers();
        List lUser = QuanTriHeThongDataLogic.listUserByParentId(tenDangNhap);
        if (lUser != null && lUser.size() > 0) {
            JSONObject objAll = new JSONObject();
            JSONArray arrayObj = new JSONArray();
            objAll.put("total", lUser.size());
            for (Object o : lUser) {
                Object[] obj = (Object[]) o;
                JSONObject jObj = new JSONObject();
                String userid = String.valueOf(obj[0]);
                String username = String.valueOf(obj[1]);
                String userpass = String.valueOf(obj[2]);
                String fullname = String.valueOf(obj[3]);
                String email = String.valueOf(obj[4]);
                String ip = String.valueOf(obj[5]);
                String phonenumber = String.valueOf(obj[6]);
                String address = String.valueOf(obj[7]);
                String checkip = String.valueOf(obj[8]);
                String posname = OmapUtils.parserObjectToString(obj[10]);

                if (phonenumber.equals("null")) {
                    phonenumber = "";
                }
                if (email.equals("null")) {
                    email = "";
                }
                if (address.equals("null")) {
                    address = "";
                }
                if (ip.equals("null")) {
                    ip = "";
                }
                if (checkip.equals("1")) {
                    checkip = "Kiểm tra";
                } else {
                    checkip = "Không kiểm tra";
                }

                jObj.put("userid", userid);
                jObj.put("username", username);
                jObj.put("userpass", userpass);
                jObj.put("fullname", fullname);
                jObj.put("email", email);
                jObj.put("ip", ip);
                jObj.put("phonenumber", phonenumber);
                jObj.put("address", address);
                jObj.put("checkip", checkip);
                jObj.put("posname", posname);
                arrayObj.add(jObj);
            }
            objAll.put("rows", arrayObj);
            out.print(objAll);
            out.flush();
        } else {
            JSONObject objAll = new JSONObject();
            JSONArray arrayObj = new JSONArray();
            objAll.put("total", 0);
            objAll.put("rows", arrayObj);
            out.print(objAll);
            out.flush();
        }
    }

    private void xuatBaoCao(PrintWriter out, String userName, HttpServletRequest request, HttpServletResponse response) {

        String userId = OmapSessionUtils.getUserId(request);
        String startDate = request.getParameter("startDate");
        int key = Integer.parseInt(request.getParameter("key"));
        String endDate = request.getParameter("endDate");
        String fileUpload = getServletContext().getRealPath("") + File.separator + Constant.UPLOAD_DIRECTORY;
        String fileDownload = request.getContextPath() + "/" + Constant.UPLOAD_WEB_DIRECTORY;
        String urlDL = QuanTriHeThongDataLogic.xuatBaocao(userId, key, userName, startDate, endDate, fileUpload, fileDownload);

        if (urlDL != "") {
            JSONObject jObj = new JSONObject();
            jObj.put("code", "0");
            jObj.put("url", urlDL);
            jObj.put("detail", "Xóa vai trò thành công");
            out.print(jObj);
            out.flush();
        } else {
            JSONObject jObj = new JSONObject();
            jObj.put("code", "1");
            jObj.put("detail", "Lỗi");
            out.print(jObj);
            out.flush();
        }
    }
}
