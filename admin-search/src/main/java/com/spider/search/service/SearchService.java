package com.spider.search.service;

import com.spider.search.mapper.SearchMapper;
import com.spider.search.util.HttpUtils;
import com.spider.search.vo.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: chenxi
 * @Date: 2018-11-20 15:30
 * @description:
 **/
@Service
public class SearchService{

    @Autowired
    private SearchMapper searchMapper;

    public Search search(String keywords, String website) {
        Search search = searchMapper.selectByPrimaryKey(1);
        Map<String, String> params = new HashMap<String, String>();
        params.put(search.getKeywords(), keywords);
        String result = HttpUtils.sendGet(search.getUrl(), params);
        System.out.println("结果是:" + result);
        return search;
    }




}
