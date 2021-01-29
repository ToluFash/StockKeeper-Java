package com.stockkeeper.Views;

import com.stockkeeper.Controller.Global;
import com.stockkeeper.Views.uicomponents.FontsList;
import com.stockkeeper.Views.uicomponents.RefreshButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseListener;

public class EventRow extends JPanel {

    private MouseListener mouseListener;
    private EventItem eventItem;
    private boolean selected;
    private FlowLayout layout;
    private JLabel eventItemText;
    private JProgressBar progressBar;
    private RefreshButton rButton;
    private boolean buttonOn;

    public EventRow(EventItem eventItem, Boolean button, MouseListener mouseListener) {

        super();
        this.eventItem = eventItem;
        this.mouseListener = mouseListener;
        buttonOn = button;
        setPreferredSize(new Dimension(370,30));
        setBackground(Global.colorScheme.getSecondaryColor());
        layout = new FlowLayout(FlowLayout.LEFT);
        setLayout(layout);
        setBorder(new EmptyBorder(0,2,0,2));

        setUpRow();
    }

    public EventItem getTicket() {
        return eventItem;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean getButtonOn() {
        return buttonOn;
    }

    public void setButtonOn(boolean buttonOn) {
        this.buttonOn = buttonOn;
        rButton.setVisible(buttonOn);
    }

    private void setUpRow(){
        eventItemText = new JLabel(eventItem.getEventText());
        eventItemText.setFont(FontsList.getHelvetica(Font.PLAIN, 12));
        progressBar = eventItem.getProgressBar();
        rButton = eventItem.getButton();

        //set sizes of labels
        eventItemText.setPreferredSize(new Dimension(220,30));
        progressBar.setPreferredSize(new Dimension(100,12));
        rButton.setPreferredSize(new Dimension(20,20));
        rButton.addMouseListener(mouseListener);

        add(eventItemText);
        add(progressBar);
        add(rButton);
        rButton.setVisible(buttonOn);
    }


    public EventItem getEventItem() {
        return eventItem;
    }

    public void setEventItem(EventItem eventItem) {
        this.eventItem = eventItem;
    }

    public static class EventTest implements EventItem, Runnable{

        private String eventText;
        private JProgressBar progressBar;
        private RefreshButton rButton;
        private int count;
        private EventRow eventRow;

        public EventTest() {
            this.eventRow = eventRow;
            eventText ="Commit Sales 238761 " + Global.count;
            progressBar = new JProgressBar(0,100){
                @Override
                protected void paintBorder(Graphics g) {
                    g.drawRect(1,1,getWidth()-1,getHeight()-1);
                }
            };
            progressBar.setForeground(Color.GREEN);
            progressBar.setFont(FontsList.getSitkaBanner(Font.PLAIN, 8));
            rButton = new RefreshButton();
            Global.count++;
        }

        @Override
        public String getEventText() {
            return eventText;
        }

        @Override
        public JProgressBar getProgressBar() {
            return progressBar;
        }

        @Override
        public RefreshButton getButton() {
            return rButton;
        }

        @Override
        public EventRow getEventRow() {
            return eventRow;
        }

        public void setEventRow(EventRow eventRow) {
            this.eventRow = eventRow;
        }


        @Override
        public void run() {
            count = 0;
            progressBar.setValue(0);
            try{
                EventPanel.available.acquire();
                while (count <= 100)
                {
                    count += 1;
                    progressBar.setValue(count);
                    if (count > 99){
                        double x = Math.random();
                        if(x < 0.5){
                            Global.eventPanel.moveFromPendingtoFailed(this);
                            return;
                        }
                    }
                }
                Global.eventPanel.moveFromPendingtoCompleted(this);
            }
            catch (InterruptedException e){

            }
            finally {
                EventPanel.available.release();
            }

        }

    }
}
