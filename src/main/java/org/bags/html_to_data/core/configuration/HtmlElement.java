package org.bags.html_to_data.core.configuration;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;

public enum HtmlElement {
    ATTRIBUTES("attributes",element -> element.attributes().asList().stream().collect(Collectors.toMap(Attribute::getKey, Attribute::getValue))),
    TAG_NAME("tagName",Element::tagName),
    OWN_TEXT("ownText",Element::ownText),
    ATTRIBUTES_SIZE("attributeSize",Element::attributesSize),
    CHILDREN_SIZE("childrenSize",Element::childNodeSize),
    CSS_SELECTOR("cssSelector",Element::cssSelector),
    CLASS_NAME("className",Element::className),
    CLASS_NAMES("classNames",Element::classNames),
    DATA("data",Element::data),
    DATA_SET("dataSet",Element::dataset),
    HAS_PARENT("hasParent",Element::hasParent),
    HAS_TEXT("hasText",Element::hasText),
    HTML("html",Element::html),
    ID("id",Element::id),
    IS_BLOCK("isBlock",Element::isBlock),
    NORMAL_NAME("normalName",Element::normalName),
    SIBLING_INDEX("childIndex",Element::siblingIndex),
    TEXT("text",Element::text),
    VAL("val",Element::val),
    WHOLE_OWN_TEXT("wholeOwnText",Element::wholeOwnText),
    WHOLE_TEXT("wholeText",Element::wholeText),
    BASE_URI("baseURI",Element::baseUri);
    public final String key;
    public final Function<Element,?> valueExtractor;
    private HtmlElement(String key, Function<Element, ?> valueExtractor){
        this.valueExtractor = valueExtractor;
        this.key = key;
    }
    
}
