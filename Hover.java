/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import static battleship.Board.board;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author 145004436
 */
public class Hover {
        private static boolean init;
        public static Piece hover[][] = new Piece[Board.NUM_ROWS()][Board.NUM_COLUMNS()];
        private static boolean rotate;
        private static int mouseGetX;
        private static int mouseGetY;
        private static int shrink;

    public static void Highlight(int xpixel, int ypixel) {
        int xdelta = Window.getWidth2()/10;
        int ydelta = Window.getHeight2()/10;
        int zcol = (xpixel - Window.getX(0))/xdelta;
        int zrow = (ypixel - Window.getY(0))/ydelta;
        mouseGetX = xpixel;
        mouseGetY = ypixel;
        if (zcol == Board.NUM_COLUMNS() || zcol < 0 || xpixel < Window.getX(0) || ypixel < Window.getY(0)|| zrow < 0 || zrow == Board.NUM_ROWS())
            return;
//        System.out.println(zcol+"             "+zrow);
        if (!rotate)
        {
            if (zrow > 1)
            {
                hover[zrow][zcol] = new Piece(Player.GetCurrentPlayer().getColor());
                if (shrink < 2)
                {
                    hover[zrow-1][zcol] = new Piece(Player.GetCurrentPlayer().getColor());
                    if (shrink < 1)
                        hover[zrow-2][zcol] = new Piece(Player.GetCurrentPlayer().getColor());
                }

            }
            else if (zrow == 1) {
                hover[zrow][zcol] = new Piece(Player.GetCurrentPlayer().getColor());
                if (shrink < 2)
                {
                    hover[zrow-1][zcol] = new Piece(Player.GetCurrentPlayer().getColor());
                    if (shrink < 1)
                        hover[zrow+1][zcol] = new Piece(Player.GetCurrentPlayer().getColor());
                }
            }
            else if (zrow == 0) {
                hover[zrow][zcol] = new Piece(Player.GetCurrentPlayer().getColor());
                if (shrink < 2)
                {
                    hover[zrow+1][zcol] = new Piece(Player.GetCurrentPlayer().getColor());
                    if (shrink < 1)
                        hover[zrow+2][zcol] = new Piece(Player.GetCurrentPlayer().getColor());
                }
            }
        }
        else 
        {
            if (zcol > 1)
            {
                hover[zrow][zcol] = new Piece(Player.GetCurrentPlayer().getColor());
                if (shrink < 2)
                {
                    hover[zrow][zcol-1] = new Piece(Player.GetCurrentPlayer().getColor());
                    if (shrink < 1)
                        hover[zrow][zcol-2] = new Piece(Player.GetCurrentPlayer().getColor());
                }

            }
            else if (zcol == 1) {
                hover[zrow][zcol] = new Piece(Player.GetCurrentPlayer().getColor());
                if (shrink < 2)
                {
                    hover[zrow][zcol-1] = new Piece(Player.GetCurrentPlayer().getColor());
                    if (shrink < 1)
                        hover[zrow][zcol+1] = new Piece(Player.GetCurrentPlayer().getColor());
                }
            }
            else if (zcol == 0) {
                hover[zrow][zcol] = new Piece(Player.GetCurrentPlayer().getColor());
                if (shrink < 2)
                {
                    hover[zrow][zcol+1] = new Piece(Player.GetCurrentPlayer().getColor());
                    if (shrink < 1)
                        hover[zrow][zcol+2] = new Piece(Player.GetCurrentPlayer().getColor());
                }
            }
        }
    }
    public static void Click(){
        shrink++;
        if (shrink > 2)
        {
            Player.SwitchTurn();
            shrink = 0;
        }
        System.out.println(shrink+"");
        
    }
    public static void PlaceShip(int xpixel,int ypixel) {
        
        int ydelta = Window.getHeight2()/Board.NUM_ROWS();
        int xdelta = Window.getWidth2()/Board.NUM_COLUMNS();

        int zcol = (xpixel-Window.getX(0))/xdelta;
        int zrow = (ypixel-Window.getY(0))/ydelta;

        if (!Hover.getRotate())
        {
            if (zrow > 1)
            {
                board[zrow][zcol] = new Piece(Player.GetCurrentPlayer().getColor());
                if (shrink < 2)
                {
                    board[zrow-1][zcol] = new Piece(Player.GetCurrentPlayer().getColor());
                    if (Hover.getShrink() < 1)
                        board[zrow-2][zcol] = new Piece(Player.GetCurrentPlayer().getColor());
                }

            }
            else if (zrow == 1) {
                board[zrow][zcol] = new Piece(Player.GetCurrentPlayer().getColor());
                if (shrink < 2)
                {
                    board[zrow-1][zcol] = new Piece(Player.GetCurrentPlayer().getColor());
                    if (shrink < 1)
                        board[zrow+1][zcol] = new Piece(Player.GetCurrentPlayer().getColor());
                }
            }
            else if (zrow == 0) {
                board[zrow][zcol] = new Piece(Player.GetCurrentPlayer().getColor());
                if (shrink < 2)
                {
                    board[zrow+1][zcol] = new Piece(Player.GetCurrentPlayer().getColor());
                    if (shrink < 1)
                        board[zrow+2][zcol] = new Piece(Player.GetCurrentPlayer().getColor());
                }
            }
        }
        else 
        {
            if (zcol > 1)
            {
                board[zrow][zcol] = new Piece(Player.GetCurrentPlayer().getColor());
                if (shrink < 2)
                {
                    board[zrow][zcol-1] = new Piece(Player.GetCurrentPlayer().getColor());
                    if (shrink < 1)
                        board[zrow][zcol-2] = new Piece(Player.GetCurrentPlayer().getColor());
                }

            }
            else if (zcol == 1) {
                board[zrow][zcol] = new Piece(Player.GetCurrentPlayer().getColor());
                if (shrink < 2)
                {
                    board[zrow][zcol-1] = new Piece(Player.GetCurrentPlayer().getColor());
                    if (shrink < 1)
                        board[zrow][zcol+1] = new Piece(Player.GetCurrentPlayer().getColor());
                }
            }
            else if (zcol == 0) {
                board[zrow][zcol] = new Piece(Player.GetCurrentPlayer().getColor());
                if (shrink < 2)
                {
                    board[zrow][zcol+1] = new Piece(Player.GetCurrentPlayer().getColor());
                    if (shrink < 1)
                        board[zrow][zcol+2] = new Piece(Player.GetCurrentPlayer().getColor());
                }
            }
        }
    }
    public static void Move(){
        for (int zrow=0;zrow<Board.NUM_ROWS();zrow++)
        {
            for (int zcol=0;zcol<Board.NUM_COLUMNS();zcol++)        
            {
                hover[zrow][zcol] = null;
            }
        }
    }
    public static void Draw(Graphics2D g) {
        int ydelta = Window.getHeight2()/Board.NUM_COLUMNS();
        int xdelta = Window.getWidth2()/Board.NUM_ROWS();
        
        for (int zrow=0;zrow<Board.NUM_ROWS();zrow++) {
            for (int zcol=0;zcol<Board.NUM_COLUMNS();zcol++)   {
                if (hover[zrow][zcol] != null)
                    hover[zrow][zcol].draw(g, zrow, zcol, xdelta, ydelta);
            }
        }
        
    }
    public static int mouseGetX() {
        return(mouseGetX);
    }
    public static int mouseGetY() {
        return(mouseGetY);
    }
    public static int getShrink(){
        return(shrink);
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
    
}
