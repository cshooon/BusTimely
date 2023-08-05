package hoon.sth.bustimely.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemList {
    @JacksonXmlProperty(localName = "arrmsg1")
    private String arrmsg1;

    @JacksonXmlProperty(localName = "arrmsg2")
    private String arrmsg2;

    @JacksonXmlProperty(localName = "stNm")
    private String stNm;
}
