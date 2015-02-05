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
package cn.edu.hust.xie.blogclient.blogger;

/**
 *
 * @author padicao
 */
public class RequestParam {
    public RequestParam(String t, String v) {
        type = t;
        value = v;
    }
    
    private String type;
    private String value;

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
