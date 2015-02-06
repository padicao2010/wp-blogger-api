package cn.edu.hust.xie.blogclient.metaweblog;

import cn.edu.hust.xie.blogclient.BlogCore;
import cn.edu.hust.xie.blogclient.Request;
import cn.edu.hust.xie.blogclient.RequestParam;
import cn.edu.hust.xie.blogclient.Response;
import cn.edu.hust.xie.blogclient.XMLTags;
import java.util.HashMap;
import java.util.Map;

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

/**
 *
 * @author padicao
 */
public class NewMediaObjectRequestMW extends Request {

    private static final String METHOD_NAME = "metaWeblog.newMediaObject";
    
    private static final String NAME = "name";
    private static final String TYPE = "type";
    private static final String BITS = "bits";
    private static final String OVERWRITE = "overwrite";
    
    public NewMediaObjectRequestMW(BlogCore c, String fileName, String type, 
            String content, boolean overwrite) {
        super(METHOD_NAME);
        params = new RequestParam[4];
        params[0] = c.getBlogId();
        params[1] = c.getUserName();
        params[2] = c.getPassWord();
        
        RequestParam nameParam = new RequestParam(XMLTags.STRING, fileName);
        RequestParam typeParam = new RequestParam(XMLTags.STRING, type);
        RequestParam contentParam = new RequestParam(XMLTags.BASE64, content);
        RequestParam overwriteParam = new RequestParam(XMLTags.BOOLEAN, overwrite ? "1" : "0");
        Map<String, RequestParam> map = new HashMap<String, RequestParam>();
        map.put(NAME, nameParam);
        map.put(TYPE, typeParam);
        map.put(BITS, contentParam);
        map.put(OVERWRITE, overwriteParam);
        
        params[3] = new RequestParam(XMLTags.STRUCT, map);
    }
    @Override
    public Response getResponseInstance() {
        
        return new NewMediaObjectResponseMW();
    }
    
}
