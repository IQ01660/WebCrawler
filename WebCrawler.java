import java.util.*;
import java.io.BufferedReader;
import java.net.URL;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawler
{
    private Queue<String> queue;
    private List<String> discoveredWebsites;

    public WebCrawler()
    {
        this.queue = new LinkedList<String>();
        this.discoveredWebsites = new Vector<String>();
    }

    public void discoverWeb(String source)
    {
        this.queue.add(source);
        this.discoveredWebsites.add(source);

        while (!this.queue.isEmpty())
        {
            String v = this.queue.remove();
            String rawHtml = readUrl(v);
            //System.out.print(rawHtml);
            String regexp = "http://(\\w+\\.)*(\\w+)";
            Pattern pattern = Pattern.compile(regexp);
            Matcher matcher = pattern.matcher(rawHtml);

            while( matcher.find() )
            {
                String actualUrl = matcher.group();
                if (!discoveredWebsites.contains(actualUrl))
                {
                    discoveredWebsites.add(actualUrl);
                    System.out.println("Website has been found with URL: " + actualUrl);

                    this.queue.add(actualUrl);
                }
            }
        }
    }

    private String readUrl(String v)
    {
        String rawHtml = "";

        try
        {
            URL url = new URL(v);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine = "";
            while( (inputLine = in.readLine()) != null)
            {
                rawHtml += inputLine;
            }

            in.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return rawHtml;
    }
}