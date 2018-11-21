package com.spider.search.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @Author: chenxi
 * @Date: 2018-11-21 10:53
 * @description:
 **/
public class HtmlUtil {
    public static String parse(String doc){
        Document document = Jsoup.parse(doc);
        Elements elements = document.select(".res-book-item");
        for (Element element:elements){
            Element element1 = element.selectFirst(".book-img-box img[src]");
            String bookPicture = element1.attr("src");
            System.out.println(bookPicture);

        }
        return null;

    }
}
