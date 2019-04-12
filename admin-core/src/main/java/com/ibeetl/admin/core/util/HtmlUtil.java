package com.ibeetl.admin.core.util;

import com.ibeetl.admin.core.web.vo.SearchItem;
import com.ibeetl.admin.core.web.vo.SearchItemResut;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: chenxi
 * @Date: 2018-11-21 10:53
 * @description:
 **/
public class HtmlUtil {

    private static final Logger logger = LoggerFactory.getLogger(HtmlUtil.class);

    public static List<SearchItemResut> parse(String doc, SearchItem searchItem){
        Document document = Jsoup.parse(doc);
        System.out.println(document.baseUri());
        document.setBaseUri(searchItem.getBaseUri());
        Elements elements = document.select(searchItem.getBookItem());
        List<SearchItemResut> resuts = new LinkedList<>();
        for (Element element:elements){
            SearchItemResut resut = new SearchItemResut();
            //获取书籍图片
            if(StringUtils.isNotBlank(searchItem.getBookPicture())) {
                Element element1 = element.selectFirst(searchItem.getBookPicture());
                String bookPicture = element1.absUrl(searchItem.getBookPictureEx());
                resut.setBookPicture(bookPicture);
                logger.info("书籍封面地址========》{}", bookPicture);
            }

            //获取书籍名
            Element element2 = element.selectFirst(searchItem.getBookName());
            String bookName = element2.text();
            resut.setBookName(bookName);
            logger.info("书籍名========》{}",bookName);

            //书籍作者
            if(StringUtils.isNotBlank(searchItem.getBookAuthor())) {
                Element element3 = element.selectFirst(searchItem.getBookAuthor());
                String author = element3.text().replaceAll(searchItem.getBookAuthorEx(),"");
                resut.setAuthor(author);
                logger.info("作者========》{}", author);
            }

            //获取书籍简介
            Element element4 = element.selectFirst(searchItem.getBookIntro());
            String intro = element4.text();
            resut.setIntro(intro);
            logger.info("书籍简介========》{}",intro);

            //获取最新更新
            if(StringUtils.isNotBlank(searchItem.getBookUpdate())) {
                Element element5 = element.selectFirst(searchItem.getBookUpdate());
                if(element5!=null) {
                    String update = element5.text();
                    resut.setUpdate(update);
                    logger.info("最新更新========》{}", update);
                }
            }

            //获取字数
            if(StringUtils.isNotBlank(searchItem.getBookWords())){
                Element element6 = element.selectFirst(searchItem.getBookWords());
                if(element6!=null){
                    String wordNum = element6.text().replaceAll(searchItem.getBookWordsEx(),"");
                    resut.setWordNum(wordNum);
                    logger.info("字数========》{}",wordNum);
                }
            }
            resuts.add(resut);



        }
        return resuts;

    }
}
