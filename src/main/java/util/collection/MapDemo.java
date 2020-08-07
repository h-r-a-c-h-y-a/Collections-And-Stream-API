package util.collection;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapDemo {
    public static void main(String[] args) {
        Article basketballNews = Article.ArticleBuilder.getInstance()
                .setBasketballNews("uygfwaftcgwr")
                .build();
        Article basketballNews2 = Article.ArticleBuilder.getInstance()
                .setBasketballNews("drudrhjrs6uesjhu ndejt7ikmj ")
                .build();
        List<Article> articleList = new ArrayList<>();
        List<Article> articleList1 = new ArrayList<>();
        articleList.add(basketballNews);
        articleList1.add(basketballNews2);
        Map<String, List<Article>> map = new HashMap<>();
        map.put("basketball", articleList);
        map.merge("basketball", articleList1, (list1, list2) ->
                Stream.of(list1, list2)
                .flatMap(Collection::stream)
                .collect(Collectors.toList()));
    }
}
