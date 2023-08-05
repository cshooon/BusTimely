package hoon.sth.bustimely.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

@JacksonXmlRootElement(localName = "ServiceResult")
@Getter
@Setter
public class ServiceResult {

    @JacksonXmlProperty(localName = "msgBody")
    private MsgBody msgBody;

}







