
package classicarkanoiadgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GamePlay extends JPanel implements KeyListener , ActionListener {

    public boolean play = false;
    private int score = 0;

    private int totalbricks = 28;
    private int lives = 3;
    private Timer timer;
    private int delay = 8; //For the speed.

    private int playerX = 250; //the position of the stick (___) it moves in X only ")

    private int ballPosX = 120; //the position of the ball in X-axis.
    private int ballPosY = 350; //the position of the ball in Y-axis.
    public int ballXdir = 1;
    public int ballYdir = 2;

    private MapGenerator map;

    public GamePlay(){
        map = new MapGenerator(4,7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay , this);
        timer.start();

    }

    public void paint (Graphics g){

        //background
        g.setColor(Color.BLACK);
        g.fillRect(1,1,692,700);


        //drawing map1
        map.draw((Graphics2D) g);

        //borders
        g.setColor(new Color(224,255,255));
        g.fillRect(0,0,3,700);
        g.fillRect(0,0,692,3);
        g.fillRect(0,50,692,3);
        g.fillRect(592,0,3,700);

        //draw the score
        g.setColor(new Color(176,224,230));
        g.setFont(new Font("serif",Font.ITALIC,30));
        g.drawString(" Score : "+score,20,35);

//draw lives
g.setColor(new Color(176,224,230));
        g.setFont(new Font("serif",Font.ITALIC,30));
        g.drawString(" Lives : "+lives,400,35);
        
        //the paddel
        g.setColor(Color.CYAN);
        g.fill3DRect(playerX,600,100,10,true);

        //the ball
        g.setColor(new Color(255,250,205));
        g.fillOval(ballPosX,ballPosY,20,20);
if(totalbricks<=0)
{
     play = false;
            ballYdir=0;
            ballXdir=0;

            //change the color of the ball when player lose;
            g.setColor(new Color(255,69,0));
            g.fillOval(ballPosX,ballPosY,20,20);
            g.setColor(Color.white);
            g.drawString("You Won,Your Score is: "+score, 130, 300);
            g.drawString("Press Enter to Start the next level", 130, 350);
}
        //if the player loses;
        if(ballPosY > 610 ){
            play = false;
            ballYdir=0;
            ballXdir=0;
            
            //change the color of the ball when player lose;
            g.setColor(new Color(255,69,0));
            g.fillOval(ballPosX,ballPosY,20,20);
            g.setColor(Color.white);
            g.drawString("Your Score is: "+score, 130, 300);
            g.drawString("Press Enter to continue", 130, 350);
        }


        g.dispose();

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();

        if(play){
            if(new Rectangle(ballPosX , ballPosY , 20 ,20).intersects(new Rectangle(playerX,600,100,8))){
                ballYdir = -ballYdir;
            }
            A: for(int i=0;i<map.map.length;i++)
               for(int j=0;j<map.map[0].length;j++)
    {
        if(map.map[i][j]>0)
        {
            int brickX=j*map.brickWidth+80;
            int brickY=i*map.brickHeight+50;
            int brickwidth=map.brickWidth;
            int brickheight=map.brickHeight;
            Rectangle rect=new Rectangle(brickX,brickY,brickwidth,brickheight);
            Rectangle ballrect=new Rectangle(ballPosX,ballPosY,20,20);
            Rectangle brickrect=rect;
            if(ballrect.intersects(brickrect))
            {
                map.setbrickvalue(0,i,j);
                totalbricks--;
                score+=2;
                if(ballPosX+19<=brickrect.x||ballPosY+1>=brickrect.x+ brickrect.width)
                {
                    ballXdir=-ballXdir;
                }
                else
                {
                    ballYdir=-ballYdir;
                }
                break A;
            }
        }
    }
 }
            ballPosX+=ballXdir;
            ballPosY+=ballYdir;
            if(ballPosX < 0){ //for left border
                ballXdir = -ballXdir;
            }
            if(ballPosY < 0){ //for top border
                ballYdir = -ballYdir;
            }
            if(ballPosX > 570){ //for right border
                ballXdir = -ballXdir;
            }

        repaint();
    }
 @Override
    public void keyReleased(KeyEvent e) { }
    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode()== KeyEvent.VK_RIGHT){
               if(playerX >=485){
                   playerX = 485;
               }else {
                   MoveRight();
               }
        }
        if(e.getKeyCode()== KeyEvent.VK_LEFT){
               if(playerX <= 10){
                   playerX = 10;
               }else {
                   MoveLeft();
               }
        }
        if(e.getKeyCode()==KeyEvent.VK_ENTER)
        {
            if(!play)
            {
                play=true;
                ballPosX=120;
                ballPosY=350;
                ballXdir=1;
                ballYdir=2;
                playerX=310;
                score=0;
              totalbricks=28;
              map=new MapGenerator(4,7);
              repaint();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_P)
        {
          play = false;
          ballXdir = 0;
          ballYdir = 0;
          new NewJFrame().setVisible(true);
          
        }
        
    }

    public void MoveRight(){
        play = true;
        playerX+=30;
    }
    public void MoveLeft(){
        play = true;
        playerX-=30;
    }
}
        

