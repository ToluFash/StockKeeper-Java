package com.stockkeeper.Views;

import com.stockkeeper.Views.uicomponents.RefreshButton;

import javax.swing.*;

public interface EventItem {

    String getEventText();
    JProgressBar getProgressBar();
    RefreshButton getButton();
    EventRow getEventRow();
    void setEventRow(EventRow eventRow);


}
