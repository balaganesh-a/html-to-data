package org.bags.json;

import java.io.IOException;

import org.bags.html_to_data.core.configuration.HtmlElement;
import org.bags.html_to_data.json.HtmlToJson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.gson.Gson;

public class Main {

    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.parse("<body id = 'abc'>asdfs<br></body>");
        // var doc1 = Jsoup.connect("https://www.google.com/chrome/").get();
        var htmlToJson = HtmlToJson.builder().extracts(HtmlElement.ATTRIBUTES)
            .extracts(HtmlElement.TAG_NAME)
            .extracts(HtmlElement.OWN_TEXT).build();
        var out = htmlToJson.toJson(doc.body());
        System.out.println(out);
    }




}
