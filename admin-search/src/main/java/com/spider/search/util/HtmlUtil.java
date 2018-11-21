package com.spider.search.util;

import com.spider.search.vo.SearchItem;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: chenxi
 * @Date: 2018-11-21 10:53
 * @description:
 **/
public class HtmlUtil {

    private static final Logger logger = LoggerFactory.getLogger(HtmlUtil.class);

    public static String parse(String doc, SearchItem searchItem){
        Document document = Jsoup.parse(doc);
        System.out.println(document.baseUri());
        document.setBaseUri(searchItem.getBaseUri());
        Elements elements = document.select(searchItem.getBookItem());
        for (Element element:elements){

            //获取书籍图片
            if(StringUtils.isNotBlank(searchItem.getBookPicture())) {
                Element element1 = element.selectFirst(searchItem.getBookPicture());
                String bookPicture = element1.absUrl(searchItem.getBookPictureEx());
                logger.info("书籍封面地址========》{}", bookPicture);
            }

            //获取书籍名
            Element element2 = element.selectFirst(searchItem.getBookName());
            String bookName = element2.text();
            logger.info("书籍名========》{}",bookName);

            //书籍作者
            if(StringUtils.isNotBlank(searchItem.getBookAuthor())) {
                Element element3 = element.selectFirst(searchItem.getBookAuthor());
                String author = element3.text();
                logger.info("作者========》{}", author);
            }

            //获取书籍简介
            Element element4 = element.selectFirst(searchItem.getBookIntro());
            String intro = element4.text();
            logger.info("书籍简介========》{}",intro);

            //获取最新更新
            if(StringUtils.isNotBlank(searchItem.getBookUpdate())) {
                Element element5 = element.selectFirst(searchItem.getBookUpdate());
                String update = element5.text();
                logger.info("最新更新========》{}", update);
            }

            //获取字数
            if(StringUtils.isNotBlank(searchItem.getBookWords())){
                Element element6 = element.selectFirst(searchItem.getBookWords());
                String wordNum = element6.text().replace(searchItem.getBookWordsEx(),"");
                logger.info("字数========》{}",wordNum);
            }



        }
        return null;

    }
}
