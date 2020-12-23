 
package flappybird;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class Flappybird extends Canvas implements Runnable,KeyListener{
    
private static final long serialVersionUID=1L;


      public static final int WIDTH=640,HEIGHT=480;
      private boolean running=false;
      private Thread thread;
      public static int score=0;
      public static Room room;
      public Bird bird;
      
      public Flappybird(){
          Dimension d= new Dimension(Flappybird.WIDTH,Flappybird.HEIGHT);
          setPreferredSize(d);
          addKeyListener(this);
          room=new Room(80);
          bird=new Bird(20,Flappybird.HEIGHT/2,room.tubes);
      }
      public synchronized void start(){
          if (running) return;
          running=true;
          thread=new Thread(this);
          thread.start();
          
      }
      public synchronized void stop(){
          if (!running) return;
          running=false;
          
          try {
              thread.join();
          } catch (InterruptedException ex) {
              ex.printStackTrace();
          }  
          }
    public static void main(String[] args) {
        JFrame frame  =new JFrame("FLAPPY-BIRD-GAME");
        Flappybird flappybird = new Flappybird();
         frame.add(flappybird);
         frame.setResizable(false);
         frame.pack();
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setLocationRelativeTo(null);
         frame.setVisible(true);
             flappybird.start();
       
    }
     @Override  
 
public void run(){
    int fps=0;
    double timer=System.currentTimeMillis();
    long lastTime=System.nanoTime();
    double delta=0;
    double ns=1000000000/60;
    while(running){
        long now=System.nanoTime();
        delta+=(now-lastTime)/ns;
        lastTime=now;
        while(delta>=1){
        update();
        render();
        fps++;
        delta--;
    } if(System.currentTimeMillis()-timer >=1000){
        System.out.println("FPS:"+fps);
        fps=0;
        timer+=1000;
    }}
    stop();
}
private void render(){
    BufferStrategy bs =getBufferStrategy();
    if (bs == null){
        createBufferStrategy(3);
        return;
    }
    Graphics g =bs.getDrawGraphics();
    g.setColor(Color.LIGHT_GRAY);
    g.fillRect(0, 0, Flappybird.WIDTH, Flappybird.HEIGHT);
    room.render(g);
    bird.render(g);
    g.setColor(Color.white);
    g.setFont(new Font(Font.DIALOG,Font.BOLD,19));
    g.drawString("Score:"+(int) score, 10, 20);
    g.dispose();
    bs.show();
}
private void update(){
  room.update();
  bird.update(); 
}


    @Override
    public void keyPressed(KeyEvent ex) {
        if(ex.getKeyCode()==KeyEvent.VK_SPACE){
            bird.isPressed=true;
        }
    }

    @Override
    public void keyReleased(KeyEvent ex) {
         if(ex.getKeyCode()==KeyEvent.VK_SPACE){
            bird.isPressed=false;
        }
        
    }
    @Override
    public void keyTyped(KeyEvent ex) {
        
    }
    }
    

    
