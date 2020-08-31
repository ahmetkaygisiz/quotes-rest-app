package com.akua.repository;

import com.akua.database.DatabaseManager;
import com.akua.domain.Quotes;

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

    public void insertData(Quotes quotes) throws SQLException {
        String query =
                "INSERT INTO QUOTES (quote, author) VALUES ('"+ quotes.getQuote()+"','"+quotes.getAuthor()+"');";

        executeQuery(query);
    }

    public List<Quotes> getAllQuotes() throws SQLException, InstantiationException, IllegalAccessException {
        String query = "SELECT * FROM QUOTES;";

        List<Quotes> list = getQueryResults(query);

        return list;
    }

    public Quotes getQuotesById(int id) throws SQLException {
        String query = "SELECT * FROM QUOTES WHERE id=" + id;

        return getQuote(query);
    }

    public void getQuotesCount(){
        String query = "SELECT COUNT(*) FROM public.QUOTES;";


    }

    public void executeQuery(String query) throws SQLException {
        dm.connect();
        dm.executePS(query);
        dm.close();
    }

    public List<Quotes> getQueryResults(String query) throws SQLException{
        dm.connect();
        List<Quotes> arr = dm.getQueryDataList(query);
        dm.close();

        return arr;
    }

    public Quotes getQuote(String query) throws SQLException {
        dm.connect();
        Quotes quotes = dm.getQueryDataList(query).get(0);
        dm.close();

        return quotes;
    }


}
