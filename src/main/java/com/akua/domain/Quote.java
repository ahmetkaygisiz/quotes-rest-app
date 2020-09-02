package com.akua.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quote {
    @Expose
    Integer id;

    @SerializedName("quote")
    @Expose
    String quote;

    @SerializedName("author")
    @Expose
    String author;

    public Object[] toObjectArray() {
        return new Object[] { quote, author};
    }

    // for deserialize
    private static class QuotesTypeToken extends TypeToken<Quote> {}
    public static final Type TYPE_TOKEN = new QuotesTypeToken().getType();
}
