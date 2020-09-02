package com.akua.database;

import com.akua.database.configuration.PostgresDatabaseConnection;
import com.akua.domain.Quote;

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

    public PreparedStatement getPreparedStatement(String query) throws SQLException {
        return conn.prepareStatement(query);
    }

    public PreparedStatement getPreparedStatementWithParams(String query, Object[] params) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(query);

        for(int i = 0; i < params.length; i++){
            ps.setObject(i + 1, params[i]);
        }

        return ps;
    }

    public boolean executePS(String query) throws SQLException {
        return getPreparedStatement(query).execute();
    }

    public boolean executePSWithParams(String query, Object[] params) throws SQLException{
        return getPreparedStatementWithParams(query, params).execute();
    }

    public List<Quote> getResultSet(String query) throws SQLException{
        ResultSet rs = getPreparedStatement(query).executeQuery();

        return resultSetToOArrayList(rs);
    }

    // Make Function Generic AGAIN !!!
    public List<Quote> resultSetToOArrayList(ResultSet rs) throws SQLException {
        List<Quote> dataList = new ArrayList<>();

        while( rs.next() ){
            Quote q = new Quote();

            q.setId(rs.getInt("id"));
            q.setAuthor(rs.getString("author"));
            q.setQuote(rs.getString("quote"));

            dataList.add(q);
        }

        return dataList;
    }
}
