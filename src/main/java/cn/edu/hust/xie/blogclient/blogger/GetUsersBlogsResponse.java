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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author padicao
 */
public class GetUsersBlogsResponse extends Response{

    private static final String BLOG_ID = "blogid";
    private static final String HOME_PAGE = "url";
    private static final String BLOG_NAME = "blogName";
        
    private List<BlogInfo> blogs;

    public List<BlogInfo> getBlogs() {
        return blogs;
    }
    
    public GetUsersBlogsResponse() {
        blogs = new ArrayList<BlogInfo>();
    }
    @Override
    public void loadXML(Object obj) {
        blogs.clear();
        List<Object> objList = (List<Object>)obj;
        for(Object o : objList) {
            BlogInfo blog = new BlogInfo();
            Map<String, Object> map = (Map<String, Object>)o;
            blog.blogId = (String)map.get(BLOG_ID);
            blog.blogName = (String)map.get(BLOG_NAME);
            blog.homePage = (String)map.get(HOME_PAGE);
            blogs.add(blog);
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(BlogInfo b : blogs) {
            sb.append(b.toString()).append("; ");
        }
        return sb.toString();
    }
}
