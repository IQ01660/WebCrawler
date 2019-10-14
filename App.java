public class App
{
    public static void main(String[] args)
    {
        WebCrawler crawler = new WebCrawler();
        String rootUrl = "https://www.bbc.com";
        crawler.discoverWeb(rootUrl);
    }
}