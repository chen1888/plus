package com.ibeetl.admin.console.web;

import com.ibeetl.admin.console.service.SearchService;
import com.ibeetl.admin.core.mapper.SearchItemMapper;
import com.ibeetl.admin.core.web.vo.Result;
import com.ibeetl.admin.core.web.vo.SearchItem;
import com.ibeetl.admin.core.web.vo.SearchItemResut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * @Author: chenxi
 * @Date: 2018-11-20 17:56
 * @description:
 **/
@Controller
public class SearchController {
    private long count = 0;
    @Autowired
    private SearchService searchService;
    @Autowired
    private SearchItemMapper searchItemMapper;

    @RequestMapping("test")
    public @ResponseBody Result test(String keywords, String origin,String pageSize,String pageNum){
        Result result = new Result();
        List<SearchItemResut> searchItemResuts = searchService.search(keywords,origin,pageSize,pageNum);
        result.setData(searchItemResuts);
        return result;

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

//    @RequestMapping("/")
//    public String index(){
//        return "index";
//    }


}
