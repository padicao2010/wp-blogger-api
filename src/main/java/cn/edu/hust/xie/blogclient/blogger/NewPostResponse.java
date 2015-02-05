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
public class NewPostResponse extends Response {

    private int postId;
    
    public NewPostResponse() {
        postId = -1;
    }
    
    @Override
    public void loadXML(Object obj) {
        String s = (String)obj;
        //System.out.println(s);
        postId = Integer.valueOf(s);
    }
    
    public int getPostId() {
        return postId;
    }
    
    @Override
    public String toString() {
        return String.format("PostId : %d", postId);
    }
}
