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

import java.awt.Component;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Aldrich
 */
public class ScorePanel extends JPanel {
    
    JTable table;
    AbstractTableModel tableModel;
    String userName;
    JLabel enterName;
    JTextField nameInput;
    JButton submit;
    Scanner  inputscan, filescan;
    File file;
    BufferedReader in;
    String read;
    int score;
    Object rowData[][] = new Object[3][3];
    String columnNames[] = { "Name", "Score"};

    public ScorePanel(int s) {
        super();
        score = s;
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        nameInput = new JTextField("Enter your name",SwingConstants.CENTER);
        submit = new JButton("Submit");
        
        loadScoresFromFile();
        
        table = new JTable(rowData,columnNames);
        submit.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel highscore = new JLabel("High Scores");
        highscore.setFont(new Font("serif", Font.PLAIN, 24));
        setSize(100,300);
        //add(enterName);
        add(nameInput);
        add(submit);
        add(highscore);
        add(table);
        
    
    }
    
    public void loadScoresFromFile(){
        try {
            //open a bufferedReader to file helloworld.txt
            in = new BufferedReader(new FileReader(D4D.class.getClassLoader()
                              .getResource("highscore.txt").getPath()
                              .replaceAll("%20", " ")));
            
            
                read = in.readLine();
                //while(read != null){
                String[] split = read.split("-");
                for(int x = 0; x < 3; x++){
                    rowData[x][0] = split[0];
                    rowData[x][1] = split[1];
                    }
                //}
            
            
            in.close();
        }
        catch(IOException e){
            System.out.println("There was a problem:" + e);
        }
        
        /*
        if(filescan.hasNextLine()){
            String[] data = filescan.nextLine().split(" ");
            String name = data[0];
            String highscore = data[1];
        }
                */
    }
}
