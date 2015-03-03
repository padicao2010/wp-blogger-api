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

/**
 *
 * @author padicao
 */
public class GetPostRequestWP extends Request {

    private static final String METHOD_NAME = "wp.getPost";
    
    public GetPostRequestWP(BlogCore c, int post, String[] fields) {
        super(METHOD_NAME);
        if(fields == null || fields.length == 0) {
            this.params = new RequestParam[4];
        } else {
            this.params = new RequestParam[5];
        }
        
        params[0] = c.getBlogId();
        params[1] = c.getUserName();
        params[2] = c.getPassWord();
        params[3] = new RequestParam(XMLTags.I4, Integer.toString(post));
        
        if(fields != null && fields.length > 0) {
            List<RequestParam> fieldList = new ArrayList<RequestParam>();
            for(String s : fields) {
                fieldList.add(new RequestParam(XMLTags.STRING, s));
            }
            params[4] = new RequestParam(XMLTags.ARRAY, fieldList);
        }
    }
    @Override
    public Response getResponseInstance() {
        return new GetPostResponseWP();
    }
    
}
