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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author padicao
 */
public class GetPostsRequestWP extends Request {
    
    private static final String METHOD_NAME = "wp.getPosts";
    
    private Map<String, RequestParam> filterMap;
    private List<RequestParam> fieldList;
    
    public GetPostsRequestWP(BlogCore c) {
        super(METHOD_NAME);
        params = new RequestParam[5];
        params[0] = c.getBlogId();
        params[1] = c.getUserName();
        params[2] = c.getPassWord();
        filterMap = new HashMap<String, RequestParam>();
        fieldList = new ArrayList<RequestParam>();
        params[3] = new RequestParam(XMLTags.STRUCT, filterMap);
        params[4] = new RequestParam(XMLTags.ARRAY, fieldList);
    }
    
    public void addNumberFilter(int number) {
        filterMap.put("number", new RequestParam(XMLTags.I4, Integer.toString(number)));
    }
    
    public void addOffsetFilter(int offset) {
        filterMap.put("offset", new RequestParam(XMLTags.I4, Integer.toString(offset)));
    }
    
    public void addFieldsFilter(String[] fields) {
        if(fields == null || fields.length == 0) {
            return;
        }
        
        for(String f : fields) {
            fieldList.add(new RequestParam(XMLTags.STRING, f));
        }
    }
    
    @Override
    public Response getResponseInstance() {
        return new GetPostsResponseWP();
    }
    
}
