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

import java.util.Map;
import java.util.Map.Entry;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author padicao
 */
public class RequestParam {
    public RequestParam(String t, Object v) {
        type = t;
        value = v;
    }
    
    private String type;
    private Object value;

    //public String getType() {
    //    return type;
    //}

    //public String getValue() {
    //    return value;
    //}
    
    private void constructStructElement(Document doc, Element root, Object obj) {
        Map<String, RequestParam> map = (Map<String, RequestParam>)obj;
        for(Entry<String, RequestParam> entry : map.entrySet()) {
            String name = entry.getKey();
            RequestParam param = entry.getValue();
            
            Element memberEle = doc.createElement(XMLTags.MEMBER);
            
            Element nameEle = doc.createElement(XMLTags.NAME);
            nameEle.setTextContent(name);
            memberEle.appendChild(nameEle);
            
            Element valueEle = doc.createElement(XMLTags.VALUE);
            Element typeEle = doc.createElement(param.type);
            if(param.type.equals(XMLTags.STRUCT)) {
                constructStructElement(doc, typeEle, param.value);
            } else {
                typeEle.setTextContent((String)param.value);
            }
            valueEle.appendChild(typeEle);
            memberEle.appendChild(valueEle);
            
            root.appendChild(memberEle);
        }
    }
    
    public Element getElement(Document doc) {
        Element paramEle = doc.createElement(XMLTags.PARAM);
        Element valueEle = doc.createElement(XMLTags.VALUE);
        Element typeEle = doc.createElement(type);
        if(type.equals(XMLTags.STRUCT)) {
            constructStructElement(doc, typeEle, value);
        } else {
            typeEle.setTextContent((String)value);
        }
        valueEle.appendChild(typeEle);
        paramEle.appendChild(valueEle);
        return paramEle;
    }
}
