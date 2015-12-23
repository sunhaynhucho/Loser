/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.omap.database;

import com.kiemanh.vn.DbConnection;
import java.sql.SQLException;

/**
 *
 * @author KiemPQ
 */
public class DbImpl extends DbConnection {


    public DbImpl(int index, DbConnection conn) throws SQLException {
        super(index, conn.connection());
    }


}
