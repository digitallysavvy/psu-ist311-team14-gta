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

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import javax.swing.*;

/**
 *
 * @author Aldrich
 */
public class SettingPanel extends JPanel implements ActionListener{
    
    //int difficulty;
    Rectangle car;
    LayoutManager layout;
    JButton one,two,three,start;
    JSlider difficulty;
    ImageIcon car1, car2, car3; 
    
    
    public SettingPanel(){
        super();
        layout = new GridLayout(5,1,10,30);
        setLayout(layout);
        
        one = new JButton("car1");
        two = new JButton("car2");
        three = new JButton("car3");
        start = new JButton("Start");
        start.addActionListener(this);
        difficulty = new JSlider(5,15,10);
        
        add(one);
        add(two);
        add(three);
        
        //Dictionary for JSlider text labels
        Hashtable<Integer, JLabel> sizeTable = new Hashtable<Integer, JLabel>();
        sizeTable.put (5, new JLabel("Easy"));
        sizeTable.put (10, new JLabel("Normal"));
        sizeTable.put (15, new JLabel("Hard"));
        difficulty.setLabelTable (sizeTable);
        
        difficulty.setPaintTicks(true);
        difficulty.setPaintLabels(true);
        difficulty.setMajorTickSpacing(5);
        
        add(difficulty);
        add(start);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj == start){
            //Create Game Panel
        }
        if(obj == one){
            //Set car to image 1
        }
        if(obj == two){
            //Set car to image 2
        }
        if(obj == three){
            //Set car to image 3
        }
    }
}