package com.ibeetl.admin.console.service;

import com.ibeetl.admin.console.web.query.SearchSiteQuery;
import com.ibeetl.admin.core.mapper.SearchItemMapper;
import com.ibeetl.admin.core.mapper.SearchSiteMapper;
import com.ibeetl.admin.core.util.HtmlUtil;
import com.ibeetl.admin.core.util.HttpUtils;
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


    public void save(SearchItem searchItem){
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

    }


    public List<SearchSite> findSiteList(SearchSiteQuery query){
        Integer pageSize = (int)query.getPageQuery().getPageSize();
        Integer start = ((int)query.getPageQuery().getPageNumber()-1)*pageSize;
        return searchSiteMapper.findPageList(query.getWebsite(),start,pageSize);
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

    public void batchDeleteSiteId(List<Integer> ids){
        try {
            searchSiteMapper.deleteSite(ids);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public SearchSite findSiteById(Integer id){
        return searchSiteMapper.selectByPrimaryKey(id);
    }

    public void updateSiteById(SearchSite searchSite){
        searchSite.setUpdateTime(new Date());
        searchSiteMapper.updateByPrimaryKey(searchSite);
    }






}
