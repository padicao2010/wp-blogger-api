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
import java.util.Map;

/**
 *
 * @author padicao
 */
public class GetPostResponseWP extends Response {
    private static final String POST_ID = "post_id";
    private static final String POST_TITLE = "post_title";
    private static final String POST_STATUS = "post_status";
    private static final String POST_CONTENT = "post_content";
    
    private PostInfo post;
    
    public GetPostResponseWP() {
        post = new PostInfo();
    }
    
    public PostInfo getPost() {
        return post;
    }
    
    @Override
    public void loadXML(Object obj) {
        Map<String, Object> map = (Map<String, Object>)obj;
        post.title = (String)map.get(POST_TITLE);
        post.postId = (String)map.get(POST_ID);
        post.status = (String)map.get(POST_STATUS);
        post.content = (String)map.get(POST_CONTENT);
    }
        
    @Override
    public String toString() {
        return post.toString();
    }
}
