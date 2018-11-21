package com.spider.search.service;

import com.spider.search.mapper.SearchItemMapper;
import com.spider.search.mapper.SearchSiteMapper;
import com.spider.search.util.HtmlUtil;
import com.spider.search.util.HttpUtils;
import com.spider.search.vo.SearchItem;
import com.spider.search.vo.SearchItemResut;
import com.spider.search.vo.SearchSite;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: chenxi
 * @Date: 2018-11-20 15:30
 * @description:
 **/
@Service
public class SearchService{

    @Autowired
    private SearchSiteMapper searchSiteMapper;
    @Autowired
    private SearchItemMapper searchItemMapper;

    public List<SearchItemResut> search(String keywords, String website,String pageSize,String pageNum) {
        List<SearchSite> sites = searchSiteMapper.findbyWebsite(website);
        if(sites.size() == 0){
            return null;
        }
        SearchSite site = sites.get(0);
        Map<String, String> params = new HashMap<>();
        params.put(site.getKeywords(), keywords);
        if(StringUtils.isNotBlank(pageNum)){
            params.put(site.getPagenum(), pageNum);
        }
        if(StringUtils.isNotBlank(pageSize)){
            params.put(site.getPagesize(),pageSize);
        }
        String result = HttpUtils.sendGet(site.getUrl(), params);
        List<SearchItem> searchItems = searchItemMapper.findBySiteId(site.getId());
        if(searchItems.size() == 0){
            return null;
        }

        System.out.println("结果是:" + result);
        return HtmlUtil.parse(result,searchItems.get(0));
    }







}
