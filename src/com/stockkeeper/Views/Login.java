package com.stockkeeper.Views;

import com.stockkeeper.Controller.API;
import com.stockkeeper.Controller.helpers.Helper;
import com.stockkeeper.Controller.Global;
import com.stockkeeper.Models.customexceptions.NoUserFoundException;
import com.stockkeeper.Views.labels.SLabel;
import com.stockkeeper.Views.uicomponents.*;
import com.stockkeeper.Models.user.Entity;
import com.stockkeeper.Models.user.User;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.GregorianCalendar;

public class Login extends JFrame implements ActionListener, MouseListener, FocusListener {
    private Container con;
    private JPanel conP;
    private User user;
    private SLabel error;
    private CrazyInput entity;
    private CrazyInput username;
    private CrazyPasswordInput password;


    public Login(){
        super("StockKeeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        con = getContentPane();
        setIconImage(new ImageIcon(getClass().getResource("/com/stockkeeper/images/title.png")).getImage());
        setSize(600,450);
        setResizable(false);
        this.conP = new JPanel();
        conP.setLayout(new BorderLayout());
        conP.setBorder(new EmptyBorder(20,20,20,20));
        conP.setBorder(new LineBorder(new Color(0xC6CDD2),1));
        add(conP);
        conP.setBackground(new Color(0xFFFFFF));
        error = new SLabel("");
        error.getLabel().setFont(new Font("Arial", Font.ITALIC, 12));
        error.getLabel().setForeground(Color.WHITE);
        conP.add(error, BorderLayout.NORTH);
        setUpLoginPane();
    }

    private void setUpLoginPane() {
        //init container
        JPanel container = new JPanel(new FlowLayout(FlowLayout.LEFT));
        container.setBackground(Color.WHITE);
        container.setBorder(new EmptyBorder(30,20,20,20));
        conP.add(container);
        //init levels
        Dimension loginSize = new Dimension(500,60);
        Dimension levelSize = new Dimension(500,40);
        //init components
        STextArea login = new STextArea("Login",loginSize);
        login.setFont(new Font("Segoe UI", Font.BOLD, 40));
        STextArea message = new STextArea("Sign in to your account",levelSize);
        message.setForeground(new Color(0x73808E));
        message.setLocation(20,30);
        SPanel entityCon = new SPanel(levelSize);
        SPanel usernameCon = new SPanel(levelSize);
        SPanel passwordCon = new SPanel(levelSize);
        entityCon.setLayout(new BoxLayout(entityCon, BoxLayout.X_AXIS));
        usernameCon.setLayout(new BoxLayout(usernameCon, BoxLayout.X_AXIS));
        passwordCon.setLayout(new BoxLayout(passwordCon, BoxLayout.X_AXIS));
        SPanel buttonPanel = new SPanel(new BorderLayout(),levelSize);
        Helper.addComponents(container, new JComponent[]{login,message,entityCon,usernameCon,passwordCon,buttonPanel});
        //init username
        entity = new CrazyInput();
        entity.getsLabel().setBackground(new Color(0x4D8CC9));
        entity.setPlaceholder("Organization");
        username = new CrazyInput();
        username.getsLabel().setBackground(new Color(0x40D249));
        password = new CrazyPasswordInput();
        password.getsLabel().setBackground(new Color(0xE9D15E));
        username.setPlaceholder("Username");
        entityCon.add(entity);
        usernameCon.add(username);
        passwordCon.add(password);
        entity.getsTextField().addMouseListener(this);
        username.getsTextField().addMouseListener(this);
        password.getsPasswordField().addMouseListener(this);
        entity.getsTextField().addFocusListener(this);
        username.getsTextField().addFocusListener(this);
        password.getsPasswordField().addFocusListener(this);

        entity.getsTextField().setText("StockKeeper Inc");
        username.getsTextField().setText("ToluFash");
        password.getsPasswordField().setText("0709");
        //init button
        FancyButton loginB = new FancyButton("Login");
        loginB.setForeground(new Color(0xffffff));
        loginB.setBackground(new Color(0x25A6D7));
        loginB.addActionListener(this);
        FancyButton registerB = new FancyButton("Register");
        registerB.setForeground(new Color(0xffffff));
        registerB.setBackground(new Color(0x25A6D7));
        buttonPanel.add(loginB, BorderLayout.WEST);
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(registerB, BorderLayout.EAST);
        buttonPanel.setBorder(new EmptyBorder(20,0,0,200));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            StringBuilder pass = new StringBuilder();
            for (char chr : password.getsPasswordField().getPassword())
                pass.append(chr);
            user = API.getUser(entity.getsTextField().getText(),username.getsTextField().getText(), pass.toString());
            Entity entity1 = API.getEntity(entity.getsTextField().getText(),username.getsTextField().getText(), pass.toString());
            GregorianCalendar upperLimit = API.getTodayDate(entity1,user);

            Global.entity = entity1;
            Global.user = user;
            Global.products = API.getProducts(entity1, user);
            Global.stock = API.getStock(entity1,user);
            Global.sales = API.getSales(entity1,user);
            Global.purchases = API.getPurchases(entity1,user);
            Global.expenses = API.getExpenses(entity1,user);
            Global.startGetStock();
            Global.startGetSales();
            Global.startGetPurchases();
            Global.startGetReturnsInward();
            Global.startGetReturnsOutward();
            Global.startGetDamages();
            Global.startGetDebtors();
            Global.startGetCreditors();
            Global.startGetExpenses();
            MainView baseView = new MainView(entity1, user);
            Global.mainView = baseView;
            setVisible(false);
            baseView.setVisible(true);

        }
        catch (NoUserFoundException ex){
            error.setBackground(Color.RED);
            error.getLabel().setText("Incorrect credentials, please try again!");
        }
        catch (Exception nos){
            error.setBackground(Color.RED);
            error.getLabel().setText("Network Error, please try again!");
            nos.printStackTrace();
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        error.setBackground(Color.WHITE);
        error.getLabel().setText("");

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void focusGained(FocusEvent e) {
        error.setBackground(Color.WHITE);
        error.getLabel().setText("");

    }

    @Override
    public void focusLost(FocusEvent e) {

    }
}
