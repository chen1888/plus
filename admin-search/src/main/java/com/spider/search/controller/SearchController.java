package com.spider.search.controller;

import com.spider.search.mapper.SearchItemMapper;
import com.spider.search.service.SearchService;
import com.spider.search.vo.SearchItem;
import com.spider.search.vo.SearchSite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @Author: chenxi
 * @Date: 2018-11-20 17:56
 * @description:
 **/
@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;
    @Autowired
    private SearchItemMapper searchItemMapper;

    @RequestMapping("test")
    public @ResponseBody SearchSite test(String keywords,String origin){
        return searchService.search(keywords,origin);
    }


    @RequestMapping("test1")
    public @ResponseBody String test1(String keywords,String origin){
        SearchItem searchItem = new SearchItem();
        searchItem.setSiteId(1);
        searchItem.setBaseUri("http:");
        searchItem.setBookItem(".res-book-item");
        searchItem.setBookPicture(".book-img-box img[src]");
        searchItem.setBookPictureEx("src");
        searchItem.setBookName(".book-mid-info h4");
        searchItem.setBookIntro(".book-mid-info .intro");
        searchItem.setBookUpdate(".book-mid-info .update a");
        searchItem.setBookWords(".book-right-info .total p");
        searchItem.setBookAuthor(".book-mid-info a.name");
        searchItem.setBookWordsEx("总字数");
        searchItem.setCreateTime(new Date());
        searchItem.setUpdateTime(new Date());
        searchItemMapper.insert(searchItem);
        return null;
    }


}
