package com.ibeetl.admin.core.web.query;

import com.ibeetl.admin.core.annotation.Query;
import com.ibeetl.admin.core.web.query.PageParam;

/**
 * @Author: chenxi
 * @Date: 2018-11-27 11:12
 * @description:
 **/
public class SearchSiteQuery extends PageParam {
    @Query(name="网站",display=true,fuzzy=true)
    private String website ;

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
