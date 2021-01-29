package com.stockkeeper.Views;

import javax.swing.*;

public class Index extends JFrame{
    /*
    private JFrame frame;
    private Container contentPane = getContentPane();
    private FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
    private JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel body = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel body1 = new JPanel();
    private JPanel body2 = new JPanel(new FlowLayout());
    private JPanel footer = new JPanel(new FlowLayout());
    private Toolkit tk = Toolkit.getDefaultToolkit();
    private Dimension screen = tk.getScreenSize();
    private User user;

    public Index(User user) {
        super("StockKeeper");
        this.user = user;
        setSize(screen.width,screen.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        body1.setLayout(new BoxLayout(body1,BoxLayout.Y_AXIS));
        initContentPane();
        setSizes();
        addLogo();
        addDateandUserInfo();
        addNavigation();
        addMain();

    }
    private void initColors(){
        header.setBackground(new Color(0xFEFFEF));
        body.setBackground(new Color(0xFEFFEF));
        body1.setBackground(new Color(0xFEFFEF));
        body2.setBackground(new Color(0xFEFFEF));
        footer.setBackground(new Color(0xFEFFEF));


    }
    private void initContentPane(){
        contentPane.setBackground(new Color(0xFEFFEF));
        contentPane.setLayout(layout);
        contentPane.add(header);
        contentPane.add(body);
        body.add(body1);
        body.add(body2);
        contentPane.add(footer);
    }

    private void setSizes(){

        header.setPreferredSize(new Dimension(screen.width,100));
        body.setPreferredSize(new Dimension(screen.width,900 ));
        footer.setPreferredSize(new Dimension(screen.width,25));

    }

    private void addLogo(){
        JLabel logo = new JLabel(new ImageIcon(getClass().getResource("/com/stockkeeper/images/logo.png ","logo"));
        logo.setBorder(new LineBorder(new Color(0, 0, 0), 1,true));
        logo.setPreferredSize(new Dimension(200,80));
        this.header.add(logo);

    }
    private void addDateandUserInfo(){

        JPanel logo = new JPanel();
        logo.setBorder(new LineBorder(new Color(0, 0, 0), 1,true));
        logo.setLayout(new FlowLayout(FlowLayout.RIGHT));
        Date date = new Date();
        JLabel dateLabel = new JLabel(date.toString());
        JButton userinfo = new JButton(user.getType() + ": " +user.getName());
        userinfo.setBackground(new Color(0x949519));
        logo.add(userinfo);
        logo.add(dateLabel);
        logo.setPreferredSize(new Dimension(screen.width-250,80));
        this.header.add(logo);
    }
    private void addNavigation(){
        JButton stock = new JButton("Stock");
        JButton calendar = new JButton("Calendar");
        JButton purchases = new JButton("Purchases");
        JButton sales = new JButton("Sales");
        JButton returnsIn = new JButton("Returns Inward");
        JButton returnsOut = new JButton("Returns Outward");
        JButton damages= new JButton("Damages");
        JButton debtors= new JButton("Debtors");
        JButton management = new JButton("Management");

        JComponent []navs = {stock,calendar,purchases,sales,returnsIn,returnsOut,damages,debtors,management};
        this.body1.setBorder(new LineBorder(new Color(0, 0, 0), 1,true));
        this.body1.setPreferredSize(new Dimension(300,850));
        Helper.setSize(navs,new Dimension(1000,50));
        Helper.addComponents(this.body1,navs);

    }
    private void addMain(){
        JLabel logo = new JLabel("Fasugba Tolulope");
        logo.setBorder(new LineBorder(new Color(0, 0, 0), 1,true));
        logo.setPreferredSize(new Dimension(screen.width - 350,850));
        this.body2.add(logo);

    }
    private void addFooter(){




    }
    */
}
