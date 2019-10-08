/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author tomta
 */
public class Menu {
    private static boolean gameStart;
    private static boolean tutorial;
    private static Image MenuBG;
    private static Image BattleTxt;
    private static Image ReturnTXT;
    
    public static void Draw(Graphics2D g, Battleship thisObj){
        int xdelta = 75;
        int ydelta = 65;
        if (!gameStart){                    // Before pressing start
            
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, Window.xsize, Window.ysize);
            Init();
            
            g.drawImage(MenuBG,getX(),getY(),Window.getWidth2(),Window.getHeight2(),thisObj);
            g.drawImage(BattleTxt,getX(),getY(),Window.getWidth2(),Window.getHeight2()/4,thisObj);
            
            g.setColor(Color.WHITE);
            g.fillRect(xdelta/2+getX()-2,398+Window.getY(0)+3,3*xdelta+4,2*ydelta+4);
            g.setColor(Color.RED); ///Start button
            g.fillRect(xdelta/2+getX(),403+Window.getY(0),3*xdelta,2*ydelta);
            
            g.setColor(Color.WHITE);
            g.fillRect((4*xdelta)+(xdelta/2)+getX()-2,403+Window.getY(0)-2,3*xdelta+4,2*ydelta+4);
            g.setColor(Color.BLUE);  // tutorial button
            g.fillRect((4*xdelta)+(xdelta/2)+getX(),403+Window.getY(0),3*xdelta,2*ydelta);
            
            g.setColor(Color.WHITE);    //start text
            g.setFont(new Font("Arial",Font.BOLD,60));
            g.drawString("START", 50+getX(),490+getY());     
            
            g.setColor(Color.WHITE);    //How to play text
            g.setFont(new Font("Arial",Font.BOLD,50));
            g.drawString("HOW TO ", 370,460+getY());   
            g.drawString("PLAY ", 370,515+getY());
        }
        if (tutorial)          // if you clicked "how to play"
        {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, Window.xsize, Window.ysize);
            g.setColor(Color.WHITE);
            g.fillRect((4*xdelta)+(xdelta/2)+getX()-2,403+Window.getY(0)-2,3*xdelta+4,2*ydelta+4);
            
            g.setColor(Color.BLUE);  // tutorial button
            g.fillRect((4*xdelta)+(xdelta/2)+getX(),403+Window.getY(0),3*xdelta,2*ydelta);
            
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial",Font.BOLD,50));
            g.drawString("RETURN", 370,460+getY());
            g.drawImage(ReturnTXT,590,465+getY(),-230,70,thisObj);
            
            g.setFont(new Font("High Tower Text",Font.PLAIN,20));
            g.drawString("Place ship/ Destroy ship:  LEFT CLICK",20,getY());
            g.drawString("Rotate ship:  R", 20,20+getY());
            g.drawString("Scout Ship movement: WASD", 20,40+getY());
            g.drawString("Scout Ship destroy:  SPACE", 20,60+getY());
            g.drawString("Each player has 5 ships to place.", 20,100+getY());
            g.drawString("After each player places all their ships, the battle begins.", 20,120+getY());
            g.drawString("To play the game, you must guess where the other player's ships are,", 20,160+getY());
            g.drawString("and destroy them by pressing LEFT CLICK. Destroy all ships to win!", 20,180+getY());
            g.drawString("If you destroy a piece of a ship, your turn continues.", 20,220+getY());
            g.drawString("If you miss, the other player's turn begins.", 20,240+getY());
            g.drawString("SCOUT SHIP:", 20,280+getY());
            g.drawString("After 2 TURNS (for each player), you get a SCOUT SHIP.", 20,300+getY());
            g.drawString("The scout ship can only move 2 times, so place it wisely.", 20,320+getY());
            g.drawString("Move the scout ship with WASD.", 20,340+getY());
            g.drawString("Press SPACE to destroy the ship you are under.", 20,360+getY());

        }

    }
    public static void drawImage(Graphics2D g, Battleship thisObj, Image image,int xpos,int ypos,double rot,double xscale,
                double yscale) {
            int width = image.getWidth(thisObj);
            int height = image.getHeight(thisObj);
            g.translate(xpos,ypos);
            g.rotate(rot  * Math.PI/180.0);
            g.scale( xscale , yscale );

            g.drawImage(image,-width/2,-height/2,
            width,height,thisObj);

            g.scale( 1.0/xscale,1.0/yscale );
            g.rotate(-rot  * Math.PI/180.0);
            g.translate(-xpos,-ypos);
        }
    public static void Init()
    {
        MenuBG = Toolkit.getDefaultToolkit().getImage("./BattleshipBG.jpg");
        BattleTxt = Toolkit.getDefaultToolkit().getImage("./BattleshipTitle.PNG");
        ReturnTXT = Toolkit.getDefaultToolkit().getImage("./ReturnText.PNG");
    }
    public static void Reset(){
        gameStart = false;
        tutorial = false;
    }
    public static int getX() {
        return(Window.getX(0));
    }
    public static int getY() {
        return (Window.getY(0));
    }
    public static boolean gameStart(){
        return(gameStart);
    }
    public static void checkClick(int xpixel, int ypixel)
    {
        int xdelta = Board.xdelta();
        int ydelta = Board.ydelta();
        int zcol = (xpixel-Window.getX(0));
        int zrow = (ypixel-Window.getY(0));
//        System.out.println(zcol+  " " + zrow);
        if (zcol > 35 && zcol < 265 && zrow > 400 && zrow < 540){
//            System.out.println("start"); 
            gameStart = true;
        }
        else if (zcol > 335 && zcol < 565 && zrow > 400 && zrow < 540 && !gameStart)
        {
//            System.out.println("howtoplay");
            if (tutorial)
                tutorial = false;
            else 
                tutorial = true;
        }
        
    }
    
}
