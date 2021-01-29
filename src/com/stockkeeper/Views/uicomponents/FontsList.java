package com.stockkeeper.Views.uicomponents;

import java.awt.*;

public class FontsList {
    Font sitkaBanner;
    Font sansSerif;
    Font trebuchetMS;
    Font timesNewRomans;
    Font agencyFB;
    Font calibri;


    public static Font getSitkaBanner(int fontType, int size) {
        return new Font("Sitka Banner", fontType, size);
    }

    public static Font getSansSerif(int fontType, int size) {
        return new Font("SansSerif", fontType, size);
    }

    public static Font getTrebuchetMS(int fontType, int size) {
        return new Font("Trebuchet MS", fontType, size);
    }

    public static Font getTimesNewRomans(int fontType, int size) {
        return new Font("Times New Romans", fontType, size);
    }

    public static Font getAgencyFB(int fontType, int size) {
        return new Font("Agency FB", fontType, size);
    }

    public static Font getConsolas(int fontType, int size) {
        return new Font("Consolas", fontType, size);
    }
    public static Font getHelvetica(int fontType, int size) {
        return new Font("Helvetica", fontType, size);
    }

    public static Font getCalibri(int fontType, int size) {
        return new Font("Calibri", fontType, size);
    }

    public static Font getArial(int fontType, int size) {
        return new Font("Arial", fontType, size);
    }
}
