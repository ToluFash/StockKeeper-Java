package com.stockkeeper.Views.labels;

import com.stockkeeper.Views.containers.SettingsContainer;
import com.stockkeeper.Views.containers.SubSettingsContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class TopMenuLabel extends SettingsContainer {
    private  SettingsContainer container;
    private SubMenuLabel label;
    private int freeHeight;
    private  boolean loaded;

    public TopMenuLabel(String text, int textstart, int items) {
        super(280, 1);
        dimension = new Dimension(280, 16);
        setPreferredSize(dimension);
        this.label= new SubMenuLabel(text, textstart);
        this.container = new SettingsContainer(250, items);
        freeHeight = items * 2;
        container.setBackground(Color.BLUE);
        setBackground(Color.PINK);
        init();
    }
    public void init(){
        super.add(label);
        MouseListener mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                {
                    load();
                    revalidate();
                    repaint();
                }
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
        };
        label.addMouseListener(mouseListener);
        loaded=false;
    }

    public  void addToContainer(Component component){
        container.add(component);
    }
    public  void removeFromContainer(Component component){
        container.remove(component);
    }

    public void load(){
        if (loaded)
        {
            super.remove(container);
            layout.setVgap(0);
        }
        else{
            super.add(container);
            layout.setVgap(1);
        }
        loaded = !loaded;
        revalidate();
        repaint();
    }
}
