/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author tomta
 */
public class Menu {
    public static void Draw(Graphics2D g){
        int xdelta = Board.xdelta();
        int ydelta = Board.ydelta();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Window.xsize, Window.ysize);
        g.setColor(Color.RED);
        g.fillRect(75+getX(),67+Window.getY(0),3*xdelta,3*ydelta);
    }
    public static int getX()
    {
        return(Window.getX(0));
    }
    public static int getY() {
        return (Window.getY(0));
    }
    public static void checkClick(int xpixel, int ypixel)
    {
        int xdelta = Board.xdelta();
        int ydelta = Board.ydelta();
        int zcol = (xpixel-Window.getX(0));
        int zrow = (ypixel-Window.getY(0));
        System.out.println(zcol+  " " + zrow);
//        if (zcol )
//        
    }
    
}
