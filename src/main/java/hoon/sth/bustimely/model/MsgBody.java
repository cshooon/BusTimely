package hoon.sth.bustimely.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MsgBody {

    @JacksonXmlProperty(localName = "itemList")
    private List<ItemList> itemList;

}
