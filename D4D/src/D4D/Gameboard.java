/*
 * The MIT License
 *
 * Copyright 2014 macbook.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package D4D;

import D4D.ShellPanel.Difficulty;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 *
 * @author hwf5000
 */
public class Gameboard extends JPanel implements ActionListener, KeyListener{
    int delay;
    Image background;
    Car player;
    //Car enemy;
    Timer gameTimer, enemyTimer;
    int powerupTimer = 0;
    ArrayList<Car> enemies;
    Point[] lane;
    boolean powerupToggle = false;
    int score = 0;
    
    
    public Gameboard(ImageIcon selectedCar, Difficulty difficulty){
 
        switch(difficulty){
            case MEDIUM:
                delay = 30;
                break;
            case HARD:
                delay = 10;
                break;
            case EASY:
            default:
                delay = 50;
                break;
        }
        
        //create the background Image and array for the lanes
        background = new ImageIcon(getClass().getClassLoader().getResource("images/street-bg.png")).getImage();
        lane = new Point[6];
        
        // create lanes
        for(int i = 0; i < 6; i++){
            int laneStart = 100 + (i * 60);
            lane[i] = new Point(laneStart, -50);  
        }
        enemies = new ArrayList<>();
        
        //Set up the board layout and dimensions
        setLayout(null);
        setFocusable(true);
        Dimension dimensions = new Dimension(background.getWidth(null), background.getHeight(null));
        setPreferredSize(dimensions);
        setMinimumSize(dimensions);
        setMaximumSize(dimensions);
        setSize(dimensions);
        setLayout(null);
        addKeyListener(this);
        
        //Create and add player to Gameboard
        player = new Car(selectedCar, new Point (220,480));
        add(player);  
        
        //Create and add first enemy to the enemies array and the gamebaord
        Image enemyImage = new ImageIcon(getClass().getClassLoader().getResource("images/expedition.png")).getImage();
        Car enemy = new Car(new ImageIcon(enemyImage), lane[0]);
        enemies.add(enemy);
        add(enemy);
        
        
        //Create Game Timer
        gameTimer = new Timer(delay, this);
        gameTimer.addActionListener(this);
        gameTimer.start();
        
        //Create the enemy spawn timer
        enemyTimer = new Timer(5000, this);
        enemyTimer.addActionListener(this);
        enemyTimer.start();
        
    }
    
    
    public void spawnEnemy(){
        int rand = (int) Math.ceil(Math.random() * 6);
        
        //Create new emeny adding them to the list of enemies and the gameboard
        Image enemyImage = new ImageIcon(getClass().getClassLoader().getResource("images/ford.png")).getImage();

        Car enemy = new Car(new ImageIcon(enemyImage), lane[rand-1]);
        enemies.add(enemy);
        add(enemy);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        

        if(obj == enemyTimer){
            
            //Its time to create a new enemy
            if(enemies.size() < 7){
                spawnEnemy();
            }
            
            //Check if powerup is in use 
            if(powerupToggle && powerupTimer < 1){
                powerupTimer++;
            }
            
            else{
                powerupToggle = false;
                for (Car enemy : enemies){
                    enemy.speed = 5;
                }
            }
        }
        
        if(obj == gameTimer){
            
            for (Car enemy : enemies){
                
                //if enemy is still on the board
                if (enemy.location.y <= this.getHeight()) {
                    
                    //move the enemy
                    enemy.location.y += enemy.getSpeed();
                    
                    //check collision of player and enemies
                    if(collisionCheck(enemy)){
                        gameOver();
                    }
                }
                
                //reset the enemy posistion to the top with a new lane
                else{
                    int rand = (int) Math.ceil(Math.random() * 6);
                    enemy.resetEnemy(lane[rand-1]);
                    score++;
                }
            }
        }
            repaint();       
    }
    
    public void gameOver(){
        System.out.println("Game Over");
        JLabel gameover = new JLabel("Game Over", SwingConstants.CENTER);
        gameover.setFont(new Font("serif", Font.PLAIN, 36));
        gameTimer.stop();
        
        JFrame gameoverFrame = new JFrame("Game Over");
        gameoverFrame.setLayout(new GridLayout(3, 1));
        gameoverFrame.add(gameover);
        gameoverFrame.add(new ScorePanel(score));
       
        gameoverFrame.setSize(500, 500);
        gameoverFrame.setLocationRelativeTo(this);
        gameoverFrame.setVisible(true);
        gameoverFrame.setDefaultCloseOperation(gameoverFrame.EXIT_ON_CLOSE);
        
    }
    
   @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
        player.setBounds(player.location.x, player.location.y, player.width, player.height);
        for(Car enemy : enemies){
                enemy.setBounds(enemy.location.x, enemy.location.y, enemy.width, enemy.height);
                
            }
    }
    
    public boolean collisionCheck(Car enemy) {
        //for (Car enemy : enemies) {
            if(enemy.getBounds().intersects(player.getBounds())){
                return true;
            }
        //}
        return false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {


            // Move Left
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                if(player.getLocation().x >= 100){
                    player.location.x -= 25;
                }
                
                repaint();
                break;

            // Move Right    
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                if(player.getLocation().x <= 400){
                    player.location.x += 25;
                }
                
                repaint();
                break;
                
            case KeyEvent.VK_SPACE:
                
                    togglePowerUp();

        }
    }
    
    public void togglePowerUp(){
        powerupToggle = true;
       for(Car enemy: enemies){
            enemy.speed = 3;
        } 
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
