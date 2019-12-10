package ru.avalon.calculator;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;
import static java.awt.Cursor.*;


public class Calculator extends JFrame {
    private JPanel buttonsPane = null;
    private JTextField input = null;
    private JButton equalButton = null;
    private Double sum = 0d; // промежуточный результат
    Container pane = null;

    int WIDTH = 400, HEIGHT = 400;

    public Calculator() {
        this.setTitle("Calculator");
        //this.setResizable(false);
        this.setSize(WIDTH, HEIGHT);
        this.setMaximumSize(new Dimension(WIDTH+100, HEIGHT+ 100));
        this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //
        pane = this.getContentPane();
        // pane.setBounds(20, 20, this.getWidth()-5, this.getHeight()-5); // границы основного пейна от края окна
        pane.setLayout(new GridLayout(3, 1, 50, 20)); // 3 rows, 1 column layout

        // INPUT FIELD
        this.input = new JTextField("15");
        input.setEditable(false);
        input.setText("");
        pane.add(this.input);
        input.setText("0");
        Font font = input.getFont();
        font = font.deriveFont(font.getSize() * 3f);
        input.setFont(font);
        input.setHorizontalAlignment(SwingConstants.RIGHT);
        input.setFocusable(false);

        // ******************** Добавляем кнопки *****************************
        this.buttonsPane = new JPanel();
        this.buttonsPane.setLayout(new GridLayout(4, 2, 4, 4)); // 4 cols, 4 rows
        buttonsPane.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        String[] keyValues = {
                "1", "2", "3", "+",
                "4", "5", "6", "-",
                "7", "8", "9", "*",
                "CE", "0", ".", "/"};

        for (String caption: keyValues){
           JButton button = new JButton(caption);
            font = button.getFont();
            font = font.deriveFont(font.getSize() * 1.5f);
            button.setFont(font);

            // add event listener for each button
           if (caption.equals("CE"))//  REset button
               button.addActionListener(new ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                     input.setText("");
                     sum = 0d;
                   }
               });
           else if (caption.equals(".") ) // Decimal point
               button.addActionListener(new ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                       if (!input.getText().contains("."))
                           input.setText(input.getText() + ".");
                   }
               });

           else if (caption.equals("+") || caption.equals("-") || caption.equals("/")|| caption.equals("*")){ // Control buttons

           }
           else // digit buttons
               button.addActionListener(new ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                     input.setText(input.getText() + e.getActionCommand());
                   }
               });

            this.buttonsPane.add(button);
        }

        pane.add(this.buttonsPane); // Добавили вторую панель с кнопками
        //pane.add(new JPanel()); // add filler
        // *************************************************
        //  Добавляем кнопку "Равно"
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(2, 2));

        equalButton = new JButton("=");
        equalButton.setSize(new Dimension(150, 50));
        equalButton.setBorder(new EmptyBorder(10, 0, 10, 0));
        equalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //
            }
        });

        panel.add(this.equalButton, BorderLayout.CENTER);
        pane.add(panel);
        //pane.add(this.equalButton);
        //pane.add(new JPanel()); // add filler

    }
}
