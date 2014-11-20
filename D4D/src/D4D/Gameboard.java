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

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author hwf5000
 */
public class Gameboard extends JPanel implements ActionListener{
    int delay;
    Image background;
    Car player;
    Timer gameTimer, enemySpawnTimer;
    int powerupTimer;
    ArrayList<Car> enemies;
    
    
    public Gameboard(){
 
        delay = 400;
        background = new ImageIcon(getClass().getClassLoader().getResource("images/street-bg.png")).getImage();
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
        
        
        //Create player and add to Gameboard
        Image carImage = new ImageIcon(getClass().getClassLoader().getResource("images/audi.png")).getImage();
        player = new Car(new ImageIcon(carImage), new Point (220,480));
        player.setBounds(player.location.x, player.location.y, player.getWidth(), player.getHeight());
        add(player);        
        
        
        //Create Game Timer
        gameTimer = new Timer(delay, this);
        gameTimer.addActionListener(this);
        gameTimer.start();
        
        //Create Spawn Timer
        enemySpawnTimer = new Timer(delay - 200, this);
        enemySpawnTimer.addActionListener(this);
        enemySpawnTimer.start();
        
    }
    
    public void spawnEnemy(){
        Image enemyImage = new ImageIcon(getClass().getClassLoader().getResource("images/audi.png")).getImage();
        Car enemy = new Car(new ImageIcon(), new Point (200,100));
        enemies.add(enemy);
        add(enemy);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj == enemySpawnTimer){
            spawnEnemy();
        }
        if(obj == gameTimer){
            for(Car enemy : enemies){
                enemy.location.y -= 2;
            }
            repaint();
        }
    }
    
   @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
        player.setLocation(player.getLocation());
        
        
    }
    
}
