package com.infore.common.xml;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;

public class Xml {

    public SortedMap<String, String> parseWXPayXml(String xml) {
        if (xml == null || xml.trim().isEmpty()) {
            return null;
        }

        SortedMap<String, String> params = new TreeMap<>();
        try (StringReader reader = new StringReader(xml)) {
            SAXBuilder builder = new SAXBuilder();
            InputSource source = new InputSource(reader);
            Document doc = builder.build(source);
            Element root = doc.getRootElement();
            List<Element> elements = root.getChildren();
            for (Element e : elements) {
                String k = e.getName();
                String v = e.getTextNormalize();
                params.put(k, v);
            }
        } catch (JDOMException | IOException e) {
            throw new IllegalArgumentException("Xml [" + xml + "] parse error.");
        }

        return params;
    }

    public Map<String, Object> parseXml(String xml) {
        if (xml == null || xml.trim().isEmpty()) {
            return null;
        }

        Map<String, Object> map = new LinkedHashMap<>();
        try (StringReader reader = new StringReader(xml)) {
            SAXBuilder builder = new SAXBuilder();
            InputSource source = new InputSource(reader);
            Document doc = builder.build(source);
            Element root = doc.getRootElement();
            Object rootContent = parseElement(root);
            map.put(root.getName(), rootContent);
            return map;
        } catch (JDOMException | IOException e) {
            throw new IllegalArgumentException("Xml [" + xml + "] parse error.");
        }
    }

    @SuppressWarnings("unchecked")
    private Object parseElement(Element e) {
        String text = e.getTextNormalize();
        List<Attribute> attrs = e.getAttributes();
        List<Element> eles = e.getChildren();
        if (attrs.isEmpty() && eles.isEmpty()) {
            return text;
        } else {
            Map<String, Object> map = new LinkedHashMap<>();
            if (!text.equals("")) {
                map.put("#", text);
            }

            for (Attribute attr : attrs) {
                map.put("@" + attr.getName(), attr.getValue());
            }

            for (Element ele : eles) {
                String name = ele.getName();
                Object newContent = parseElement(ele);
                Object content = map.get(ele.getName());
                if (content == null) {
                    map.put(name, newContent);
                } else {
                    if (content instanceof List) {
                        ((List<Object>) content).add(newContent);
                    } else {
                        List<Object> list = new ArrayList<>();
                        list.add(content);
                        list.add(newContent);
                        map.put(name, list);
                    }
                }
            }

            return map;
        }
    }

}
