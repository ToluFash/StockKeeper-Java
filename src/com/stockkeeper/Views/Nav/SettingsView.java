package com.stockkeeper.Views.Nav;

import com.stockkeeper.Controller.API;
import com.stockkeeper.Controller.helpers.Helper;
import com.stockkeeper.Models.user.Entity;
import com.stockkeeper.Models.user.User;
import com.stockkeeper.Views.containers.SettingsContainer;
import com.stockkeeper.Views.labels.*;
import com.stockkeeper.Views.uicomponents.CrazyInput;
import com.stockkeeper.Views.uicomponents.FancyButton;
import com.stockkeeper.Views.uicomponents.SPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SettingsView extends JDialog implements ActionListener, MouseListener, FocusListener {
    private String title;
    private Entity entity;
    private User user;
    private ArrayList<String> locationStack;
    private SLabel error;
    //Top Container
    Container container;
    //Navigation Components
    private JPanel nav;
    private JPanel toggle;
    private JPanel orgS;
    private JPanel perS;

    public SettingsView(Frame frame, Entity entity, User user, String title){
        //init
        super(frame,title);
        this.user = user;
        this.entity =entity;
        this.title =title;
        init();
        initContentPane();
        setUpBackground();
        setUpErrorPane();
        setupNav();
        setUpPersonalSettings();
        setUpOrganizationalSettings();
        setVisible(true);
    }

    private void init(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/com/stockkeeper/images/icon.png")).getImage());
        setSize(1366,768);
        setResizable(true);
    }

    private void initContentPane(){
        container = getContentPane();
        container.setLayout(new BorderLayout());
    }

    private void setUpBackground(){
        getContentPane().setBackground(new Color(0xffffff));
    }

    private void setUpErrorPane(){
        error = new SLabel("");
        error.getLabel().setFont(new Font("Segoe UI", Font.ITALIC, 12));
        error.getLabel().setForeground(Color.WHITE);
    }
    private void setupNav(){
        nav = new JPanel();
        nav.setBorder(LineBorder.createGrayLineBorder());
        nav.setPreferredSize(new Dimension(300,768));
        nav.setBackground(Color.WHITE);
        orgS = new SettingsContainer(290,1);
        perS = new SettingsContainer(290,1);
        toggle = new JPanel();
        toggle.setPreferredSize(new Dimension(290, 50));
        toggle.setBackground(Color.WHITE);
        nav.add(toggle);
        toggle.add(new ToggleLabel(""));
        nav.add(orgS);
        nav.add(perS);
        container.add(nav, BorderLayout.WEST);
    }

    private void setUpPersonalSettings(){
        TopMenuLabel2 perS = user.getPersonalSettings().getSettingsTree();
        this.perS.add(perS);
    }
    private void setUpOrganizationalSettings(){
        TopMenuLabel2 orgS = entity.getOrganizationSettings().getSettingsTree();
        this.orgS.add(orgS);
    }
    private void pushLocation(String location){
        Container container = getContentPane();
        //Navigation Toggle and Location Components
        locationStack.add(location);
    }

    private void reSetUpOrganizationSettings(SubMenuLabel top, int start){
        for (Component component : top.getComponents()){
            SubMenuLabel comp = (SubMenuLabel) component;
            if (comp.getComponents().length != 0){
                reSetUpOrganizationSettings(comp, comp.getTextstart());
            }
        }
    }

    private void removeLocation(){
        locationStack.remove(locationStack.size()-1);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
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
