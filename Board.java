
package connect4;
import java.awt.*;

public class Board {
    private final static int NUM_CONNECT_WIN = 4;    
    
    private final static int NUM_ROWS = 8;
    private final static int NUM_COLUMNS = 8;      
    private static Piece board[][] = new Piece[NUM_ROWS][NUM_COLUMNS];
   

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
        public static void RemovePiecePixel (int xpixel, int ypixel) {

        int ydelta = Window.getHeight2()/NUM_ROWS;
        int xdelta = Window.getWidth2()/NUM_COLUMNS;    
            
        int zcol = (xpixel-Window.getX(0))/xdelta;
        int zrow = (ypixel-Window.getY(0))/ydelta;    
             
                if(Player.GetCurrentPlayer().getColor() != board[zrow][zcol].getColor())
                {
                    board[zrow][zcol] = null;
                    //work only with zcol
                    int theRow = zrow-1;
                    while (board[theRow][zcol] != null)
                    {
                        theRow--;
                        board[theRow][zcol] = board[zrow][zcol];
                    }
                }

                
            
        
        }
    
        public static void AddPiecePixel(int xpixel,int ypixel) {
        
        int ydelta = Window.getHeight2()/NUM_ROWS;
        int xdelta = Window.getWidth2()/NUM_COLUMNS;

        int zcol = (xpixel-Window.getX(0))/xdelta;
        int zrow = (ypixel-Window.getY(0))/ydelta;

        int theRow = NUM_ROWS-1;
        if((xpixel-Window.getX(0)) > 0 && zcol < NUM_ROWS && (ypixel-Window.getY(0)) > 0 && zrow < NUM_COLUMNS){

            while (board[theRow][zcol] != null && theRow > 0)
            {
                theRow--; 
            }
            board[theRow][zcol] = new Piece(Player.GetCurrentPlayer().getColor());
            Player.SwitchTurn();
        }

    } 
    
}
