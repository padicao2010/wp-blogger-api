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
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author padicao
 */
public class DeletePostRequestWP extends Request {

    private static final String METHOD_NAME = "wp.deletePost";
    
    public DeletePostRequestWP(BlogCore c, PostInfo p) {
        super(METHOD_NAME);
        params = new RequestParam[4];
        params[0] = c.getBlogId();
        params[1] = c.getUserName();
        params[2] = c.getPassWord();
        params[3] = new RequestParam(XMLTags.I4, p.postId);
    }
    
    @Override
    public Response getResponseInstance() {
        return new DeletePostResponseWP();
    }
    
}
