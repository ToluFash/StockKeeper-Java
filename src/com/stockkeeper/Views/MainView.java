
package com.stockkeeper.Views;

import com.stockkeeper.Controller.helpers.Helper;
import com.stockkeeper.Controller.Global;
import com.stockkeeper.Models.user.Entity;
import com.stockkeeper.Models.user.User;
import com.stockkeeper.Views.Nav.*;
import com.stockkeeper.Views.labels.NavButton;
import com.stockkeeper.Views.uicomponents.FontsList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;

public class MainView extends JFrame implements MouseListener{

    //Core Models
    private Entity entity;
    private User user;


    //Top Panels
    private Container container;
    private JPanel navPanel;
    private JPanel center;
    private JPanel footerPanel;
    private JLabel footerLabel;



    //

    private NavButton navButton1;
    private NavButton navButton2;
    private NavButton navButton3;
    private NavButton navButton4;
    private NavButton navButton5;
    private NavButton navButton6;
    private NavButton navButton7;
    private NavButton navButton8;
    private NavButton navButton9;
    private NavButton navButton10;
    private NavButton navButton11;
    private StockView stockView;
    private SalesView salesView;
    private PurchasesView purchasesView;

    private ReturnsInView returnsInView;
    private ReturnsOutView returnsOutView;
    private DamagesView damagesView;
    private DebtorsView debtorsView;
    private CreditorsView creditorsView;
    private TaxesView taxesView;
    private ExpenseView expenseView;
    //private ReportsView reportsView;

    //Layouts
    private FlowLayout flowLayout;
    private FlowLayout flowLayout2;
    private FlowLayout flowLayoutNav;

    // Footer
    private JPanel footerPanelRight;
    private JButton eventButton;

    public MainView(Entity entity, User user) {
        // Basic Init
        super("StockKeeper");
        setIconImage(new ImageIcon(getClass().getResource("/com/stockkeeper/images/title.png")).getImage());
        this.setSize(1366,768);
        this.setMinimumSize(new Dimension(800,0));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.container = this.getContentPane();
        this.container.setLayout(new BorderLayout());
        this.container.setBackground(Color.WHITE);
        // Entity
        this.entity = entity;
        this.user = user;

        setUpLayout();
        initMenuBars();
        initTopPanels();
        initNav();
        initFooter();
        initEventPanel();
        initViews();
        center.add(stockView);
    }


    private  void setUpLayout(){
        flowLayout = new FlowLayout();
        flowLayout2 = new FlowLayout();
        flowLayoutNav = new FlowLayout();
        flowLayoutNav.setHgap(5);
        flowLayout.setAlignment(FlowLayout.LEFT);
        flowLayout2.setAlignment(FlowLayout.RIGHT);
        flowLayoutNav.setAlignment(FlowLayout.LEFT);
    }
    private void initMenuBars(){
        JFrame jFrame = this;
        JMenuBar mainBar = new JMenuBar();
        mainBar.setBorder(new LineBorder(Global.colorScheme.getSecondaryColor()));
        Font menuFont = new Font("Segoe UI", Font.PLAIN, 12);
        mainBar.setBackground(Global.colorScheme.getPrimaryColor());
        Color menuColor = Color.WHITE;
        this.setJMenuBar(mainBar);
        JMenu menu1 = new JMenu("File");
        menu1.setFont(menuFont);
        menu1.setForeground(menuColor);
        JMenu menu2 = new JMenu("View");
        menu2.setFont(menuFont);
        menu2.setForeground(menuColor);
        JMenu menu3 = new JMenu("Reports");
        menu3.setFont(menuFont);
        menu3.setForeground(menuColor);
        JMenu menu4 = new JMenu("Settings");
        menu4.setFont(menuFont);
        menu4.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                new SettingsView(jFrame, entity, user, "Purchases");
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        menu4.setForeground(menuColor);
        JMenu menu5 = new JMenu("Account");
        menu5.setFont(menuFont);
        menu5.setForeground(menuColor);
        JMenu menu6 = new JMenu("Help");
        menu6.setFont(menuFont);
        menu6.setForeground(menuColor);
        JMenu[] allMenus = {menu1,menu2,menu3,menu4,menu5, menu6};
        Helper.addComponents(mainBar, allMenus);

    }
    private void initViews(){

        stockView = new StockView(entity,user);
        salesView = new SalesView(entity,user);
        purchasesView = new PurchasesView(entity,user);
        returnsInView = new ReturnsInView(entity,user);
        returnsOutView = new ReturnsOutView(entity,user);
        damagesView = new DamagesView(entity,user);
        debtorsView = new DebtorsView(entity,user);
        creditorsView = new CreditorsView(entity,user);
        taxesView = new TaxesView(entity,user);
        expenseView = new ExpenseView(entity,user);
       // reportsView = new ReportsView(entity,user);


    }
    private void initTopPanels(){
        navPanel = new JPanel();
        navPanel = new JPanel(new BorderLayout());
        navPanel.setBackground(Global.colorScheme.getPrimaryColor());
        navPanel.setPreferredSize(new Dimension(1366,80));

        center = new JPanel();
        center.setLayout(new BorderLayout());
        center.setBackground(Global.colorScheme.getQuaternaryColor());
        center.setPreferredSize(new Dimension(320,710));

        footerPanel= new JPanel(new BorderLayout());
        footerPanel.setBackground(Global.colorScheme.getSecondaryColor());
        footerPanel.setPreferredSize(new Dimension(320,30));
        this.container.add(navPanel, BorderLayout.NORTH);
        this.container.add(center, BorderLayout.CENTER);
        this.container.add(footerPanel, BorderLayout.SOUTH);
    }
    private void initNav(){

        FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT);
        flowLayout.setVgap(0);
        // Navigation and Profile Bar Container
        JPanel navigationCon = new JPanel(new BorderLayout());
        navigationCon.setBackground(Global.colorScheme.getPrimaryColor());

        //Navigation Container
        JPanel navigation = new JPanel(flowLayoutNav);
        navigation.setPreferredSize(new Dimension(400,200));
        navigation.setBorder(new EmptyBorder(5,30,0,0));
        navigation.setBackground(Global.colorScheme.getPrimaryColor());

        //Profile Bar Container
        JPanel profileBar = new JPanel(new BorderLayout());
        profileBar.setBorder(new EmptyBorder(0,0,0,0));
        profileBar.setPreferredSize(new Dimension(230,80));
        profileBar.setBackground(Global.colorScheme.getPrimaryColor());

        JPanel profilePic = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Ellipse2D ellipse2D = new Ellipse2D.Double(0,9,65,65);
                g.setColor(Global.colorScheme.getQuinaryColor());
                ((Graphics2D) g).fill(ellipse2D);
            }
        };
        profilePic.setPreferredSize(new Dimension(80, 80));
        JPanel userInfo = new JPanel(flowLayout);
        userInfo.setPreferredSize(new Dimension(50,200));
        userInfo.setBorder(new EmptyBorder(16,20,5,30));
        userInfo.setBackground(Global.colorScheme.getPrimaryColor());

        JLabel orgName = new JLabel(entity.getName()){

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                FontMetrics fontMetrics = getFontMetrics(FontsList.getSitkaBanner(Font.BOLD, 14));
                g.setFont(FontsList.getSitkaBanner(Font.BOLD, 14));
                int textWidth = 0;

                for (int x = 0; x < getText().length(); x++){
                    textWidth += fontMetrics.charWidth(getText().charAt(x));
                }
                g2d.drawString(getText(), getWidth()- textWidth, 11);

            }
        };
        orgName.setPreferredSize(new Dimension(100,18));
        JLabel userName = new JLabel("Fasugba Tolulope"){

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                FontMetrics fontMetrics = getFontMetrics(FontsList.getSitkaBanner(Font.BOLD, 14));
                g.setFont(FontsList.getSitkaBanner(Font.BOLD, 14));
                int textWidth = 0;

                for (int x = 0; x < getText().length(); x++){
                    textWidth += fontMetrics.charWidth(getText().charAt(x));
                }
                g2d.drawString(getText(), getWidth()- textWidth, 11);

            }
        };
        userName.setPreferredSize(new Dimension(100,18));
        JLabel logOut = new JLabel("Sign Out"){

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                FontMetrics fontMetrics = getFontMetrics(FontsList.getSitkaBanner(Font.PLAIN, 14));
                g.setFont(FontsList.getSitkaBanner(Font.PLAIN, 14));
                int textWidth = 0;

                for (int x = 0; x < getText().length(); x++){
                    textWidth += fontMetrics.charWidth(getText().charAt(x));
                }
                g2d.drawString(getText(), getWidth()- textWidth, 11);

            }
        };
        logOut.setPreferredSize(new Dimension(100,18));

        userInfo.add(orgName);
        userInfo.add(userName);
        userInfo.add(logOut);

        profileBar.add(profilePic, BorderLayout.WEST);
        profileBar.add(userInfo, BorderLayout.CENTER);



        //Icons Init
        ImageIcon stock = new ImageIcon(getClass().getResource("/com/stockkeeper/images/stockIcon.png"));
        ImageIcon sales = new ImageIcon(getClass().getResource("/com/stockkeeper/images/salesIcon.png"));
        ImageIcon purchases= new ImageIcon(getClass().getResource("/com/stockkeeper/images/purchasesIcon.png"));
        ImageIcon returnsIn = new ImageIcon(getClass().getResource("/com/stockkeeper/images/returnsInIcon.png"));
        ImageIcon returnsOut = new ImageIcon(getClass().getResource("/com/stockkeeper/images/returnsOutIcon.png"));
        ImageIcon damages = new ImageIcon(getClass().getResource("/com/stockkeeper/images/damagesIcon.png"));
        ImageIcon debtors = new ImageIcon(getClass().getResource("/com/stockkeeper/images/debtorsIcon.png"));
        ImageIcon creditors = new ImageIcon(getClass().getResource("/com/stockkeeper/images/creditorsIcon.png"));
        ImageIcon taxes = new ImageIcon(getClass().getResource("/com/stockkeeper/images/taxesIcon.png"));
        ImageIcon expenses = new ImageIcon(getClass().getResource("/com/stockkeeper/images/expensesIcon.png"));
        ImageIcon reports = new ImageIcon(getClass().getResource("/com/stockkeeper/images/reportsIcon.png"));

        navButton1 = new NavButton("Stock", stock,4);
        navButton2 = new NavButton("Sales",sales,4);
        navButton3 = new NavButton("Purchases",purchases,4);
        navButton4 = new NavButton("Returns In.",returnsIn,4);
        navButton5 = new NavButton("Returns Out.",returnsOut,4);
        navButton6 = new NavButton("Damages",damages,4);
        navButton7 = new NavButton("Debtors",debtors,4);
        navButton8 = new NavButton("Creditors",creditors,4);
        navButton9 = new NavButton("Taxes",taxes,4);
        navButton10 = new NavButton("Expenses",expenses,4);
        navButton11 = new NavButton("Reports",reports,4);
        navButton1.addMouseListener(this);
        navButton2.addMouseListener(this);
        navButton3.addMouseListener(this);
        navButton4.addMouseListener(this);
        navButton5.addMouseListener(this);
        navButton6.addMouseListener(this);
        navButton7  .addMouseListener(this);
        navButton8.addMouseListener(this);
        navButton9.addMouseListener(this);
        navButton10.addMouseListener(this);
        navButton11.addMouseListener(this);


        Helper.addComponents(navigation, new NavButton[]{navButton1,navButton2,navButton3,navButton4,navButton5,navButton6,navButton7, navButton8,navButton9,navButton10,navButton11});
        navigationCon.add(navigation, BorderLayout.CENTER);

        // Helper.setSize(new NavButton[]{navButton1,navButton2,navButton3,navButton4,navButton5,navButton6,navButton7}, new Dimension(280,40));
        this.navPanel.add(navigationCon, BorderLayout.CENTER);
        this.navPanel.add(profileBar, BorderLayout.EAST);
    }
    private void initFooter(){

        this.footerLabel = new JLabel();
        footerLabel.setForeground(Color.WHITE);
        footerLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        this.footerPanel.add(footerLabel, BorderLayout.CENTER);

        //
        footerPanelRight = new JPanel(flowLayout);
        footerPanelRight.setPreferredSize(new Dimension(90,30));
        footerPanelRight.setBackground(Global.colorScheme.getSecondaryColor());
        footerPanel.add(footerPanelRight, BorderLayout.EAST);



        //Event Panel
        eventButton = new JButton(){@Override
        public Dimension getPreferredSize() {
            return new Dimension(15, 19);
        }


            @Override
            public boolean isBorderPainted() {
                return false;
            }

            @Override
            protected void paintComponent(Graphics g) {

                if (getModel().isPressed()) {
                    g.setColor(Global.colorScheme.getSecondaryColor());
                } else if (getModel().isRollover()) {
                    g.setColor(Global.colorScheme.getSecondaryColor());
                } else {
                    g.setColor(Global.colorScheme.getSecondaryColor());
                }
                g.fillRect(0, 0, getWidth()-1, getHeight());
                g.drawImage(new ImageIcon(getClass().getResource("/com/stockkeeper/images/taskIcon.png")).getImage(), 0,0,null);
            }

        };
        footerPanelRight.add(eventButton);
        footerPanelRight.add(Global.trafficPanel);
        eventButton.addMouseListener(this);



    }
    private void initEventPanel(){
        Global.eventPanel.addtoPending(new EventRow.EventTest());
        Global.eventPanel.addtoPending(new EventRow.EventTest());
        Global.eventPanel.addtoPending(new EventRow.EventTest());
        Global.eventPanel.addtoPending(new EventRow.EventTest());
    }

    private void refreshView(JPanel jPanel){

        center.removeAll();
        center.add(jPanel);
        center.revalidate();
        center.repaint();
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == eventButton){
            Global.eventPanel.show(footerPanelRight, -312, -400);
        }
        if (e.getSource() == navButton1){
            refreshView(stockView);
        }
        if (e.getSource() == navButton2){
            refreshView(salesView);
        }

        if (e.getSource() == navButton3){
            refreshView(purchasesView);
        }
        if (e.getSource() == navButton4){
            refreshView(returnsInView);
        }
        if (e.getSource() == navButton5){
            refreshView(returnsOutView);
        }
        if (e.getSource() == navButton6){
            refreshView(damagesView);
        }
        if (e.getSource() == navButton7){
            refreshView(debtorsView);
        }
        if (e.getSource() == navButton8){
            refreshView(creditorsView);
        }
        if (e.getSource() == navButton9){
            refreshView(taxesView);
        }
        if (e.getSource() == navButton10){
            refreshView(expenseView);
        }/*
        if (e.getSource() == navButton11){
            refreshView(reportsView);
        }
        */

    }
    @Override
    public void mousePressed(MouseEvent e) {

    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    @Override
    public void mouseExited(MouseEvent e) {

    }
}