/*
 * The MIT License
 *
 * Copyright 2014 Hermes Frangoudis (hwf5000), Aldrich Fung.
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

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Aldrich, hwf5000
 */
public class ShellPanel extends JPanel implements ActionListener{
    
    SettingPanel settings;
    Gameboard gameboard;
    public enum Difficulty {
    EASY, MEDIUM, HARD
}
    
    public ShellPanel(){
        super();
        setLayout(new BorderLayout());
        settings = new SettingPanel();
        add(settings);
        settings.start.addActionListener(this);
    }
    
    
    
    public void addGameboard(ImageIcon player, Difficulty d){
        remove(settings);
        settings.start.removeActionListener(this);
        gameboard = new Gameboard(player, d);
        add(gameboard);
        gameboard.getTopLevelAncestor().requestFocus();
        gameboard.requestFocusInWindow();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if(obj == settings.start){
            Difficulty difficulty;
            
            int d = settings.difficulty.getValue();
            
            switch(d){

                case 2:
                    difficulty = Difficulty.MEDIUM;
                    break;
                case 3:
                    difficulty = Difficulty.HARD;
                    break;
                
                case 1:
                default:
                    difficulty = Difficulty.EASY;
                    break;
            }
            
            addGameboard(settings.player, difficulty);
        }
    }
}
