package ru.avalon.colorPicker;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

//import javax.swing.border.EmptyBorder;
//import java.awt.event.*;

public class Colorpicker extends JFrame
{
  final int WIDTH = 600, HEIGHT = 250;
  String hexColor;
  // Constructor
  public Colorpicker() {
    this.setTitle("Color Picker");
    //this.setResizable(false);
    this.setSize(WIDTH, HEIGHT);
    this.setMaximumSize(new Dimension(WIDTH+100, HEIGHT+ 100));
    this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    //
    Container pane = this.getContentPane();
    pane.setLayout(new GridLayout(1, 2, 20, 20));

    JPanel leftPanel, rightPanel;
    // Левая часть окна с отображалкой цветов
    leftPanel = new JPanel();
    JLabel label = new JLabel();
    leftPanel.add(label);
    pane.add(leftPanel);

    // Правая часть окна со слайдерами
    rightPanel = new JPanel();
    rightPanel.setLayout(new GridLayout(3, 2, 10, 10));

    rightPanel.add(new JLabel("Red"));
    JSlider redSlider = new JSlider(0, 255);
    //redSlider.
    rightPanel.add(redSlider);

    rightPanel.add(new JLabel("Green"));
    JSlider greenSlider = new JSlider(0, 255);
    rightPanel.add(greenSlider);

    rightPanel.add(new JLabel("Blue"));
    JSlider blueSlider = new JSlider(0, 255);
    rightPanel.add(blueSlider);

    JSlider[] sliders = {redSlider, greenSlider, blueSlider};
    for (JSlider slider: sliders) {
      slider.setMajorTickSpacing(125);
      slider.setMinorTickSpacing(5);
      slider.setPaintTicks(true);
      slider.setPaintLabels(true);

      slider.addChangeListener(new ChangeListener() {
        public void stateChanged(ChangeEvent e) {
          // меняем надпись
          hexColor = "#";
          String strValue;
          for (JSlider slider: sliders) {
            int value = slider.getValue();
            strValue = Integer.toHexString(value);
            strValue = strValue.length()==2?strValue:"0"+strValue;
            hexColor += strValue;
          }
          //int value = ((JSlider)e.getSource()).getValue();
          label.setText(hexColor);
        }
      });
    }

    pane.add(rightPanel);
  }


}
