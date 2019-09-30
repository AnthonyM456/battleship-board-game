package battleship;
import java.awt.*;

public class Piece {
    private Color color;

    private int shipType;

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

    
    Piece(Color _color)
    {
        shipType = (int) (Math.random()*3);
        color = _color;
    }
    public Color getColor()
    {
        return (color);
    }

    public void draw(Graphics2D g,int row,int column, int xdelta,int ydelta, Battleship thisObj) {
        g.setColor(color); 
//        g.fillOval( Window.getX(column*xdelta), Window.getY(row*ydelta),xdelta,ydelta);
        if(color == Player.getPlayer1().getColor())
        g.drawImage(destroyer_blue,Window.getX(0)+xdelta*column, Window.getY(0)+ydelta*row,xdelta,ydelta,thisObj);
        else if(color == Player.getPlayer2().getColor())
        g.drawImage(destroyer_red,Window.getX(0)+xdelta*column, Window.getY(0)+ydelta*row,xdelta,ydelta,thisObj);
    //put x image here
        else
        g.drawImage(battleship_blue,Window.getX(0)+xdelta*column, Window.getY(0)+ydelta*row,xdelta,ydelta,thisObj);
        
    }
    
    public static void Init(){
        battleship_blue = Toolkit.getDefaultToolkit().getImage("./battleship_blue.png");
        battleship_red = Toolkit.getDefaultToolkit().getImage("./battleship_red.png");
        
        bomber_blue = Toolkit.getDefaultToolkit().getImage("./bomber_blue.png");
        bomber_red = Toolkit.getDefaultToolkit().getImage("./bomber_red.png");
        
        cruiser_blue = Toolkit.getDefaultToolkit().getImage("./cruiser_blue.png");
        cruiser_red = Toolkit.getDefaultToolkit().getImage("./cruiser_red.png");
        
        destroyer_blue = Toolkit.getDefaultToolkit().getImage("./destroyer_blue.png");
        destroyer_red = Toolkit.getDefaultToolkit().getImage("./destroyer_red.png");
        
        sub_blue = Toolkit.getDefaultToolkit().getImage("./sub_blue.png");  
        sub_red = Toolkit.getDefaultToolkit().getImage("./sub_red.png");    
    }
    
    
}
