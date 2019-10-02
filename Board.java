
package battleship;
import java.awt.*;
import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class Board {
    private final static int NUM_CONNECT_WIN = 4;    
    
    private final static int NUM_ROWS = 12;
    private final static int NUM_COLUMNS = 12;      
    private static Piece board[][][] = new Piece[NUM_ROWS][NUM_COLUMNS][4];
    private static int BoardSel;

    private static Image WaterBgGif1;
    private static Image WaterBgGif2;
    
    
    private static sound bgSound;
    private static sound MisExplo;
    
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
        
        System.out.println(Window.getWidth2() + " " + Window.getHeight2());
        
        if(bgSound.donePlaying)
        bgSound = new sound("loadout_ambient.wav");
    }
    
    public static void ExplosionSound_HIT(int xpixel, int ypixel){
        
        int ydelta = Window.getHeight2()/NUM_ROWS;
        int xdelta = Window.getWidth2()/NUM_COLUMNS;
        
        int zcol = (xpixel-Window.getX(0))/xdelta;
        int zrow = (ypixel-Window.getY(0))/ydelta;  
        
        if(BoardSel == 2 || BoardSel == 3)
            return;
        
        int RanVal = (int)(Math.random()*4);
        if(board[zrow][zcol][BoardSel].getColor() != Color.GRAY){
            if(RanVal == 0)
            MisExplo = new sound("artillery_strike_far_01.wav"); 
            else if(RanVal == 1)
            MisExplo = new sound("artillery_strike_far_02.wav"); 
            else if(RanVal == 2)
            MisExplo = new sound("artillery_strike_far_03.wav"); 
            else
            MisExplo = new sound("artillery_strike_far_04.wav"); 
        }
        System.out.println(RanVal);
    }
    
//    public static void ExplosionSound_MISS(int xpixel, int ypixel){
//        
//        int ydelta = Window.getHeight2()/NUM_ROWS;
//        int xdelta = Window.getWidth2()/NUM_COLUMNS;
//        
//        int zcol = (xpixel-Window.getX(0))/xdelta;
//        int zrow = (ypixel-Window.getY(0))/ydelta;  
//        
//        int RanVal = (int)(Math.random()*4);
//            if(RanVal == 0)
//            MisExplo = new sound("artillery_strike_far_water_01.wav"); 
//            else if(RanVal == 1)
//            MisExplo = new sound("artillery_strike_far_water_02.wav"); 
//            else if(RanVal == 2)
//            MisExplo = new sound("artillery_strike_far_water_03.wav"); 
//            else
//            MisExplo = new sound("artillery_strike_far_water_04.wav"); 
//        
//        System.out.println(RanVal);
//    }
    
    public static void BoardSwitch(){
// 0 1 2 3        
        
        if(Player.GetCurrentPlayer() == Player.getPlayer1())
            BoardSel = 2;
        else if(Player.GetCurrentPlayer() == Player.getPlayer2())
            BoardSel = 3;
        

        
        
        
        
    }
    
    public static void BoardSwitchSpace(){
        if(BoardSel == 2)
            BoardSel = 1;
        else if(BoardSel == 3)
            BoardSel = 0;
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
//g.drawImage(WaterBgGif2,Window.getX(0),Window.getY(0),Window.getWidth2(),Window.getHeight2(),thisObj); 

// draw border
        g.setColor(Color.black);
        g.drawPolyline(x, y, 5);
//draw text
    if(BoardSel == 2){
        g.setColor(Color.blue);
        g.setFont(new Font("Impact", Font.BOLD,50));
        g.drawString("Player 1's Turn", Window.getX((Window.WINDOW_WIDTH/2)-190), 225);
        g.setColor(Color.white);
        g.setFont(new Font("Impact", Font.BOLD,40));
        g.drawString("Press Space to Continue", 115, 425);
    }
    else
    {
        g.setColor(Color.red);
        g.setFont(new Font("Impact", Font.BOLD,50));
        g.drawString("Player 2's Turn", Window.getX((Window.WINDOW_WIDTH/2)-190), 225);
        g.setColor(Color.white);
        g.setFont(new Font("Impact", Font.BOLD,40));
        g.drawString("Press Space to Continue", 115, 425);

    }
        
        
        return;
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
                    Player.SwitchTurn();
            }
        }
    
    }
    
        public static boolean MisCollision(int xpixel, int ypixel){
            int ydelta = Window.getHeight2()/NUM_ROWS;
            int xdelta = Window.getWidth2()/NUM_COLUMNS;    
            
            int zcol = (xpixel-Window.getX(0))/xdelta;
            int zrow = (ypixel-Window.getY(0))/ydelta;    
            System.out.println(zrow + " " + zcol);
            
            if(zrow < NUM_ROWS && zrow > -1 && zcol < NUM_COLUMNS && zcol > -1) 
                if(board[zrow][zcol][BoardSel] != null)
                    return true;
           
            
            return false;
        }
        
        public static void RemovePiecePixel (int xpixel, int ypixel) {

        int ydelta = Window.getHeight2()/NUM_ROWS;
        int xdelta = Window.getWidth2()/NUM_COLUMNS;    
            
        int zcol = (xpixel-Window.getX(0))/xdelta;
        int zrow = (ypixel-Window.getY(0))/ydelta;    
         
        if(board[zrow][zcol][BoardSel] != null){


        board[zrow][zcol][BoardSel] = new Piece(Color.GRAY);
        }
        
        
//        Board.BoardSwitch();
//        Player.SwitchTurn(); 
        
        
        }
        public static int xdelta(){
            int xdelta = Window.getWidth2()/NUM_COLUMNS;
            return(xdelta);
        }
    
        public static int ydelta(){
            int ydelta = Window.getHeight2()/NUM_ROWS;
            return(ydelta);
        }
        
        public static void AddPiecePixel(int xpixel,int ypixel) {
        
        int ydelta = Window.getHeight2()/NUM_ROWS;
        int xdelta = Window.getWidth2()/NUM_COLUMNS;

        int zcol = (xpixel-Window.getX(0))/xdelta;
        int zrow = (ypixel-Window.getY(0))/ydelta;

        if(zrow < NUM_ROWS && zrow > -1 && zcol < NUM_COLUMNS && zcol > -1) {
            if(BoardSel == 2 || BoardSel == 3)
                return;
            
        board[zrow][zcol][BoardSel] = new Piece(Player.GetCurrentPlayer().getColor());

        Board.BoardSwitch();
        Player.SwitchTurn();
                
        }

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
        g.drawImage(WaterBgGif1,Window.getX(0),Window.getY(0),Window.getWidth2(),Window.getHeight2(),thisObj); 
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
