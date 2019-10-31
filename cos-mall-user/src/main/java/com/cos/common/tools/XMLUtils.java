package com.cos.common.tools;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @desc : xml解析工具
 * @User: @Created by yangtk
 * @Date: @Date 2019/9/24 11:56
 * @Classname: XMLUtils
 * @To change this template use File | Settings | File Templates.
 */
public class XMLUtils {

    // 必须有一个根节点才可以
    public static Map<String, Object> xmlToMap(String xml) {
        SAXReader saxReader = new SAXReader();
        Document document = null;
        try {
            document = saxReader.read(new ByteArrayInputStream(xml.getBytes()));
            Map map = Dom2Map(document);
            System.out.println("map>>> " + map);
            return map;
        } catch (DocumentException e) {
            e.printStackTrace();
            System.out.println("xml解析map失败->原文件:" + xml);
        }
        return new HashMap<>();
    }

    /**
     * 将Document对象转为Map（String→Document→Map）
     *
     * @param
     * @return
     */
    @SuppressWarnings("rawtypes")
    private static Map<String, Object> Dom2Map(Document doc) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (doc == null) {
            return map;
        }
        Element root = doc.getRootElement();
        for (Iterator iterator = root.elementIterator(); iterator.hasNext(); ) {
            Element e = (Element) iterator.next();
            if (e.elements().size() > 0) {
                map.put(e.getName(), Dom2Map(e));
            } else {
                map.put(e.getName(), e.getText());
            }
        }
        return map;
    }

    /**
     * 将Element对象转为Map（String→Document→Element→Map）
     *
     * @param
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private static Map Dom2Map(Element e) {
        Map map = new HashMap();
        List list = e.elements();
        if (list.size() > 0) {
            for (Element iter : ((List<Element>) list)) {
                List mapList = new ArrayList();
                if (iter.elements().size() > 0) {
                    Map m = Dom2Map(iter);
                    if (map.get(iter.getName()) != null) {
                        Object obj = map.get(iter.getName());
                        if (!(obj instanceof List)) {
                            mapList = new ArrayList();
                            mapList.add(obj);
                            mapList.add(m);
                        }
                        if (obj instanceof List) {
                            mapList = (List) obj;
                            mapList.add(m);
                        }
                        map.put(iter.getName(), mapList);
                    } else
                        map.put(iter.getName(), m);
                } else {
                    if (map.get(iter.getName()) != null) {
                        Object obj = map.get(iter.getName());
                        if (!(obj instanceof List)) {
                            mapList = new ArrayList();
                            mapList.add(obj);
                            mapList.add(iter.getText());
                        }
                        if (obj instanceof List) {
                            mapList = (List) obj;
                            mapList.add(iter.getText());
                        }
                        map.put(iter.getName(), mapList);
                    } else
                        map.put(iter.getName(), iter.getText());//公共map resultCode=0
                }
            }
        } else
            map.put(e.getName(), e.getText());
        return map;
    }

    public static void main(String[] args) {
        String str1 = "<HEADER>" +
                "       <a>" +
                "       <DB_ID><![CDATA[SUCCESS]]></DB_ID>" +
                "       <DB_ID>EUe</DB_ID>" +
                "<DB_ID><b>b</b><c>ccc</c></DB_ID>" +
                "</a>" +
                "   </HEADER>";
        String str2 = "<ROOT>" +
                "  <HEADER>" +
                "      <POOL_ID>2</POOL_ID>" +
                "      <CHANNEL_ID>11</CHANNEL_ID>" +
                "      <USERNAME>tom</USERNAME>" +
                "      <PASSWORD>sss</PASSWORD>" +
                "  </HEADER>" +
                "  <BODY>" +
                "      <BUSLIST>" +
                "          <PHONE_NO>7107300212</PHONE_NO>" +
                "          <TRACE_ID>97D2C7D26224A2DAE9A1CB501E60F395</TRACE_ID>" +
                "          <TENANT_ID>EUR</TENANT_ID>" +
                "          <LANG>zh_CN</LANG>" +
                "      </BUSLIST>" +
                "      <BUSLIST>" +
                "          <PHONE_NO>2222300212</PHONE_NO>" +
                "          <TRACE_ID>444424A2DAE9A1CB501E60F395</TRACE_ID>" +
                "          <TENANT_ID>USA</TENANT_ID>" +
                "          <LANG>zh_CN</LANG>" +
                "      </BUSLIST>" +
                "  </BODY>" +
                "</ROOT>";
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("Request", "getData");
        Map<String, Object> innerMap = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        innerMap.put("test", list);
        innerMap.put("test2", "bbb");
        result.put("data", innerMap);
        System.out.println(createXmlByMap("xml", result));
    }

    public static String createXmlByMap(String parentName, Map<String, Object> params, boolean isCDATA) {
        return createXmlByMap(parentName,params,isCDATA,false);
    }

    public static String createXmlByMap(String parentName, Map<String, Object> params, boolean isCDATA,boolean format) {
        if (params == null || params.isEmpty() || StringUtils.isEmpty(parentName)) {
            return "";
        }
        Document doc = DocumentHelper.createDocument();
        doc.addElement(parentName);
        String xml = iteratorXml(doc.getRootElement(), params, isCDATA);
        if(format){
            return formatXML(xml);
        }else{
            return xml;
        }

    }

    /**
     * 通过Map创建XML,Map可以多层转换
     * 可以自定义parent节点
     *
     * @param params
     * @return String-->XML
     */
    public static String createXmlByMap(String parentName, Map<String, Object> params) {
        return createXmlByMap(parentName, params, false);
    }

    /**
     * MapToXml循环遍历创建xml节点
     * 此方法在value中加入CDATA标识符
     *
     * @param element 根节点
     *                //     * @param parentName 子节点名字
     * @param params  map数据
     * @return String-->Xml
     */

    @SuppressWarnings("unchecked")
    public static String iteratorXml(Element element, Map<String, Object> params, boolean isCDATA) {
        Element e = element;
        Set<String> set = params.keySet();
        for (Iterator<String> it = set.iterator(); it.hasNext(); ) {
            String key = (String) it.next();
            if (params.get(key) instanceof List) {
                List<Object> valList = (List<Object>) params.get(key);
                for (Object obj : valList) {
                    if (obj instanceof Map) {
                        iteratorXml(e.addElement(key), (Map<String, Object>) params.get(key), isCDATA);
                    } else {
                        e.addElement(key).addText(obj.toString());
                    }
                }
            } else if (params.get(key) instanceof Map) {
                iteratorXml(e.addElement(key), (Map<String, Object>) params.get(key), isCDATA);
            } else {
                String value = params.get(key) == null ? "" : params.get(key).toString();
                if (!isCDATA) {
                    e.addElement(key).addText(value);
                } else {
                    e.addElement(key).addCDATA(value);
                }
            }
        }
        return e.asXML();
    }

    /**
     * 格式化xml,显示为容易看的XML格式
     *
     * @param inputXML
     * @return
     */
    public static String formatXML(String inputXML) {
        String requestXML = null;
        XMLWriter writer = null;
        Document document = null;
        try {
            SAXReader reader = new SAXReader();
            document = reader.read(new StringReader(inputXML));
            if (document != null) {
                StringWriter stringWriter = new StringWriter();
                OutputFormat format = new OutputFormat("	", true);//格式化，每一级前的空格
                format.setNewLineAfterDeclaration(false);    //xml声明与内容是否添加空行
                format.setSuppressDeclaration(true);        //是否设置xml声明头部 设置为true的时候不声明头部信息
                format.setNewlines(true);        //设置分行
                writer = new XMLWriter(stringWriter, format);
                writer.write(document);
                writer.flush();
                requestXML = stringWriter.getBuffer().toString();
            }
            return requestXML;
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {

                }
            }
        }
    }


    /**
     * 通过XML转换为Map<String,Object>
     *
     * @param xml 为String类型的Xml
     * @return 第一个为Root节点，Root节点之后为Root的元素，如果为多层，可以通过key获取下一层Map
     */
//    public static Map<String, Object> createMapByXml(String xml) {
//        Document doc = null;
//        try {
//            doc = DocumentHelper.parseText(xml);
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        }
//        Map<String, Object> map = new HashMap<String, Object>();
//        if (doc == null)
//            return map;
//        Element rootElement = doc.getRootElement();
//        elementTomap(rootElement, map);
//        return map;
//    }

    /***
     *
     * XmlToMap核心方法，里面有递归调用
     *
     * @param
     * @param
     */
//    @SuppressWarnings("unchecked")
//    public static Map<String, Object> elementTomap(Element outele, Map<String, Object> outmap) {
//        List<Element> list = outele.elements();
//        int size = list.size();
//        if (size == 0) {
//            outmap.put(outele.getName(), outele.getTextTrim());
//        } else {
//            Map<String, Object> innermap = new HashMap<String, Object>();
//            for (Element ele1 : list) {
//                String eleName = ele1.getName();
//                Object obj = innermap.get(eleName);
//                if (obj == null) {
//                    elementTomap(ele1, innermap);
//                } else {
//                    if (obj instanceof java.util.Map) {
//                        List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
//                        list1.add((Map<String, Object>) innermap.remove(eleName));
//                        elementTomap(ele1, innermap);
//                        list1.add((Map<String, Object>) innermap.remove(eleName));
//                        innermap.put(eleName, list1);
//                    } else {
//                        elementTomap(ele1, innermap);
//                        ((List<Map<String, Object>>) obj).add(innermap);
//                    }
//                }
//            }
//            outmap.put(outele.getName(), innermap);
//        }
//        return outmap;
//    }

}