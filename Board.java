package battleship;
import java.awt.*;

public class Board {
    private final static int NUM_ROWS = 10;
    private final static int NUM_COLUMNS = 10;      
    public static Piece board[][][] = new Piece[NUM_ROWS][NUM_COLUMNS][2];
    public static int BoardSel;

    public static void Reset() {
//        thePiece = new Piece(Color.red);
//clear the board.
    for(int b=0;b<2;b++)
        for (int zrow=0;zrow<NUM_ROWS;zrow++)
            for (int zcol=0;zcol<NUM_COLUMNS;zcol++)
                board[zrow][zcol][b] = null;
        
        BoardSel = 0;
//        board[2][3][0] = new Piece(Color.red);
        
    }
    

    public static void Animate(){
        int ydelta = Window.getHeight2()/NUM_ROWS;
        int xdelta = Window.getWidth2()/NUM_COLUMNS;
        
        //System.out.println(Window.getWidth2() + " " + Window.getHeight2());
        
        
    }
    
    public static void BoardSwitch(){
        if(Player.GetCurrentPlayer() == Player.getPlayer1())
            BoardSel = 1;
        else if(Player.GetCurrentPlayer() == Player.getPlayer2())
            BoardSel = 0;
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
                if (board[zrow][zcol][BoardSel] != null)
                {
                    board[zrow][zcol][BoardSel].draw(g, zrow, zcol, xdelta, ydelta);
 
                    
                }
                Player.SwitchTurn();
            }
        }
    
    }
    
        public static boolean MisCollision(int xpixel, int ypixel){
            int ydelta = Window.getHeight2()/NUM_ROWS;
            int xdelta = Window.getWidth2()/NUM_COLUMNS;    
            
            int zcol = (xpixel-Window.getX(0))/xdelta;
            int zrow = (ypixel-Window.getY(0))/ydelta;    
            //System.out.println(zrow + " " + zcol);
            
            if(board[zrow][zcol][BoardSel] != null)
                return true;
            else 
                return false;
        }
        
        public static void RemovePiecePixel (int xpixel, int ypixel) {

        int ydelta = Window.getHeight2()/NUM_ROWS;
        int xdelta = Window.getWidth2()/NUM_COLUMNS;    
            
        int zcol = (xpixel-Window.getX(0))/xdelta;
        int zrow = (ypixel-Window.getY(0))/ydelta;    
             

        board[zrow][zcol][BoardSel] = null;
        Player.SwitchTurn();    
        
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
        public static void AddPiecePixel(int xpixel,int ypixel) {
        
        int ydelta = Window.getHeight2()/NUM_ROWS;
        int xdelta = Window.getWidth2()/NUM_COLUMNS;

        int zcol = (xpixel-Window.getX(0))/xdelta;
        int zrow = (ypixel-Window.getY(0))/ydelta;

        if((xpixel-Window.getX(0)) > 0 && zcol < NUM_ROWS && (ypixel-Window.getY(0)) > 0){

                
                board[zrow][zcol][BoardSel] = new Piece(Player.GetCurrentPlayer().getColor());

                
                Player.SwitchTurn();
            
        }

    }
}
