/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.omap.database;

import com.kiemanh.vn.DbConnection;
import java.sql.SQLException;

public class DbConnectionRefactoryImpl implements com.kiemanh.vn.DbConnectionRefactory {

//------------------------------------------------------------------------------
    public DbConnectionRefactoryImpl() {
    }

//------------------------------------------------------------------------------
    @Override
    public com.kiemanh.vn.DbConnection refactory(int index, DbConnection conn) throws SQLException {
        return new DbImpl(index, conn);
    }
}