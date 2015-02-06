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
package cn.edu.hust.xie.blogclient;

/**
 *
 * @author padicao
 */
public class BlogCore{

    private RequestParam appKey;
    private RequestParam userName;
    private RequestParam passWord;
    private RequestParam blogId;

    public RequestParam getAppKey() {
        return appKey;
    }

    public void setAppKey(String app) {
        appKey = new RequestParam(XMLTags.STRING, app);
    }

    public RequestParam getUserName() {
        return userName;
    }

    public void setUserName(String user) {
        userName = new RequestParam(XMLTags.STRING, user);
    }

    public RequestParam getPassWord() {
        return passWord;
    }

    public void setPassWord(String pw) {
        passWord = new RequestParam(XMLTags.BASE64, pw);
    }
    
    public RequestParam getBlogId() {
        return blogId;
    }
    
    public void setBlogId(int blogid) {
        blogId = new RequestParam(XMLTags.STRING, String.format("%d", blogid));
    }
}
