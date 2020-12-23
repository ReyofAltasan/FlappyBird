
package flappybird;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;


public class Bird extends Rectangle{
private static final long serialVersionUID=1l;
private float spd=4;
public boolean isPressed=false;
private BufferedImage sheet; 
private BufferedImage[] texture;
private ArrayList<Rectangle> tubes ;
private int imageIndex=0;
private boolean isFalling=false;
private int frames =0;
private float gravity=0.3f;
public Bird (int x ,int y, ArrayList<Rectangle> tubes ){
    setBounds(x,y,25,25);
   this.tubes=tubes;
    texture=new BufferedImage[3] ;
    try {
  
       sheet=ImageIO.read(getClass().getResource("/res/OIP 4.png"));
       
       texture[0]=sheet.getSubimage(0,0,100,90);
       texture[1]=sheet.getSubimage(32,0,16,20);
       texture[2]=sheet.getSubimage(90,0,50,46);



    } catch(IOException ex){}
           
}
    public void update(){
           isFalling=false;
        if(isPressed){
             spd=4;
            y-=(spd);
            imageIndex=2;
            frames=0;
        }else{
             isFalling=true;
            y+=spd;
            frames++;
            if(frames>20)frames=20;
           
        }
        if( isFalling){
            spd+=gravity;
            if(spd>8) spd =8;
        if(  frames >=20)   imageIndex=1;
        else  imageIndex=0;
          
        
        }
        for(int i=0; i< tubes.size();i++){
            if(this.intersects(tubes.get(i))) {
                //restart the game.
                Flappybird.room= new Room(80);
                tubes=Flappybird.room.tubes;
                y=Flappybird.HEIGHT/2;
                Flappybird.score=0;
                spd=4;
                break;
            }
        }
        if(y>=Flappybird.HEIGHT){
                //restart the game.
                Flappybird.room= new Room(80);
                      tubes=Flappybird.room.tubes;
                y=Flappybird.HEIGHT/2;
                  Flappybird.score=0;
                  spd=4;
        }
}
public void render(Graphics g){
    g.drawImage(texture[imageIndex], x,y, width, height,null);
  
}
}