/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package D4D;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.util.Hashtable;
import javax.swing.*;

/**
 *
 * @author Aldrich
 */
public class SettingPanel extends JPanel{
    
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
        difficulty = new JSlider(5,15,10);
        
        add(one);
        add(two);
        add(three);
        
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
}
