package com.akua;

import com.akua.domain.Quote;
import com.akua.service.QuotesService;
import com.akua.utils.Jsons;

import static spark.Spark.*;

// Default Port 4567
public class App {
    public static void main(String[] args) {
        QuotesService quotesService = new QuotesService();

        try{
            //quotesService.insertDataFromJsonFile("./src/main/resources/quotes.json");

            // port(8080);

            // GET - Get quotes randomly
            get("/quotes/random", (req,res) -> {
                res.type("application/json");
                return quotesService.getJsonRandomQuote();
            });

            // GET - Get quote by id
            get("/quotes/:id", (req,res) -> {
                res.type("application/json");

                return  quotesService.getJsonQuoteById(Integer.parseInt(req.params("id")));
            });

            // GET - Get all quotes
            get("/quotes", (req,res) -> {
                res.type("application/json");

                return quotesService.getJsonQuoteList();
            });

            // POST - Save a quote
            post("/quotes", (req,res) -> {
                res.type("application/json");
                res.status(201);

                // deserialize json
                Quote q = Jsons.jsonToObject(req.body(), Quote.TYPE_TOKEN);
                quotesService.save(q);

                return "Quote saved.";
            });

            // PUT - Update a quote by
            put("/quotes/:id", (req,res) -> {
                res.type("application/json");
                Quote q = Jsons.jsonToObject(req.body(), Quote.TYPE_TOKEN);

                Quote inDb = quotesService.getQuoteById(Integer.parseInt(req.params("id")));
                inDb.setAuthor(q.getAuthor());
                inDb.setQuote(q.getQuote());

                quotesService.updateQuoteById(inDb);

                return "Quote updated.";
            });

            // DELETE - Delete quote by id
            delete("/quotes/:id", (req,res) -> {
                res.type("application/json");
                String id = req.params("id");

                quotesService.deleteById(Integer.parseInt(id));
                return "Quote deleted " + id;
            });

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }
}
