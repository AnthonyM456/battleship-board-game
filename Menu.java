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
            g.setColor(Color.RED);  // tutorial button
            g.fillRect((4*xdelta)+(xdelta/2)+getX(),403+Window.getY(0),3*xdelta,2*ydelta);
            
            g.setColor(Color.WHITE);    //start text
            g.setFont(new Font("High Tower Text",Font.BOLD,60));
            g.drawString("START", 39+getX(),490+getY());     
            
            g.setColor(Color.WHITE);    //How to play text
            g.setFont(new Font("High Tower Text",Font.BOLD,45));
            g.drawString("HOW TO ", 370,460+getY());   
            g.drawString("PLAY ", 410,515+getY());
            
//            Levels.SwitchLevel();
        }
        if (tutorial)          // if you clicked "how to play"
        {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, Window.xsize, Window.ysize);
            g.setColor(Color.WHITE);
            g.fillRect((4*xdelta)+(xdelta/2)+getX()-2,403+Window.getY(0)-2,3*xdelta+4,2*ydelta+4);
            
            g.setColor(Color.BLACK);  // tutorial button
            g.fillRect((4*xdelta)+(xdelta/2)+getX(),403+Window.getY(0),3*xdelta,2*ydelta);
            
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial",Font.BOLD,50));
            g.drawString("RETURN", 370,460+getY());
            g.drawImage(ReturnTXT,590,465+getY(),-230,70,thisObj);
            g.setFont(new Font("High Tower Text",Font.PLAIN,30));
            g.drawString("Place ship:  LEFT CLICK",20,getY());
            g.drawString("Rotate ship:  R", 20,30+getY());
            g.drawString("Scout Ship movement: WASD", 20,60+getY());
            g.drawString("Destroy ship:  SPACE", 20,90+getY());
            
            g.setFont(new Font("High Tower Text",Font.BOLD,40));
            g.drawString("PHASE 1: Ship Placement", 20,160+getY());
            g.setFont(new Font("High Tower Text",Font.PLAIN,21));
            g.drawString("Each player has 5 battleships to place.", 20,190+getY());
            g.drawString("Make sure your opponent doesn't see where you place your ships.", 20,210+getY());
            
            g.setFont(new Font("High Tower Text",Font.BOLD,40));
            g.drawString("PHASE 2: Seek and Destroy.", 20,270+getY());
            g.setFont(new Font("High Tower Text",Font.PLAIN,21));
            g.drawString("To play the game, you must place your SCOUT SHIP,", 20,300+getY());
            g.drawString("guess where your opponent's battleships are by moving with WASD,", 20,320+getY());
            g.drawString("and destroy them by pressing SPACE. Destroy all ships to win!", 20,340+getY());
            g.drawString("If you destroy a piece of a ship, your turn continues.", 20,360+getY());
            g.drawString("If you miss, the other player's turn begins. Good Luck!", 20,380+getY());
           
            
//            Levels.SwitchLevel();
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
    public static boolean tutorial(){
        return(tutorial);
    }
    
    public static void GSSwitch(){
        gameStart = false;
    }
    public static void checkClick(int xpixel, int ypixel)
    {
        int xdelta = Board.xdelta();
        int ydelta = Board.ydelta();
        int zcol = (xpixel-Window.getX(0));
        int zrow = (ypixel-Window.getY(0));
//        System.out.println(zcol+  " " + zrow);
            if (zcol > 35 && zcol < 265 && zrow > 400 && zrow < 540){
//                System.out.println("Started Game"); 
                gameStart = true;
                
                Levels.SwitchLevel();
            }
            else if (zcol > 335 && zcol < 565 && zrow > 400 && zrow < 540 && !gameStart)
            {
//                System.out.println("How to Play");
                if (tutorial)
                    tutorial = false;
                else 
                    tutorial = true;
                
                Levels.SwitchLevel();
            }
        
        
        
    }
    
}
