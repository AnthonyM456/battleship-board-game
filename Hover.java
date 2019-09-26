/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import static battleship.Board.board;

/**
 *
 * @author 145004436
 */
public class Hover {
        private static boolean init;

    public static void Highlight(int xpixel, int ypixel) {
        int xdelta = Window.getWidth2()/10;
        int ydelta = Window.getHeight2()/10;
        int zcol = (xpixel - Window.getX(0))/xdelta;
        int zrow = (ypixel - Window.getY(0))/ydelta;
        if (zcol == 10 || zcol < 0 || zrow < 0 || zrow == 10)
            return;
        System.out.println(zcol+"             "+zrow);
        if (zrow > 1)
        {
            board[zrow][zcol] = new Piece(Player.GetCurrentPlayer().getColor());
            board[zrow-1][zcol] = new Piece(Player.GetCurrentPlayer().getColor());
            board[zrow-2][zcol] = new Piece(Player.GetCurrentPlayer().getColor());
        }
        else if (zrow == 1) {
            board[zrow][zcol] = new Piece(Player.GetCurrentPlayer().getColor());
            board[zrow-1][zcol] = new Piece(Player.GetCurrentPlayer().getColor());
            board[zrow+1][zcol] = new Piece(Player.GetCurrentPlayer().getColor());
        }
        else if (zrow == 0) {
            board[zrow][zcol] = new Piece(Player.GetCurrentPlayer().getColor());
            board[zrow+1][zcol] = new Piece(Player.GetCurrentPlayer().getColor());
            board[zrow+2][zcol] = new Piece(Player.GetCurrentPlayer().getColor());
        }
    }
    public static void Move(){
        for (int zrow=0;zrow<Board.NUM_ROWS();zrow++)
        {
            for (int zcol=0;zcol<Board.NUM_COLUMNS();zcol++)        
            {
                board[zrow][zcol] = null;
            }
        }
    }
    
}
