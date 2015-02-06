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

/**
 *
 * @author padicao
 */
public class CategoryInfo {
    public String categoryId;
    public String parentId;
    public String categoryName;
    public String categoryDescription;
    public String description;
    public String htmlUrl;
    public String rssUrl;
    
    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %s, %s, %s",
                categoryId, parentId, categoryName, categoryDescription,
                description, htmlUrl, rssUrl);
    }
}
