package com.akua.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quotes {
    @Expose(serialize = false)
    Integer id;

    @SerializedName("quote")
    @Expose
    String quote;

    @SerializedName("author")
    @Expose
    String author;

}
