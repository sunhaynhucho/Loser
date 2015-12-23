/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.omap.database;

import com.elcom.omap.util.OmapUtils;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.kiemanh.vn.DbConnection;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.text.Normalizer;
import java.util.Iterator;
import java.util.regex.Pattern;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 *
 * @author elcom154
 */
public class DBActions {

    private static short numberOfColumns;

    public static List getListCongViec(int userId, String textSearch) {

        return null;
    }

    public static List getListUsers(int userId) {
        String sql = "";
        ArrayList<String> params = new ArrayList<String>();
        List resutl = DataPeresitents.queryList(sql, params);
        return null;
    }

    public static List getListUserForTree(int userId) {
        String sql = "select b.userid,b.username,b.fullname, c.pos_name,c.pos_type, 0 as parentid,b.email,c.pos_id "
                + "from qtht_users b, qtht_positions c "
                + "where b.userid = ? and b.pos_id = c.pos_id";

        ArrayList<String> params = new ArrayList<String>();
        params.add(userId + "");
        List<Integer> luserId = new ArrayList<Integer>();
        List resutl = DataPeresitents.queryList(sql, params);
        if (resutl == null) {
            return null;
        }
        luserId.add(userId);
        Object[] obj = (Object[]) resutl.get(0);
        int chucvu0 = OmapUtils.parserInteger(obj[4]);
        int mnv0 = OmapUtils.parserInteger(obj[0]);
        List lCapDuoi0 = getAllListCapDuoi(mnv0, chucvu0);
        if (lCapDuoi0 != null) {
            for (Object obj0s : lCapDuoi0) {
                Object[] o1 = (Object[]) obj0s;
                int chucvu1 = OmapUtils.parserInteger(o1[4]);
                int mnv1 = OmapUtils.parserInteger(o1[0]);
                if (!luserId.contains(mnv1)) {
                    resutl.add(obj0s);
                    luserId.add(mnv1);
                    List lCapDuoi1 = getAllListCapDuoi(mnv1, chucvu1);
                    if (lCapDuoi1 != null) {
                        for (Object obj1s : lCapDuoi1) {
                            Object[] o2 = (Object[]) obj1s;
                            int chucvu2 = OmapUtils.parserInteger(o2[4]);
                            int mnv2 = OmapUtils.parserInteger(o2[0]);
                            if (!luserId.contains(mnv2)) {
                                resutl.add(obj1s);
                                luserId.add(mnv2);
                                List lCapDuoi2 = getAllListCapDuoi(mnv2, chucvu2);
                                if (lCapDuoi2 != null) {
                                    for (Object obj2s : lCapDuoi2) {
                                        Object[] o3 = (Object[]) obj2s;
                                        int chucvu3 = OmapUtils.parserInteger(o3[4]);
                                        int mnv3 = OmapUtils.parserInteger(o3[0]);
                                        if (!luserId.contains(mnv3)) {
                                            resutl.add(obj2s);
                                            luserId.add(mnv3);
                                            List lCapDuoi3 = getAllListCapDuoi(mnv3, chucvu3);
                                            if (lCapDuoi3 != null) {
                                                for (Object obj3s : lCapDuoi3) {
                                                    Object[] o4 = (Object[]) obj3s;
                                                    int chucvu4 = OmapUtils.parserInteger(o4[4]);
                                                    int mnv4 = OmapUtils.parserInteger(o4[0]);
                                                    if (!luserId.contains(mnv4)) {
                                                        resutl.add(obj3s);
                                                        luserId.add(mnv4);
                                                        List lCapDuoi4 = getAllListCapDuoi(mnv4, chucvu4);
                                                        if (lCapDuoi4 != null) {
                                                            for (Object obj4s : lCapDuoi4) {
                                                                Object[] o5 = (Object[]) obj4s;
//                                                                int chucvu5 = OmapUtils.parserInteger(o4[4]);
                                                                int mnv5 = OmapUtils.parserInteger(o4[0]);
                                                                if (!luserId.contains(mnv5)) {
                                                                    resutl.add(obj4s);
                                                                    luserId.add(mnv5);
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }
        return resutl;
    }

    public static List getAllListCapDuoi(int userId, int vt) {
        if (vt < 4) {
            String sql2 = "SELECT b.userid,b.username,b.fullname, c.pos_name,c.pos_type, " + userId + " as parentid,b.email,c.pos_id  "
                    + "from qtht_users b, qtht_positions c "
                    + "where b.pos_id = c.pos_id and b.userid in (select USERID from qtht_permission where manager_id = ?) order by c.pos_type";
            ArrayList<String> params2 = new ArrayList<String>();
            params2.add(userId + "");
            List resutl2 = DataPeresitents.queryList(sql2, params2);
            return resutl2;
        }
        return null;
    }

    public static List getListPhoPhong(int userId) {
        String sql = "select b.userid,b.username,b.fullname, c.pos_name,c.pos_type, "
                + "0 as parentid,b.email,c.pos_id, b.image_url from qtht_users b, qtht_positions c "
                + "where b.pos_id = c.pos_id and "
                + "b.userid in (select userid from qtht_permission where manager_id = ?)";

        ArrayList<String> params = new ArrayList<String>();
        params.add(userId + "");
        List resutl = DataPeresitents.queryList(sql, params);
        return resutl;
    }

    public static List getListTruongBan(int userId) {
        String sql = "select b.userid,b.username,b.fullname, c.pos_name,c.pos_type, 0 as parentid,b.email,c.pos_id, b.image_url "
                + "from qtht_users b, qtht_positions c "
                + "where b.pos_id = c.pos_id "
                + "and b.userid in "
                + "(select userid from qtht_permission where manager_id = ?) "
                + "and b.userid not in "
                + "(select userid from qtht_permission where manager_id in ("
                + "select manager_id from qtht_permission where userid = ? ))";

        ArrayList<String> params = new ArrayList<String>();
        params.add(userId + "");
        params.add(userId + "");
        List resutl = DataPeresitents.queryList(sql, params);
        return resutl;
    }

    public static List getListPhoBan(int userId) {
        String sql = "select b.userid,b.username,b.fullname, c.pos_name,c.pos_type, 0 as parentid,b.email,c.pos_id, b.image_url "
                + "from qtht_users b, qtht_positions c "
                + "where b.pos_id = c.pos_id "
                + "and b.userid in "
                + "(select userid from qtht_permission where manager_id = ?) "
                + "and b.userid not in "
                + "(select userid from qtht_permission where manager_id in ("
                + "select manager_id from qtht_permission where userid = ? ))";

        ArrayList<String> params = new ArrayList<String>();
        params.add(userId + "");
        params.add(userId + "");
        List resutl = DataPeresitents.queryList(sql, params);
        return resutl;
    }

    public static List getListNhanvien(int userId) {
        String sql = "select b.userid,b.username,b.fullname, c.pos_name,c.pos_type, 0 as parentid,b.email,c.pos_id, b.image_url "
                + "from qtht_users b, qtht_positions c "
                + "where b.pos_id = c.pos_id "
                + "and b.userid in "
                + "(select userid from qtht_permission where manager_id = ?) "
                + "and b.userid not in "
                + "(select userid from qtht_permission where manager_id in (select manager_id from qtht_permission where userid in("
                + "select manager_id from qtht_permission where userid = ? )))";

        ArrayList<String> params = new ArrayList<String>();
        params.add(userId + "");
        params.add(userId + "");
        List resutl = DataPeresitents.queryList(sql, params);
        return resutl;
    }

    public static List getListNhanvienCapDuoi(int userId) {
        String sql = "select b.userid,b.username,b.fullname, c.pos_name,c.pos_type, 0 as parentid,b.email,c.pos_id, b.image_url "
                + "from qtht_users b, qtht_positions c "
                + "where b.pos_id = c.pos_id "
                + "and b.userid in "
                + "(select userid from qtht_permission where manager_id = ?) order by c.pos_type";
//                + "and b.userid not in "
//                + "(select userid from qtht_permission where manager_id in (select manager_id from qtht_permission where userid in("
//                + "select manager_id from qtht_permission where userid = ? )))";

        ArrayList<String> params = new ArrayList<String>();
        params.add(userId + "");
//        params.add(userId + "");
        List resutl = DataPeresitents.queryList(sql, params);
        return resutl;
    }

    public static List getListWork(int userId, String startDate, String endDate) {
//        String sql = "select a.work_id,a.work_content,a.work_status,a.work_process,DATE_FORMAT(a.create_time, '%d/%m/%Y'),"
//                + "DATE_FORMAT(a.update_time, '%d/%m/%Y'),DATE_FORMAT(a.deadline, '%d/%m/%Y'), ifnull(b.sl_doc,0),ifnull(c.sl_cme,0),ifnull(d.sl_rs,0),a.userid,f.full_name "
//                + "from tbl_works a "
//                + "left join (select work_id, count(1) as sl_doc from tbl_works_documents group by work_id) b "
//                + "on a.work_id = b.work_id "
//                + "left join (select work_id, count(1) as sl_rs from tbl_works_result group by work_id) d "
//                + "on a.work_id = d.work_id "
//                + "left join (select work_id, count(1) as sl_cme from tbl_comments group by work_id) c "
//                + "on a.work_id = c.work_id "
//                + "left join (select userid, fullname as full_name from qtht_users) f "
//                + "on a.userid = f.userid "
//                + "where ( WEEKOFYEAR(create_time) = WEEKOFYEAR(NOW()) or work_process = 0 or work_process = 2 ) "
//                + "and a.work_id in (select work_id from qtht_users_works where userid = ?)";


        String sql = "select a.work_id,a.work_content,a.work_status,a.work_process,DATE_FORMAT(a.create_time, '%d/%m/%Y'),"
                + "DATE_FORMAT(a.update_time, '%d/%m/%Y'),DATE_FORMAT(a.deadline, '%d/%m/%Y'), ifnull(b.sl_doc,0),ifnull(c.sl_cme,0),ifnull(d.sl_rs,0),a.userid,f.full_name "
                + "from tbl_works a "
                + "left join (select work_id, count(1) as sl_doc from tbl_works_documents group by work_id) b "
                + "on a.work_id = b.work_id "
                + "left join (select work_id, count(1) as sl_rs from tbl_works_result group by work_id) d "
                + "on a.work_id = d.work_id "
                + "left join (select work_id, count(1) as sl_cme from tbl_comments group by work_id) c "
                + "on a.work_id = c.work_id "
                + "left join (select userid, fullname as full_name from qtht_users) f "
                + "on a.userid = f.userid "
                + "where a.work_id in (select work_id from qtht_users_works where userid = ?) ";

        ArrayList<String> params = new ArrayList<String>();
        params.add(userId + "");
        if (startDate != null && !"".equals(startDate)) {
            sql += " and a.deadline >= STR_TO_DATE(?,'%d/%m/%Y')";
            params.add(startDate);
        }
        if (endDate != null && !"".equals(endDate)) {
            sql += " and a.deadline <= STR_TO_DATE(?,'%d/%m/%Y')";
            params.add(endDate);
        }
        sql += " order by work_id desc";

        List resutl = DataPeresitents.queryList(sql, params);
        return resutl;
    }

    public static int addComment(int userId, int workId, String content) {
        DbConnection conn = null;
        java.sql.CallableStatement cs = null;
        String sql = "{ call pro_tbl_comments_add(?,?,?,?)}";
        try {
            conn = MySQLAccess.getInstance().getConn();
            cs = conn.prepareCall(sql);
            cs.setInt(1, userId);
            cs.setInt(2, workId);
            cs.setString(3, content);
            cs.registerOutParameter(4, Types.INTEGER);
            cs.executeUpdate();
            // Call a procedure with one OUT parameter
            int maLoi = cs.getInt(4);
            return maLoi;
        } catch (Exception se) {
            OmapUtils.getLogger().error("pro_tbl_comments_add==>", se);
        } finally {
            MySQLAccess.getInstance().closeCallableStatement(cs);
            MySQLAccess.getInstance().closeConn(conn);
        }
        return 0;
    }

    public static int editComment(int commentId, String content) {
        DbConnection conn = null;
        java.sql.CallableStatement cs = null;
        String sql = "{ call pro_tbl_comments_edit(?,?,?)}";
        try {
            conn = MySQLAccess.getInstance().getConn();
            cs = conn.prepareCall(sql);
            cs.setInt(1, commentId);
            cs.setString(2, content);
            cs.registerOutParameter(3, Types.INTEGER);
            cs.executeUpdate();
            // Call a procedure with one OUT parameter
            int maLoi = cs.getInt(3);
            return maLoi;
        } catch (Exception se) {
            OmapUtils.getLogger().error("pro_tbl_comments_edit==>", se);
        } finally {
            MySQLAccess.getInstance().closeCallableStatement(cs);
            MySQLAccess.getInstance().closeConn(conn);
        }
        return 0;
    }

    public static int deleteComment(int commentId) {
        DbConnection conn = null;
        java.sql.CallableStatement cs = null;
        String sql = "{ call pro_tbl_comments_delete(?,?,?)}";
        try {
            conn = MySQLAccess.getInstance().getConn();
            cs = conn.prepareCall(sql);
            cs.setInt(1, commentId);
            cs.setInt(2, -1);
            cs.setInt(3, -1);
            cs.executeUpdate();
            // Call a procedure with one OUT parameter
            return 0;
        } catch (Exception se) {
            OmapUtils.getLogger().error("pro_tbl_comments_delete==>", se);
        } finally {
            MySQLAccess.getInstance().closeCallableStatement(cs);
            MySQLAccess.getInstance().closeConn(conn);
        }
        return 0;
    }

    public static int deleteDocument(int id) {
        DbConnection conn = null;
        java.sql.CallableStatement cs = null;
        String sql = "{ call pro_tbl_documents_delete(?,?,?)}";
        try {
            conn = MySQLAccess.getInstance().getConn();
            cs = conn.prepareCall(sql);
            cs.setInt(1, id);

            cs.registerOutParameter(2, Types.VARCHAR);
            cs.registerOutParameter(3, Types.INTEGER);
            cs.executeUpdate();
            // Call a procedure with one OUT parameter
            int maLoi = cs.getInt(3);
            if (maLoi == 0) {
                String filePath = cs.getString(2);
                if (filePath != null) {
                    File file = new File(filePath);
                    file.delete();
                }
            }
            return maLoi;
        } catch (Exception se) {
            OmapUtils.getLogger().error("pro_tbl_documents_delete==>", se);
        } finally {
            MySQLAccess.getInstance().closeCallableStatement(cs);
            MySQLAccess.getInstance().closeConn(conn);
        }
        return 0;
    }

    public static int deleteResult(int id) {
        DbConnection conn = null;
        java.sql.CallableStatement cs = null;
        String sql = "{ call pro_tbl_result_delete(?,?,?)}";
        try {
            conn = MySQLAccess.getInstance().getConn();
            cs = conn.prepareCall(sql);
            cs.setInt(1, id);

            cs.registerOutParameter(2, Types.VARCHAR);
            cs.registerOutParameter(3, Types.INTEGER);
            cs.executeUpdate();
            // Call a procedure with one OUT parameter
            int maLoi = cs.getInt(3);
            if (maLoi == 0) {
                String filePath = cs.getString(2);
                if (filePath != null) {
                    File file = new File(filePath);
                    file.delete();
                }
            }
            return maLoi;
        } catch (Exception se) {
            OmapUtils.getLogger().error("pro_tbl_result_delete==>", se);
        } finally {
            MySQLAccess.getInstance().closeCallableStatement(cs);
            MySQLAccess.getInstance().closeConn(conn);
        }
        return 0;
    }

    public static int editDocument(int docId, String docTitle, String docContent, String docUrl, String docDesc) {
        DbConnection conn = null;
        java.sql.CallableStatement cs = null;
        String sql = "{ call pro_tbl_documents_edit(?,?,?,?,?,?)}";
        try {
            conn = MySQLAccess.getInstance().getConn();
            cs = conn.prepareCall(sql);
            cs.setInt(1, docId);
            cs.setString(2, docTitle);
            cs.setString(3, docContent);
            cs.setString(4, docUrl);
            cs.setString(5, docDesc);
            cs.registerOutParameter(6, Types.INTEGER);
            cs.executeUpdate();
            // Call a procedure with one OUT parameter
            int maLoi = cs.getInt(6);
            return maLoi;
        } catch (Exception se) {
            OmapUtils.getLogger().error("pro_tbl_documents_edit==>", se);
        } finally {
            MySQLAccess.getInstance().closeCallableStatement(cs);
            MySQLAccess.getInstance().closeConn(conn);
        }
        return 0;
    }

    public static int editResult(int docId, String docTitle, String docContent, String docUrl, String docDesc) {
        DbConnection conn = null;
        java.sql.CallableStatement cs = null;
        String sql = "{ call pro_tbl_result_edit(?,?,?,?,?,?)}";
        try {
            conn = MySQLAccess.getInstance().getConn();
            cs = conn.prepareCall(sql);
            cs.setInt(1, docId);
            cs.setString(2, docTitle);
            cs.setString(3, docContent);
            cs.setString(4, docUrl);
            cs.setString(5, docDesc);
            cs.registerOutParameter(6, Types.INTEGER);
            cs.executeUpdate();
            // Call a procedure with one OUT parameter
            int maLoi = cs.getInt(6);
            return maLoi;
        } catch (Exception se) {
            OmapUtils.getLogger().error("pro_tbl_result_edit==>", se);
        } finally {
            MySQLAccess.getInstance().closeCallableStatement(cs);
            MySQLAccess.getInstance().closeConn(conn);
        }
        return 0;
    }

    public static int addDocument(int workId, String docTitle, String docContent, String docUrl, String docPath, String docDesc, String urlPdf) {
        DbConnection conn = null;
        java.sql.CallableStatement cs = null;
        String sql = "{ call pro_tbl_documents_add(?,?,?,?,?,?,?)}";
        try {
            conn = MySQLAccess.getInstance().getConn();
            cs = conn.prepareCall(sql);
            cs.setString(1, docTitle);
            cs.setString(2, docContent);
            cs.setString(3, docUrl);
            cs.setString(4, docPath);
            cs.setString(5, docDesc);
            cs.setString(6, urlPdf);
            cs.registerOutParameter(7, Types.INTEGER);
            cs.executeUpdate();
            // Call a procedure with one OUT parameter
            int maLoi = cs.getInt(7);
            if (maLoi != -1) {
                String sql2 = "{ call pro_tbl_works_documents_add(?,?,?)}";
                java.sql.CallableStatement cs2 = null;
                try {
                    conn = MySQLAccess.getInstance().getConn();
                    cs2 = conn.prepareCall(sql2);
                    cs2.setInt(1, maLoi);
                    cs2.setInt(2, workId);
                    cs2.registerOutParameter(3, Types.INTEGER);
                    cs2.executeUpdate();
                } catch (Exception se) {
                    OmapUtils.getLogger().error("pro_tbl_works_documents_add==>", se);
                } finally {
                    MySQLAccess.getInstance().closeCallableStatement(cs2);
                }
            }
            return maLoi;
        } catch (Exception se) {
            OmapUtils.getLogger().error("pro_tbl_documents_add==>", se);
        } finally {
            MySQLAccess.getInstance().closeCallableStatement(cs);
            MySQLAccess.getInstance().closeConn(conn);
        }
        return 0;
    }

    public static int addResult(int workId, String docTitle, String docContent, String docUrl, String docPath, String docDesc, String textDoc, String urlPdf) {
        DbConnection conn = null;
        java.sql.CallableStatement cs = null;
        String sql = "{ call pro_tbl_result_add(?,?,?,?,?,?,?,?)}";
        try {
            conn = MySQLAccess.getInstance().getConn();
            cs = conn.prepareCall(sql);
            cs.setString(1, docTitle);
            cs.setString(2, docContent);
            cs.setString(3, docUrl);
            cs.setString(4, docPath);
            cs.setString(5, docDesc);
            cs.setString(6, textDoc);
            cs.setString(7, urlPdf);
            cs.registerOutParameter(8, Types.INTEGER);
            cs.executeUpdate();
            // Call a procedure with one OUT parameter
            int maLoi = cs.getInt(8);
            if (maLoi != -1) {
                String sql2 = "{ call pro_tbl_works_result_add(?,?,?)}";
                java.sql.CallableStatement cs2 = null;
                try {
                    conn = MySQLAccess.getInstance().getConn();
                    cs2 = conn.prepareCall(sql2);
                    cs2.setInt(1, maLoi);
                    cs2.setInt(2, workId);
                    cs2.registerOutParameter(3, Types.INTEGER);
                    cs2.executeUpdate();
                } catch (Exception se) {
                    OmapUtils.getLogger().error("pro_tbl_works_result_add==>", se);
                } finally {
                    MySQLAccess.getInstance().closeCallableStatement(cs2);
                }
            }
            return maLoi;
        } catch (Exception se) {
            OmapUtils.getLogger().error("pro_tbl_result_add==>", se);
        } finally {
            MySQLAccess.getInstance().closeCallableStatement(cs);
            MySQLAccess.getInstance().closeConn(conn);
        }
        return 0;
    }

    public static int addWork(String workContent, int workStatus, int workProcess, String dealine, int userId, int wuserIDWork) {
        DbConnection conn = null;
        java.sql.CallableStatement cs = null;
        String sql = "{ call pro_tbl_works_add(?,?,?,STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s'),?,?)}";
        try {
            conn = MySQLAccess.getInstance().getConn();
            cs = conn.prepareCall(sql);
            cs.setString(1, workContent);
            cs.setInt(2, workStatus);
            cs.setInt(3, workProcess);
            cs.setString(4, dealine);
            cs.setInt(5, wuserIDWork);
            cs.registerOutParameter(6, Types.INTEGER);
            cs.executeUpdate();
            // Call a procedure with one OUT parameter
            int wId = cs.getInt(6);
            if (wId != -1) {
                String sql2 = "{ call pro_qtht_users_works_add(?,?)}";
                java.sql.CallableStatement cs2 = null;
                try {
                    cs2 = conn.prepareCall(sql2);
                    cs2.setInt(1, userId);
                    cs2.setInt(2, wId);
                    cs2.executeUpdate();
                    // Call a procedure with one OUT parameter
                } catch (Exception se) {
                    OmapUtils.getLogger().error("pro_qtht_users_works_add==>", se);
                } finally {
                    MySQLAccess.getInstance().closeCallableStatement(cs2);
                }
            }
            return 0;
        } catch (Exception se) {
            OmapUtils.getLogger().error("pro_tbl_works_add==>", se);
        } finally {
            MySQLAccess.getInstance().closeCallableStatement(cs);
            MySQLAccess.getInstance().closeConn(conn);
        }
        return 0;
    }

    public static int editWork(int workId, String workContent, int workStatus, int workProcess, String dealine) {
        DbConnection conn = null;
        java.sql.CallableStatement cs = null;
        String sql = "{ call pro_tbl_works_edit(?,?,?,?,STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s'),?)}";
        try {
            conn = MySQLAccess.getInstance().getConn();
            cs = conn.prepareCall(sql);
            cs.setInt(1, workId);
            cs.setString(2, workContent);
            cs.setInt(3, workStatus);
            cs.setInt(4, workProcess);
            cs.setString(5, dealine);
            cs.registerOutParameter(6, Types.INTEGER);
            cs.executeUpdate();
            // Call a procedure with one OUT parameter
            int maLoi = cs.getInt(6);
            return maLoi;
        } catch (Exception se) {
            OmapUtils.getLogger().error("pro_tbl_works_edit==>", se);
        } finally {
            MySQLAccess.getInstance().closeCallableStatement(cs);
            MySQLAccess.getInstance().closeConn(conn);
        }
        return 0;
    }

    public static int deleteWork(int workId) {
        DbConnection conn = null;
        java.sql.CallableStatement cs = null;
        String sql = "{ call pro_tbl_works_delete(?,?)}";
        try {
            conn = MySQLAccess.getInstance().getConn();
            cs = conn.prepareCall(sql);
            cs.setInt(1, workId);
            cs.registerOutParameter(2, Types.INTEGER);
            cs.executeUpdate();
            // Call a procedure with one OUT parameter
            int maLoi = cs.getInt(2);
            return maLoi;
        } catch (Exception se) {
            OmapUtils.getLogger().error("pro_tbl_works_delete==>", se);
        } finally {
            MySQLAccess.getInstance().closeCallableStatement(cs);
            MySQLAccess.getInstance().closeConn(conn);
        }
        return 0;
    }

    public static int updateProcessWork(int workId, int process) {
        DbConnection conn = null;
//        java.sql.CallableStatement cs = null;
        PreparedStatement cs = null;
//        String sql = "{ call pro_tbl_works_update_process(?,?,?)}";
        String sql = "update tbl_works set work_process = ? where work_id = ?";
        try {
            conn = MySQLAccess.getInstance().getConn();
//            cs = conn.prepareCall(sql);
            cs = conn.prepareStatement(sql);
            cs.setInt(1, process);
            cs.setInt(2, workId);
//            cs.registerOutParameter(3, Types.INTEGER);
            int maLoi = cs.executeUpdate();
            // Call a procedure with one OUT parameter
//            int maLoi = cs.getInt(3);
            return maLoi;
        } catch (Exception se) {
            OmapUtils.getLogger().error("pro_tbl_works_update_process==>", se);
        } finally {
//            MySQLAccess.getInstance().closeCallableStatement(cs);
            MySQLAccess.getInstance().closePreparedStatement(cs);
            MySQLAccess.getInstance().closeConn(conn);
        }
        return 0;
    }

    public static List getListComment(int workId) {
        String sql = "select a.cmn_id, a.cmn_content, a.userid, b.fullname, DATE_FORMAT(a.create_time, '%d/%m/%Y'), DATE_FORMAT(a.update_time, '%d/%m/%Y') from tbl_comments a, qtht_users b "
                + "where a.userid = b.userid and a.work_id = ? order by a.create_time";
        ArrayList<String> params = new ArrayList<String>();
        params.add(workId + "");
        List resutl = DataPeresitents.queryList(sql, params);
        return resutl;
    }

    public static List getListDocument(int workId) {
        String sql = "select a.doc_id, a.doc_title, a.doc_content, a.doc_url,a.doc_path, a.doc_desc, a.url_pdf, DATE_FORMAT(a.doc_time, '%d/%m/%Y') "
                + "from tbl_documents a "
                + "where a.doc_id in (select doc_id from tbl_works_documents where work_id = ?) "
                + "order by a.doc_title";
        ArrayList<String> params = new ArrayList<String>();
        params.add(workId + "");
        List resutl = DataPeresitents.queryList(sql, params);
        return resutl;
    }

    public static List getListResult(int workId) {
        String sql = "select a.rs_id, a.rs_title, a.rs_content, a.rs_url,a.rs_path, a.rs_desc, a.rs_pdf, DATE_FORMAT(a.rs_time, '%d/%m/%Y') "
                + "from tbl_result a "
                + "where a.rs_id in (select rs_id from tbl_works_result where work_id = ?) "
                + "order by a.rs_title";
        ArrayList<String> params = new ArrayList<String>();
        params.add(workId + "");
        List resutl = DataPeresitents.queryList(sql, params);
        return resutl;
    }

    public static String getComment(int id) {
        DbConnection conn = null;
        java.sql.CallableStatement cs = null;
        String sql = "{ call pro_tbl_comments_get(?,?)}";
        try {
            conn = MySQLAccess.getInstance().getConn();
            cs = conn.prepareCall(sql);
            cs.setInt(1, id);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.executeUpdate();
            // Call a procedure with one OUT parameter
            String kq = cs.getString(2);
            return kq;
        } catch (Exception se) {
            OmapUtils.getLogger().error("pro_tbl_comments_get==>", se);
        } finally {
            MySQLAccess.getInstance().closeCallableStatement(cs);
            MySQLAccess.getInstance().closeConn(conn);
        }
        return "";
    }

    public static List getListCategory(String name) {
        String sql = "select a.cat_id, a.cat_name, a.parent_id, ifnull(b.cat_name,''), a.cat_desc, a.create_time, a.update_time "
                + "from tbl_cats a left join tbl_cats b on a.parent_id = b.cat_id";
        ArrayList<String> params = new ArrayList<String>();
        if (name != null && !"".equals(name)) {
            sql += " where a.cat_name like ?";
            params.add("%" + name + "%");
        }
        sql += " order by a.cat_name";
        List resutl = DataPeresitents.queryList(sql, params);
        return resutl;
    }

    public static int addCategory(String catName, int parentId, String catDesc) {
        DbConnection conn = null;
        java.sql.CallableStatement cs = null;
        String sql = "{ call pro_tbl_cats_add(?,?,?,?)}";
        try {
            conn = MySQLAccess.getInstance().getConn();
            cs = conn.prepareCall(sql);
            cs.setString(1, catName);
            cs.setInt(2, parentId);
            cs.setString(3, catDesc);
            cs.registerOutParameter(4, Types.INTEGER);
            cs.executeUpdate();
            // Call a procedure with one OUT parameter
            //int catID = cs.getString(2);
            return 0;
        } catch (Exception se) {
            OmapUtils.getLogger().error("pro_tbl_cats_add==>", se);
        } finally {
            MySQLAccess.getInstance().closeCallableStatement(cs);
            MySQLAccess.getInstance().closeConn(conn);
        }
        return -1;
    }

    public static int editCategory(int catId, String catName, int parentId, String catDesc) {
        DbConnection conn = null;
        java.sql.CallableStatement cs = null;
        String sql = "{ call pro_tbl_cats_edit(?,?,?,?,?)}";
        try {
            conn = MySQLAccess.getInstance().getConn();
            cs = conn.prepareCall(sql);
            cs.setInt(1, catId);
            cs.setString(2, catName);
            cs.setInt(3, parentId);
            cs.setString(4, catDesc);
            cs.registerOutParameter(5, Types.INTEGER);
            cs.executeUpdate();
            // Call a procedure with one OUT parameter
            //int catID = cs.getString(2);
            return 0;
        } catch (Exception se) {
            OmapUtils.getLogger().error("pro_tbl_cats_edit==>", se);
        } finally {
            MySQLAccess.getInstance().closeCallableStatement(cs);
            MySQLAccess.getInstance().closeConn(conn);
        }
        return -1;
    }

    public static int deleteCategory(int catId) {
        DbConnection conn = null;
        java.sql.CallableStatement cs = null;
        String sql = "{ call pro_tbl_cats_delete(?,?)}";
        try {
            conn = MySQLAccess.getInstance().getConn();
            cs = conn.prepareCall(sql);
            cs.setInt(1, catId);
            cs.registerOutParameter(2, Types.INTEGER);
            cs.executeUpdate();
            // Call a procedure with one OUT parameter
            //int catID = cs.getString(2);
            return 0;
        } catch (Exception se) {
            OmapUtils.getLogger().error("pro_tbl_cats_delete==>", se);
        } finally {
            MySQLAccess.getInstance().closeCallableStatement(cs);
            MySQLAccess.getInstance().closeConn(conn);
        }
        return -1;
    }

    public static int addDocument(String docTitle, String docContent, String docUrl, String docPath, String docDesc, int catId, String urlPdf, String username) {
        DbConnection conn = null;
        java.sql.CallableStatement cs = null;
        String sql = "{ call pro_tbl_docs_add(?,?,?,?,?,?,?,?)}";
        try {
            conn = MySQLAccess.getInstance().getConn();
            cs = conn.prepareCall(sql);
            cs.setString(1, docTitle);
            cs.setString(2, docContent);
            cs.setString(3, docUrl);
            cs.setString(4, docPath);
            cs.setString(5, docDesc);
            cs.setString(6, urlPdf);
            cs.setString(7, username);
            
            cs.registerOutParameter(8, Types.INTEGER);
            cs.executeUpdate();
            // Call a procedure with one OUT parameter
            int maLoi = cs.getInt(8);
            if (maLoi != -1) {
                String sql2 = "{ call pro_tbl_cats_docs_add(?,?)}";
                java.sql.CallableStatement cs2 = null;
                try {
                    conn = MySQLAccess.getInstance().getConn();
                    cs2 = conn.prepareCall(sql2);
                    cs2.setInt(1, catId);
                    cs2.setInt(2, maLoi);
                    // cs2.registerOutParameter(3, Types.INTEGER);
                    cs2.executeUpdate();
                } catch (Exception se) {
                    OmapUtils.getLogger().error("pro_tbl_cats_docs_add==>", se);
                } finally {
                    MySQLAccess.getInstance().closeCallableStatement(cs2);
                }
            }
            return maLoi;
        } catch (Exception se) {
            OmapUtils.getLogger().error("pro_tbl_docs_add==>", se);
        } finally {
            MySQLAccess.getInstance().closeCallableStatement(cs);
            MySQLAccess.getInstance().closeConn(conn);
        }
        return 0;
    }

    public static int editDocument(int docId, String docTitle, String docContent, String docUrl, String docPath, String docDesc, int catId) {
        DbConnection conn = null;
        java.sql.CallableStatement cs = null;
        String sql = "{ call pro_tbl_documents_edit(?,?,?,?,?,?)}";
        try {
            conn = MySQLAccess.getInstance().getConn();
            cs = conn.prepareCall(sql);
            cs.setInt(1, docId);
            cs.setString(1, docTitle);
            cs.setString(2, docContent);
            cs.setString(3, docUrl);
            cs.setString(4, docPath);
            cs.setString(5, docDesc);
            cs.registerOutParameter(6, Types.INTEGER);
            cs.executeUpdate();
            // Call a procedure with one OUT parameter
            int maLoi = cs.getInt(6);
            if (maLoi != -1) {

                String sql3 = "{ call pro_tbl_cats_docs_delete(?,?,?)}";
                java.sql.CallableStatement cs3 = null;
                try {
                    conn = MySQLAccess.getInstance().getConn();
                    cs3 = conn.prepareCall(sql3);
                    cs3.setInt(1, -1);
                    cs3.setInt(2, docId);
                    cs3.registerOutParameter(3, Types.INTEGER);
                    cs3.executeUpdate();
                } catch (Exception se) {
                    OmapUtils.getLogger().error("pro_tbl_cats_docs_delete==>", se);
                } finally {
                    MySQLAccess.getInstance().closeCallableStatement(cs3);
                }

                String sql2 = "{ call pro_tbl_cats_docs_add(?,?,?)}";
                java.sql.CallableStatement cs2 = null;
                try {
                    conn = MySQLAccess.getInstance().getConn();
                    cs2 = conn.prepareCall(sql2);
                    cs2.setInt(1, catId);
                    cs2.setInt(2, maLoi);
                    cs2.registerOutParameter(3, Types.INTEGER);
                    cs2.executeUpdate();
                } catch (Exception se) {
                    OmapUtils.getLogger().error("pro_tbl_cats_docs_add==>", se);
                } finally {
                    MySQLAccess.getInstance().closeCallableStatement(cs2);
                }


            }
            return maLoi;
        } catch (Exception se) {
            OmapUtils.getLogger().error("pro_tbl_documents_edit==>", se);
        } finally {
            MySQLAccess.getInstance().closeCallableStatement(cs);
            MySQLAccess.getInstance().closeConn(conn);
        }
        return 0;
    }

    public static List getListDoc(String name, int catId) {
        String sql = "select a.doc_id, a.doc_name, a.doc_content, b.cat_name, a.doc_url, a.doc_desc, a.doc_path, a.url_pdf, a.username from tbl_docs a, tbl_cats_docs c, tbl_cats b\n"
                + "where a.doc_id = c.doc_id and c.cat_id = b.cat_id";
        ArrayList<String> params = new ArrayList<String>();
        if (name != null && !"".equals(name)) {
            sql += " and (a.doc_name like ? or a.doc_content like ?)";
            params.add("%" + name + "%");
            params.add("%" + name + "%");
        }
        if (catId != -1) {
            sql += " and a.doc_id in (select doc_id from tbl_cats_docs where cat_id = ?)";
            params.add(catId + "");
        }
        sql += " order by a.doc_name";
        List resutl = DataPeresitents.queryList(sql, params);
        return resutl;
    }

    public static List getListCaterotyCha() {
        String sql = "select cat_id, cat_name, parent_id from tbl_cats";
        ArrayList<String> params = new ArrayList<String>();
        sql += " order by cat_name";
        List resutl = DataPeresitents.queryList(sql, params);
        return resutl;
    }

    public static Object[] getListCategoryId(int cid) {
        String sql = "select cat_id, cat_name, parent_id, cat_desc from tbl_cats where cat_id=?";
        ArrayList<String> params = new ArrayList<String>();
        params.add(cid + "");
        sql += " order by cat_id";
        List resutl = DataPeresitents.queryList(sql, params);
        if (resutl == null || resutl.size() == 0) {
            return new Object[4];
        }
        return (Object[]) resutl.get(0);
    }

    public static Object[] getListDocumentId(int did) {
        String sql = "select a.doc_id, a.doc_name, a.doc_content, b.cat_name,b.cat_id, a.doc_url, a.doc_desc, a.doc_path \n"
                + "from tbl_docs a, tbl_cats_docs c, tbl_cats b\n"
                + "where a.doc_id = c.doc_id and c.cat_id = b.cat_id and a.doc_id = ?";
        ArrayList<String> params = new ArrayList<String>();
        params.add(did + "");
        sql += " order by doc_id";
        List resutl = DataPeresitents.queryList(sql, params);
        if (resutl == null || resutl.size() == 0) {
            return new Object[4];
        }
        return (Object[]) resutl.get(0);
    }

    public static int editDocumentId(int docId, String docName, int catId, String searchKey) {
        String sql = "update tbl_docs set doc_name=?, doc_content=? where doc_id =?";
        ArrayList<String> params = new ArrayList<String>();
        params.add(docName);
        params.add(searchKey);
        params.add(docId + "");

        int i = 0;
        try {
            i = DataPeresitents.updateData(sql, params);
        } catch (SQLException ex) {
        }

        if (i > 0) {
            DbConnection conn = null;
            try {

                String sql3 = "{ call pro_tbl_cats_docs_delete(?,?)}";
                java.sql.CallableStatement cs3 = null;
                conn = MySQLAccess.getInstance().getConn();
                try {

                    cs3 = conn.prepareCall(sql3);
                    cs3.setInt(1, -1);
                    cs3.setInt(2, docId);
                    cs3.executeUpdate();
                } catch (Exception se) {
                    OmapUtils.getLogger().error("pro_tbl_cats_docs_delete==>", se);
                } finally {
                    MySQLAccess.getInstance().closeCallableStatement(cs3);
                }

                String sql2 = "{ call pro_tbl_cats_docs_add(?,?)}";
                java.sql.CallableStatement cs2 = null;
                try {
//                    conn = MySQLAccess.getInstance().getConn();
                    cs2 = conn.prepareCall(sql2);
                    cs2.setInt(1, catId);
                    cs2.setInt(2, docId);
                    cs2.executeUpdate();
                } catch (Exception se) {
                    OmapUtils.getLogger().error("pro_tbl_cats_docs_add==>", se);
                } finally {
                    MySQLAccess.getInstance().closeCallableStatement(cs2);
                }
            } catch (Exception se) {
                OmapUtils.getLogger().error("pro_tbl_cats_docs_add==>", se);
            } finally {
                MySQLAccess.getInstance().closeConn(conn);
            }
            return 0;
        }
        return -1;
    }

    public static int deleteDocumentId(int did) {

        String sql4 = "select doc_path from tbl_docs where doc_id=?";
        PreparedStatement pstm = null;
        ResultSet rs = null;
        DbConnection conn = null;

        try {
            conn = MySQLAccess.getInstance().getConn();
            pstm = conn.prepareStatement(sql4);
            pstm.setInt(1, did);
            rs = pstm.executeQuery();

            if (rs.next()) {
                String filePath = rs.getString(1).trim();
                File file = new File(filePath);
                file.delete();
            }

        } catch (Exception se) {
            OmapUtils.getLogger().error("select pathFile==>", se);
        } finally {
            MySQLAccess.getInstance().closeResultSet(rs);
            MySQLAccess.getInstance().closePreparedStatement(pstm);
        }

        String sql = "delete from tbl_docs where doc_id =?";
        ArrayList<String> params = new ArrayList<String>();
        params.add(did + "");
        int i = 0;
        try {
            i = DataPeresitents.updateData(sql, params);
        } catch (SQLException ex) {
        }

        if (i > 0) {

            try {

                String sql3 = "{ call pro_tbl_cats_docs_delete(?,?)}";
                java.sql.CallableStatement cs3 = null;

                try {

                    cs3 = conn.prepareCall(sql3);
                    cs3.setInt(1, -1);
                    cs3.setInt(2, did);
                    cs3.executeUpdate();

                } catch (Exception se) {
                    OmapUtils.getLogger().error("pro_tbl_cats_docs_delete==>", se);
                } finally {
                    MySQLAccess.getInstance().closeCallableStatement(cs3);
                }

            } catch (Exception se) {
                OmapUtils.getLogger().error("pro_tbl_cats_docs_add==>", se);
            } finally {
                MySQLAccess.getInstance().closeConn(conn);
            }
            return 0;
        }
        return -1;
    }

    public static int addWorks(List<String[]> ltemp, int useridWork) {
        DbConnection conn = null;
        java.sql.CallableStatement cs = null;
        String sql = "{ call pro_tbl_works_adds(?,STR_TO_DATE(?,'%d/%m/%Y'),STR_TO_DATE(?,'%d/%m/%Y'),?,?,?,?,?)}";
        try {
            conn = MySQLAccess.getInstance().getConn();
            cs = conn.prepareCall(sql);
            for (String[] items : ltemp) {
                cs.setString(1, items[0]);
                cs.setString(2, items[1]);
                cs.setString(3, items[2]);
                cs.setString(4, items[3]);
                cs.setString(5, items[4]);
                cs.setString(6, items[5]);
                cs.setString(7, items[6]);
                cs.setInt(8, useridWork);
                cs.addBatch();
            }
            cs.executeBatch();
            conn.commit();
            // Call a procedure with one OUT parameter
            //int catID = cs.getString(2);
            return 0;
        } catch (Exception se) {
            OmapUtils.getLogger().error("pro_tbl_works_adds==>", se);
        } finally {
            MySQLAccess.getInstance().closeCallableStatement(cs);
            MySQLAccess.getInstance().closeConn(conn);
        }
        return -1;



    }

    public static String showContentResult(int id) {

        DbConnection conn = null;
        java.sql.PreparedStatement cs = null;
        ResultSet rs = null;
        String html = "";
        String sql = "select rs_doc from tbl_result where rs_id = ?";
        try {
            conn = MySQLAccess.getInstance().getConn();
            cs = conn.prepareStatement(sql);
            cs.setInt(1, id);
            rs = cs.executeQuery();
            if (rs.next()) {
                html = rs.getString(1);
            }
            return html;
        } catch (Exception se) {
            OmapUtils.getLogger().error("tbl_result_get ==>", se);
        } finally {
            MySQLAccess.getInstance().closeResultSet(rs);
            MySQLAccess.getInstance().closePreparedStatement(cs);
            MySQLAccess.getInstance().closeConn(conn);
        }
        return "";

    }

    public static String showContentDocument(int id) {

        DbConnection conn = null;
        java.sql.PreparedStatement cs = null;
        ResultSet rs = null;
        String html = "";
        String sql = "select doc_content from tbl_documents where doc_id = ?";
        try {
            conn = MySQLAccess.getInstance().getConn();
            cs = conn.prepareStatement(sql);
            cs.setInt(1, id);
            rs = cs.executeQuery();
            if (rs.next()) {
                html = rs.getString(1);
            }
            return html;
        } catch (Exception se) {
            OmapUtils.getLogger().error("tbl_documents_get ==>", se);
        } finally {
            MySQLAccess.getInstance().closeResultSet(rs);
            MySQLAccess.getInstance().closePreparedStatement(cs);
            MySQLAccess.getInstance().closeConn(conn);
        }
        return "";

    }

    public static String showEditContent(int id) {

        DbConnection conn = null;
        java.sql.PreparedStatement cs = null;
        ResultSet rs = null;
        String html = "";
        String sql = "select rs_content from tbl_result where rs_id = ?";
        try {
            conn = MySQLAccess.getInstance().getConn();
            cs = conn.prepareStatement(sql);
            cs.setInt(1, id);
            rs = cs.executeQuery();
            if (rs.next()) {
                html = rs.getString(1);
            }
            return html;
        } catch (Exception se) {
            OmapUtils.getLogger().error("tbl_documents_get ==>", se);
        } finally {
            MySQLAccess.getInstance().closeResultSet(rs);
            MySQLAccess.getInstance().closePreparedStatement(cs);
            MySQLAccess.getInstance().closeConn(conn);
        }
        return "";

    }

    public static int editContentResult(int rsId, String content) {
        DbConnection conn = null;
        java.sql.CallableStatement cs = null;
        String sql = "{ call pro_tbl_result_edit_content(?,?,?)}";
        try {
            conn = MySQLAccess.getInstance().getConn();
            cs = conn.prepareCall(sql);
            cs.setInt(1, rsId);
            cs.setString(2, content);
            cs.registerOutParameter(3, Types.INTEGER);
            cs.executeUpdate();
            // Call a procedure with one OUT parameter
            int maLoi = cs.getInt(3);
            return maLoi;
        } catch (Exception se) {
            OmapUtils.getLogger().error("pro_tbl_result_edit_content==>", se);
        } finally {
            MySQLAccess.getInstance().closeCallableStatement(cs);
            MySQLAccess.getInstance().closeConn(conn);
        }
        return 0;
    }

    public static String showContentTailieu(int id) {

        DbConnection conn = null;
        java.sql.PreparedStatement cs = null;
        ResultSet rs = null;
        String html = "";
        String sql = "select doc_content from tbl_docs where doc_id  = ?";
        try {
            conn = MySQLAccess.getInstance().getConn();
            cs = conn.prepareStatement(sql);
            cs.setInt(1, id);
            rs = cs.executeQuery();
            if (rs.next()) {
                html = rs.getString(1);
            }
            return html;
        } catch (Exception se) {
            OmapUtils.getLogger().error("tbl_documents_get ==>", se);
        } finally {
            MySQLAccess.getInstance().closeResultSet(rs);
            MySQLAccess.getInstance().closePreparedStatement(cs);
            MySQLAccess.getInstance().closeConn(conn);
        }
        return "";

    }

    public String changeAlias(String fileName) {

        String temp = Normalizer.normalize(fileName, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String temp2 = pattern.matcher(temp).replaceAll("").replaceAll("Đ", "D").replaceAll("đ", "d");
        String temp3 = temp2.replaceAll(" ", "");

        return temp3;
    }

    public static void convertDocxToPDF(String src, String desc, String fileText) {
        try {
            FileInputStream fs = new FileInputStream(src);
            XWPFDocument doc = new XWPFDocument(fs);
            Document pdfdoc = new Document(PageSize.A4, 72, 72, 72, 72);
            PdfWriter pwriter = PdfWriter.getInstance(pdfdoc, new FileOutputStream(desc));
            pwriter.setInitialLeading(20);
            List<XWPFParagraph> plist = doc.getParagraphs();
            pdfdoc.open();

            for (int i = 0; i < plist.size(); i++) {
                XWPFParagraph pa = plist.get(i);
                List<XWPFRun> runs = pa.getRuns();

                for (int j = 0; j < runs.size(); j++) {

                    XWPFRun run = runs.get(j);
                    List<XWPFPicture> piclist = run.getEmbeddedPictures();
                    Iterator<XWPFPicture> iterator = piclist.iterator();

                    while (iterator.hasNext()) {
                        XWPFPicture pic = iterator.next();
                        XWPFPictureData picdata = pic.getPictureData();
                        byte[] bytepic = picdata.getData();
                        Image imag = Image.getInstance(bytepic);
                        pdfdoc.add(imag);

                    }

                    String text = run.getText(-1);
                    if (text != null) {

                        pwriter.getAcroForm().setNeedAppearances(true);
                        BaseFont unicode = BaseFont.createFont(fileText, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                        FontSelector fss = new FontSelector();
                        fss.addFont(new Font(unicode));
                        addContent(pdfdoc, text, fss);
                    }

                }
            }
            pdfdoc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void convertDocToPDF(String src, String desc, String fileText) {

        try {
            InputStream is = new BufferedInputStream(new FileInputStream(src));
            HWPFDocument doc = new HWPFDocument(is);
            WordExtractor wd = new WordExtractor(doc);
            String text = wd.getText();
            Document document = new Document(PageSize.A4, 72, 72, 72, 72);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(desc));
            document.open();
            writer.getAcroForm().setNeedAppearances(true);
            BaseFont unicode = BaseFont.createFont(fileText, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

            FontSelector fs = new FontSelector();
            fs.addFont(new Font(unicode));

            addContent(document, text, fs);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addContent(Document document, String paragraphs, FontSelector fs) throws DocumentException {
        Phrase phrase = fs.process(paragraphs);
        document.add(new Paragraph(phrase));
    }

    public static String replaceTypeFile(String fileName) {
        String nameNew = fileName;
        if (fileName.endsWith(".xls") == true) {
            nameNew = fileName.replace(".xls", ".pdf");
        } else if (fileName.endsWith(".xlsx") == true) {
            nameNew = fileName.replace(".xlsx", ".pdf");
        } else if (fileName.endsWith(".doc") == true) {
            nameNew = fileName.replace(".doc", ".pdf");
        } else if (fileName.endsWith(".docx") == true) {
            nameNew = fileName.replace(".docx", ".pdf");
        } else if (fileName.endsWith(".pdf") == true) {
            nameNew = fileName.replace(".pdf", ".pdf");
        }

        return nameNew;
    }

    public static Workbook loadSpreadSheet(File xlsFile) throws Exception {
        Workbook workbook = null;

        String ext = getFileExtension(xlsFile.getName());
        if (ext.equalsIgnoreCase("xlsx")) {
            OPCPackage pkg = OPCPackage.open(xlsFile.getAbsolutePath());
            workbook = new XSSFWorkbook(pkg);
            pkg.close();
        } else if (ext.equalsIgnoreCase("xls")) {
            InputStream xlsFIS = new FileInputStream(xlsFile);
            workbook = new HSSFWorkbook(xlsFIS);
            xlsFIS.close();
        } else {
            throw new Exception("FILE EXTENSION NOT RECOGNIZED");
        }
        return workbook;
    }

    private static String getFileExtension(String fileName) {
        String ext = "";
        int mid = fileName.lastIndexOf(".");
        ext = fileName.substring(mid + 1, fileName.length());
        System.out.println("File Extension --" + ext);
        return ext;
    }

    private static void readSpreadSheet(Workbook workbook, String desc) throws IOException,
            DocumentException {

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(desc));
        document.open();
        addMetaData(document);

        Anchor anchor = new Anchor("");


        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        Paragraph subPara = new Paragraph("");
        Section subCatPart = catPart.addSection(subPara);
        addEmptyLine(subPara, 5);

        Sheet sheet = workbook.getSheetAt(0);

        // Iterate through each rows from first sheet
        Iterator<Row> rowIterator = sheet.iterator();

        int temp = 0;
        boolean flag = true;
        PdfPTable table = null;

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            int cellNumber = 0;

            if (flag) {
                table = new PdfPTable(row.getLastCellNum());
                flag = false;
            }

            // For each row, iterate through each columns
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        if (temp == 0) {
                            numberOfColumns = row.getLastCellNum();
                            PdfPCell c1 = new PdfPCell(new Phrase(
                                    cell.getStringCellValue()));
                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(c1);
                            table.setHeaderRows(1);

                        } else {
                            cellNumber = checkEmptyCellAndAddCellContentToPDFTable(cellNumber, cell, table);
                        }
                        cellNumber++;
                        break;

                    case Cell.CELL_TYPE_NUMERIC:
                        cellNumber = checkEmptyCellAndAddCellContentToPDFTable(cellNumber, cell, table);
                        cellNumber++;
                        break;
                }
            }
            temp = 1;
            if (numberOfColumns != cellNumber) {
                for (int i = 0; i < (numberOfColumns - cellNumber); i++) {
                    table.addCell("");
                }
            }
        }
        subCatPart.add(table);
        // Now add all this to the document
        document.add(catPart);
        document.close();
    }

    private static void addMetaData(Document document) {
        document.addTitle("My first PDF");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Uvaraj");
        document.addCreator("Uvaraj");
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    private static int checkEmptyCellAndAddCellContentToPDFTable(int cellNumber, Cell cell, PdfPTable table) {
        if (cellNumber == cell.getColumnIndex()) {
            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                table.addCell(Double.toString(cell.getNumericCellValue()));
            }
            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                table.addCell(cell.getStringCellValue());
            }

        } else {
            while (cellNumber < cell.getColumnIndex()) {

                table.addCell("");
                cellNumber++;

            }
            if (cellNumber == cell.getColumnIndex()) {
                if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    table.addCell(Double.toString(cell.getNumericCellValue()));
                }
                if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                    table.addCell(cell.getStringCellValue());
                }

            }
            cellNumber = cell.getColumnIndex();
        }

        return cellNumber;
    }

    public static void convertExcelToPDF(String src, String desc) {
        File xlsFile = new File(src);
        Workbook workbook;
        try {
            workbook = loadSpreadSheet(xlsFile);
            readSpreadSheet(workbook, desc);
        } catch (FileNotFoundException e) {
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
