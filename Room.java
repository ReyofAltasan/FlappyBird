
package flappybird;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;


public class Room {
    public ArrayList<Rectangle>tubes;
    private int time;
    private int currentTime=0;
    private int spd=4;
    private Random random;
    private final int SPACE_TUBES=96;
    private final int WIDTH_TUBES=32;
    public Room(int time){
        tubes=new ArrayList<>();
        this.time=time;
        random=new Random();
    }
    public void update (){ 
        currentTime++;
        if(currentTime==time){
            //Create our new tube
            currentTime=0;
       
            int height1=random.nextInt(Flappybird.HEIGHT/2);
             int y2 =height1+SPACE_TUBES;
             int height2=Flappybird.HEIGHT -y2;
             tubes.add(new Rectangle (Flappybird.WIDTH,0,WIDTH_TUBES,height1));
              tubes.add(new Rectangle (Flappybird.WIDTH,y2,WIDTH_TUBES,height2));
        }
        boolean shouldPlaySound=false;
        for(int i=0; i<tubes.size(); i++){
            Rectangle rect =tubes.get(i);
            rect.x-=spd;
            if(rect.x+rect.width <=0){
              shouldPlaySound=true;
                tubes.remove(i--);
                Flappybird.score++;
                continue;
            }
        }
        if(shouldPlaySound)Sound.test.play();
    }
    public void render (Graphics g){
        g.setColor(Color.ORANGE);
        for (int i =0 ; i< tubes.size();i++){
               Rectangle rect =tubes.get(i);
               g.fillRect(rect.x, rect.y, rect.width, rect.height);
            
        }
    }
}
