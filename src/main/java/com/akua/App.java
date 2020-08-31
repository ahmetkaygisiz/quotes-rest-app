package com.akua;

import com.akua.service.QuotesService;

import static spark.Spark.*;

// Default Port 4567
public class App {
    public static void main(String[] args) {
        QuotesService service = new QuotesService();

        try{
            //service.insertDataFromJsonFile("./src/main/resources/quotes.json");

            get("/random-quote", (req,res) -> {
                res.type("application/json");
                return service.getRandomQuote();
            });

            get("/all", (req,res) -> {
                res.type("application/json");
                return service.getAllQuotes();
            });

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }
}
