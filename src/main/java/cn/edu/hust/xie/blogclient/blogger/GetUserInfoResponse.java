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
public class GetUserInfoResponse extends Response{

    private static final String USER_ID = "userid";
    private static final String NICK_NAME = "nickname";
    private static final String FIRST_NAME = "firstname";
    private static final String LAST_NAME = "lastname";
    private static final String URL = "url";
    
    private UserInfo user;
    public GetUserInfoResponse() {
        user = new UserInfo();
    }
    @Override
    public void loadXML(Object obj) {
        Map<String, Object>map = (Map<String, Object>)obj;
        user.userId = Integer.valueOf((String)map.get(USER_ID));
        user.nickName = (String)map.get(NICK_NAME);
        user.firstName = (String)map.get(FIRST_NAME);
        user.lastName = (String)map.get(LAST_NAME);
        user.url = (String)map.get(URL);
    }
    
    @Override
    public String toString() {
        return user.toString();
    }
}
