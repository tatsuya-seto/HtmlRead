import javax.swing.*;
import javax.swing.border.Border;
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
    private JLabel Label1;
    private JLabel Label2;
    private JLabel Label3;
    private JLabel Label4;
    private JLabel Label5;
    private JLabel Label6;
    private JLabel Label7;
    private JLabel Label8;
    private JLabel Label9;
    private JTextArea outputArea;
    private JPanel BottomPanel;
    private JPanel TopPanel;
    private JPanel LongPanel;
    private JPanel MidPanel;
    private JPanel GoPanel;
    private JPanel FillerPanel;
    private JPanel SearchPanel;
    private JMenuBar mb;
    private JMenu file, edit, help;
    private JMenuItem cut, copy, paste, selectAll;
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
        mainFrame = new JFrame("Java SWING Examples");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new BorderLayout());

        //menu at top
        cut = new JMenuItem("cut");
        copy = new JMenuItem("copy");
        paste = new JMenuItem("paste");
        selectAll = new JMenuItem("selectAll");
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);

        Link = new JTextField();
        Link.setBounds(500, 1, WIDTH - 100, 1);

        ta = new JTextField();
        ta.setBounds(50, 1, WIDTH - 100, 1);
        ta.setSize(1, 1);


        mb = new JMenuBar();
        file = new JMenu("File");
        edit = new JMenu("Edit");
        help = new JMenu("Help");
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        mb.add(file);
        mb.add(edit);
        mb.add(help);
        //end menu at top

        Label1 = new JLabel("               ", JLabel.CENTER);
        Label1.setSize(5, 100);

        Label2 = new JLabel("       Insert Link: ", JLabel.RIGHT);
        Label2.setSize(350, 100);

        Label3 = new JLabel("               ", JLabel.CENTER);
        Label3.setSize(350, 100);

        Label4 = new JLabel("", JLabel.CENTER);
        Label4.setSize(350, 100);

        Label5 = new JLabel("       Search Word:", JLabel.RIGHT);
        Label5.setSize(350, 100);

        Label6 = new JLabel("Top 0", JLabel.CENTER);
        Label6.setSize(350, 100);

        Label7 = new JLabel("           ", JLabel.CENTER);
        Label7.setSize(350, 100);

        Label8 = new JLabel("Top 0", JLabel.CENTER);
        Label8.setSize(350, 100);

        Label9 = new JLabel("Top 0", JLabel.CENTER);
        Label9.setSize(350, 100);

        outputArea = new JTextArea("Insert a link and search term to get the links you need!");
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);

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

        FillerPanel = new JPanel();
        FillerPanel.setLayout(new BorderLayout());
        FillerPanel.setVisible(true);

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
        JButton secondButton = new JButton("2");
        JButton thirdButton = new JButton("3");
        JButton fourthButton = new JButton("4");
        JButton fifthButton = new JButton("Top 1");
        JButton sixthButton = new JButton("Top 2");
        JButton seventhButton = new JButton("Top 3");
        JButton eigthButton = new JButton("Top 4");
        JButton ninthButton = new JButton("1");
        JButton tenthButton = new JButton("button 10");

        goButton.setActionCommand("go");

        goButton.addActionListener(new ButtonClickListener());

        mainFrame.add(TopPanel, BorderLayout.CENTER);
        mainFrame.add(BottomPanel, BorderLayout.SOUTH);

        BottomPanel.add(LongPanel);
        BottomPanel.add(MidPanel);
        BottomPanel.add(SearchPanel);
        BottomPanel.add(SearchPanel);

        TopPanel.add(new JScrollPane(outputArea));

        LongPanel.add(Label2, BorderLayout.WEST);
        LongPanel.add(Link, BorderLayout.CENTER);
        LongPanel.add(Label1, BorderLayout.EAST);
        SearchPanel.add(Label5);
        SearchPanel.add(ta);
        SearchPanel.add(GoPanel);
        GoPanel.add(Label3, BorderLayout.WEST);
        GoPanel.add(goButton, BorderLayout.CENTER);
        SearchPanel.add(Label7);


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
                    }

                    outputArea.setText(allLinks);

            }
        }
    }
}