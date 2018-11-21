package com.spider.search.controller;

import com.spider.search.service.SearchService;
import com.spider.search.vo.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: chenxi
 * @Date: 2018-11-20 17:56
 * @description:
 **/
@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;

    @RequestMapping("test")
    public @ResponseBody Search test(){
        return searchService.search("1","d");
    }
}
