package com.spider.search.util;

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

    public static String parse(String doc){
        Document document = Jsoup.parse(doc);
        System.out.println(document.baseUri());
        document.setBaseUri("http:");
        Elements elements = document.select(".res-book-item");
        for (Element element:elements){

            //获取书籍图片
            Element element1 = element.selectFirst(".book-img-box img[src]");
            String bookPicture = element1.absUrl("src");
            logger.info("书籍封面地址========》{}",bookPicture);

            //获取书籍名
            Element element2 = element.selectFirst(".book-mid-info h4");
            String bookName = element2.text();
            logger.info("书籍名========》{}",bookName);

            //书籍作者
            Element element3 = element.selectFirst(".book-mid-info a.name");
            String author = element3.text();
            logger.info("作者========》{}",author);

            //获取书籍简介
            Element element4 = element.selectFirst(".book-mid-info .intro");
            String intro = element4.text();
            logger.info("书籍简介========》{}",intro);

            //获取最新更新
            Element element5 = element.selectFirst(".book-mid-info .update a");
            String update = element5.text();
            logger.info("最新更新========》{}",update);

            //获取字数
            Element element6 = element.selectFirst(".book-right-info .total p");
            String wordNum = element6.text().replace("总字数","");
            logger.info("字数========》{}",wordNum);



        }
        return null;

    }
}
