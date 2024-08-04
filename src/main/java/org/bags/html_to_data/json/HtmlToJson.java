package org.bags.html_to_data.json;

import java.util.function.Function;

import org.bags.html_to_data.core.HtmlDataExtractor;
import org.bags.html_to_data.core.HtmlDataExtractor.HtmlExtractorBuilder;
import org.bags.html_to_data.core.configuration.HtmlElement;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.google.gson.Gson;

public class HtmlToJson {
    HtmlDataExtractor dataExtractor;
    Gson gson;
    private HtmlToJson(HtmlToJsonBuilder htmlToJsonBuilder){
        this.dataExtractor = htmlToJsonBuilder.extractorBuilder.build();
        this.gson = htmlToJsonBuilder.gson;
    } 
    public String toJson(Element document){
        return gson.toJson(dataExtractor.extractTreeNode(document));
    }
    public static String parse(Document document){
        return HtmlToJson.builder().extractsAllData().build().toJson(document);
    }
    public static HtmlToJsonBuilder builder(){
        return new HtmlToJsonBuilder();
    }
    public static class HtmlToJsonBuilder{
        private HtmlExtractorBuilder extractorBuilder = defaultBuilder();
        private Gson gson = defaultGson();
        private static HtmlExtractorBuilder defaultBuilder(){
            return HtmlDataExtractor.builder();
        }
        private static Gson defaultGson() {
            return new Gson().newBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        }

        public HtmlToJsonBuilder extracts(HtmlElement extractor) {
            extractorBuilder.extracts(extractor);
            return this;
        }
    
        public HtmlToJsonBuilder extractsAllData() {
            extractorBuilder.extractsAllData();
            return this;
        }

        public HtmlToJsonBuilder generateIdBy(Function<Element, String> generator) {
            extractorBuilder.generateIdBy(generator);
            return this;
        }

        public HtmlToJsonBuilder buildJsonWith(Gson gson){
            this.gson = gson;
            return this;
        }

        public HtmlToJson build() {
            return new HtmlToJson(this);
        }

    }
}
