
package battleship;
import static battleship.Hover.hover;
import java.awt.*;

public class Board {
    private final static int NUM_CONNECT_WIN = 4;    
    
    private final static int NUM_ROWS = 10;
    private final static int NUM_COLUMNS = 10;      
    public static Piece board[][] = new Piece[NUM_ROWS][NUM_COLUMNS];
   

    public static void Reset() {
//        thePiece = new Piece(Color.red);
//clear the board.
        for (int zrow=0;zrow<NUM_ROWS;zrow++)
            for (int zcol=0;zcol<NUM_COLUMNS;zcol++)
                board[zrow][zcol] = null;
    }

    public static void Animate(){
        int ydelta = Window.getHeight2()/NUM_ROWS;
        int xdelta = Window.getWidth2()/NUM_COLUMNS;
        
    }
    
    public static void Draw(Graphics2D g) {
 //draw grid
        int ydelta = Window.getHeight2()/NUM_ROWS;
        int xdelta = Window.getWidth2()/NUM_COLUMNS;
 
        g.setColor(Color.black);
        for (int zi = 1;zi<NUM_ROWS;zi++)
        {
            g.drawLine(Window.getX(0),Window.getY(zi*ydelta),
                    Window.getX(Window.getWidth2()),Window.getY(zi*ydelta));
        }
        
        for (int zi = 1;zi<NUM_COLUMNS;zi++)
        {
            g.drawLine(Window.getX(zi*xdelta),Window.getY(0),
                    Window.getX(zi*xdelta),Window.getY(Window.getHeight2()));
        }
        for (int zrow=0;zrow<NUM_ROWS;zrow++)
        {
            for (int zcol=0;zcol<NUM_COLUMNS;zcol++)        
            {
                if (board[zrow][zcol] != null)
                    board[zrow][zcol].draw(g, zrow, zcol, xdelta, ydelta);
                    Player.SwitchTurn();
            }
        }
    }
    public static void RemovePiecePixel(int xpixel, int ypixel){
        int xdelta = Window.getWidth2()/NUM_COLUMNS;
        int ydelta = Window.getHeight2()/NUM_ROWS;
        
        int zcol = (xpixel - Window.getX(0))/xdelta;
        int zrow = (ypixel - Window.getY(0))/ydelta;
        
        if (zcol == NUM_COLUMNS || zcol < 0 || zrow < 0 || zrow == NUM_ROWS) 
            return;
        
        if (board[zrow][zcol] != null && board[zrow][zcol].getColor() != Player.GetCurrentPlayer().getColor())
        {
            System.out.println("gotem");
            int zchangeRow = zrow;
            while (zchangeRow > 0 && board[zchangeRow-1][zcol] != null)
            {
                board[zchangeRow][zcol] = board[zchangeRow-1][zcol];
                zchangeRow--;
            }
            board[zchangeRow][zcol] = null;
            Player.SwitchTurn();
        }
        
    }
    public static int xdelta(){
        int xdelta = Window.getWidth2()/NUM_COLUMNS;
        return(xdelta);
    }
    public static int ydelta(){
        int ydelta = Window.getHeight2()/NUM_ROWS;
        return(ydelta);
    }
    public static int NUM_ROWS(){
        return(NUM_ROWS);
    }
    public static int NUM_COLUMNS(){
        return(NUM_COLUMNS);
    }
//    
}
