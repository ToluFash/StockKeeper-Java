package com.stockkeeper.Views.uicomponents;

import com.stockkeeper.Controller.helpers.Helper;
import com.stockkeeper.Controller.Global;
import com.stockkeeper.Views.uicomponents.fancytextinput.IntegerInputField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;

public class IntegerInput extends JPanel implements ActionListener, MouseListener {

    private JLabel increase;
    private JLabel decrease;
    private ImageIcon blackArrowUp;
    private ImageIcon blackArrowDown;
    private ImageIcon greyArrowUp;
    private ImageIcon greyArrowDown;
    private IntegerInputField numberField;
    private java.util.Timer timer;
    private java.util.Timer timer2;
    private int change;
    private String placeholder;

    public IntegerInput(Dimension dimension) {
        BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
        setLayout(layout);
        setPreferredSize(dimension);
        setBorder(BorderFactory.createEmptyBorder());
        NumberFormat amountFormat = NumberFormat.getNumberInstance();
        Dimension textInputDimension = new Dimension((int)dimension.getWidth()-21, (int)dimension.getHeight());
        numberField = new IntegerInputField(amountFormat, textInputDimension);
        numberField.setPreferredSize(textInputDimension);
        numberField.setValue(0);
        JPanel buttonsPanel = new JPanel(){

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.LIGHT_GRAY);
                g2d.fillRect(0,0,getWidth(),getHeight());
                g2d.setColor(Color.LIGHT_GRAY);
                g2d.fillRoundRect(0,0,getWidth(),getHeight(),5,5);
                g2d.setColor(Color.LIGHT_GRAY);
                g2d.drawRoundRect(0,0,getWidth()-1,getHeight()-1,5,5);
            }
        };

        buttonsPanel.setLayout(new GridLayout(2,1));
        buttonsPanel.setPreferredSize(new Dimension(21, (int) dimension.getHeight()));
        buttonsPanel.setBackground(Global.colorScheme.getDenaryColor());
        blackArrowUp = new ImageIcon(getClass().getResource("/com/stockkeeper/images/arrowBlackUp.png"));
        blackArrowDown = new ImageIcon(getClass().getResource("/com/stockkeeper/images/arrowBlackDown.png"));
        greyArrowUp = new ImageIcon(getClass().getResource("/com/stockkeeper/images/arrowGreyUp.png"));
        greyArrowDown = new ImageIcon(getClass().getResource("/com/stockkeeper/images/arrowGreyDown.png"));
        increase = new JLabel(blackArrowUp);
        decrease = new JLabel(blackArrowDown);
        increase.setPreferredSize(new Dimension(20, (int) dimension.getHeight()/2));
        decrease.setPreferredSize(new Dimension(20, (int) dimension.getHeight()/2));
        increase.setBorder(new EmptyBorder(3,3,1,2));
        decrease.setBorder(new EmptyBorder(0,3,3,2));
        increase.addMouseListener(this);
        decrease.addMouseListener(this);
        Helper.addComponents(buttonsPanel, new JComponent[] {increase, decrease});
        Helper.addComponents(this, new JComponent[] {numberField, buttonsPanel});
        change = 1;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public IntegerInputField getNumberField() {
        return numberField;
    }

    public void setNumberField(IntegerInputField numberField) {
        this.numberField = numberField;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        try{

        if (e.getSource() == increase)
            numberField.setValue((int) numberField.getValue() + change);
        if (e.getSource() == decrease && (int)numberField.getValue() > 0)
            numberField.setValue((int)numberField.getValue() - change < 0 ? 0 : (int)numberField.getValue() - change);
        }

    catch (Exception el){
        Global.Print(el.getStackTrace());
    }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == increase)
            increase.setIcon(greyArrowUp);
        if (e.getSource() == decrease)
            decrease.setIcon(greyArrowDown);

    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == increase)
            increase.setIcon(blackArrowUp);
        if (e.getSource() == decrease)
            decrease.setIcon(blackArrowDown);

    }
}
