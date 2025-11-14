import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ReadGui implements ActionListener {
    private JFrame mainFrame;
    private JLabel FillerLabel1;
    private JLabel InsertLinkLabel;
    private JLabel FillerLabel2;
    private JLabel SearchWordLabel;
    private JLabel FillerLabel3;
    private JEditorPane outputArea;
    private JPanel BottomPanel;
    private JPanel TopPanel;
    private JPanel LongPanel;
    private JPanel MidPanel;
    private JPanel GoPanel;
    private JPanel SearchPanel;
    private JTextField ta; //typing area
    private JTextField Link; //typing area
    private int WIDTH = 800;
    private int HEIGHT = 700;


    public ReadGui() {
        prepareGUI();
    }

    public static void main(String[] args) {
        ReadGui ReadGui = new ReadGui();
        ReadGui.showEventDemo();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Link Reader");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new BorderLayout());


        Link = new JTextField();
        Link.setBounds(500, 1, WIDTH - 100, 1);

        ta = new JTextField();
        ta.setBounds(50, 1, WIDTH - 100, 1);
        ta.setSize(1, 1);


        FillerLabel1 = new JLabel("               ", JLabel.CENTER);
        FillerLabel1.setSize(5, 100);

        InsertLinkLabel = new JLabel("       Insert Link: ", JLabel.RIGHT);
        InsertLinkLabel.setSize(350, 100);

        FillerLabel2 = new JLabel("               ", JLabel.CENTER);
        FillerLabel2.setSize(350, 100);


        SearchWordLabel = new JLabel("       Search Word:", JLabel.RIGHT);
        SearchWordLabel.setSize(350, 100);

        FillerLabel3 = new JLabel("           ", JLabel.CENTER);
        FillerLabel3.setSize(350, 100);

        outputArea = new JEditorPane();
        outputArea.setText("Insert a link and search term to get the links you need!");
        outputArea.setEditable(false);
        outputArea.setContentType("text/html");
        outputArea.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                try { Desktop.getDesktop().browse(e.getURL().toURI()); } catch (Exception ex) { ex.printStackTrace(); }
            }
        });


        JScrollPane scroll = new JScrollPane(outputArea);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        BottomPanel = new JPanel();
        BottomPanel.setLayout(new GridLayout(4, 1));
        BottomPanel.setVisible(true);

        TopPanel = new JPanel();
        TopPanel.setLayout(new GridLayout(1, 1));
        TopPanel.setVisible(true);

        LongPanel = new JPanel();
        LongPanel.setLayout(new BorderLayout());
        LongPanel.setVisible(true);

        MidPanel = new JPanel();
        MidPanel.setLayout(new BorderLayout());
        MidPanel.setVisible(true);

        SearchPanel = new JPanel();
        SearchPanel.setLayout(new GridLayout(1, 4));
        SearchPanel.setVisible(true);

        GoPanel = new JPanel();
        GoPanel.setLayout(new BorderLayout());
        GoPanel.setVisible(true);


    }

    private void showEventDemo() {

        JButton goButton = new JButton("Go");
        goButton.setSize(20, 1);
        goButton.setActionCommand("go");
        goButton.addActionListener(new ButtonClickListener());

        mainFrame.add(TopPanel, BorderLayout.CENTER);
        mainFrame.add(BottomPanel, BorderLayout.SOUTH);

        BottomPanel.add(LongPanel);
        BottomPanel.add(MidPanel);
        BottomPanel.add(SearchPanel);
        BottomPanel.add(SearchPanel);

        TopPanel.add(new JScrollPane(outputArea));

        LongPanel.add(InsertLinkLabel, BorderLayout.WEST);
        LongPanel.add(Link, BorderLayout.CENTER);
        LongPanel.add(FillerLabel1, BorderLayout.EAST);

        SearchPanel.add(SearchWordLabel);
        SearchPanel.add(ta);
        SearchPanel.add(GoPanel);
        SearchPanel.add(FillerLabel3);

        GoPanel.add(FillerLabel2, BorderLayout.WEST);
        GoPanel.add(goButton, BorderLayout.CENTER);

        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }



        private class ButtonClickListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if (command.equals("go")) {
                    String urlText = Link.getText().trim();
                    String searchword = ta.getText().trim();
                    //Getting url and searchword texts

                    if (urlText.isEmpty() || searchword.isEmpty()) {
                        outputArea.setText("Please enter both a link and a search word");
                        return;
                    }//Text returns this error if both a search term and a link is not inserted

                    String allLinks = "";
                    try {
                        URL url = new URL(urlText);

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
                                        if (link.contains(searchword)) {
                                            allLinks += link + "\n";
                                        }

                                    }
                                }
                            }
                        }
                        reader.close();
                    } catch (Exception ex) {
                        allLinks = "Error: " + ex.getMessage();
                    }

                    if (allLinks.isEmpty()) {
                        allLinks = "No links found containing \"" + searchword + "\"";
                    }   else {
                        //build HTML with clickable links
                        StringBuilder html = new StringBuilder("<html><body>");
                        String[] lines = allLinks.split("\\R");   // split on newlines

                        for (String link : lines) {
                            link = link.trim();
                            if (link.isEmpty()) continue;

                            html.append("<a href=\"")
                                    .append(link)
                                    .append("\">")
                                    .append(link)
                                    .append("</a><br>");
                        }

                        html.append("</body></html>");

                        outputArea.setText(html.toString());
                    }

            }
        }
    }
}