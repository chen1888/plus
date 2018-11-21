package com.spider.search.service;

import com.spider.search.mapper.SearchItemMapper;
import com.spider.search.mapper.SearchSiteMapper;
import com.spider.search.util.HtmlUtil;
import com.spider.search.util.HttpUtils;
import com.spider.search.vo.SearchItem;
import com.spider.search.vo.SearchItemResut;
import com.spider.search.vo.SearchSite;
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

    public List<SearchItemResut> search(String keywords, String website) {
        List<SearchSite> sites = searchSiteMapper.findbyWebsite(website);
        if(sites==null || sites.size() == 0){
            return null;
        }
        Map<String, String> params = new HashMap<String, String>();
        params.put(sites.get(0).getKeywords(), keywords);
        String result = HttpUtils.sendGet(sites.get(0).getUrl(), params);
        List<SearchItem> searchItems = searchItemMapper.findBySiteId(sites.get(0).getId());
        if(searchItems.size() == 0 || searchItems == null){
            return null;
        }

        System.out.println("结果是:" + result);
        return HtmlUtil.parse(result,searchItems.get(0));
    }






}
