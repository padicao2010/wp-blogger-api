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

import cn.edu.hust.xie.blogclient.Response;
import java.util.Map;

/**
 *
 * @author padicao
 */
public class GetPostResponse extends Response{
    private static final String POST_ID = "postid";
    private static final String USER_ID = "userid";
    private static final String DATE_TIME = "dateCreated";
    private static final String CONTENT = "content";
    
    private PostInfo post;
    
    public GetPostResponse() {
        post = new PostInfo();
    }
    
    public PostInfo getPost() {
        return post;
    }
    
    @Override
    public void loadXML(Object obj) {
        Map<String, Object> map = (Map<String, Object>)obj;
        post.userId = (String)map.get(USER_ID);
        post.postId = (String)map.get(POST_ID);
        post.dateCreated = (String)map.get(DATE_TIME);
        post.content = (String)map.get(CONTENT);
    }
    
    @Override
    public String toString() {
        return post.toString();
    }
}
