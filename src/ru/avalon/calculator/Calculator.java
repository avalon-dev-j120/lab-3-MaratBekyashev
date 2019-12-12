package ru.avalon.calculator;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    private JPanel buttonsPane = null;
    private JTextField input = null;
    private JButton equalButton = null;
    private Double a = 0d; // first operand
    private Double b = 0d; // first operand
    private Double res = 0d;

    Container pane = null;
    final int WIDTH = 400, HEIGHT = 400;

    Double[] buffer = new Double[3];
    String[] sign = new String[1];
    ActionListener signListener;

    public Calculator() {
        buffer[0] = 0d;
        buffer[1] = 0d;
        buffer[2] = 0d;
        sign[0] = "+";

        this.setTitle("Calculator");
        //this.setResizable(false);
        this.setSize(WIDTH, HEIGHT);
        this.setMaximumSize(new Dimension(WIDTH+100, HEIGHT+ 100));
        this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //
        pane = this.getContentPane();
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
        signListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switch (sign[0]) {
                    case ("+"):
                        buffer[0] = buffer[0] + buffer[1];
                        break;
                    case ("-"):
                        buffer[0] = buffer[0] - buffer[1];
                        break;
                    case ("*"):
                        buffer[0] = buffer[0] * buffer[1];
                        break;
                    case ("/"):
                        buffer[0] = buffer[0] / buffer[1];
                        break;
                }
                buffer[2] = 0d;
                buffer[1] = 0d;

                sign[0] = e.getActionCommand();
                if (sign[0] == "=") {
                    buffer[2] = 2d;
                }
                input.setText(Double.toString(buffer[0]));
            }
        };

        for (String caption: keyValues){
           JButton button = new JButton(caption);
            font = button.getFont();
            font = font.deriveFont(font.getSize() * 1.5f);
            button.setFont(font);

           //  CE button
           if (caption.equals("CE"))
             button.addActionListener(this::actionPerformed);
           // Decimal point
           else if (caption.equals("."))
               button.addActionListener(new ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                       if (!input.getText().contains("."))
                           input.setText(input.getText() + ".");
                   }
               });
           // Обработка арифметических операций
           else if (caption.equals("+") || caption.equals("-") || caption.equals("/")|| caption.equals("*")) // Control buttons
               button.addActionListener(signListener);
           else // обработка ЦИФР
               button.addActionListener(new ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                       if (buffer[2] == 0) {
                           input.setText("");
                           buffer[2] = 1d;
                       }
                       if (buffer[2] == 2) {
                           input.setText("");
                           buffer[0] = 0d;
                           buffer[1] = 0d;
                           sign[0] = "+";
                           buffer[2] = 1d;
                       }
                       input.setText(input.getText() + e.getActionCommand());
                       buffer[1] = Double.parseDouble(input.getText());
                   }
               });

            this.buttonsPane.add(button);
        }

        pane.add(this.buttonsPane); // Добавили вторую панель с кнопками
        // *************************************************
        //  Добавляем кнопку "Равно"
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(2, 2));

        equalButton = new JButton("=");
        equalButton.setSize(new Dimension(150, 50));
        equalButton.setBorder(new EmptyBorder(10, 0, 10, 0));
        equalButton.addActionListener(signListener);

        panel.add(this.equalButton, BorderLayout.CENTER);
        pane.add(panel);
        //pane.add(this.equalButton);
        //pane.add(new JPanel()); // add filler

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        input.setText("");
        buffer[0] = 0d;
        buffer[1] = 0d;
        sign[0] = "+";
    }

}
