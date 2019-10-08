package battleship;
import java.awt.*;

import static battleship.Board.xdelta;

public class Piece {
    private Color color;

    private int shipType;
    private static int shipLength = 5;
    private static int sub;
    private boolean shipRotation;
    private static Image battleship_blue;
    private static Image battleship_red;
    private static Image bomber_blue;
    private static Image bomber_red;
    private static Image cruiser_blue;
    private static Image cruiser_red;
    private static Image destroyer_blue;
    private static Image destroyer_red;
    private static Image sub_blue;    
    private static Image sub_red;
    private static Image carrier_blue;
    private static Image carrier_red;
    private static Image battleship_blueV;
    private static Image battleship_redV;
    private static Image bomber_blueV;
    private static Image bomber_redV;
    private static Image cruiser_blueV;
    private static Image cruiser_redV;
    private static Image destroyer_blueV;
    private static Image destroyer_redV;
    private static Image sub_blueV;    
    private static Image sub_redV;
    private static Image carrier_blueV;
    private static Image carrier_redV;
    private static Image scoutRight;    
    private static Image scoutDown; 
    private static Image scoutUp;
    private static Image scoutLeft;


    
    private static Image X;
    
    Piece(Color _color)
    {
        shipType = Board.getshipSize();
        color = _color;
        shipRotation = Board.getRotate();
    }
    public Color getColor()
    {
        return (color);
    }

    public void draw(Graphics2D g,int row,int column, int xdelta,int ydelta, Battleship thisObj) {
        g.setColor(color); 
        if (!shipRotation)  ///Horizontal
        {
            if(color == Player.getPlayer1().getColor()) //blue
            {
                if (shipType == 6)
                    g.drawImage(carrier_blue,Window.getX(0)+xdelta*column, Window.getY(0)+ydelta*row,xdelta*shipType,ydelta,thisObj);
                else if (shipType == 5)
                    g.drawImage(battleship_blue,Window.getX(0)+xdelta*column, Window.getY(0)+ydelta*row,xdelta*shipType,ydelta,thisObj);
                else if (shipType == 4)
                    g.drawImage(cruiser_blue,Window.getX(0)+xdelta*column, Window.getY(0)+ydelta*row,xdelta*shipType,ydelta,thisObj);
                else if (shipType == 3)
                    g.drawImage(sub_blue,Window.getX(0)+xdelta*column, Window.getY(0)+ydelta*row,xdelta*shipType,ydelta,thisObj);
                else if (shipType == 2)
                    g.drawImage(destroyer_blue,Window.getX(0)+xdelta*column, Window.getY(0)+ydelta*row,xdelta*shipType,ydelta,thisObj);
            }
            else if(color == Player.getPlayer2().getColor())    //red
            {
                if (shipType == 6)
                    g.drawImage(carrier_red,Window.getX(0)+xdelta*column, Window.getY(0)+ydelta*row,xdelta*shipType,ydelta,thisObj);
                else if (shipType == 5)
                    g.drawImage(battleship_red,Window.getX(0)+xdelta*column, Window.getY(0)+ydelta*row,xdelta*shipType,ydelta,thisObj);
                else if (shipType == 4)
                    g.drawImage(cruiser_red,Window.getX(0)+xdelta*column, Window.getY(0)+ydelta*row,xdelta*shipType,ydelta,thisObj);
                else if (shipType == 3)
                    g.drawImage(sub_red,Window.getX(0)+xdelta*column, Window.getY(0)+ydelta*row,xdelta*shipType,ydelta,thisObj);
                else if (shipType == 2)
                    g.drawImage(destroyer_red,Window.getX(0)+xdelta*column, Window.getY(0)+ydelta*row,xdelta*shipType,ydelta,thisObj);
            }
            else if (color == Color.gray)  //draw X
                g.drawImage(X,Window.getX(0)+xdelta*column, Window.getY(0)+ydelta*row,xdelta,ydelta,thisObj);
            else if (color == Color.white){
//                g.fillOval(Window.getX(column*xdelta), Window.getY(row*ydelta),xdelta,ydelta);
            }
            else if (color == Color.green){ //scout ship
                if (Scout.scoutDirection() == 0){ //up W
                g.drawImage(scoutUp,Window.getX(0)+xdelta*column, Window.getY(0)+ydelta*row,xdelta,ydelta,thisObj);    
                }
                else if (Scout.scoutDirection() == 1){ //left A
                    g.drawImage(scoutLeft,Window.getX(0)+xdelta*column, Window.getY(0)+ydelta*row,xdelta,ydelta,thisObj);    
                }
                else if (Scout.scoutDirection() == 2){ //down S
                    g.drawImage(scoutDown,Window.getX(0)+xdelta*column, Window.getY(0)+ydelta*row,xdelta,ydelta,thisObj);    
                }
                else if (Scout.scoutDirection() == 3){ //right D
                    g.drawImage(scoutRight,Window.getX(0)+xdelta*column, Window.getY(0)+ydelta*row,xdelta,ydelta,thisObj);    
                }
            }
        }
        else    //vertical
        {
            if(color == Player.getPlayer1().getColor()) //blue
            {
                if (shipType == 6)
                    g.drawImage(carrier_blueV,Window.getX(0)+xdelta*column, Window.getY(0)+ydelta*row,xdelta,ydelta*shipType,thisObj);
                else if (shipType == 5)
                    g.drawImage(battleship_blueV,Window.getX(0)+xdelta*column, Window.getY(0)+ydelta*row,xdelta,ydelta*shipType,thisObj);
                else if (shipType == 4)
                    g.drawImage(cruiser_blueV,Window.getX(0)+xdelta*column, Window.getY(0)+ydelta*row,xdelta,ydelta*shipType,thisObj);
                else if (shipType == 3)
                    g.drawImage(sub_blueV,Window.getX(0)+xdelta*column, Window.getY(0)+ydelta*row,xdelta,ydelta*shipType,thisObj);
                else if (shipType == 2)
                    g.drawImage(destroyer_blueV,Window.getX(0)+xdelta*column, Window.getY(0)+ydelta*row,xdelta,ydelta*shipType,thisObj);
            }
            else if(color == Player.getPlayer2().getColor())    //red
            {
                if (shipType == 6)
                    g.drawImage(carrier_redV,Window.getX(0)+xdelta*column, Window.getY(0)+ydelta*row,xdelta,ydelta*shipType,thisObj);
                else if (shipType == 5)
                    g.drawImage(battleship_redV,Window.getX(0)+xdelta*column, Window.getY(0)+ydelta*row,xdelta,ydelta*shipType,thisObj);
                else if (shipType == 4)
                    g.drawImage(cruiser_redV,Window.getX(0)+xdelta*column, Window.getY(0)+ydelta*row,xdelta,ydelta*shipType,thisObj);
                else if (shipType == 3)
                    g.drawImage(sub_redV,Window.getX(0)+xdelta*column, Window.getY(0)+ydelta*row,xdelta,ydelta*shipType,thisObj);
                else if (shipType == 2)
                    g.drawImage(destroyer_redV,Window.getX(0)+xdelta*column, Window.getY(0)+ydelta*row,xdelta,ydelta*shipType,thisObj);
            }
            else if (color == Color.gray)  //draw X
                g.drawImage(X,Window.getX(0)+xdelta*column, Window.getY(0)+ydelta*row,xdelta,ydelta,thisObj);
            else if (color == Color.white)
            {
//                g.fillOval(Window.getX(column*xdelta), Window.getY(row*ydelta),xdelta,ydelta);
            }
            else if (color == Color.green)
            {
                if (Scout.scoutDirection() == 0){ //up W
                g.drawImage(scoutUp,Window.getX(0)+xdelta*column, Window.getY(0)+ydelta*row,xdelta,ydelta,thisObj);    
                }
                else if (Scout.scoutDirection() == 1){ //left A
                    g.drawImage(scoutLeft,Window.getX(0)+xdelta*column, Window.getY(0)+ydelta*row,xdelta,ydelta,thisObj);    
                }
                else if (Scout.scoutDirection() == 2){ //down S
                    g.drawImage(scoutDown,Window.getX(0)+xdelta*column, Window.getY(0)+ydelta*row,xdelta,ydelta,thisObj);    
                }
                else if (Scout.scoutDirection() == 3){ //right D
                    g.drawImage(scoutRight,Window.getX(0)+xdelta*column, Window.getY(0)+ydelta*row,xdelta,ydelta,thisObj);    
                }
            }

        }
        
    }
    
    public static void Init(){
        battleship_blue = Toolkit.getDefaultToolkit().getImage("./battleship_blue.png");
        battleship_red = Toolkit.getDefaultToolkit().getImage("./battleship_red.png");
        
        bomber_blue = Toolkit.getDefaultToolkit().getImage("./bomber_blue.png");
        bomber_red = Toolkit.getDefaultToolkit().getImage("./bomber_red.png");
        
        cruiser_blue = Toolkit.getDefaultToolkit().getImage("./cruiser_blue.png");
        cruiser_red = Toolkit.getDefaultToolkit().getImage("./cruiser_red.png");
        
        carrier_blue = Toolkit.getDefaultToolkit().getImage("./carrier_blue.png");
        carrier_red = Toolkit.getDefaultToolkit().getImage("./carrier_red.png");
        
        destroyer_blue = Toolkit.getDefaultToolkit().getImage("./destroyer_blue.png");
        destroyer_red = Toolkit.getDefaultToolkit().getImage("./destroyer_red.png");
        
        sub_blue = Toolkit.getDefaultToolkit().getImage("./sub_blue.png");  
        sub_red = Toolkit.getDefaultToolkit().getImage("./sub_red.png");    
        
        battleship_blueV = Toolkit.getDefaultToolkit().getImage("./battleship_blueV.png");
        battleship_redV = Toolkit.getDefaultToolkit().getImage("./battleship_redV.png");
        
        bomber_blueV = Toolkit.getDefaultToolkit().getImage("./bomber_blueV.png");
        bomber_redV = Toolkit.getDefaultToolkit().getImage("./bomber_redV.png");
        
        cruiser_blueV = Toolkit.getDefaultToolkit().getImage("./cruiser_blueV.png");
        cruiser_redV = Toolkit.getDefaultToolkit().getImage("./cruiser_redV.png");
        
        carrier_blueV = Toolkit.getDefaultToolkit().getImage("./carrier_blueV.png");
        carrier_redV = Toolkit.getDefaultToolkit().getImage("./carrier_redV.png");
        
        destroyer_blueV = Toolkit.getDefaultToolkit().getImage("./destroyer_blueV.png");
        destroyer_redV = Toolkit.getDefaultToolkit().getImage("./destroyer_redV.png");
        
        sub_blueV = Toolkit.getDefaultToolkit().getImage("./sub_blueV.png");  
        sub_redV = Toolkit.getDefaultToolkit().getImage("./sub_redV.png");    
        
        scoutRight = Toolkit.getDefaultToolkit().getImage("./scoutRight.png");        
        scoutDown = Toolkit.getDefaultToolkit().getImage("./scoutDown.png");  
        scoutUp = Toolkit.getDefaultToolkit().getImage("./scoutUp.png");    
        scoutLeft = Toolkit.getDefaultToolkit().getImage("./scoutLeft.png");    
        
        X = Toolkit.getDefaultToolkit().getImage("./XPixel.png");
    }
    
    
}
