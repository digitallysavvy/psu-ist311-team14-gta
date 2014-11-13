/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package D4D;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author Aldrich
 */
public class OuterFrame extends JFrame{
    
    ShellPanel mainPanel;
    
    public OuterFrame(){
        super("D4D Game");
        
        mainPanel = new ShellPanel();
        
        // Frame Properties
        getContentPane().setLayout(new BorderLayout());
	getContentPane().add(mainPanel, "Center");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
	setSize (600, 900);
	setVisible(true);
    }
}
