package com.stockkeeper.Views.uicomponents;

import com.stockkeeper.Controller.Global;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TickSwitchableTextInput extends JPanel implements  ActionListener {


    private SwitchableTextInput switchableTextInput;
    private JCheckBox checkBox;

    public TickSwitchableTextInput(Dimension dimension, Boolean numberFormat, String toolTip) {

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setPreferredSize(dimension);
        setBorder(new EmptyBorder(0,3,0,0));
        setBackground(Color.WHITE);
        Dimension dimension2 = new Dimension((int)dimension.getWidth()-25, (int)dimension.getHeight());
        this.switchableTextInput = new SwitchableTextInput(dimension2, numberFormat);
        this.checkBox = new JCheckBox();
        checkBox.setBackground(Color.WHITE);
        add(checkBox);
        add(switchableTextInput);
        checkBox.setToolTipText(toolTip);
        checkBox.setBackground(Color.WHITE);
        checkBox.addActionListener(this);
        switchableTextInput.setVisible(false);
    }

    public SwitchableTextInput getSwitchableTextInput() {
        return switchableTextInput;
    }

    public void setSwitchableTextInput(SwitchableTextInput switchableTextInput) {
        this.switchableTextInput = switchableTextInput;
    }

    public JCheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(JCheckBox checkBox) {
        this.checkBox = checkBox;
    }

    @Override protected void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(0,0,getWidth(),getHeight(),5,5);
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.drawRoundRect(0,0,getWidth()-1,getHeight()-1,5,5);
        super.paintComponent(g);
    }

    @Override public void updateUI() {
        super.updateUI();
        setOpaque(false);
        // setBackground(new Color(0x0, true));
        setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
            switchableTextInput.setVisible(checkBox.isSelected());
    }
}
