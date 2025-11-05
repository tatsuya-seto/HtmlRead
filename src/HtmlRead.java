import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import java.awt.*;

    public class HtmlRead {

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
                    if (line.contains("href=")) {
                        int start = line.indexOf("href=") + 5;
                        char quote = line.charAt(start);
                        if (quote == '"' || quote == '\'') {
                            int end = line.indexOf(quote, start + 1);
                            if (end != -1) {
                                String link = line.substring(start + 1, end);
                                System.out.println(link);

                            }
                        }
                    }

                }

                reader.close();
            } catch (Exception ex) {
                System.out.println(ex);
            }


        }

    }


