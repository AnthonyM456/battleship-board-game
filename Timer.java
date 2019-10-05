
package battleship;

import java.awt.*;

public class Timer {
    
    private static int timeCount;
    
   public static void Reset(){
        timeCount = 0;
    } 
    
    public static void Animate(){
        
        if(timeCount % 50 == 49)
//            System.out.println(timeCount);
        
        
        
        
        timeCount++;
    }
    
    
}
