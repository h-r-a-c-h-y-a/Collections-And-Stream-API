package util.collection;

public class Article {
    private String footballNews;
    private String basketballNews;
    private String handballNews;
    private String boxNews;
    private String samboNews;

    public Article(ArticleBuilder builder){
        this.basketballNews = builder.basketballNews;
        this.footballNews = builder.footballNews;
        this.boxNews = builder.boxNews;
        this.handballNews = builder.handballNews;
        this.samboNews = builder.samboNews;
    }

    public static class ArticleBuilder {
        private String footballNews;
        private String basketballNews;
        private String handballNews;
        private String boxNews;
        private String samboNews;

        public static ArticleBuilder getInstance(){
            return new ArticleBuilder();
        }

        private ArticleBuilder() {
        }

        public ArticleBuilder setFootballNews(String footballNews) {
            this.footballNews = footballNews;
            return this;
        }

        public ArticleBuilder setBasketballNews(String basketballNews) {
            this.basketballNews = basketballNews;
            return this;
        }

        public ArticleBuilder setHandballNews(String handballNews) {
            this.handballNews = handballNews;
            return this;
        }

        public ArticleBuilder setBoxNews(String boxNews) {
            this.boxNews = boxNews;
            return this;
        }

        public ArticleBuilder setSamboNews(String samboNews) {
            this.samboNews = samboNews;
            return this;
        }

        public Article build(){
            return new Article(this);
        }
    }

    @Override
    public String toString() {
        return "util.collection.Article{" +
                "footballNews='" + footballNews + '\'' +
                ", basketballNews='" + basketballNews + '\'' +
                ", handballNews='" + handballNews + '\'' +
                ", boxNews='" + boxNews + '\'' +
                ", samboNews='" + samboNews + '\'' +
                '}';
    }
}
