package com.stockkeeper.Views.labels;

import com.stockkeeper.Controller.Global;
import com.stockkeeper.Views.uicomponents.FontsList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class NavButton extends JPanel implements  MouseListener{

    private JLabel iconLabel;
    private JLabel title;
    private boolean selected;

    public NavButton(String text, ImageIcon icon, int horizontalAlignment) {
        setBackground(Global.colorScheme.getPrimaryColor());
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap(0);
        setLayout(layout);
        setBorder(new EmptyBorder(0,0,0,0));
        selected = false;
        this.title = new JLabel(text);
        this.title.setFont(FontsList.getCalibri(Font.BOLD, 12));
        this.iconLabel = new JLabel(icon);
        setPreferredSize(new Dimension(70,75));
        this.iconLabel.setPreferredSize(new Dimension(60,50));
        add(iconLabel);
        add(title);
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.selected = true;
            title.setForeground(Global.colorScheme.getDenaryColor());


    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e){
        title.setForeground(Global.colorScheme.getDenaryColor());
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (!selected)
        title.setForeground(Color.BLACK);

    }

}
