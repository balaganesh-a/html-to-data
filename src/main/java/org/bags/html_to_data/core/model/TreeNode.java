package org.bags.html_to_data.core.model;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.bags.html_to_data.core.configuration.HtmlElement;
import org.jsoup.nodes.Element;

public class TreeNode {
    private String id;
    Map<String, ?> data;
    private List<TreeNode> children;

    public TreeNode(Element element, Function<Element, String> idGenerator, Set<HtmlElement> extractors) {
        this.data = extractors.stream()
            .collect(Collectors.toMap(extractor -> extractor.key, extractor -> extractor.valueExtractor.apply(element)));
        this.id = idGenerator.apply(element);
        this.children = element.children().stream().map(child -> new TreeNode(child, idGenerator, extractors)).toList();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
}
