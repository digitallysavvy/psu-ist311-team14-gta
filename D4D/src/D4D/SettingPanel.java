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

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import javax.swing.*;

/**
 *
 * @author Aldrich
 */
public class SettingPanel extends JPanel implements ActionListener{
    
    LayoutManager layout;
    JButton audiButton,bentlyButton,lamboButton,start;
    JSlider difficulty;
    ImageIcon player, audiImage, bentlyImage, lamboImage;
    
    
    public SettingPanel(){
        super();
        layout = new GridLayout(5,1,10,30);
        setLayout(layout);
        
        player = new ImageIcon(getClass().getClassLoader().getResource("images/default.png"));
        audiImage = new ImageIcon(getClass().getClassLoader().getResource("images/audi.png"));
        bentlyImage = new ImageIcon(getClass().getClassLoader().getResource("images/bently.png"));
        lamboImage = new ImageIcon(getClass().getClassLoader().getResource("images/lamborghini.png"));
        
        audiButton = new JButton(audiImage);
        bentlyButton = new JButton(bentlyImage);
        lamboButton = new JButton(lamboImage);
        start = new JButton("Start");
        
        audiButton.addActionListener(this);
        bentlyButton.addActionListener(this);
        lamboButton.addActionListener(this);
        
        add(audiButton);
        add(bentlyButton);
        add(lamboButton);
        
        difficulty = new JSlider(1,3,2);
        
        //Dictionary for JSlider text labels
        Hashtable<Integer, JLabel> sizeTable = new Hashtable<Integer, JLabel>();
        sizeTable.put (1, new JLabel("Easy"));
        sizeTable.put (2, new JLabel("Normal"));
        sizeTable.put (3, new JLabel("Hard"));
        difficulty.setLabelTable (sizeTable);
        
        difficulty.setPaintTicks(true);
        difficulty.setPaintLabels(true);
        difficulty.setMajorTickSpacing(1);
        difficulty.setSnapToTicks(true);
        
        add(difficulty);
        add(start);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if(obj == audiButton){
            player = audiImage;
        }
        if(obj == bentlyButton){
            player = bentlyImage;
        }
        if(obj == lamboButton){
            player = lamboImage;
        }
    }
}
