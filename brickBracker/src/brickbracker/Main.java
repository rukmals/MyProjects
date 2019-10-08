/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbracker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


/**
 *
 * @author user
 */
public class Main {
     public static void main(String []args) {
        JFrame obj = new JFrame();
        Gameplay gamePlay = new Gameplay();
        obj.setBounds(10,10,700,600);
        obj.setTitle("Breakout Ball");
        obj.setResizable(false);
        obj.setVisible(true);  
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        obj.add(gamePlay); 
    }
    
    
}
class Gameplay extends JPanel implements KeyListener,ActionListener{
    private boolean play = false;
    private int score = 0;
    private int totalBricks =9;
    private Timer timer;
    private int delay = 8;
    private int playerX = 310;
    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir = -1; 
    private int ballYdir = -1; 
    private MapGenerator map;
    private int row = 3;
    private int col = 3;
    private int level = 1;
    public Gameplay(){
        map = new MapGenerator(row,col);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay,this);
        timer.start();
    }
    public void paint(Graphics g){
        //background
        g.setColor(Color.black);
        g.fillRect(1,1,69200000,5920);
        //drawing map
        map.draw((Graphics2D)g);
        //border
        g.setColor(Color.black);
        g.fillRect(0,0,3,592);
        g.fillRect(0,0,692,3);
        g.fillRect(691,0,3,592);
        //scores
        g.setColor(Color.white);
        g.setFont(new Font("serif",Font.BOLD,25));
        g.drawString(""+score, 590, 30);
        //the paddle
        g.setColor(Color.green);
        g.fillRect(playerX, 550 , 100, 8);
        //set level
        g.setColor(Color.red);
        g.drawString("Level "+level,300,20);
        //the ball
        g.setColor(Color.yellow);
        g.fillOval(ballposX,ballposY,20,20);
        if(totalBricks<=0){
            play=false;
            ballXdir=0;
            ballYdir=0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("You Won", 190, 300);
            
            g.setFont(new Font("serif",Font.BOLD,20));
            g.drawString("Prss Enter to Go Next Level", 230, 350);
        }
        if(ballposY>570){
            play=false;
            ballXdir=0;
            ballYdir=0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Game Over,Scores:", 190, 300);
            
            g.setFont(new Font("serif",Font.BOLD,20));
            g.drawString("Prss R to Restart", 230, 350);
             
        }
        g.dispose(); 
    }
    public void actionPerformed(ActionEvent e){
        timer.start();
        if(play){
            if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,8))){
                ballYdir = -ballYdir; 
            }
            A: for(int i = 0 ; i<map.map.length;i++){
                for(int j = 0;j<map.map[0].length;j++){
                    if(map.map[i][j]>0){
                       int brickX = j*map.brickWidth+80;
                       int brickY = i*map.brickHeight+50;
                       int brickWidth = map.brickWidth;
                       int brickHeight = map.brickHeight;
                       
                       Rectangle rect = new Rectangle(brickX,brickY,brickWidth,brickHeight);
                       Rectangle ballRect = new Rectangle(ballposX,ballposY,20,20);
                       Rectangle brickRect = rect;
                       if(ballRect.intersects(brickRect)){
                           map.setBrickValue(0, i, j);
                           totalBricks--;
                           score+=5;
                           
                           if(ballposX+19 <= brickRect.x || ballposX+1>=brickRect.x+brickRect.width){
                               ballXdir = -ballXdir;
                           }else{
                               ballYdir = -ballYdir;
                           }
                           break A;
                       }
                    }
                    
                }
            }
            ballposX+=ballXdir;
            ballposY+=ballYdir;
            if(ballposX<0){
                ballXdir = -ballXdir;
            }
            if(ballposY<0){
                ballYdir = -ballYdir;
            }
            if(ballposX>670){
                ballXdir = -ballXdir;
            }
        }
        repaint();
        
    } 
    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    //public void keyReleased(keyEvent e){}
    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            if(playerX>500){
                playerX=580;
            }else{
                moveRight();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            if(playerX<100){
                playerX=10;
            }else{
                moveLeft();
            }
            
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
             row+=1;
             col+=1;
             level+=1;
            if(!play & totalBricks==0){
                play = true;
                ballposX = 120;
                ballposY = 350;
                ballXdir = -1;
                ballYdir = -2;
                playerX = 310;
                score = 0;
                totalBricks = row*col;
                map = new MapGenerator(row,col);
                
                repaint();
            }
            
        }if(e.getKeyCode() == KeyEvent.VK_R){
            if(!play){
                play = true;
                ballposX = 120;
                ballposY = 350;
                ballXdir = -1;
                ballYdir = -2;
                playerX = 310;
                score = 0;
                totalBricks = row*col;
                map = new MapGenerator(row,col);
                
                repaint();
            }
            
        }
         if(e.getKeyCode()==KeyEvent.VK_SPACE){
            if(play){
                play = false;
            }else{
                play = true;
            }
        }
        
    }public void moveRight(){
        play = true;
        playerX+=20; 
    }
    public void moveLeft(){
        play = true;
        playerX-=20; 
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
       
}
