package com.stockkeeper.Views.uicomponents;

import com.stockkeeper.Views.labels.SLabel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class STable extends JPanel {
    private String []header;
    private String[][] data;
    private GridLayout gridLayout;
    private Color headerBackground = new Color(0x96BCFF);
    private Color[] bodyBackground = {new Color(0xFFFFFF), new Color(0x989898)};


    public STable( String[][] data,String[] header) {
        this.header = header;
        this.data = data;
        gridLayout =new GridLayout(data.length+1,header.length);
        setLayout(gridLayout);
        setUpHeader();
        setUpBody();
    }

    public STable( String[][] data,String[] header,Color hB, Color[] bB) {
        this.header = header;
        this.data = data;
        this.headerBackground =hB;
        this.bodyBackground =bB;
        gridLayout =new GridLayout(data.length+1,header.length);
        setLayout(gridLayout);
        setUpHeader();
        setUpBody();
    }
    public STable( String[][] data,String[] header, int hGap, int vGap) {
        this.header = header;
        this.data = data;
        gridLayout =new GridLayout(data.length+1,header.length);
        gridLayout.setHgap(hGap);
        gridLayout.setVgap(vGap);
        setLayout(gridLayout);
        setUpHeader();
        setUpBody();
    }


    public STable( String[][] data,String[] header,Color hB, Color[] bB, int hGap, int vGap) {
        this.header = header;
        this.data = data;
        this.headerBackground =hB;
        this.bodyBackground =bB;
        gridLayout =new GridLayout(data.length+1,header.length);
        gridLayout.setHgap(hGap);
        gridLayout.setVgap(vGap);
        setLayout(gridLayout);
        setUpHeader();
        setUpBody();
    }




    private void Print(Object obj){
        System.out.println(obj);
    }



    private void setUpBody(){
        int y = 0;
        for (String[] obj: data){
            for(int x = 0; x < header.length; x++ ){
                SLabel cell = new SLabel(obj[x]);
                cell.setBackground(bodyBackground[y%2]);
                cell.setBorder(new LineBorder(bodyBackground[(y+1)%2]));
                add(cell);
            }
            y++;
        }
    }
    private void setUpHeader(){
        for (String obj: header){
                SLabel cell = new SLabel(obj);
            cell.setBorder(new LineBorder(bodyBackground[1]));
                cell.setBackground(headerBackground);
                add(cell);
        }
    }

    public Color getHeaderBackground() {
        return headerBackground;
    }

    public void setHeaderBackground(Color headerBackground) {
        this.headerBackground = headerBackground;
    }

    public Color[] getBodyBackground() {
        return bodyBackground;
    }

    public void setBodyBackground(Color[] bodyBackground) {
        this.bodyBackground = bodyBackground;
    }


    public void setHgap(int hGap){
        gridLayout.setHgap(hGap);


    }
    public void setVgap(int vGap){
        gridLayout.setVgap(vGap);


    }

}
