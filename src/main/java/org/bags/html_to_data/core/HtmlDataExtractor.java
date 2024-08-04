package org.bags.html_to_data.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.bags.html_to_data.core.configuration.HtmlElement;
import org.bags.html_to_data.core.model.TreeNode;
import org.jsoup.nodes.Element;

public class HtmlDataExtractor {
    private final Set<HtmlElement> elementDataExtractors;
    private final Function<Element, String> idGenerator;

    private HtmlDataExtractor(HtmlExtractorBuilder builder) {
        this.elementDataExtractors = builder.elementDataExtractors;
        this.idGenerator = builder.idGenerator;
    }

    public static HtmlExtractorBuilder builder() {
        return new HtmlExtractorBuilder();
    }
    
    public TreeNode extractTreeNode(Element document){
        return new TreeNode(document, idGenerator, elementDataExtractors);
    }

    public static class HtmlExtractorBuilder {
        Set<HtmlElement> elementDataExtractors = new HashSet<>();
        Function<Element, String> idGenerator = defaultIdGenerator();

        public HtmlExtractorBuilder extracts(HtmlElement extractor) {
            elementDataExtractors.add(extractor);
            return this;
        }
        
        public HtmlExtractorBuilder extractsAllData() {
            this.elementDataExtractors = Arrays.stream(HtmlElement.values()).collect(Collectors.toSet());
            return this;
        }

        public HtmlExtractorBuilder generateIdBy(Function<Element, String> generator) {
            this.idGenerator = generator;
            return this;
        }

        public HtmlDataExtractor build() {
            return new HtmlDataExtractor(this);
        }

        private static Function<Element, String> defaultIdGenerator() {
            var id = new AtomicLong();
            return element -> String.valueOf(id.getAndIncrement());
        }

    }

    public Set<HtmlElement> getElementDataExtractors() {
        return Collections.unmodifiableSet(elementDataExtractors);
    }

    public Function<Element, String> getIdGenerator() {
        return idGenerator;
    }

}
