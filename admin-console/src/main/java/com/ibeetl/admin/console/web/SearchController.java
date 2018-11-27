package com.ibeetl.admin.console.web;

import com.ibeetl.admin.console.service.SearchService;
import com.ibeetl.admin.console.web.query.UserQuery;
import com.ibeetl.admin.core.mapper.SearchItemMapper;
import com.ibeetl.admin.core.web.vo.Result;
import com.ibeetl.admin.core.web.vo.SearchItem;
import com.ibeetl.admin.core.web.vo.SearchItemResut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * @Author: chenxi
 * @Date: 2018-11-20 17:56
 * @description:
 **/
@Controller
public class SearchController {
    private static final String MODEL1 = "/search";
    private static final String MODEL2 = "/admin/search";

    private long count = 0;

    @Autowired
    private SearchService searchService;

    @RequestMapping(MODEL1 + "/test")
    public @ResponseBody Result test(String keywords, String origin,String pageSize,String pageNum){
        Result result = new Result();
        List<SearchItemResut> searchItemResuts = searchService.search(keywords,origin,pageSize,pageNum);
        result.setData(searchItemResuts);
        return result;

    }
    @RequestMapping(MODEL2 + "/save")
    public @ResponseBody Result save(SearchItem searchItem){
        Result result = new Result();
        searchService.save(searchItem);
        return result;

    }


    @GetMapping(MODEL2 + "/add.do")
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("/admin/search/add.html");
        return view;
    }

    @GetMapping(MODEL2 + "/site/index.do")
    public ModelAndView siteIndex() {
        ModelAndView view = new ModelAndView("/admin/search/siteIndex.html");
        view.addObject("search", UserQuery.class.getName());
        return view;
    }


//    @RequestMapping("/")
//    public String index(){
//        return "index";
//    }


}
