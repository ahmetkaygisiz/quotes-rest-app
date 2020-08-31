package com.akua;

import com.akua.domain.Quotes;
import com.akua.repository.QuotesRepository;

import static spark.Spark.*;
// Default Port 4567

public class App {
    public static void main(String[] args) {
        //get("/random-quote", (req,rest) -> "HelloWorld!");
        //get("/all-quote")
        try{
            QuotesRepository repository = new QuotesRepository();

            // drop table
           //repository.dropTable();

            // create table
            //repository.createTable();

            // Insert data
            /*Quotes q = new Quotes();
            q.setQuote("Benim adim hidir, elimden gelen budur.");
            q.setAuthor("Anonim");
            repository.insertData(q);
             */

            repository.getAllQuotes().forEach(i -> System.out.println(i));

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
