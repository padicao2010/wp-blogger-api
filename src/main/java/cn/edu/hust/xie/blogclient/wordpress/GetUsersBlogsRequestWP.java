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

import cn.edu.hust.xie.blogclient.BlogCore;
import cn.edu.hust.xie.blogclient.Request;
import cn.edu.hust.xie.blogclient.RequestParam;
import cn.edu.hust.xie.blogclient.Response;

/**
 *
 * @author padicao
 */
public class GetUsersBlogsRequestWP extends Request  {

    private static final String METHOD_NAME = "wp.getUsersBlogs";
    
    public GetUsersBlogsRequestWP(BlogCore c) {
        super(METHOD_NAME);
        this.params = new RequestParam[2];
        params[0] = c.getUserName();
        params[1] = c.getPassWord();
    }
    
    @Override
    public Response getResponseInstance() {
        return new GetUsersBlogsResponseWP();
    }
    
}
