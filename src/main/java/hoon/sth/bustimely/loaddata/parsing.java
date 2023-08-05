package hoon.sth.bustimely.loaddata;

import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class parsing {
    public static void main(String[] args) {
        Document xml;
        try {
            URL url = new URL("http://ws.bus.go.kr/api/rest/arrive/getArrInfoByRouteAll" +
                    "?serviceKey=VHXolF1A3nhOwhk%2FeXEk90QbCM9jdiNZeW3UARrYPqMHX9sQwmlJr5hirxBFdwlT3" +
                    "VjJQA8nGqkHt1oBEvO5tg%3D%3D&busRouteId=124900003");
            URLConnection urlConnect = url.openConnection();
            urlConnect.connect();

            InputSource is = new InputSource(urlConnect.getInputStream());

            xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);

            // root element 취득
            Element element = xml.getDocumentElement();

            // get itemList nodes
            NodeList itemListNodes = element.getElementsByTagName("itemList");

            for (int i = 0; i < itemListNodes.getLength(); i++) {
                Element itemListElement = (Element) itemListNodes.item(i);

                String arrmsg1 = itemListElement.getElementsByTagName("arrmsg1").item(0).getTextContent();
                String arrmsg2 = itemListElement.getElementsByTagName("arrmsg2").item(0).getTextContent();
                String stNm = itemListElement.getElementsByTagName("stNm").item(0).getTextContent();

                System.out.println("arrmsg1: " + arrmsg1);
                System.out.println("arrmsg2: " + arrmsg2);
                System.out.println("stNm: " + stNm);
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

