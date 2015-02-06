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

import cn.edu.hust.xie.blogclient.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author padicao
 */
public class GetCategoriesResponseMW extends Response {

    private static final String CATEGORY_ID = "categoryId";
    private static final String PARENT_ID = "parentId";
    private static final String CATEGORY_NAME = "categoryName";
    private static final String CATEGORY_DESCRIPTION = "categoryDescription";
    private static final String DESCRIPTION = "description";
    private static final String HTML_URL = "htmlUrl";
    private static final String RSS_URL = "rssUrl";
    
    private List<CategoryInfo> categories;
    
    public GetCategoriesResponseMW() {
        categories = new ArrayList<CategoryInfo>();
    }

    public List<CategoryInfo> getCategories() {
        return categories;
    }
    
    @Override
    public void loadXML(Object obj) {
        categories.clear();
        List<Object> objList = (List<Object>)obj;
        for(Object o : objList) {
            Map<String, Object> map = (Map<String, Object>)o;
            CategoryInfo ci = new CategoryInfo();
            ci.categoryId = (String)map.get(CATEGORY_ID);
            ci.parentId = (String)map.get(PARENT_ID);
            ci.categoryName = (String)map.get(CATEGORY_NAME);
            ci.categoryDescription = (String)map.get(CATEGORY_DESCRIPTION);
            ci.description = (String)map.get(DESCRIPTION);
            ci.htmlUrl = (String)map.get(HTML_URL);
            ci.rssUrl = (String)map.get(RSS_URL);
            categories.add(ci);
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(CategoryInfo ci : categories) {
            sb.append(ci.toString()).append("\n");
        }
        return sb.toString();
    }
    
}
