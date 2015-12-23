/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.omap.database;

import com.kiemanh.vn.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author KiemPQ-PC
 */
public class DataPeresitents {

    private static Logger logger = LoggerFactory.getLogger(DataPeresitents.class);

    public static List<Object[]> connvertToList(ResultSet resultSet) throws SQLException {
        List<Object[]> records = new ArrayList();
        if (resultSet != null) {
            while (resultSet.next()) {
                int cols = resultSet.getMetaData().getColumnCount();
                Object[] arr = new Object[cols];
                for (int i = 0; i < cols; i++) {
                    arr[i] = resultSet.getObject(i + 1);
                }
                records.add(arr);
            }
            return records;
        }
        return null;
    }

    public static List queryList(String sql, ArrayList<String> params) {
        DbConnection con = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        try {
            con = MySQLAccess.getInstance().getConn();
            pre = con.prepareStatement(sql);
            int index = 0;
            if (params.size() > 0) {
                for (String param : params) {
                    index++;
                    pre.setString(index, param);
                }
            }
            rs = pre.executeQuery();
            return connvertToList(rs);


        } catch (SQLException ex) {
            logger.error("queryList()==>" + sql, ex);
            //  ex.printStackTrace();
        } catch (Exception ex) {
            logger.error("queryList()==>" + sql, ex);
            //ex.printStackTrace();
        } finally {
            MySQLAccess.getInstance().closeResultSet(rs);
            MySQLAccess.getInstance().closePreparedStatement(pre);
            MySQLAccess.getInstance().closeConn(con);
        }
        return null;

    }

    public static int[] UpdateData(String strquery, String params[][]) {
        DbConnection conn = null;
        PreparedStatement pre = null;
        try {
            conn = MySQLAccess.getInstance().getConn();
            pre = conn.prepareStatement(strquery);
            if (params.length > 0) {

                for (int i = 0; i < params.length; i++) {
                    int index = 0;
                    for (int j = 0; j < params[i].length; j++) {
                        index++;
                        pre.setString(index, params[i][j].toString());
                    }
                    pre.addBatch();
                }

            }
            return pre.executeBatch();
        } catch (SQLException e) {
            logger.error("UpdateData()==>" + strquery, e);
            //System.out.println("code loi=" + e.getMessage());
            return null;
        } catch (Exception e) {
            logger.error("UpdateData()==>" + strquery, e);
            //System.out.println("ex=" + e.getMessage());
            return null;
        } finally {
            try {
                conn.commit();

            } catch (SQLException ex) {
                logger.error("UpdateData()Ko commit duoc ==>" + strquery, ex);
            }
            MySQLAccess.getInstance().closePreparedStatement(pre);
            MySQLAccess.getInstance().closeConn(conn);
        }

    }

    public static int updateData(String strquery, ArrayList<String> params) throws SQLException { // CA UPDATE VA DELETE
        DbConnection conn = null;
        PreparedStatement pre = null;
        try {
            conn = MySQLAccess.getInstance().getConn();
            pre = conn.prepareStatement(strquery);
            int index = 0;
            if (params.size() > 0) {
                for (String param : params) {
                    index++;
                    pre.setString(index, param);
                }
            }
            return pre.executeUpdate();

        } catch (SQLException e) {
            logger.error("UpdateData()==>" + strquery, e);
            return 0;
        } catch (Exception e) {
            logger.error("UpdateData()==>" + strquery, e);
            return 0;
        } finally {
            conn.commit();
            MySQLAccess.getInstance().closePreparedStatement(pre);
            MySQLAccess.getInstance().closeConn(conn);
        }
    }

    public static int[] UpdateDataDynamic(String strquery, ArrayList params) {
        DbConnection conn = null;
        PreparedStatement pre = null;
        try {
            conn = MySQLAccess.getInstance().getConn();
            pre = conn.prepareStatement(strquery);
            if (params.size() > 0) {

                for (int i = 0; i < params.size(); i++) {
                    int index = 0;
                    String[] obj = (String[]) params.get(i);
                    for (int j = 0; j < obj.length; j++) {
                        index++;
                        pre.setString(index, obj[j].toString());
                    }
                    pre.addBatch();
                }

            }
            return pre.executeBatch();
        } catch (SQLException e) {
            logger.error("UpdateDataDynamic()==>" + strquery, e);
            return null;
        } catch (Exception e) {
            logger.error("UpdateDataDynamic()==>" + strquery, e);
            return null;
        } finally {
            try {
                conn.commit();

            } catch (SQLException ex) {
                logger.error("UpdateDataDynamic()Ko commit duoc ==>" + strquery, ex);
            }
            MySQLAccess.getInstance().closePreparedStatement(pre);
            MySQLAccess.getInstance().closeConn(conn);
        }

    }

    public static long callFunctionReturnLong(String sql, ArrayList<ParamObject> params) {
        DbConnection conn = null;
        java.sql.CallableStatement cs = null;
        int idx = 2;
        long total = 0;
        try {
            conn = MySQLAccess.getInstance().getConn();
            cs = conn.prepareCall(sql);
            // Set the value for the IN OUT parameter
            cs.registerOutParameter(1, Types.NUMERIC);
            for (ParamObject param : params) {
                int typeParam = param.getParamType();
                String value = param.getValue();
                switch (typeParam) {
                    case ParamObject.TYPE_BYTE:
                        byte vByte = ParamObject.convertToByte(value);
                        cs.setByte(idx, vByte);
                        break;
                    case ParamObject.TYPE_SHOT:
                        short vShort = ParamObject.convertToShort(value);
                        cs.setShort(idx, vShort);
                        break;
                    case ParamObject.TYPE_INT:
                        int vInt = ParamObject.convertToInt(value);
                        cs.setInt(idx, vInt);
                        break;
                    case ParamObject.TYPE_FLOAT:
                        float vFloat = ParamObject.convertToFloat(value);
                        cs.setFloat(idx, vFloat);
                        break;
                    case ParamObject.TYPE_DOUBLE:
                        double vDouble = ParamObject.convertToDouble(value);
                        cs.setDouble(idx, vDouble);
                        break;
                    case ParamObject.TYPE_LONG:
                        long vLong = ParamObject.convertToLong(value);
                        cs.setLong(idx, vLong);
                        break;
                    case ParamObject.TYPE_BOOLEAN:
                        boolean vBoolean = ParamObject.convertToBoolean(value);
                        cs.setBoolean(idx, vBoolean);
                        break;
                    case ParamObject.TYPE_STRING:
                        cs.setString(idx, value);
                        break;
                    case ParamObject.TYPE_DATE:
                        java.sql.Date vDate = ParamObject.convertToDate(value);
                        cs.setDate(idx, vDate);
                        break;
                    default:
                        break;
                }
                idx++;
            }

            // Execute the stored procedure
            cs.executeUpdate();
            // Call a procedure with one OUT parameter
            total = cs.getLong(1);
        } catch (Exception se) {
            logger.error("callFunctionReturnLong()==>", se);
        } finally {
            MySQLAccess.getInstance().closeCallableStatement(cs);
            MySQLAccess.getInstance().closeConn(conn);
        }
        return total;
    }
}
