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

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author padicao
 */
public class GetRecentPostsResponse extends Response {

    private List<PostInfo> posts;
    
    public GetRecentPostsResponse() {
        posts = new ArrayList<PostInfo>();
    }
    
    public List<PostInfo> getPosts() {
        return posts;
    }
    
    @Override
    public void loadXML(Object obj) {
        posts.clear();
        List<Object> postObjList = (List<Object>)obj;
        for(Object p : postObjList) {
            GetPostResponse postRes = new GetPostResponse();
            postRes.loadXML(p);
            PostInfo post = postRes.getPost();
            posts.add(post);
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(PostInfo p : posts) {
            sb.append(p.toString()).append("\n");
        }
        return sb.toString();
    }
}
