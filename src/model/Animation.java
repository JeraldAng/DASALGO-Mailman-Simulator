package model;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
public class Animation {
    private BufferedImage sprite;
    private BufferedImage bg;
    private JFrame f;
    private Container contentPane;
    private DrawingComponent dc;
    private Timer anim;
    private Timer pause;
    private int x;
    private int y;
    //private int i;
    private boolean p1, p2, p3;

    private class DrawingComponent extends JComponent{
        @Override
        protected void paintComponent(Graphics g){
            Graphics2D g2d = (Graphics2D) g;
            try{
                sprite = ImageIO.read(new File("MAIL DEILVERY SPRITE PNG(1).png")); //just edit the filenames if iba yung filenames sayo
                bg = ImageIO.read(new File("MailmanSend.jpg"));
            }
            catch(IOException ex){
                System.out.println("File not found");
            }
            g2d.drawImage(bg, 0, 0, null);
            g2d.drawImage(sprite, x, y, null);
            if(x >= 320 && p3 == false){
                anim.stop();
                pause.start();
                p3 = true;
            }
            else if(x >= 200 && p2 == false){
                anim.stop();
                pause.start();
                p2 = true;
            }
            else if(x >= 80 && p1 == false){
                anim.stop();
                pause.start();
                p1 = true;
            }
            if(x < 440)
                x += 1;
        }
    }

    public void setupFrame(){
        x = -45; //starting coordinates(x & y)
        y = 245;
        //i = 2000;
        pause = new Timer(2000, new ActionListener(){  // change the number (naka-2000 siya ngayon) to change how long it pauses on a point
            @Override
            public void actionPerformed(ActionEvent ae){
                anim.start();
            }
        });
        pause.setRepeats(false);
        p1 = false;
        p2 = false;
        p3 = false;
        f = new JFrame();
        contentPane = f.getContentPane();
        dc = new DrawingComponent();
        f.setTitle("Mailman Animation");
        contentPane.setPreferredSize(new Dimension(600, 400));
        contentPane.add(dc);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        anim = new Timer(1000/60, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                dc.repaint();
            }
        });
        anim.start();
        f.setVisible(true);
    }
}
