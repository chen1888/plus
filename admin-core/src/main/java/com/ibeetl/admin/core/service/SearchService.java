package com.ibeetl.admin.core.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ibeetl.admin.core.mapper.SearchItemMapper;
import com.ibeetl.admin.core.mapper.SearchSiteMapper;
import com.ibeetl.admin.core.util.HtmlUtil;
import com.ibeetl.admin.core.util.HttpUtils;
import com.ibeetl.admin.core.web.query.SearchItemQuery;
import com.ibeetl.admin.core.web.query.SearchSiteQuery;
import com.ibeetl.admin.core.web.vo.SearchItem;
import com.ibeetl.admin.core.web.vo.SearchItemResut;
import com.ibeetl.admin.core.web.vo.SearchSite;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
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

    public List<SearchItemResut> search(String keywords, String website, String pageSize, String pageNum) {
        List<SearchSite> sites = searchSiteMapper.findbyWebsite(website);
        if(sites.size() == 0){
            return null;
        }
        SearchSite site = sites.get(0);
        Map<String, String> params = new HashMap<>();
        params.put(site.getKeywords(), keywords);
        if(StringUtils.isNotBlank(pageNum) && StringUtils.isNotBlank(site.getPagenum())){
            params.put(site.getPagenum(), pageNum);
        }
        if(StringUtils.isNotBlank(pageSize) && StringUtils.isNotBlank(site.getPagesize())){
            params.put(site.getPagesize(),pageSize);
        }
        String result = HttpUtils.sendGet(site.getUrl(), params);
        System.out.println("结果是:" + result);
        List<SearchItem> searchItems = searchItemMapper.findBySiteId(site.getId());
        if(searchItems.size() == 0){
            return null;
        }

        return HtmlUtil.parse(result,searchItems.get(0));
    }



    public List<SearchSite> findSiteList(SearchSiteQuery query){
        Integer pageSize = (int)query.getPageQuery().getPageSize();
        Integer start = ((int)query.getPageQuery().getPageNumber()-1)*pageSize;
        return searchSiteMapper.findPageList(query.getWebsite(),start,pageSize);
    }
    public PageInfo<SearchItem> findItemList(SearchItemQuery query){
        PageHelper.startPage((int)query.getPageQuery().getPageNumber(),(int)query.getPageQuery().getPageSize());
        List<SearchItem> searchItems = null;
        if(null == query.getSiteId()){
             searchItems = searchItemMapper.selectAll();
        }else {
             searchItems = searchItemMapper.findBySiteId(query.getSiteId());
        }
        return new PageInfo<>(searchItems);
    }

    public Integer count(){
        try {
            return searchSiteMapper.count();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Integer saveSite(SearchSite searchSite){
        searchSite.setCreateTime(new Date());
        searchSite.setUpdateTime(new Date());
        try {
            return searchSiteMapper.insertSite(searchSite);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Integer saveItem(SearchItem searchItem){
        searchItem.setCreateTime(new Date());
        searchItem.setUpdateTime(new Date());
        return searchItemMapper.insert(searchItem);
    }

    public void batchDeleteSiteId(List<Integer> ids){
        try {
            searchSiteMapper.deleteSite(ids);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void batchDeleteItemId(List<Integer> ids){
        try {
            searchItemMapper.deleteItem(ids);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public SearchSite findSiteById(Integer id){
        return searchSiteMapper.selectByPrimaryKey(id);
    }

    public SearchItem findItemById(Integer id){
        SearchItem searchItem = searchItemMapper.selectByPrimaryKey(id);
        SearchSite site = findSiteById(searchItem.getSiteId());
        if(site!=null){
            searchItem.setWebSite(site.getWebsite());
        }
        return searchItem;
    }

    public void updateSiteById(SearchSite searchSite){
        searchSite.setUpdateTime(new Date());
        searchSiteMapper.updateByPrimaryKey(searchSite);
    }

    public void updateItemById(SearchItem searchItem){
        searchItem.setUpdateTime(new Date());
        searchItemMapper.updateByPrimaryKey(searchItem);
    }


    public List<SearchSite> findAllSite(){
        return searchSiteMapper.selectAll();
    }






}
