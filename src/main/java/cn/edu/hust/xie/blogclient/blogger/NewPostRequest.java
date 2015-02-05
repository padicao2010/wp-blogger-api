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
public class NewPostRequest extends Request {

    private static final String METHOD_NAME = "blogger.newPost";
    
    public NewPostRequest(BloggerCore c, String content, boolean publish) {
        super(METHOD_NAME);
        params = new RequestParam[6];
        params[0] = c.getAppKey();
        params[1] = c.getBlogId();
        params[2] = c.getUserName();
        params[3] = c.getPassWord();
        params[4] = new RequestParam(XMLTags.STRING, content);
        params[5] = new RequestParam(XMLTags.BOOLEAN, publish ? "1" : "0");
    }
    @Override
    public Response getResponseInstance() {
        return new NewPostResponse();
    }
    
}
