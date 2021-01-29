package com.stockkeeper.Views.uicomponents;

import com.stockkeeper.Controller.Global;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ViewChangePanel extends JPanel implements MouseListener {

    private JLabel listLabel;
    private JLabel tilesLabel;
    private JLabel selected;

    public ViewChangePanel() {
        super();
        FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
        layout.setHgap(1);
        setLayout(layout);
        setPreferredSize(new Dimension(55,30));
        setBackground(Global.colorScheme.getQuaternaryColor());
        listLabel = new JLabel(new ImageIcon(getClass().getResource("/com/stockkeeper/images/listIcon.png")));
        tilesLabel = new JLabel(new ImageIcon(getClass().getResource("/com/stockkeeper/images/tilesIcon.png")));
        listLabel.addMouseListener(this);
        tilesLabel.addMouseListener(this);
        add(listLabel);
        add(tilesLabel);
        // To delete and implement properly
        setSelection(listLabel);
    }


    public JLabel getListLabel() {
        return listLabel;
    }

    public void setListLabel(JLabel listLabel) {
        this.listLabel = listLabel;
    }

    public JLabel getTilesLabel() {
        return tilesLabel;
    }

    public void setTilesLabel(JLabel tilesLabel) {
        this.tilesLabel = tilesLabel;
    }

    private  void setSelection(JLabel label){
        if (label == listLabel){
            listLabel.setIcon(new ImageIcon(getClass().getResource("/com/stockkeeper/images/listIcon2.png")));
            selected = listLabel;
        }
        else{
            tilesLabel.setIcon(new ImageIcon(getClass().getResource("/com/stockkeeper/images/tilesIcon2.png")));
            selected = tilesLabel;
        }

    }

    public JLabel getSelected() {
        return selected;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == listLabel) {
            if(e.getSource() != selected){
                setSelection(listLabel);
                tilesLabel.setIcon(new ImageIcon(getClass().getResource("/com/stockkeeper/images/tilesIcon.png")));
            }
        }
        else{
            if(e.getSource() != selected){
                setSelection(tilesLabel);
               listLabel.setIcon(new ImageIcon(getClass().getResource("/com/stockkeeper/images/listIcon.png")));
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == listLabel) {
            if(e.getSource() != selected)
            listLabel.setIcon(new ImageIcon(getClass().getResource("/com/stockkeeper/images/listIcon2.png")));
        }
        else{
            if(e.getSource() != selected)
            tilesLabel.setIcon(new ImageIcon(getClass().getResource("/com/stockkeeper/images/tilesIcon2.png")));
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == listLabel) {
            if(e.getSource() != selected)
            listLabel.setIcon(new ImageIcon(getClass().getResource("/com/stockkeeper/images/listIcon.png")));
        }
        else{
            if(e.getSource() != selected)
            tilesLabel.setIcon(new ImageIcon(getClass().getResource("/com/stockkeeper/images/tilesIcon.png")));
        }
    }
}
