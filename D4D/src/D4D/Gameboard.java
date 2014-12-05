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
    Timer gameTimer, enemySpawnTimer;
    int powerupTimer = 0;
    ArrayList<Car> enemies;
    Point[] lane;
    boolean powerupToggle = false;
    
    
    public Gameboard(ImageIcon p, Difficulty d){
 
        delay = 30;
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
        
        //Create player and add to Gameboard
        player = new Car(p, new Point (220,480));
        add(player);  
        
        
        Image enemyImage = new ImageIcon(getClass().getClassLoader().getResource("images/expedition.png")).getImage();
        Car enemy = new Car(new ImageIcon(enemyImage), lane[0]);
        enemies.add(enemy);
        add(enemy);
        
        
        //Create Game Timer
        gameTimer = new Timer(delay, this);
        gameTimer.addActionListener(this);
        gameTimer.start();
        
        //Create Spawn Timer
        enemySpawnTimer = new Timer(5000, this);
        enemySpawnTimer.addActionListener(this);
        enemySpawnTimer.start();
        
    }
    
    public void spawnEnemy(){
        int rand = (int) Math.ceil(Math.random() * 6);
        Image enemyImage = new ImageIcon(getClass().getClassLoader().getResource("images/ford.png")).getImage();
        Car enemy = new Car(new ImageIcon(enemyImage), lane[rand-1]);
        enemies.add(enemy);
        add(enemy);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj == enemySpawnTimer){
            if(enemies.size() < 7){
                spawnEnemy();
            }
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
                if (enemy.location.y <= this.getHeight()) {
                    enemy.location.y += enemy.getSpeed();
                    if(collisionCheck(enemy)){
                        gameOver();
                    }
                }
                else{
                    int rand = (int) Math.ceil(Math.random() * 6);
                    enemy.resetEnemy(lane[rand-1]);
                }
            }
        }
            repaint();       
    }
    
    public void gameOver(){
        JLabel gameover = new JLabel("Game Over", SwingConstants.CENTER);
        gameover.setFont(new Font("serif", Font.PLAIN, 36));
        gameTimer.stop();
        JFrame gameoverFrame = new JFrame("Game Over");
        gameoverFrame.setLayout(new GridLayout(3, 1));
        gameoverFrame.add(gameover);
        JLabel lose = new JLabel("You lose", SwingConstants.CENTER);
        gameoverFrame.add(lose);
        gameoverFrame.setSize(300, 300);
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
