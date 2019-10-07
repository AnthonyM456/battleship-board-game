/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;
import static battleship.Board.ship;
import java.awt.*;
/**
 *
 * @author tomta
 */
public class Scout {
    private static Image sub_blueV;
    private static boolean scoutActive;
    private static int scoutCount;
    private static Piece hover[][][] = new Piece[Board.getNUM_ROWS()][Board.getNUM_COLUMNS()][4];
    private static Piece sub[][][] = new Piece[Board.getNUM_ROWS()][Board.getNUM_COLUMNS()][4];
    private static int direction;
    private static int scoutCol;
    private static int scoutRow;
    private static boolean scoutPlaced;
    private static int scoutMoved;

    public static void scoutAdd(int xpixel,int ypixel) {

        int ydelta = Window.getHeight2()/Board.getNUM_ROWS();
        int xdelta = Window.getWidth2()/Board.getNUM_COLUMNS();

        int zcol = (xpixel-Window.getX(0))/xdelta;
        int zrow = (ypixel-Window.getY(0))/ydelta;
        if (scoutPlaced)
            return;
        
        scoutCol = zcol;
        scoutRow = zrow;

        if (scoutActive){
            sub[zrow][zcol][Board.BoardSel()] = new Piece (Color.green);
            scoutPlaced = true;
//            scoutActive = false;
        }

    }
    public static void scoutHover(int xpixel,int ypixel){
        
        int ydelta = Window.getHeight2()/Board.getNUM_ROWS();
        int xdelta = Window.getWidth2()/Board.getNUM_COLUMNS();

        int zcol = (xpixel-Window.getX(0))/xdelta;
        int zrow = (ypixel-Window.getY(0))/ydelta;
        
        if(zrow < Board.getNUM_ROWS() && zrow > -1 && zcol < Board.getNUM_COLUMNS() && zcol > -1) {
            if(Board.BoardSel() == 2 || Board.BoardSel() == 3)
                return;
        if (scoutPlaced)
            return;

        hover[zrow][zcol][Board.BoardSel()] = new Piece(Color.green);
//        System.out.println("scoutHover");
        }        
    }
    public static void Move(){
        if(Board.BoardSel() == 2 || Board.BoardSel() == 3)
            return;
        for (int zrow=0;zrow<Board.getNUM_ROWS();zrow++)
        {
            for (int zcol=0;zcol<Board.getNUM_COLUMNS();zcol++)        
            {
                hover[zrow][zcol][Board.BoardSel()] = null;
            }
        }
    }
    public static boolean getScoutActive(){
        return(scoutActive);
    }
    public static void scoutCount(){
        scoutCount++;
        if (scoutCount == 2)
        {
            scoutActive = true;
        }
//            if (scoutActive)
//            {
//                System.out.println("scoutActive");
//                ship[4][4][Board.BoardSel()] = new Piece (Color.green);
//            }
    }
    public static void changeScoutDirection(int keyPressed){
        direction = keyPressed;
    }
    public static int scoutDirection(){
        return(direction);
    }
    public static void scoutClear(){
        for (int zrow=0;zrow<Board.getNUM_ROWS();zrow++)
        {
            for (int zcol=0;zcol<Board.getNUM_COLUMNS();zcol++)        
            {
                sub[zrow][zcol][Board.BoardSel()] = null;
            }
        }
    }
    public static void scoutMove(int keyPressed){
        if(Board.BoardSel() == 2 || Board.BoardSel() == 3)
            return;
        if (!scoutPlaced)
            return;
        boolean validMovement = true;
        
//        sub[scoutRow][scoutCol][Board.BoardSel()] = new Piece (Color.green);
        
        if (keyPressed == 1){
            if (scoutRow-1 < 0)
                validMovement = false;
            if (validMovement && scoutMoved < 2)
                scoutRow--;
            sub[scoutRow][scoutCol][Board.BoardSel()] = new Piece (Color.green);
        }
        else if (keyPressed == 2){
            if (scoutCol-1 < 0)
                validMovement = false;
            if (validMovement && scoutMoved < 2)
                scoutCol--;
            sub[scoutRow][scoutCol][Board.BoardSel()] = new Piece (Color.green);
        }
        else if (keyPressed == 3){
            if (scoutRow+1 > Board.getNUM_ROWS()-1)
                validMovement = false;
            if (validMovement && scoutMoved < 2)
                scoutRow++;
            sub[scoutRow][scoutCol][Board.BoardSel()] = new Piece (Color.green);
        }
        else if (keyPressed == 4){
            if (scoutCol+1 > Board.getNUM_COLUMNS()-1)
            {
                validMovement = false;
                System.out.println("wow");
            }
            if (validMovement && scoutMoved < 2)
                scoutCol++;
            sub[scoutRow][scoutCol][Board.BoardSel()] = new Piece (Color.green);
        }
        if (validMovement)
            scoutMoved++;
    }
    public static void scoutShoot(){
        if (sub[scoutRow][scoutCol][Board.BoardSel()] != null)
            ship[scoutRow][scoutCol][Board.BoardSel()] = new Piece (Color.gray);
    }
    public static void Draw(Graphics2D g, Battleship thisObj) {
        for (int zrow=0;zrow<Board.getNUM_ROWS();zrow++)
        {
            for (int zcol=0;zcol<Board.getNUM_COLUMNS();zcol++){
                if (sub[zrow][zcol][Board.BoardSel()] != null){
                    sub[zrow][zcol][Board.BoardSel()].draw(g, zrow, zcol, Board.xdelta(), Board.ydelta(), thisObj);
                }
            }
        }
        for (int zrow=0;zrow<Board.getNUM_ROWS();zrow++)
        {
            for (int zcol=0;zcol<Board.getNUM_COLUMNS();zcol++){
                if (hover[zrow][zcol][Board.BoardSel()] != null)    {
                    hover[zrow][zcol][Board.BoardSel()].draw(g, zrow, zcol, Board.xdelta(), Board.ydelta(), thisObj);
                }
            }
        }
    }
}
        

        
//        public void draw(Graphics2D g,int row,int column, int xdelta,int ydelta, Battleship thisObj) {
//        g.drawImage(sub_blueV,Window.getX(0)+xdelta*column, Window.getY(0)+ydelta*row,xdelta,ydelta,thisObj);
//
//    }
