package com.akua.repository;

import com.akua.database.DatabaseManager;
import com.akua.domain.Quote;

import java.sql.SQLException;
import java.util.List;

public class QuotesRepository {

    private DatabaseManager dm = new DatabaseManager();

    public void createTable() throws SQLException {
        String query =
                "CREATE TABLE QUOTES " +
                        "(id SERIAL PRIMARY KEY," +
                        " quote TEXT," +
                        " author VARCHAR(255))";

        executeQuery(query);
    }

    public void dropTable() throws SQLException {
        String query = "DROP TABLE QUOTES";

        executeQuery(query);
    }

    public void insertQuotes(Quote quotes) throws SQLException {
        String query = "INSERT INTO QUOTES (quote, author) VALUES (?, ?)";

        executeQueryWithParams(query, quotes.toObjectArray());
    }

    public List<Quote> getAllQuotes() throws SQLException {
        String query = "SELECT * FROM QUOTES;";

        List<Quote> list = getQueryResults(query);

        return list;
    }

    public Quote getRandomQuote() throws SQLException {
        String query = "SELECT * FROM QUOTES ORDER BY RANDOM() LIMIT 1;";

        return getQuote(query);
    }

    public Quote getQuotesById(int id) throws SQLException {
        String query = "SELECT * FROM QUOTES WHERE id=" + id;

        return getQuote(query);
    }

    public void deleteQuotesById(int id) throws SQLException {
        String query = "DELETE FROM QUOTES WHERE id=" + id;

        executeQuery(query);
    }

    public void updateQuotesById(Quote quotes) throws SQLException {
        String query = "UPDATE QUOTES set quote = ?, author = ? where id = ?";

        executeQueryWithParams(query, new Object[]{quotes.getQuote(), quotes.getAuthor(), quotes.getId()});
    }


    public void executeQuery(String query) throws SQLException {
        dm.connect();
        dm.executePS(query);
        dm.close();
    }

    public void executeQueryWithParams(String query, Object[] params) throws SQLException{
        dm.connect();
        dm.executePSWithParams(query, params);
        dm.close();
    }

    public List<Quote> getQueryResults(String query) throws SQLException{
        dm.connect();
        List<Quote> quotes = dm.getResultSet(query);
        dm.close();

        return  quotes;
    }

    public Quote getQuote(String query) throws SQLException {
        dm.connect();
        Quote quote= dm.getResultSet(query).get(0);
        dm.close();

        return quote;
    }
}
