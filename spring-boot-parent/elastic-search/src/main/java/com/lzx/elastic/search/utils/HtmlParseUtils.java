package com.lzx.elastic.search.utils;

import com.lzx.elastic.search.model.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HtmlParseUtils {

    public static List<Content> parseJD(String keywords) {
        //获取请求  https://search.jd.com/Search?keyword=java
        String url = "https://search.jd.com/Search?keyword=" + keywords;
        try {
            //解析网页。（jsoup返回Document就是浏览器的Document对象）
            Document document = Jsoup.parse(new URL(url), 30000);
            //所有你在js中可以使用的方法，这里都能用。
            Element element = document.getElementById("J_goodsList");
            //获取所有的li元素
            Elements elements = element.getElementsByTag("li");
            //商品集合
            List<Content> goodsList = new ArrayList<>();
            //获取所有元素的内容，这里el就是每一个li标签了
            for (Element el : elements) {
                String img = el.getElementsByTag("img").eq(0).attr("data-lazy-img");
                String price = el.getElementsByClass("p-price").eq(0).text();
                String title = el.getElementsByClass("p-name").eq(0).text();
                Content content = new Content();
                content.setTitle(title);
                content.setPrice(price);
                content.setImg(img);
                goodsList.add(content);
            }
            return goodsList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
