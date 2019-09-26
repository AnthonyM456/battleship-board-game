
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
    
    private final static int NUM_ROWS = 10;
    private final static int NUM_COLUMNS = 10;      
    private static Piece board[][][] = new Piece[NUM_ROWS][NUM_COLUMNS][2];
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
    
    public static void ExplosionSound(){
        MisExplo = new sound("artillery_strike_far_01.wav"); 
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
            System.out.println(zrow + " " + zcol);
            
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
        Board.BoardSwitch();
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
        public static void AddPiecePixel(int xpixel,int ypixel) {
        
        int ydelta = Window.getHeight2()/NUM_ROWS;
        int xdelta = Window.getWidth2()/NUM_COLUMNS;

        int zcol = (xpixel-Window.getX(0))/xdelta;
        int zrow = (ypixel-Window.getY(0))/ydelta;

        if((xpixel-Window.getX(0)) > 0 && zcol < NUM_ROWS && (ypixel-Window.getY(0)) > 0){

                
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
        
    public static void Init(){
        WaterBgGif1 = Toolkit.getDefaultToolkit().getImage("./WaterBgGif.gif");
        WaterBgGif2 = Toolkit.getDefaultToolkit().getImage("./OceanBG.gif");
        
        bgSound = new sound("loadout_ambient.wav");
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
