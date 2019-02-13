package com.ibeetl.admin.core.web.query;

import com.ibeetl.admin.core.annotation.Query;

/**
 * @Author: chenxi
 * @Date: 2019-02-13 10:29
 * @description:
 **/
public class SearchItemQuery  extends PageParam {
    @Query(name="网站",display=true,fuzzy=true)
    private Integer siteId ;

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer website) {
        this.siteId = website;
    }
}
