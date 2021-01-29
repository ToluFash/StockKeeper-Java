package com.stockkeeper.Views.labels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class TopMenuLabel2 extends SubMenuLabel implements MouseListener {
    private  boolean loaded;
    private  boolean in;
    private ArrayList<Component> components;
    private ArrayList<TopMenuLabel2> topMenuLabel2s;
    private JPanel panel;
    public TopMenuLabel2(String text) {
        super(text, 5, new Font("Segoe UI", Font.PLAIN, 14));
        panel = new JPanel(new GridLayout(2,1));
        panel.setPreferredSize(new Dimension(150,20));
        setPreferredSize(new Dimension(260,20));
        setForeground(new Color(0x6898DC));
        loaded = false;
        components = new ArrayList<>();
        topMenuLabel2s = new ArrayList<>();
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (in)
        {
            changeBackground(g);
        }
        else
        {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,    RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0,0,getWidth(),getHeight());
            g2d.setColor(getForeground());
            Image image;
            if(loaded)
                image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/stockkeeper/images/setdown.png"));
            else
                image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/stockkeeper/images/setright.png"));
            g2d.drawImage(image,textstart,getHeight()/2 - 3,null);
            g2d.drawString(getText(), textstart + 13,getHeight()/2 + 6);
        }
    }

    protected void changeBackground(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,    RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(0x6898DC));
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(getForeground());
        Image image;
        if(loaded)
            image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/stockkeeper/images/setdown2.png"));
        else
            image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/stockkeeper/images/setright2.png"));
        g2d.drawImage(image,textstart,getHeight()/2 - 3,null);
        g2d.setColor(Color.white);
        g2d.drawString(getText(), textstart + 13,getHeight()/2 + 7);
    }

    public void expand(){
        if (!loaded){
            //getParent().add(panel);
            ArrayList<Component> arrayList = new ArrayList<>();
            for (int x = 0; x < getParent().getComponents().length; x++) {
                arrayList.add(getParent().getComponents()[x]);
                if (getParent().getComponents()[x] == this){
                        arrayList.addAll(components);
                }
            }
            for (int x = 0; x < arrayList.size(); x++) {
                getParent().add(arrayList.get(x));
            }

        }
        else {
           for (int x = 0; x < components.size(); x++){
               getParent().remove(components.get(x));
           }
           // getParent().remove(panel);

        }
        loaded=!loaded;
    }

    public void contract(){
        if (loaded)
           for (int x = 0; x < components.size(); x++){
               getParent().remove(components.get(x));
           }
        loaded= false;
    }
    public int getPosition(){
        int position = -1;
        for (int x = 0; x < getParent().getComponents().length; x++){
            if (getParent().getComponents()[x] == this){
                position = x;
                break;
            }
        }

        return position;
    }

    public Component add(SubMenuLabel comp) {
        components.add(comp);
        panel.add(comp);
        comp.setTextstart(textstart + 15);
        if (comp instanceof  TopMenuLabel2)
            topMenuLabel2s.add((TopMenuLabel2) comp);
        return comp;
    }

    public void remove(Component comp) {
        components.remove(comp);
        panel.remove(comp);
    }

    @Override
    public void revalidate() {
        super.revalidate();
        Component parent = getParent();
        while(parent != null){
            parent.revalidate();
            parent = parent.getParent();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (int x = 0; x < topMenuLabel2s.size(); x++){
            topMenuLabel2s.get(x).contract();
        }
        expand();
        revalidate();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        changeBackground(getGraphics());
        in = true;

    }

    @Override
    public void mouseExited(MouseEvent e) {
        in = false;
        repaint();
    }
}
