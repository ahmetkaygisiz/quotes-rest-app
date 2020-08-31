package com.akua.service;

import com.akua.domain.Quotes;
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

    public void insertDataFromJsonFile(String jsonFile){
        try {
            Type collectionType = new TypeToken<List<Quotes>>(){}.getType();
            JsonArray element = Jsons.readJsonFile(jsonFile);
            Collection<Quotes> quotes = Jsons.asCollection(element, collectionType);

            for(Quotes q : quotes){
                quotesRepository.insertData(new Quotes(null,q.getQuote(),q.getAuthor()));
            }
        } catch (FileNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public String getRandomQuote() throws SQLException {
        Quotes q = quotesRepository.getRandomQuote();

        return Jsons.objectToJson(q);
    }

    public String getAllQuotes() throws SQLException {
        List<Quotes> quoteList = quotesRepository.getAllQuotes();

        return Jsons.objectToJson(quoteList);
    }

    public void createTable() throws SQLException {
        quotesRepository.createTable();
    }

    public void dropTable() throws SQLException {
        quotesRepository.dropTable();
    }
}
