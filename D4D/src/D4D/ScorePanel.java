/*
 * The MIT License
 *
 * Copyright 2014 Aldrich.
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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Aldrich
 */
public class ScorePanel extends JPanel {
    
    JTable table;
    AbstractTableModel tableModel;
    JLabel name, score, date;
    Scanner scan;
    File file;
            
    
    public ScorePanel() {
        super();
        file = new File(getClass().getClassLoader().getResource("highscore.txt").getFile());
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ScorePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        table = new JTable();
    }
    
    public void loadScoresFromFile(){
        if(scan.hasNextLine()){
            String[] data = scan.nextLine().split(" ");
            String name = data[0];
            String highscore = data[1];
            String date = data[2];
        }
    }
}
