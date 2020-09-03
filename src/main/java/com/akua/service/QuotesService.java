package com.akua.service;

import com.akua.domain.Quote;
import com.akua.repository.QuotesRepository;
import com.akua.utils.Jsons;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class QuotesService {

    private QuotesRepository quotesRepository;

    public QuotesService(){
        quotesRepository = new QuotesRepository();
    }

    // SERIALIZE
    public String getJsonRandomQuote() {
        try {
            return Jsons.objectToJson(getRandomQuote());
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Error !";
    }

    public String getJsonQuoteById(int id){
        try {
            Quote q = getQuoteById(id);

            return Jsons.objectToJson(q);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Error !";
    }

    public String getJsonQuoteList() {
        try {
            return Jsons.objectToJson(getAllQuotes());
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Error !";
    }

    // Import Quotes from the Json File
    public void insertDataFromJsonFile(String jsonFile){
        try {
            Type collectionType = new TypeToken<List<Quote>>(){}.getType();
            JsonArray element = Jsons.readJsonFile(jsonFile);
            Collection<Quote> quotes = Jsons.asCollection(element, collectionType);

            for(Quote q : quotes){
                quotesRepository.insertQuotes(new Quote(null,q.getQuote(),q.getAuthor()));
            }
        } catch (FileNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    // Repository Operations
    public void save(Quote quotes) throws SQLException {
        quotesRepository.insertQuotes(quotes);
    }

    public void deleteById(int id) throws SQLException {
        quotesRepository.deleteQuotesById(id);
    }

    public void updateQuoteById(Quote quotes) throws SQLException {
        quotesRepository.updateQuotesById(quotes);
    }

    public Quote getRandomQuote() throws SQLException {
        return quotesRepository.getRandomQuote();
    }

    public Quote getQuoteById(int id) throws SQLException {
        return quotesRepository.getQuotesById(id);
    }

    public List<Quote> getAllQuotes() throws SQLException {
        return  quotesRepository.getAllQuotes();
    }

    // Table Operations
    public void createTable() throws SQLException {
        quotesRepository.createTable();
    }

    public void dropTable() throws SQLException {
        quotesRepository.dropTable();
    }
}
