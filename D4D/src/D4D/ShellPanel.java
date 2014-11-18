/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package D4D;

import javax.swing.JPanel;

/**
 *
 * @author Aldrich
 */
public class ShellPanel extends JPanel{
    
    SettingPanel setting;
    
    public ShellPanel(){
        super();
        setting = new SettingPanel();
        //setLayout(null);
        add(setting);
    }
}
