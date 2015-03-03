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
package cn.edu.hust.xie.blogclient.wordpress;

import cn.edu.hust.xie.blogclient.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author padicao
 */
public class GetTermsResponseWP extends Response {

    private static final String TERM_ID = "term_id";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    
    private List<TermInfo> terms;
    
    public GetTermsResponseWP() {
        terms = new ArrayList<TermInfo>();
    }
    
    @Override
    public void loadXML(Object obj) {
        terms.clear();
        List<Object> objList = (List<Object>)obj;
        for(Object o : objList) {
            Map<String, Object> map = (Map<String, Object>)o;
            TermInfo ti = new TermInfo();
            ti.id = (String)map.get(TERM_ID);
            ti.name = (String)map.get(NAME);
            ti.description = (String)map.get(DESCRIPTION);
            terms.add(ti);
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(TermInfo ti : terms) {
            sb.append("{").append(ti.toString()).append("}; ");
        }
        return sb.toString();
    }
}
