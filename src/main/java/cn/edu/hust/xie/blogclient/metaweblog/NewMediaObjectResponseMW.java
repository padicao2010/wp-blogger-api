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
package cn.edu.hust.xie.blogclient.metaweblog;

import cn.edu.hust.xie.blogclient.Response;
import java.util.Map;

/**
 *
 * @author padicao
 */
public class NewMediaObjectResponseMW extends Response {
    private static final String ID = "id";
    private static final String FILE = "file";
    private static final String URL = "url";
    private static final String TYPE = "type";
    
    private MediaInfo media;

    public NewMediaObjectResponseMW() {
        media = new MediaInfo();
    }
    
    public MediaInfo getMedia() {
        return media;
    }
    
    @Override
    public void loadXML(Object obj) {
        Map<String, Object> map = (Map<String, Object>)obj;
        media.id = (String)map.get(ID);
        media.file = (String)map.get(FILE);
        media.url = (String)map.get(URL);
        media.type = (String)map.get(TYPE);
    }
    
    @Override
    public String toString() {
        return media.toString();
    }
}
