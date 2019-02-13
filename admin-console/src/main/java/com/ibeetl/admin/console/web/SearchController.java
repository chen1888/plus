package com.ibeetl.admin.console.web;

import com.github.pagehelper.PageInfo;
import com.ibeetl.admin.core.service.SearchService;
import com.ibeetl.admin.core.web.query.SearchItemQuery;
import com.ibeetl.admin.core.web.query.SearchSiteQuery;
import com.ibeetl.admin.core.util.ConvertUtil;
import com.ibeetl.admin.core.web.JsonResult;
import com.ibeetl.admin.core.web.vo.Result;
import com.ibeetl.admin.core.web.vo.SearchItem;
import com.ibeetl.admin.core.web.vo.SearchItemResut;
import com.ibeetl.admin.core.web.vo.SearchSite;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping(MODEL2 + "/add.do")
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("/admin/search/add.html");
        return view;
    }

    @GetMapping(MODEL2 + "/site/index.do")
    public ModelAndView siteIndex() {
        ModelAndView view = new ModelAndView("/admin/search/site_index.html");
        view.addObject("search", SearchSiteQuery.class.getName());
        return view;
    }

    @GetMapping(MODEL2 + "/item/index.do")
    public ModelAndView itemIndex() {
        ModelAndView view = new ModelAndView("/admin/search/item_index.html");
        view.addObject("search", SearchSiteQuery.class.getName());
        return view;
    }

    @PostMapping(MODEL2 + "/item/list.json")
    @ResponseBody
    public JsonResult<PageQuery<SearchSite>> itemList(SearchItemQuery query) {
        PageInfo<SearchItem> itemList = searchService.findItemList(query);
        PageQuery<SearchSite> page = query.getPageQuery();
        page.setTotalRow(itemList.getTotal());
        page.setList(itemList.getList());
        return JsonResult.success(page);
    }

    @PostMapping(MODEL2 + "/site/list.json")
    @ResponseBody
    public JsonResult<PageQuery<SearchSite>> siteIndex(SearchSiteQuery query) {
        if("".equals(query.getWebsite())){
            query.setWebsite(null);
        }
        int total = searchService.count();
        List<SearchSite> list = searchService.findSiteList(query);
        PageQuery<SearchSite> page = query.getPageQuery();
        page.setTotalRow(total);
        page.setList(list);
        return JsonResult.success(page);
    }

    @GetMapping(MODEL2 + "/site/add.do")
    public ModelAndView siteAdd() {
        ModelAndView view = new ModelAndView("/admin/search/site_add.html");
        return view;
    }

    @GetMapping(MODEL2 + "/item/add.do")
    public ModelAndView itemAdd() {
        ModelAndView view = new ModelAndView("/admin/search/item_add.html");
        return view;
    }

    @GetMapping(MODEL2 + "/site/edit.do")
    public ModelAndView siteEdit(Integer id) {
        ModelAndView view = new ModelAndView("/admin/search/site_edit.html");
        SearchSite searchSite = searchService.findSiteById(id);
        view.addObject("searchSite", searchSite);
        return view;
    }


    @PostMapping(MODEL2 + "/site/save.json")
    @ResponseBody
    public JsonResult saveSite(@Validated SearchSite searchSite) {
        Integer id = searchService.saveSite(searchSite);
        return JsonResult.success(id);
    }

    @PostMapping(MODEL2 + "/item/save.json")
    @ResponseBody
    public JsonResult saveItem(@Validated SearchItem searchItem) {
        Integer id = searchService.saveItem(searchItem);
        return JsonResult.success(id);
    }

    @PostMapping(MODEL2 + "/site/batchDel.json")
    @ResponseBody
    public JsonResult deleteSite(String ids) {
        List<Integer> dels = ConvertUtil.str2Int(ids);
        searchService.batchDeleteSiteId(dels);
        return new JsonResult().success();
    }

    @PostMapping(MODEL2 + "/item/batchDel.json")
    @ResponseBody
    public JsonResult deleteItem(String ids) {
        List<Integer> dels = ConvertUtil.str2Int(ids);
        searchService.batchDeleteSiteId(dels);
        return new JsonResult().success();
    }
    /**
     * 更新
     * @param fun
     * @return
     */
    @PostMapping(MODEL2 + "/site/update.json")
    @ResponseBody
    public JsonResult update(SearchSite searchSite) {
        searchService.updateSiteById(searchSite);
        return new JsonResult().success();
    }




}
