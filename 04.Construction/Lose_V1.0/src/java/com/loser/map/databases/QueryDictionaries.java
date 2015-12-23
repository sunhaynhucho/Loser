package com.loser.map.databases;

import com.kiemanh.vn.DbConnection;
import com.loser.map.sql.SqlMapView;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author VinhNV
 */
public class QueryDictionaries {
    
    private static final Logger logger = LoggerFactory.getLogger(SqlMapView.class);

    /**
     * select some columns of all records from table name of columns must be
     * selected is defined in an array of String type parameter @columnNames
     * alow null if you want select all
     *
     * @param tableName
     * @param columnNames
     * @return list of objects
     * @throws java.sql.SQLException
     */
    public static List<Map<String, Object>> toList(String tableName, String[] columnNames) throws SQLException, Exception {
        
        String sql = "select";
        if (columnNames != null && columnNames.length > 0) {
            int length = columnNames.length;
            for (int i = 0; i < length; i++) {
                sql += " " + columnNames[i];
                if (i != length - 1) {
                    sql += ",";
                }
            }
            sql+=" from "+ tableName;
        } else {
            sql = "select * from " + tableName;
        }
        logger.debug("Start query:" + sql);
        DbConnection con = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        List<Map<String, Object>> result = null;
        try {
            con = MySQLAccess.getInstance().getConn();
            pre = con.prepareStatement(sql);
            rs = pre.executeQuery();
            result = connvertToList(rs);
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        } finally {
            MySQLAccess.getInstance().closeResultSet(rs);
            MySQLAccess.getInstance().closePreparedStatement(pre);
            MySQLAccess.getInstance().closeConn(con);
        }
        return result;
    }

    /**
     * select all records meet the conditional in where clause
     *
     * @param tableName
     * @param whereClause
     * @param params
     * @return list of objects
     * @throws java.sql.SQLException
     */
    public static List<Map<String, Object>> where(String tableName, String whereClause, ParamObject[] params) throws SQLException, Exception {
        String sql = "select * from " + tableName + " where " + whereClause;
        DbConnection con = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        List<Map<String, Object>> result = null;
        try {
            con = MySQLAccess.getInstance().getConn();
            pre = con.prepareStatement(sql);
            if (params != null && params.length > 0) {
                setParameter(pre, params);
            }
            logger.debug("Start query:" + sql);
            rs = pre.executeQuery();
            result = connvertToList(rs);
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        } finally {
            MySQLAccess.getInstance().closeResultSet(rs);
            MySQLAccess.getInstance().closePreparedStatement(pre);
            MySQLAccess.getInstance().closeConn(con);
        }
        return result;
    }

    /**
     * select with custom sql query
     *
     * @param sql
     * @param params
     * @return list of objects
     * @throws java.sql.SQLException
     */
    public static List<Map<String, Object>> querySpecial(String sql, ParamObject[] params) throws SQLException, Exception {
        DbConnection con = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        List<Map<String, Object>> result = null;
        try {
            con = MySQLAccess.getInstance().getConn();
            pre = con.prepareStatement(sql);
            if (params != null && params.length > 0) {
                setParameter(pre, params);
            }
            logger.debug("Start query:" + sql);
            rs = pre.executeQuery();
            result = connvertToList(rs);
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        } finally {
            MySQLAccess.getInstance().closeResultSet(rs);
            MySQLAccess.getInstance().closePreparedStatement(pre);
            MySQLAccess.getInstance().closeConn(con);
        }
        return result;
    }

    /**
     * select top records by @top parameter where clause alow null params
     * parameter all null
     *
     * @param tableName
     * @param top
     * @param whereClause
     * @param params
     * @return list of objects
     * @throws java.sql.SQLException
     */
    public static List<Map<String, Object>> top(String tableName, int top, String whereClause, ParamObject[] params) throws SQLException, Exception {
        DbConnection con = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        List<Map<String, Object>> result = null;
        String sql = "select top " + top;
        if (whereClause == null || whereClause.isEmpty()) {
            sql += " where " + whereClause;
        }
        sql += " from " + tableName;
        try {
            con = MySQLAccess.getInstance().getConn();
            pre = con.prepareStatement(sql);
            if (params != null && params.length > 0) {
                setParameter(pre, params);
            }
            logger.debug("Start query:" + sql);
            rs = pre.executeQuery();
            result = connvertToList(rs);
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        } finally {
            MySQLAccess.getInstance().closeResultSet(rs);
            MySQLAccess.getInstance().closePreparedStatement(pre);
            MySQLAccess.getInstance().closeConn(con);
        }
        return result;
    }

    /**
     * update multiple records
     *
     * @param sql
     * @param parameters
     * @return result array when execute records
     * @throws java.sql.SQLException
     */
    public static int[] UpdateData(String sql, List<ParamObject[]> parameters) throws SQLException, Exception {
        DbConnection conn = null;
        PreparedStatement pre = null;
        try {
            conn = MySQLAccess.getInstance().getConn();
            pre = conn.prepareStatement(sql);
            if (parameters.size() > 0) {
                for (ParamObject[] params : parameters) {
                    setParameter(pre, params);
                    pre.addBatch();
                }
            }
            logger.debug("Start query:" + sql);
            return pre.executeBatch();
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        } finally {
            try {
                if (conn != null) {
                    conn.commit();
                }
            } catch (SQLException ex) {
                throw new SQLException(ex.getMessage(), ex);
            }
            MySQLAccess.getInstance().closePreparedStatement(pre);
            MySQLAccess.getInstance().closeConn(conn);
        }
    }

    /**
     * update single records
     *
     * @param sql
     * @param params
     * @return
     * @throws java.sql.SQLException
     */
    public static int updateData(String sql, ParamObject[] params) throws SQLException, Exception {
        DbConnection conn = null;
        PreparedStatement pre = null;
        try {
            conn = MySQLAccess.getInstance().getConn();
            pre = conn.prepareStatement(sql);
            if (params.length > 0) {
                setParameter(pre, params);
                pre.addBatch();
            }
            logger.debug("Start query:" + sql);
            return pre.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        } finally {
            try {
                if (conn != null) {
                    conn.commit();
                }
            } catch (SQLException ex) {
                throw new SQLException(ex.getMessage(), ex);
            }
            MySQLAccess.getInstance().closePreparedStatement(pre);
            MySQLAccess.getInstance().closeConn(conn);
        }
    }
    
    public static long callFunctionReturnLong(String sql, ParamObject[] params) throws Exception {
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
            logger.debug("Start query:" + sql);
            // Execute the stored procedure
            cs.executeUpdate();
            // Call a procedure with one OUT parameter
            total = cs.getLong(1);
        } catch (Exception se) {
            throw new Exception(se.getMessage(), se);
        } finally {
            MySQLAccess.getInstance().closeCallableStatement(cs);
            MySQLAccess.getInstance().closeConn(conn);
        }
        return total;
    }
    
    private static List<Map<String, Object>> connvertToList(ResultSet resultSet) throws SQLException {
        List<Map<String, Object>> records = new ArrayList();
        if (resultSet != null) {
            while (resultSet.next()) {
                int cols = resultSet.getMetaData().getColumnCount();
                Map<String, Object> record = new HashMap<>();
                for (int i = 0; i < cols; i++) {
                    Object boj = resultSet.getObject(i + 1);
                    String clumnName = resultSet.getMetaData().getColumnName(i + 1);
                    record.put(clumnName, boj);
//                    resultSet.getMetaData().get
                }
                records.add(record);
            }
            return records;
        }
        return null;
    }
    
    private static void setParameter(PreparedStatement ps, ParamObject[] params) throws SQLException {
        int idx = 1;
        for (ParamObject param : params) {
            int typeParam = param.getParamType();
            String value = param.getValue();
            switch (typeParam) {
                case ParamObject.TYPE_BYTE:
                    byte vByte = ParamObject.convertToByte(value);
                    ps.setByte(idx, vByte);
                    break;
                case ParamObject.TYPE_SHOT:
                    short vShort = ParamObject.convertToShort(value);
                    ps.setShort(idx, vShort);
                    break;
                case ParamObject.TYPE_INT:
                    int vInt = ParamObject.convertToInt(value);
                    ps.setInt(idx, vInt);
                    break;
                case ParamObject.TYPE_FLOAT:
                    float vFloat = ParamObject.convertToFloat(value);
                    ps.setFloat(idx, vFloat);
                    break;
                case ParamObject.TYPE_DOUBLE:
                    double vDouble = ParamObject.convertToDouble(value);
                    ps.setDouble(idx, vDouble);
                    break;
                case ParamObject.TYPE_LONG:
                    long vLong = ParamObject.convertToLong(value);
                    ps.setLong(idx, vLong);
                    break;
                case ParamObject.TYPE_BOOLEAN:
                    boolean vBoolean = ParamObject.convertToBoolean(value);
                    ps.setBoolean(idx, vBoolean);
                    break;
                case ParamObject.TYPE_STRING:
                    ps.setString(idx, value);
                    break;
                case ParamObject.TYPE_DATE:
                    java.sql.Date vDate = ParamObject.convertToDate(value);
                    ps.setDate(idx, vDate);
                    break;
                default:
                    break;
            }
            idx++;
        }
    }
}
