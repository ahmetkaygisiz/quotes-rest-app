package com.akua.database;

import com.akua.database.configuration.PostgresDatabaseConnection;
import com.akua.domain.Quotes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private final PostgresDatabaseConnection pdc ;
    private Connection conn ;

    public DatabaseManager(){
        pdc = new PostgresDatabaseConnection();
    }

    public void connect (){
        conn = pdc.connect();
    }

    public void close() throws SQLException {
        conn.close();
    }

    public PreparedStatement getPreparedStatement(String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }

    public boolean executePS(String query) throws SQLException {
        return getPreparedStatement(query).execute();
    }

    public List<Quotes> getQueryDataList(String query) throws SQLException{
        ResultSet rs = getPreparedStatement(query).executeQuery();

        return resultSetToOArrayList(rs);
    }

    // Make Function Generic AGAIN !!!
    public List<Quotes> resultSetToOArrayList(ResultSet rs) throws SQLException {
        List<Quotes> dataList = new ArrayList<>();

        while( rs.next() ){
            Quotes q = new Quotes();

            q.setId(rs.getInt("id"));
            q.setAuthor(rs.getString("author"));
            q.setQuote(rs.getString("quote"));

            dataList.add(q);
        }

        return dataList;
    }
}
