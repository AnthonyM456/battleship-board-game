
package battleship;

import java.awt.Color;

public class Player {
    private static Player currentTurn;
    private static Player players[] = new Player[3];
    private Color color;
    
    public static void Reset()
    {
        
        if (players[0] == null)
        {
            players[0] = new Player(Color.red);
            players[1] = new Player(Color.black);
            players[2] = new Player(Color.gray);

            
        }
        currentTurn = players[0];
        
    }
    
    
    public static Player GetCurrentPlayer()
    {
        return(currentTurn);
    }
    public static Player getPlayer1()
    {
        return(players[0]);
    }
    public static Player getPlayer2()
    {
        return(players[1]);
    }
    public static Player getPlayer3()
    {
        return(players[2]);
    }
    public static void SwitchTurn()
    {
        Scout.scoutCount();

        if(currentTurn == players[0])
            currentTurn = players[1];
        else if(currentTurn == players[1])
            currentTurn = players[0];        
    }    
    Player(Color _color)
    {
        color = _color;
        
    }
    public Color getColor()
    {
        return (color);
    }

    
}
