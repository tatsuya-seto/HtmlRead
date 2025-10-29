import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class HtmlRead {

    public static void main(String[] args) {
        HtmlRead html = new HtmlRead();
    }

    public HtmlRead() {

        try {
            System.out.println();
            System.out.print("hello \n");
            URL url = new URL("https://www.milton.edu/");

            URLConnection urlc = url.openConnection();
            urlc.setRequestProperty("User-Agent", "Mozilla 5.0 (Windows; U; " + "Windows NT 5.1; en-US; rv:1.8.0.11) ");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(urlc.getInputStream())
            );
            String line;
            while ((line = reader.readLine()) != null) {
                //only look at lines that contain href=
                if (line.contains("href=")) {
                    int start = line.indexOf("href=") + 6; //skip href="
                    int end = line.indexOf("\"", start);   //find closing quote
                    if (end > start) {
                        String link = line.substring(start, end);
                        //print only links that have "milton.edu" or start with "/"
                        if (link.contains("milton.edu") || link.startsWith("/")) {
                            System.out.println(link);
                        }
                    }
                }
            }
            reader.close();
        } catch(Exception ex) {
            System.out.println(ex);
        }

    }

}
