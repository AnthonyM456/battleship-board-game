
package battleship;

import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.swing.*;

public class Battleship extends JFrame implements Runnable {
    boolean animateFirstTime = true;
    Image image;
    Graphics2D g;

//    Player playerRed;
//    Player playerBlack;
    
    sound bgSound = null;
    Image WaterBgGif;

    public static void main(String[] args) {
        Battleship frame = new Battleship();
        frame.setSize(Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public Battleship() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {

                if (e.BUTTON1 == e.getButton() ) {
                    if (Menu.gameStart())
                    {
                        Hover.PlaceShip(e.getX(),e.getY());
                        Hover.Click();

                    }
                        Menu.checkClick(e.getX(),e.getY());
                    
                                        
                }

                if (e.BUTTON3 == e.getButton()) {
                    Board.RemovePiecePixel(e.getX(),e.getY());
                }
                repaint();
            }
        });
            

    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseDragged(MouseEvent e) {
        repaint();
      }
    });

    addMouseMotionListener(new MouseMotionAdapter() {
        public void mouseMoved(MouseEvent e) {
            if (Menu.gameStart()){
                Hover.Move();
                Hover.Highlight(e.getX(),e.getY());
            }
        repaint();
      }
    });

        addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                if (e.VK_UP == e.getKeyCode()) {
                } else if (e.VK_DOWN == e.getKeyCode()) {
                } else if (e.VK_LEFT == e.getKeyCode()) {
                } else if (e.VK_RIGHT == e.getKeyCode()) {
                } else if (e.VK_SPACE == e.getKeyCode()) {
                } else if (e.VK_R == e.getKeyCode()) {
                    Hover.Move();
                    Hover.rotateShip();
                    Hover.Highlight(Hover.mouseGetX(),Hover.mouseGetY());
                } else if (e.VK_ESCAPE == e.getKeyCode()) {
                    reset();
                }
                repaint();
            }
        });
        init();
        start();                                                                                
    }
    Thread relaxer;
////////////////////////////////////////////////////////////////////////////
    public void init() {
        requestFocus();
    }
////////////////////////////////////////////////////////////////////////////
    public void destroy() {
    }
////////////////////////////////////////////////////////////////////////////
    public void paint(Graphics gOld) {
        if (image == null || Window.xsize != getSize().width || Window.ysize != getSize().height) {
            Window.xsize = getSize().width;
            Window.ysize = getSize().height;
            image = createImage(Window.xsize, Window.ysize);
            g = (Graphics2D) image.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        }
//fill background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Window.xsize, Window.ysize);

        int x[] = {Window.getX(0), Window.getX(Window.getWidth2()), Window.getX(Window.getWidth2()), Window.getX(0), Window.getX(0)};
        int y[] = {Window.getY(0), Window.getY(0), Window.getY(Window.getHeight2()), Window.getY(Window.getHeight2()), Window.getY(0)};
//fill border
    if (Player.GetCurrentPlayer() == Player.getPlayer1())
        g.setColor(Color.GRAY);
    else
        g.setColor(Color.blue);
    g.fillPolygon(x, y, 4);
          
// draw border
        g.setColor(Color.black);
        g.drawPolyline(x, y, 5);

        if (animateFirstTime) {
            gOld.drawImage(image, 0, 0, null);
            return;
        }
        
        g.drawImage(WaterBgGif,Window.getX(0),Window.getY(0),Window.getWidth2(),Window.getHeight2(),this);     
        Board.Draw(g);
        Hover.Draw(g);
        Menu.Draw(g,this);
        if (Menu.gameStart())
        {
            WaterBgGif = Toolkit.getDefaultToolkit().getImage("./WaterBgGif.gif");
        }
        gOld.drawImage(image, 0, 0, null);
//        System.out.println(bgSound+"");
    }

////////////////////////////////////////////////////////////////////////////
// needed for     implement runnable
    public void run() {
        while (true) {
            animate();
            repaint();
            double seconds = .1;    //time that 1 frame takes.
            int miliseconds = (int) (1000.0 * seconds);
            try {
                Thread.sleep(miliseconds);
            } catch (InterruptedException e) {
            }
        }
    }
    
/////////////////////////////////////////////////////////////////////////
    public void reset() {
        Player.Reset();
        Board.Reset();
        Menu.Reset();
    }
/////////////////////////////////////////////////////////////////////////
    public void drawImage(Image image,int xpos,int ypos,double rot,double xscale,
                double yscale) {
            int width = image.getWidth(this);
            int height = image.getHeight(this);
            g.translate(xpos,ypos);
            g.rotate(rot  * Math.PI/180.0);
            g.scale( xscale , yscale );

            g.drawImage(image,-width/2,-height/2,
            width,height,this);

            g.scale( 1.0/xscale,1.0/yscale );
            g.rotate(-rot  * Math.PI/180.0);
            g.translate(-xpos,-ypos);
        }
/////////////////////////////////////////////////////////////////////////
    public void animate() {

        if (animateFirstTime) {
            animateFirstTime = false;
            if (Window.xsize != getSize().width || Window.ysize != getSize().height) {
                Window.xsize = getSize().width;
                Window.ysize = getSize().height;
            }
            
            reset();
        }
            if (!Menu.gameStart())
            {
                bgSound = null;
            }
            else
                bgSound = new sound("starwars.wav");
    }

////////////////////////////////////////////////////////////////////////////
    public void start() {
        if (relaxer == null) {
            relaxer = new Thread(this);
            relaxer.start();
        }
    }
////////////////////////////////////////////////////////////////////////////
    public void stop() {
        if (relaxer.isAlive()) {
            relaxer.stop();
        }
        relaxer = null;
    }

}
///////////////////////////////////////////////////////////////////////////
class sound implements Runnable {
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


