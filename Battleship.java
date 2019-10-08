
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
                    
                    if(Menu.gameStart()){
                        if(Board.MisCollision(e.getX(), e.getY())){
                            Board.ExplosionSound_HIT(e.getX(), e.getY());
                            Board.RemovePiecePixel(e.getX(),e.getY());
                        }
                        else
                        {
//                            Board.ExplosionSound_MISS(e.getX(), e.getY());
                            if (Scout.getScoutActive())
                               Scout.scoutAdd(e.getX(),e.getY());
                            else
                                Board.AddPiecePixel(e.getX(),e.getY()); 
                        }

                    }
                    Menu.checkClick(e.getX(), e.getY());                  
                }

                if (e.BUTTON3 == e.getButton()) {
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
                if (Scout.getScoutActive())
                {
                    Scout.Move();
                    Scout.scoutHover(e.getX(), e.getY()); 
                }
                else
                {
                    Board.Move();
                    Board.Hover(e.getX(), e.getY()); 
                }
          }

        repaint();
      }
    });

        addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                if (e.VK_UP == e.getKeyCode()) {
                } else if (e.VK_DOWN == e.getKeyCode()){
                } else if (e.VK_LEFT == e.getKeyCode()) {
                } else if (e.VK_RIGHT == e.getKeyCode()) {
                } else if (e.VK_ESCAPE == e.getKeyCode()) {
                    reset();
                } else if (e.VK_SPACE == e.getKeyCode()) {
                    Scout.scoutShoot();
                    Board.BoardSwitchSpace();
                } else if (e.VK_R == e.getKeyCode()) {
                    Board.Move();
                    Board.rotateShip();
                    Board.Hover(Board.mouseGetX(), Board.mouseGetY());
                } else if (e.VK_W == e.getKeyCode()){
                    Scout.scoutClear();
                    Scout.scoutMove(1);
                    Scout.changeScoutDirection(0);
                } else if (e.VK_A == e.getKeyCode()){
                    Scout.scoutClear();
                    Scout.scoutMove(2);
                    Scout.changeScoutDirection(1);
                } else if (e.VK_S == e.getKeyCode()){
                    Scout.scoutClear();
                    Scout.scoutMove(3);
                    Scout.changeScoutDirection(2);
                } else if (e.VK_D == e.getKeyCode()){
                    Scout.scoutClear();
                    Scout.scoutMove(4);
                    Scout.changeScoutDirection(3);
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

        
 
    if (animateFirstTime) {
        gOld.drawImage(image, 0, 0, null);
        return;
    }
        
        Board.PlayerPaint(g, this);    
        Scout.Draw(g, this);
        Board.Draw(g, this);
//        Scout.Draw(g, this);
        Menu.Draw(g, this);
        
        
        gOld.drawImage(image, 0, 0, null);
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
        Timer.Reset();
        Scout.Reset();
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
            Board.Init();
            Piece.Init();
            Menu.Init();
            reset();
        }

    Board.Animate();
    Timer.Animate();
    
        
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




