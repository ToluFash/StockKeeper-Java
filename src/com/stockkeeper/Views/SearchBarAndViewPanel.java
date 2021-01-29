package com.stockkeeper.Views;

import com.stockkeeper.Controller.Global;
import com.stockkeeper.Views.uicomponents.ViewChangePanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseListener;

public class SearchBarAndViewPanel extends JPanel {

    private JTextField searchBar;
    private ViewChangePanel viewChangePanel;


    public SearchBarAndViewPanel() {
        setPreferredSize(new Dimension(410,40));
        setBackground(Global.colorScheme.getQuaternaryColor());
        setBorder(new EmptyBorder(12,35,0,-40));
        searchBar = new JTextField(){

            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(Global.colorScheme.getQuaternaryColor());
                g.fillRect(0,0,getWidth(),getHeight());
                g.setColor(Global.colorScheme.getTertiaryColor());
                g.fillRoundRect(0,0,getWidth(),getHeight(),5,5);
                super.paintComponent(g);
            }

        };
        searchBar.setBorder(new EmptyBorder(0,10,0,0));
        searchBar.setBackground(Global.colorScheme.getTertiaryColor());
        searchBar.setPreferredSize(new Dimension(250,30));
        viewChangePanel = new ViewChangePanel();
        add(searchBar);
        add(viewChangePanel);
    }

    @Override
    public synchronized void addMouseListener(MouseListener l) {
        viewChangePanel.getListLabel().addMouseListener(l);
        viewChangePanel.getTilesLabel().addMouseListener(l);
    }

    public void addDocumentListener(DocumentListener d){
        searchBar.getDocument().addDocumentListener(d);
    }
    public JTextField getSearchBar() {
        return searchBar;
    }

    public void setSearchBar(JTextField searchBar) {
        this.searchBar = searchBar;
    }

    public ViewChangePanel getViewChangePanel() {
        return viewChangePanel;
    }

    public void setViewChangePanel(ViewChangePanel viewChangePanel) {
        this.viewChangePanel = viewChangePanel;
    }


}
