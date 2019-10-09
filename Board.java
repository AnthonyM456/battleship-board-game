
package battleship;
import java.awt.*;
import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class Board {
    private final static int WIN = 20;    
    private static int WinCounter_P1;
    private static int WinCounter_P2;
    
    private final static int NUM_ROWS = 12;
    private final static int NUM_COLUMNS = 12;      
    private static Piece board[][][] = new Piece[NUM_ROWS][NUM_COLUMNS][4];
    public static Piece ship[][][] =  new Piece[NUM_ROWS][NUM_COLUMNS][4];
    private static Piece hover[][][] =  new Piece[NUM_ROWS][NUM_COLUMNS][4];
    private static int BoardSel;
    
    private static int shipSize = 6;
    private static boolean rotate;
    private static boolean validPlacement;
    private static int mouseGetX;
    private static int mouseGetY;

    private static Image WaterBgGif1;
    private static Image WaterBgGif2;
    private static Image Clouds;
    
    
    private static sound bgSound;
    private static sound MisExplo;
    private static sound Victory;
    
    private static int Shrink = 3;
    private static int Turns = 0;
    
    public static void Reset() {
//        thePiece = new Piece(Color.red);
//clear the board.
    for(int b=0;b<2;b++)
        for (int zrow=0;zrow<NUM_ROWS;zrow++)
            for (int zcol=0;zcol<NUM_COLUMNS;zcol++)
                board[zrow][zcol][b] = null;
    
    for(int b=0;b<2;b++)
        for (int zrow=0;zrow<NUM_ROWS;zrow++)
            for (int zcol=0;zcol<NUM_COLUMNS;zcol++)
                ship[zrow][zcol][b] = null;
    
        BoardSel = 0;
        shipSize = 6;
        Turns = 0;
        WinCounter_P1 = 0;
        WinCounter_P2 = 0;
        
//        board[2][3][0] = new Piece(Color.red);
        
    }

    public static void Animate(){
        int ydelta = Window.getHeight2()/NUM_ROWS;
        int xdelta = Window.getWidth2()/NUM_COLUMNS;
        
//        System.out.println(Window.getWidth2() + " " + Window.getHeight2());
        
        if(bgSound.donePlaying)
        bgSound = new sound("loadout_ambient.wav");
    }
    
//    public static void Click(){
//        shipSize--;
//        if(Shrink == 0)
//        {
//            Board.BoardSwitch();
//            Player.SwitchTurn();
//            
//            Shrink = 3;
//            Turns++;
//            if(Turns == 2){
//                Levels.SwitchLevel();
//            }
//        }
//    }
    
    public static boolean TurnsCheck(){
        if(Turns >= 2){
            
            return true;
    }
        
        return false;
    }
    
    
    
    public static void BoardSwitch(){
// 0 1 2 3        
        if (BoardSel != 2 || BoardSel !=3 && Levels.GetCurrentLevel() == 2)
            Scout.scoutCount();
        if(Player.GetCurrentPlayer() == Player.getPlayer1())
            BoardSel = 2;
        else if(Player.GetCurrentPlayer() == Player.getPlayer2())
            BoardSel = 3;
//        System.out.println("BoardSwitch");  
 
    }
    
    public static void BoardSwitchSpace(){
        
    if(Levels.GetCurrentLevel() == 1){
        if(BoardSel == 2)
            BoardSel = 1;
        else if(BoardSel == 3)
            BoardSel = 0;
    }
    else if(Levels.GetCurrentLevel() == 2)
    {
        if(BoardSel == 2)
            BoardSel = 0;
        else if(BoardSel == 3)
            BoardSel = 1;
    }
        
    }
    
    
    public static void Draw(Graphics2D g, Battleship thisObj) {
 //draw grid
        int ydelta = Window.getHeight2()/NUM_ROWS;
        int xdelta = Window.getWidth2()/NUM_COLUMNS;
        
        
        
        if(BoardSel == 2 || BoardSel == 3){
//fill background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Window.xsize, Window.ysize);

        int x[] = {Window.getX(0), Window.getX(Window.getWidth2()), Window.getX(Window.getWidth2()), Window.getX(0), Window.getX(0)};
        int y[] = {Window.getY(0), Window.getY(0), Window.getY(Window.getHeight2()), Window.getY(Window.getHeight2()), Window.getY(0)};
//fill border
        g.setColor(Color.black);
        g.fillPolygon(x, y, 4);
        g.drawImage(Clouds,Window.getX(0),Window.getY(0),Window.getWidth2(),Window.getHeight2(),thisObj); 

// draw border
        g.setColor(Color.black);
        g.drawPolyline(x, y, 5);
//draw text
if(Levels.GetCurrentLevel() == 1){
    if(BoardSel == 2){
        g.setColor(Color.black); 
        g.setFont(new Font("Impact", Font.BOLD,50));
        g.drawString("Player 2's Turn", Window.getX((Window.WINDOW_WIDTH/2)-187), 228);
        g.setColor(Color.red); 
        g.setFont(new Font("Impact", Font.BOLD,50));
        g.drawString("Player 2's Turn", Window.getX((Window.WINDOW_WIDTH/2)-190), 225);
        
        g.setColor(Color.black);
        g.setFont(new Font("Impact", Font.BOLD,40));
        g.drawString("Let Player 1 Turn Around", 113, 328);
        g.setColor(Color.white);
        g.setFont(new Font("Impact", Font.BOLD,40));
        g.drawString("Let Player 1 Turn Around", 110, 325);
        g.setColor(Color.black);
        g.drawString("Player 1", 175, 328);
        g.setColor(Color.blue);
        g.drawString("Player 1", 172, 325);
        
        
        if(Timer.retTC() % 16 > 8){
        g.setColor(Color.black);
        g.setFont(new Font("Impact", Font.BOLD,40));
        g.drawString("Press Space to Continue", 113, 428);
        g.setColor(Color.white);
        g.setFont(new Font("Impact", Font.BOLD,40));
        g.drawString("Press Space to Continue", 110, 425);
        }
        else{
        g.setColor(Color.black);
        g.setFont(new Font("Impact", Font.BOLD,35));
        g.drawString("Press Space to Continue", 143, 428);    
        g.setColor(Color.white);
        g.setFont(new Font("Impact", Font.BOLD,35));
        g.drawString("Press Space to Continue", 140, 425);
        }
        
        
    }
}
else if(Levels.GetCurrentLevel() == 2){
    if(BoardSel == 2){
//        if(Timer.retTC() % 20 > 1){
//            g.setColor(Color.black); 
//            g.setFont(new Font("Impact", Font.BOLD,65));
//            g.drawString("M", 263, 228);
//            g.setColor(Color.yellow); 
//            g.setFont(new Font("Impact", Font.BOLD,65));
//            g.drawString("M", 260, 225);
//        }
//        if(Timer.retTC() % 20 > 5){
//            g.setColor(Color.black); 
//            g.setFont(new Font("Impact", Font.BOLD,65));
//            g.drawString("i", 318, 228);
//            g.setColor(Color.yellow); 
//            g.setFont(new Font("Impact", Font.BOLD,65));
//            g.drawString("i", 315, 225);
//        }
//        if(Timer.retTC() % 20 > 10){
//            g.setColor(Color.black); 
//            g.setFont(new Font("Impact", Font.BOLD,65));
//            g.drawString("s", 343, 228);
//            g.setColor(Color.yellow); 
//            g.setFont(new Font("Impact", Font.BOLD,65));
//            g.drawString("s", 340, 225);
//        }
//        if(Timer.retTC() % 20 > 15){
//            g.setColor(Color.black); 
//            g.setFont(new Font("Impact", Font.BOLD,65));
//            g.drawString("s", 378, 228);
//            g.setColor(Color.yellow); 
//            g.setFont(new Font("Impact", Font.BOLD,65));
//            g.drawString("s", 375, 225);
//        }
        
        g.setColor(Color.black); 
        g.setFont(new Font("Impact", Font.BOLD,50));
        g.drawString("Player 2's Turn", Window.getX((Window.WINDOW_WIDTH/2)-187), 228);
        g.setColor(Color.red); 
        g.setFont(new Font("Impact", Font.BOLD,50));
        g.drawString("Player 2's Turn", Window.getX((Window.WINDOW_WIDTH/2)-190), 225);
        
        g.setColor(Color.black);
        g.setFont(new Font("Impact", Font.BOLD,40));
        g.drawString("Let Player 1 Turn Around", 113, 328);
        g.setColor(Color.white);
        g.setFont(new Font("Impact", Font.BOLD,40));
        g.drawString("Let Player 1 Turn Around", 110, 325);
        g.setColor(Color.black);
        g.drawString("Player 1", 175, 328);
        g.setColor(Color.blue);
        g.drawString("Player 1", 172, 325);
        
        
        if(Timer.retTC() % 16 > 8){
        g.setColor(Color.black);
        g.setFont(new Font("Impact", Font.BOLD,40));
        g.drawString("Press Space to Continue", 113, 428);
        g.setColor(Color.white);
        g.setFont(new Font("Impact", Font.BOLD,40));
        g.drawString("Press Space to Continue", 110, 425);
        }
        else{
        g.setColor(Color.black);
        g.setFont(new Font("Impact", Font.BOLD,35));
        g.drawString("Press Space to Continue", 143, 428);    
        g.setColor(Color.white);
        g.setFont(new Font("Impact", Font.BOLD,35));
        g.drawString("Press Space to Continue", 140, 425);
        }
    }
    else
    {
        g.setColor(Color.black); 
        g.setFont(new Font("Impact", Font.BOLD,50));
        g.drawString("Player 1's Turn", Window.getX((Window.WINDOW_WIDTH/2)-187), 228);
        g.setColor(Color.blue); 
        g.setFont(new Font("Impact", Font.BOLD,50));
        g.drawString("Player 1's Turn", Window.getX((Window.WINDOW_WIDTH/2)-190), 225);
        
        g.setColor(Color.black);
        g.setFont(new Font("Impact", Font.BOLD,40));
        g.drawString("Let Player 2 Turn Around", 113, 328);
        g.setColor(Color.white);
        g.setFont(new Font("Impact", Font.BOLD,40));
        g.drawString("Let Player 2 Turn Around", 110, 325);
        g.setColor(Color.black);
        g.drawString("Player 2", 175, 328);
        g.setColor(Color.red);
        g.drawString("Player 2", 172, 325);
        
        
        if(Timer.retTC() % 16 > 8){
        g.setColor(Color.black);
        g.setFont(new Font("Impact", Font.BOLD,40));
        g.drawString("Press Space to Continue", 113, 428);
        g.setColor(Color.white);
        g.setFont(new Font("Impact", Font.BOLD,40));
        g.drawString("Press Space to Continue", 110, 425);
        }
        else{
        g.setColor(Color.black);
        g.setFont(new Font("Impact", Font.BOLD,35));
        g.drawString("Press Space to Continue", 143, 428);    
        g.setColor(Color.white);
        g.setFont(new Font("Impact", Font.BOLD,35));
        g.drawString("Press Space to Continue", 140, 425);
        }

    }
}
 
    return;
    }
//////////////////////write under here    
    if(Levels.GetCurrentLevel() == 1 && BoardSel == 0){
        g.setColor(Color.white);
        g.setFont(new Font("Impact", Font.BOLD,20));
        g.drawString("Ships Left to Place: " + (shipSize-1), 45, 60);

        g.setColor(Color.white);
        g.setFont(new Font("Impact", Font.BOLD,20));
        g.drawString("Player 1 place your ships", 385, 60);

        g.setColor(Color.blue);
        g.setFont(new Font("Impact", Font.BOLD,20));
        g.drawString("Player 1 ", 385, 60);
    }
    else if(Levels.GetCurrentLevel() == 1 && BoardSel == 1){
        g.setColor(Color.white);
        g.setFont(new Font("Impact", Font.BOLD,20));
        g.drawString("Ships Left to Place: " + (shipSize-1), 45, 60);

        g.setColor(Color.white);
        g.setFont(new Font("Impact", Font.BOLD,20));
        g.drawString("Player 2 place your ships", 385, 60);

        g.setColor(Color.red);
        g.setFont(new Font("Impact", Font.BOLD,20));
        g.drawString("Player 2 ", 385, 60);
    }
    
    if(Levels.GetCurrentLevel() == 2 && BoardSel == 1){
//        g.setColor(Color.white);
//        g.setFont(new Font("Impact", Font.BOLD,20));
//        g.drawString("Ships Left to Place: " + Shrink, 45, 60);

        g.setColor(Color.white);
        g.setFont(new Font("Impact", Font.BOLD,20));
        g.drawString("Player 1 Seek and Destroy", 215, 60);

        g.setColor(Color.blue);
        g.setFont(new Font("Impact", Font.BOLD,20));
        g.drawString("Player 1 ", 215, 60);
    }
    else if(Levels.GetCurrentLevel() == 2 && BoardSel == 0){
//        g.setColor(Color.white);
//        g.setFont(new Font("Impact", Font.BOLD,20));
//        g.drawString("Ships Left to Place: " + Shrink, 45, 60);

        g.setColor(Color.white);
        g.setFont(new Font("Impact", Font.BOLD,20));
        g.drawString("Player 2 Seek and Destroy", 215, 60);

        g.setColor(Color.red);
        g.setFont(new Font("Impact", Font.BOLD,20));
        g.drawString("Player 2 ", 215, 60);
    }
        
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
                    board[zrow][zcol][BoardSel].draw(g, zrow, zcol, xdelta, ydelta, thisObj);
//                    g.drawImage(battleship_blue,Window.getX(0)+xdelta*zcol, Window.getY(0)+ydelta*zrow,xdelta,ydelta,thisObj);   
                    
                }
            }
        }
        for (int zrow=0;zrow<NUM_ROWS;zrow++) {
            for (int zcol=0;zcol<NUM_COLUMNS;zcol++) {
                if (hover[zrow][zcol][BoardSel] != null){
                    hover[zrow][zcol][BoardSel].draw(g, zrow, zcol, xdelta, ydelta, thisObj);
                }
            }
        }
        for (int zrow=0;zrow<NUM_ROWS;zrow++)  {
            for (int zcol=0;zcol<NUM_COLUMNS;zcol++)       {
                if (ship[zrow][zcol][BoardSel] != null)        {
                    ship[zrow][zcol][BoardSel].draw(g, zrow, zcol, xdelta, ydelta, thisObj);

                }
            }
        }
    
    if(Levels.GetCurrentLevel() == 3)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Window.xsize, Window.ysize);

        int x[] = {Window.getX(0), Window.getX(Window.getWidth2()), Window.getX(Window.getWidth2()), Window.getX(0), Window.getX(0)};
        int y[] = {Window.getY(0), Window.getY(0), Window.getY(Window.getHeight2()), Window.getY(Window.getHeight2()), Window.getY(0)};
//fill border
        g.setColor(Color.black);
        g.fillPolygon(x, y, 4);
        g.drawImage(Clouds,Window.getX(0),Window.getY(0),Window.getWidth2(),Window.getHeight2(),thisObj); 
if(BoardSel == 1){
    if(Timer.retTC() % 16 > 8){
        g.setColor(Color.black);
        g.setFont(new Font("Impact", Font.BOLD,40));
        g.drawString("Player 1 Wins", 217, 228);
        g.setColor(Color.yellow);
        g.setFont(new Font("Impact", Font.BOLD,40));
        g.drawString("Player 1 Wins", 214, 225);
        }
    else{
        g.setColor(Color.black);
        g.setFont(new Font("Impact", Font.BOLD,35));
        g.drawString("Player 1 Wins", 233, 228);    
        g.setColor(Color.yellow);
        g.setFont(new Font("Impact", Font.BOLD,35));
        g.drawString("Player 1 Wins", 230, 225);
    }  
}
else if(BoardSel == 0){
    if(Timer.retTC() % 16 > 8){
        g.setColor(Color.black);
        g.setFont(new Font("Impact", Font.BOLD,40));
        g.drawString("Player 2 Wins", 217, 228);
        g.setColor(Color.yellow);
        g.setFont(new Font("Impact", Font.BOLD,40));
        g.drawString("Player 2 Wins", 214, 225);
        }
    else{
        g.setColor(Color.black);
        g.setFont(new Font("Impact", Font.BOLD,35));
        g.drawString("Player 2 Wins", 233, 228);    
        g.setColor(Color.yellow);
        g.setFont(new Font("Impact", Font.BOLD,35));
        g.drawString("Player 2 Wins", 230, 225);
    } 
    
    
}
    g.setColor(Color.white);
    g.setFont(new Font("Impact", Font.BOLD,15));
    g.drawString("Good job soldier,", 400, 525);
    g.drawString("Mr. Yee was saved.", 417, 545);    
// draw border
        g.setColor(Color.black);
        g.drawPolyline(x, y, 5);
    }
    
    }
////////////////////////////////////////////////////////////////////////////////////    
        public static boolean MisCollision(int zrow, int zcol){
            int ydelta = Window.getHeight2()/NUM_ROWS;
            int xdelta = Window.getWidth2()/NUM_COLUMNS;    
            
            
            if(ship[zrow][zcol][BoardSel] != null)
                return true;
           
            
            return false;
        }
        
        public static void RemovePiecePixel_HIT (int zrow, int zcol) {

        int ydelta = Window.getHeight2()/NUM_ROWS;
        int xdelta = Window.getWidth2()/NUM_COLUMNS;    
            
        
        if(BoardSel == 2 || BoardSel == 3)
                return;
        
//        if(ship[zrow][zcol][BoardSel] != null && ship[zrow][zcol][BoardSel].getColor() != Color.BLUE){
        if(ship[zrow][zcol][BoardSel].getColor() == Color.white){
        
        ship[zrow][zcol][BoardSel] = new Piece(Color.GRAY);
//        if(board[zrow][zcol][BoardSel].getColor() != Color.GRAY)
//            Board.Click();
        if(BoardSel== 0)
        WinCounter_P1++;
        else
        WinCounter_P2++;    
        
//        System.out.println("WinCounter_P1: " + WinCounter_P1);
//        System.out.println("WinCounter_P2: " + WinCounter_P2);
        }

        
        
//        Board.BoardSwitch();
//        Player.SwitchTurn(); 
        
        
        }
        
        public static void RemovePiecePixel_MISS (int zrow, int zcol) {

        int ydelta = Window.getHeight2()/NUM_ROWS;
        int xdelta = Window.getWidth2()/NUM_COLUMNS;    
            
        
        if(BoardSel == 2 || BoardSel == 3)
                return;


        
        if(ship[zrow][zcol][BoardSel] == null)
            ship[zrow][zcol][BoardSel] = new Piece(Color.BLUE);
        
        
        Board.BoardSwitch();
        Player.SwitchTurn(); 
        
        
        }
        
        public static void ExplosionSound_HIT(int zrow, int zcol){
        
        int ydelta = Window.getHeight2()/NUM_ROWS;
        int xdelta = Window.getWidth2()/NUM_COLUMNS;
        
        
        if(BoardSel == 2 || BoardSel == 3)
            return;   
        
        int RanVal = (int)(Math.random()*4);
        if(ship[zrow][zcol][BoardSel].getColor() == Color.white){
            if(RanVal == 0)
            MisExplo = new sound("artillery_strike_far_01.wav"); 
            else if(RanVal == 1)
            MisExplo = new sound("artillery_strike_far_02.wav"); 
            else if(RanVal == 2)
            MisExplo = new sound("artillery_strike_far_03.wav"); 
            else
            MisExplo = new sound("artillery_strike_far_04.wav"); 
        }
//        System.out.println(RanVal);
    }
    
    public static void ExplosionSound_MISS(int zrow, int zcol){
        
        int ydelta = Window.getHeight2()/NUM_ROWS;
        int xdelta = Window.getWidth2()/NUM_COLUMNS;
          
        
        if(BoardSel == 2 || BoardSel == 3)
            return;
        
        if(ship[zrow][zcol][BoardSel] != null)
            return;
        
        int RanVal = (int)(Math.random()*4);
            if(RanVal == 0)
            MisExplo = new sound("artillery_strike_far_water_01.wav"); 
            else if(RanVal == 1)
            MisExplo = new sound("artillery_strike_far_water_02.wav"); 
            else if(RanVal == 2)
            MisExplo = new sound("artillery_strike_far_water_03.wav"); 
            else
            MisExplo = new sound("artillery_strike_far_water_04.wav"); 
        
//        System.out.println(RanVal);
    }
    
        public static void AddPiecePixel(int xpixel,int ypixel) {
        
        int ydelta = Window.getHeight2()/NUM_ROWS;
        int xdelta = Window.getWidth2()/NUM_COLUMNS;

        int zcol = (xpixel-Window.getX(0))/xdelta;
        int zrow = (ypixel-Window.getY(0))/ydelta;
        
        if (xpixel <  Window.getSideBorder() || ypixel < Window.getTopBorder())
            return;
        if(zrow < NUM_ROWS && zrow > -1 && zcol < NUM_COLUMNS && zcol > -1) {
            if(BoardSel == 2 || BoardSel == 3)
                return;
        
            if(Levels.GetCurrentLevel() != 1)
                return;
         if (!rotate) {
                for (int i=0;i<shipSize;i++){
                    if (zcol+shipSize > NUM_COLUMNS){
                        if (zcol+i < NUM_COLUMNS && ship[zrow][zcol+i][BoardSel] != null){
                            validPlacement = false;
                            return;
                        }
                        else if (ship[zrow][(NUM_COLUMNS-shipSize)+i][BoardSel] != null){
//                            System.out.println("sickman");
                            validPlacement=false;
                            return;
                        }
                        else 
                        {
                            validPlacement = true;
                        }
                    }
                    else if (ship[zrow][zcol+i][BoardSel] != null){
//                        System.out.println("not aight");
                        validPlacement = false;
                        return;
                    }
                    else if (ship[zrow][zcol+i][BoardSel] == null){
                        validPlacement = true;
//                        System.out.println(zcol+shipSize);
                    }
                }
                if (validPlacement){
                    if (zcol+shipSize > NUM_COLUMNS){
                       board[zrow][NUM_COLUMNS-shipSize][BoardSel] = new Piece(Player.GetCurrentPlayer().getColor());
                        for (int i=0;i<shipSize;i++){
                            ship[zrow][(NUM_COLUMNS-shipSize)+i][BoardSel] = new Piece(Color.white);
                        }
                    }
                    else {
                        for (int i=0;i<shipSize;i++){
                        ship[zrow][zcol+i][BoardSel] = new Piece(Color.white);
                        board[zrow][zcol][BoardSel] = new Piece(Player.GetCurrentPlayer().getColor());
                    }
                    }
                    shipSize--;
                }
            }
            else {
                for (int i=0;i<shipSize;i++){
                    if (zrow+shipSize > NUM_ROWS){
                        if (zrow+i < NUM_ROWS && ship[zrow+i][zcol][BoardSel] != null)
                        {
                            validPlacement = false;
                            return;
                        }
                        else if (ship[(NUM_ROWS-shipSize)+i][zcol][BoardSel] != null)
                        {
//                            System.out.println("sickman");
                            validPlacement=false;
                            return;
                        }
                        else
                            validPlacement = true;
                    }
                    else if (ship[zrow+i][zcol][BoardSel] != null){
//                        System.out.println("not aight");
                        validPlacement = false;
                        return;
                    }
                    else if (ship[zrow+i][zcol][BoardSel] == null){
                        validPlacement = true;
//                        System.out.println(zcol+shipSize);
                    }
                }
                if (validPlacement){
//                    System.out.println("yo mama");
                    if (zrow+shipSize > NUM_ROWS){
                       board[NUM_ROWS-shipSize][zcol][BoardSel] = new Piece(Player.GetCurrentPlayer().getColor());
                        for (int i=0;i<shipSize;i++){
                            ship[(NUM_ROWS-shipSize)+i][zcol][BoardSel] = new Piece(Color.white);
                        }
                    }
                    else {
                        for (int i=0;i<shipSize;i++){
                        ship[zrow+i][zcol][BoardSel] = new Piece(Color.white);
                        board[zrow][zcol][BoardSel] = new Piece(Player.GetCurrentPlayer().getColor());
                        }
                    }
                }
                shipSize--;
            }

            if (shipSize < 2){      //after placing ships, switch turns
                Board.BoardSwitch();
                Player.SwitchTurn();
                
                
                shipSize = 6;
                Turns++;
                if(Turns < 4){
                    Levels.SwitchLevel();
                }
            }
        }

    }
        
    public static boolean checkWin(){
        if(WinCounter_P1 == WIN){
            return true;
        }
        else if(WinCounter_P2 == WIN){
            return true;
        }
        
        return false;
    }
/////////////////////////////////////////////////////////////////////        
    public static void Hover(int xpixel,int ypixel){
        int ydelta = Window.getHeight2()/NUM_ROWS;
        int xdelta = Window.getWidth2()/NUM_COLUMNS;

        int zcol = (xpixel-Window.getX(0))/xdelta;
        int zrow = (ypixel-Window.getY(0))/ydelta;
        mouseGetX = xpixel;
        mouseGetY = ypixel;
//        System.out.println(ypixel);
//        System.out.println(ypixel);
        if (xpixel <  Window.getSideBorder() || ypixel < Window.getTopBorder())
            return;
        if(zrow < NUM_ROWS && zrow > -1 && zcol < NUM_COLUMNS && zcol > -1) {
            if(BoardSel == 2 || BoardSel == 3)
                return;
            
            if (!rotate)
            {
                if (zcol+shipSize > NUM_COLUMNS)
                    hover[zrow][NUM_COLUMNS-shipSize][BoardSel] = new Piece(Player.GetCurrentPlayer().getColor());
                else
                    hover[zrow][zcol][BoardSel] = new Piece(Player.GetCurrentPlayer().getColor());
            }
            else
            {
                if (zrow+shipSize > NUM_ROWS)
                    hover[NUM_ROWS-shipSize][zcol][BoardSel] = new Piece(Player.GetCurrentPlayer().getColor());
                else
                    hover[zrow][zcol][BoardSel] = new Piece(Player.GetCurrentPlayer().getColor());
            }
            
        }        
    }

    public static int getshipSize(){
        return(shipSize);
    }
    public static void Move(){
        if(BoardSel == 2 || BoardSel == 3)
                return;
        for (int zrow=0;zrow<NUM_ROWS;zrow++)
        {
            for (int zcol=0;zcol<NUM_COLUMNS;zcol++)        
            {
                hover[zrow][zcol][BoardSel] = null;
            }
        }
    }
    public static void rotateShip(){
        
        if (rotate == true)
            rotate = false;
        else
            rotate = true;
    }
    public static boolean getRotate(){
        return(rotate);
    } 
    public static int mouseGetX(){
        return(mouseGetX);
    }
    public static int mouseGetY(){
        return(mouseGetY);
    }    

/////////////////////////////////////////////////////////////////////////////////       
        public static int xdelta(){
            int xdelta = Window.getWidth2()/NUM_COLUMNS;
            return(xdelta);
        }
    
        public static int ydelta(){
            int ydelta = Window.getHeight2()/NUM_ROWS;
            return(ydelta);
        }
        
        public static int BoardSel(){
            return(BoardSel);
        }
    
    public static void PlayerPaint(Graphics2D g, Battleship thisObj){
if(Player.GetCurrentPlayer() == Player.getPlayer1()){        
//fill background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Window.xsize, Window.ysize);

        int x[] = {Window.getX(0), Window.getX(Window.getWidth2()), Window.getX(Window.getWidth2()), Window.getX(0), Window.getX(0)};
        int y[] = {Window.getY(0), Window.getY(0), Window.getY(Window.getHeight2()), Window.getY(Window.getHeight2()), Window.getY(0)};
//fill border
//        g.setColor(Color.GRAY);
//        g.fillPolygon(x, y, 4);
//        g.drawImage(WaterBgGif1,Window.getX(0),Window.getY(0),Window.getWidth2(),Window.getHeight2(),thisObj); 
        
        g.drawImage(WaterBgGif2,Window.getX(0),Window.getY(0),Window.getWidth2(),Window.getHeight2(),thisObj);
// draw border
        g.setColor(Color.black);
        g.drawPolyline(x, y, 5);
}
else if(Player.GetCurrentPlayer() == Player.getPlayer2())
{
//fill background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Window.xsize, Window.ysize);

        int x[] = {Window.getX(0), Window.getX(Window.getWidth2()), Window.getX(Window.getWidth2()), Window.getX(0), Window.getX(0)};
        int y[] = {Window.getY(0), Window.getY(0), Window.getY(Window.getHeight2()), Window.getY(Window.getHeight2()), Window.getY(0)};
//fill border
//        g.setColor(Color.blue);
//        g.fillPolygon(x, y, 4);
        g.drawImage(WaterBgGif2,Window.getX(0),Window.getY(0),Window.getWidth2(),Window.getHeight2(),thisObj); 

// draw border
        g.setColor(Color.black);
        g.drawPolyline(x, y, 5);
            
}    


    }    
////////////////////////////////////////////////////        
    public static void Init(){
        WaterBgGif1 = Toolkit.getDefaultToolkit().getImage("./WaterBgGif.gif");
        WaterBgGif2 = Toolkit.getDefaultToolkit().getImage("./OceanBG.gif");
        Clouds = Toolkit.getDefaultToolkit().getImage("./Clouds.gif");
        
        
        bgSound = new sound("loadout_ambient.wav");
    }
    
    public static int getNUM_COLUMNS(){
        return(NUM_COLUMNS);
    }
    public static int getNUM_ROWS(){
        return(NUM_ROWS);
    }
    
////////////////////////////////////////////////////    
    public static class sound implements Runnable {
    Thread myThread;
    File soundFile;
    public boolean donePlaying = false;
    sound(String _name)
    {
        soundFile = new File(_name);
        myThread = new Thread(this);
        myThread.start();
        
    }
    public void run()
    {
        try {
        AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
        AudioFormat format = ais.getFormat();
//        System.out.println("Format: " + format);
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
        SourceDataLine source = (SourceDataLine) AudioSystem.getLine(info);
        source.open(format);
        source.start();
        int read = 0;
        byte[] audioData = new byte[16384];
        while (read > -1){
            read = ais.read(audioData,0,audioData.length);
            if (read >= 0) {
                source.write(audioData,0,read);
            }
        }
        donePlaying = true;

        source.drain();
        source.close();
        }
        catch (Exception exc) {
            System.out.println("error: " + exc.getMessage());
            exc.printStackTrace();
        }
    }
    }
    
    
}
