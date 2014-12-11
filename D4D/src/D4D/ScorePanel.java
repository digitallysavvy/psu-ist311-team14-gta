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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Aldrich
 */
public class ScorePanel extends JPanel implements ActionListener {

    JTable scoresTable;
    AbstractTableModel tableModel;
    String userName;
    JLabel enterName;
    JTextField nameInput;
    JButton submit;
    String read;
    int score;
    Object rowData[][] = new Object[3][3];
    String columnNames[] = {"Name", "Score"};

    HashMap<String, Integer> map = new HashMap();
    ValueComparator bvc = new ValueComparator(map);
    TreeMap<String, Integer> sorted_map = new TreeMap(bvc);

    public ScorePanel(int s) {
        super();
        score = s;
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        nameInput = new JTextField("Enter your name", SwingConstants.CENTER);
        submit = new JButton("Submit");
        submit.addActionListener(this);

        loadScoresFromFile();

        scoresTable = new JTable(rowData, columnNames);
        submit.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel highscore = new JLabel("High Scores");
        highscore.setFont(new Font("serif", Font.PLAIN, 24));
        setSize(100, 300);
        add(nameInput);
        add(submit);
        add(highscore);
        add(scoresTable);

    }

    private void loadScoresFromFile() {

        //Removes previous score and sortScores results
        map.clear();
        sorted_map.clear();

        try {
            BufferedReader in = new BufferedReader(new FileReader(D4D.class.getClassLoader()
                    .getResource("scores/highscore.txt").getPath()
                    .replaceAll("%20", " ")));

            for (String read = in.readLine(); read != null; read = in.readLine()) {
                System.out.println(read);
                String[] split = read.split("-");

                map.put(split[0], Integer.parseInt(split[1]));
            }

            sortScores();
            in.close();
        } catch (IOException e) {
            System.out.println("There was a problem:" + e);
        }

    }

    public void sortScores() {
        sorted_map.putAll(map);
        int x = 0;
        for (Map.Entry<String, Integer> entry : sorted_map.entrySet()) {

            //Displays top 3 scores after sort
            if (x < 3) {
                rowData[x][0] = entry.getKey();
                System.out.println(entry.getKey());
                rowData[x][1] = entry.getValue();
                System.out.println(entry.getValue());
            }
            x++;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == submit) {
            String n = nameInput.getText();
            int s = this.score;
            String text = n + "-" + s;
            writeScoresToFile(text);

            //Updates and re-sorts scores on file
            loadScoresFromFile();
            remove(scoresTable);
            this.validate();
            scoresTable = new JTable(rowData, columnNames);
            add(scoresTable);
            this.validate();
        }
    }

    private void writeScoresToFile(String s) {

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(D4D.class.getClassLoader()
                    .getResource("scores/highscore.txt").getPath()
                    .replaceAll("%20", " "), true));
            System.out.println(D4D.class.getClassLoader().getResource("scores/highscore.txt").getPath());
            out.write(System.lineSeparator() + s); // Newline escape sequence does not work with BufferedWriter...

            out.close();
        } catch (IOException e) {
            System.out.println("There was a problem:" + e);
        }
    }

}
