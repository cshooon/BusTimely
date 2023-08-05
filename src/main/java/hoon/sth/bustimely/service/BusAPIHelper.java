package hoon.sth.bustimely.service;

import org.springframework.stereotype.Component;

import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BusAPIHelper {
    private static final String BASE_URL = "http://ws.bus.go.kr/api/rest/arrive/";
    private static final String SERVICE_KEY = "";

    public String buildURLForRoute(String routeId) {
        return BASE_URL + "getArrInfoByRouteAll?serviceKey=" + SERVICE_KEY + "&busRouteId=" + routeId;
    }

    public String buildURLForRouteAndStop(String stopId, String routeId, int order) {
        return BASE_URL + "getArrInfoByRoute?serviceKey=" + SERVICE_KEY + "&stId=" + stopId
                + "&busRouteId=" + routeId + "&ord=" + order;

    }

    public List<Map<String, String>> getAPIResponse(String url) throws Exception {
        Document xml = getXmlDocument(url);
        NodeList itemListNodes = xml.getDocumentElement().getElementsByTagName("itemList");

        List<Map<String, String>> results = new ArrayList<>();
        for (int i = 0; i < itemListNodes.getLength(); i++) {
            results.add(buildResultMap((Element) itemListNodes.item(i)));
        }
        return results;
    }

    private Document getXmlDocument(String url) throws Exception {
        URLConnection urlConnect = new URL(url).openConnection();
        urlConnect.connect();

        InputSource is = new InputSource(urlConnect.getInputStream());
        return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
    }

    private Map<String, String> buildResultMap(Element itemListElement) {
        Map<String, String> result = new HashMap<>();
        result.put("arrmsg1", getTextContent(itemListElement, "arrmsg1"));
        result.put("arrmsg2", getTextContent(itemListElement, "arrmsg2"));
        result.put("stNm", getTextContent(itemListElement, "stNm"));
        return result;
    }

    private String getTextContent(Element element, String tagName) {
        return element.getElementsByTagName(tagName).item(0).getTextContent();
    }
}
