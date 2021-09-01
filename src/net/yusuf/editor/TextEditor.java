/*
 * MIT License
 *
 * Copyright (c) 2021 YusufIsmail
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package net.yusuf.editor;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

public class TextEditor extends JFrame implements ActionListener {
    // Text components
    JTextArea textArea;

    // The frame(screen)
    JFrame frame;

    //Constructor
    TextEditor() {
        frame = new JFrame("Yusuf's text editor");

        try {
            //set metal look and feel
            UIManager.getSystemLookAndFeelClassName();
            // Set theme to ocean
            MetalLookAndFeel.getInactiveSystemTextColor();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Text component
        textArea = new JTextArea();

        //creates a menubar
        JMenuBar mb = new JMenuBar();

        //creates the option file in the menu bar
        JMenu file = new JMenu("File");

        //Creates the menu buttons
        JMenuItem newFile = new JMenuItem("New");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem print = new JMenuItem("Print");


        //Add the action listeners for file
        newFile.addActionListener(this);
        open.addActionListener(this);
        save.addActionListener(this);
        print.addActionListener(this);

        //add them to the menu
        file.add(newFile);
        file.add(open);
        file.add(save);
        file.add(print);

        //creates the option edit in the menu bar
        JMenu edit = new JMenu("Edit");

        //Creates the menu buttons
        JMenuItem cut = new JMenuItem("Cut");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem paste = new JMenuItem("Paste");

        //add the action listeners for edit
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);

        //add them to the menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);

        //creates the option close in the menu bar
        JMenuItem close = new JMenuItem("close");

        close.addActionListener(this);

        //register all the options
        mb.add(file);
        mb.add(edit);
        mb.add(close);

        //this equals JFrame
        this.setJMenuBar(mb);
        this.add(textArea);
        this.setSize(700,700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.show();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String string = e.getActionCommand();


        if (string.equals("Cut")) {
            textArea.cut();
        } else if (string.equals("Copy")) {
            textArea.copy();
        } else if (string.equals("Paste")) {
            textArea.paste();
        } else if (string.equals("Save")) {
            //creates an object of JFileChooser class
            JFileChooser fc = new JFileChooser("f:");

            // Invoke the showsSaveDialog function to show the save dialog
            int r =  fc.showSaveDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {
                //Set the label to the path of the selected directory
                File selectedFile = new File(fc.getSelectedFile().getAbsolutePath());

                try {
                    //Creates a file writer
                    FileWriter fw = new FileWriter(selectedFile, false);

                    //Creates buffered writer to write
                    BufferedWriter bufferedWriter = new BufferedWriter(fw);

                    //write
                    bufferedWriter.write(textArea.getText());

                    bufferedWriter.flush();
                    bufferedWriter.close();

                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(frame, exception.getMessage());
                }
            }

            //If the user candles the operations
            else
                JOptionPane.showMessageDialog(frame, "the user has cancelled the operation");
        } else if (string.equals("Print")) {
            try {
                // prints the file
                textArea.print();
            } catch (PrinterException printerException) {
                printerException.printStackTrace();
            }
        } else if (string.equals("Open")) {
            // Create an object of JFileChooser class
            JFileChooser fc = new JFileChooser("f:");

            // Invoke the showsOpenDialog function to show the save dialog
            int r = fc.showOpenDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {
                // Set the label to the path of the selected directory
                File selectedFile = new File(fc.getSelectedFile().getAbsolutePath());

                try {
                    //String
                    String string1 = "";

                    // File reader
                    FileReader reader = new FileReader(selectedFile);

                    //Buffered reader
                    BufferedReader bufferedReader = new BufferedReader(reader);

                    // Initialise all

                    // Take the input from the file
                    while ((string1 = bufferedReader.readLine()) != null) {
                        string1 = string1 + "\n" + string1;
                    }

                    // Set the text
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(frame, exception.getMessage());
                }
            }
            //if the user channels the operation
            else
                JOptionPane.showMessageDialog(frame, "the user has cancelled the operation");
        }
        else if (string.equals("New")) {
            textArea.setText("");
        } else if (string.equals("Close")) {
            frame.setVisible(false);
        }
    }
}
