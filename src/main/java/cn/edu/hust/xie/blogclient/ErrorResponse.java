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

import cn.edu.hust.xie.blogclient.Response;
import java.util.Map;


/**
 *
 * @author padicao
 */
public class ErrorResponse extends Response {

    private final String FAULT_CODE = "faultCode";
    private final String FAULT_STRING = "faultString";
    
    private int faultCode;
    private String faultString;
    
    @Override
    public void loadXML(Object obj) {
        Map<String, Object> map = (Map<String, Object>)obj;
        faultCode = Integer.valueOf((String)map.get(FAULT_CODE));
        faultString = (String)map.get(FAULT_STRING);
    }
    
    @Override
    public String toString() {
        return String.format("%d: %s", faultCode, faultString);
    }
}
