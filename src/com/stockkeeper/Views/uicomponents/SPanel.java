package com.stockkeeper.Views.uicomponents;

import javax.swing.*;
import java.awt.*;

public class SPanel extends JPanel {
    public SPanel(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
    }

    public SPanel(LayoutManager layout) {
        super(layout);
    }

    public SPanel(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
    }

    public SPanel(Dimension size) {
        setMinimumSize(size);
    }
    public SPanel(LayoutManager layout, Dimension size) {
        setMinimumSize(size);
    }
    public SPanel() {
    }
}
