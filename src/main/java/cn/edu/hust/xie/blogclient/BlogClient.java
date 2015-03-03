/*
 * Copyright 2015 padicao.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.edu.hust.xie.blogclient;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author padicao
 */
public class BlogClient {

    private final DocumentBuilderFactory docBuildFactory;
    private final DocumentBuilder docBuild;
    private final TransformerFactory transformFactory;
    private final Transformer transformer;
    private final HttpClient httpClient;
    private final String URL;
    
    public BlogClient(String url) throws Exception {
        this.URL = url;

        docBuildFactory = DocumentBuilderFactory.newInstance();
        docBuild = docBuildFactory.newDocumentBuilder();
        transformFactory = TransformerFactory.newInstance();
        transformer = transformFactory.newTransformer();

        httpClient = new DefaultHttpClient();
    }

    public Response doCall(Request req) throws Exception {
        HttpPost post = new HttpPost(URL);
        
        String reqData = getXmlFromReq(req);
        System.out.println(reqData);
        StringEntity params = new StringEntity(reqData, "UTF-8");
        post.setEntity(params);
        HttpResponse response = httpClient.execute(post);
        HttpEntity resEntity = response.getEntity();

        if (resEntity != null) {
            InputStream in = resEntity.getContent();
            Response res = formatInput(in, req.getResponseInstance());
            resEntity.consumeContent();
            return res;
        }
        
        return null;
    }

    private static Node getNode(NodeList nodes, String name) {
        for (int i = 0; i < nodes.getLength(); i++) {
            Node n = nodes.item(i);
            if (n.getNodeName().equals(name)) {
                return n;
            }
        }
        return null;
    }

    private static Object xml2Struct(Node node) {
        Map<String, Object> result = new HashMap<String, Object>();
        NodeList nodes = node.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node memberNod = nodes.item(i);
            if (!memberNod.getNodeName().equals(XMLTags.MEMBER)) {
                continue;
            }
            NodeList nameValues = memberNod.getChildNodes();
            Node nameNod = getNode(nameValues, XMLTags.NAME);
            String name = nameNod.getTextContent();
            Node valueNod = getNode(nameValues, XMLTags.VALUE);
            Object value = xml2Object(valueNod);
            result.put(name, value);
            //System.out.println(name + " : " + value);
        }
        return result;
    }

    private static Object xml2Array(Node node) {
        List<Object> result = new ArrayList<Object>();
        Node dataNod = getNode(node.getChildNodes(), XMLTags.DATA);
        NodeList nodes = dataNod.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node valueNod = nodes.item(i);
            if (valueNod.getNodeName().equals(XMLTags.VALUE)) {
                Object item = xml2Object(valueNod);
                result.add(item);
            }
        }
        return result;
    }

    private static Object xml2Object(Node node) {
        NodeList nodes = node.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node n = nodes.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                String name = n.getNodeName();
                if (name.equals(XMLTags.STRUCT)) {
                    return xml2Struct(n);
                } else if (name.equals(XMLTags.ARRAY)) {
                    return xml2Array(n);
                } else {
                    return n.getTextContent();
                }
            }
        }
        return null;
    }

    private ErrorResponse checkError(Node methodResponse) {
        Node faultEle = getNode(methodResponse.getChildNodes(), XMLTags.FAULT);
        if (faultEle != null) {
            Node valueNod = getNode(faultEle.getChildNodes(), XMLTags.VALUE);
            ErrorResponse ret = new ErrorResponse();
            Object xml = xml2Object(valueNod);
            ret.loadXML(xml);
            return ret;
        }
        return null;
    }

    private Response formatInput(InputStream in, Response response) throws SAXException, IOException {
        byte[] buffer = new byte[102400];
        int len = in.read(buffer);
        System.out.println(new String(buffer, 0, len));
        InputStream bin = new ByteArrayInputStream(buffer, 0, len);

        Document resultDoc = docBuild.parse(bin);
        
        Node methodResponse = getNode(resultDoc.getChildNodes(), XMLTags.METHOD_RESPONSE);
        if (methodResponse == null) {
            return null;
        }

        ErrorResponse errRes = checkError(methodResponse);
        if (errRes != null) {
            return errRes;
        }

        Node paramsNod = getNode(methodResponse.getChildNodes(), XMLTags.PARAMS);
        Node paramNod = getNode(paramsNod.getChildNodes(), XMLTags.PARAM);
        Node valueNod = getNode(paramNod.getChildNodes(), XMLTags.VALUE);

        response.loadXML(xml2Object(valueNod));
        return response;
    }

    private String getXmlFromReq(Request req) throws TransformerException, UnsupportedEncodingException {
        Document doc = docBuild.newDocument();

        doc.setXmlVersion("1.0");

        Element rootElement = doc.createElement(XMLTags.METHOD_CALL);
        Element methodEle = doc.createElement(XMLTags.METHOD_NAME);
        methodEle.setTextContent(req.getCallName());
        rootElement.appendChild(methodEle);

        Element paramsEle = doc.createElement(XMLTags.PARAMS);
        for (RequestParam p : req.getParams()) {
            paramsEle.appendChild(p.getElement(doc));
        }
        rootElement.appendChild(paramsEle);
        doc.appendChild(rootElement);

        Source xmlSource = new DOMSource(doc);
        StringWriter stringWriter = new StringWriter();
        Result result = new StreamResult(stringWriter);
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(xmlSource, result);

        String reqData = stringWriter.getBuffer().toString();

        return reqData;
    }
}
