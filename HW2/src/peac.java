import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class peac extends JPanel {
    int startx=300;
    int starty=50;
    int size=25;
    Point p1 = new Point(5,5);
    Point[] drawpos={ p1,p1,p1,p1, p1,p1,p1,p1,p1,p1,p1,p1,p1,p1,p1,p1};
    Point[] textpos={ p1,p1,p1,p1, p1,p1,p1,p1,p1,p1,p1,p1,p1,p1,p1,p1};
    boolean[] wheream={ false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
    int[] sans = {1, 14, 14, 4, 11,7,6,9,8,10,10,5,13,2,3,15};



    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(Color.black);
        g.setColor(Color.white);;

        int drawy=starty;
        int drawx=startx;
        for (int i = 0; i <16; i++) {
            drawy=starty + 50*(i/4);
            drawx=startx + 50*(i%4);
            Point drawpoint= new Point(drawx,drawy);
            drawpos[i]=drawpoint;
            Point drawp2= new Point(drawx-2,drawy+20);
            textpos[i]=drawp2;
        }

        Font currentFont = g.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.9F);
        g.setFont(newFont);
        for (int i = 0; i <drawpos.length; i++) {
            if (wheream[i]){
                g.setColor(Color.green);
                g.fillRect( (int) drawpos[i].getX(), (int) drawpos[i].getY(),size,size);
                g.setColor(Color.BLUE);
                g.drawString(Integer.toString(sans[i]), (int) textpos[i].getX(), (int) textpos[i].getY());
            }
            else{
                g.setColor(Color.white);
                g.fillRect( (int) drawpos[i].getX(), (int) drawpos[i].getY(),size,size);
                g.setColor(Color.BLUE);
                g.drawString(Integer.toString(sans[i]), (int) textpos[i].getX(), (int) textpos[i].getY());
            }
        }
    }

}