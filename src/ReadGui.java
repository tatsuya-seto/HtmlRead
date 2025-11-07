import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
    private JLabel Label10;
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
    private JTextArea ta; //typing area
    private JTextArea Link; //typing area
    private int WIDTH=800;
    private int HEIGHT=700;


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

        Link = new JTextArea();
        Link.setBounds(500, 1, WIDTH-100, 1);

        ta = new JTextArea();
        ta.setBounds(50, 1, WIDTH-100, 1);
        ta.setSize(1,1);


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

        Label5 = new JLabel("       Search:", JLabel.RIGHT);
        Label5.setSize(350, 100);

        Label6 = new JLabel("Top 0", JLabel.CENTER);
        Label6.setSize(350, 100);

        Label7 = new JLabel("Top 0", JLabel.CENTER);
        Label7.setSize(350, 100);

        Label8 = new JLabel("Top 0", JLabel.CENTER);
        Label8.setSize(350, 100);

        Label9 = new JLabel("Top 0", JLabel.CENTER);
        Label9.setSize(350, 100);

        Label10 = new JLabel("TopRight", JLabel.CENTER);
        Label10.setSize(350, 100);

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
        BottomPanel.setLayout(new GridLayout(4,1));
        BottomPanel.setVisible(true);

        TopPanel = new JPanel();
        TopPanel.setLayout(new GridLayout(1,1));
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
        SearchPanel.setLayout(new GridLayout(1,4));
        SearchPanel.setVisible(true);

        GoPanel = new JPanel();
        GoPanel.setLayout(new BorderLayout());
        GoPanel.setVisible(true);


    }

    private void showEventDemo() {

        JButton goButton = new JButton("Go");
        goButton.setSize(20,1);
        JButton secondButton = new JButton("2");
        JButton thirdButton = new JButton("3");
        JButton fourthButton = new JButton("4");
        JButton fifthButton = new JButton("Top 1");
        JButton sixthButton = new JButton("Top 2");
        JButton seventhButton = new JButton("Top 3");
        JButton eigthButton = new JButton("Top 4");
        JButton ninthButton = new JButton("1");
        JButton tenthButton = new JButton("button 10");

        goButton.setActionCommand("Go");
        secondButton.setActionCommand("Submit");
        thirdButton.setActionCommand("Cancel");

        goButton.addActionListener(new ButtonClickListener());
        secondButton.addActionListener(new ButtonClickListener());
        thirdButton.addActionListener(new ButtonClickListener());

        mainFrame.add(TopPanel, BorderLayout.CENTER);
        mainFrame.add(BottomPanel,BorderLayout.SOUTH);

        BottomPanel.add(LongPanel);
        BottomPanel.add(MidPanel);
        BottomPanel.add(SearchPanel);
        BottomPanel.add(SearchPanel);

        TopPanel.add(ta);

        LongPanel.add(Label2,BorderLayout.WEST);
        LongPanel.add(Link,BorderLayout.CENTER);
        LongPanel.add(Label1,BorderLayout.EAST);
        SearchPanel.add(Label5);
        SearchPanel.add(ta);
        SearchPanel.add(GoPanel);
        GoPanel.add(Label3,BorderLayout.WEST);
        GoPanel.add(goButton,BorderLayout.CENTER);
        SearchPanel.add(Label7);





        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cut)
            ta.cut();
        if (e.getSource() == paste)
            ta.paste();
        if (e.getSource() == copy)
            ta.copy();
        if (e.getSource() == selectAll)
            ta.selectAll();
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

        }
    }
}