package ru.avalon.colorPicker;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;


public class Colorpicker extends JFrame implements ChangeListener
{
  final int WIDTH = 600, HEIGHT = 250;
  String hexColor;
  String currentColor;
  JPanel leftPanel, rightPanel;
  JSlider[] sliders = new JSlider[3];
  JSlider redSlider, greenSlider, blueSlider;
  Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
  StringSelection stringSelection;

  // Constructor
  public Colorpicker() {
    this.setTitle("Color Picker");
    this.setSize(WIDTH, HEIGHT);
    this.setMaximumSize(new Dimension(WIDTH+100, HEIGHT+ 100));
    this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    //
    Container pane = this.getContentPane();
    pane.setLayout(new GridLayout(1, 2, 20, 20));

    // Левая часть окна с отображалкой цветов
    leftPanel = new JPanel();
    pane.add(leftPanel);

    // Правая часть окна со слайдерами
    rightPanel = new JPanel();
    rightPanel.setLayout(new GridLayout(3, 2, 10, 10));

    rightPanel.add(new JLabel("Red"));
    redSlider = new JSlider(0, 255);
    rightPanel.add(redSlider);

    rightPanel.add(new JLabel("Green"));
    greenSlider = new JSlider(0, 255);
    rightPanel.add(greenSlider);

    rightPanel.add(new JLabel("Blue"));
    blueSlider = new JSlider(0, 255);
    rightPanel.add(blueSlider);

    sliders[0] = redSlider;
    sliders[1] = greenSlider;
    sliders[2] = blueSlider;
    for (JSlider slider: sliders) {
      slider.setMajorTickSpacing(125);
      slider.setMinorTickSpacing(5);
      slider.setPaintTicks(true);
      slider.setPaintLabels(true);
      slider.addChangeListener(this::stateChanged);
    }

    pane.add(rightPanel);
    redSlider.setValue(127);
  }

  @Override
  public void stateChanged(ChangeEvent e) {
    // меняем надпись
    currentColor = "#";
    String strValue;
    Color color;
    for (JSlider slider: sliders) {
      int value = slider.getValue();
      strValue = Integer.toHexString(value);
      strValue = strValue.length()==2?strValue:"0"+strValue;
      currentColor += strValue;
    }
    color = new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue());
    leftPanel.setBackground(color);
    leftPanel.setToolTipText(currentColor);
    stringSelection = new StringSelection(currentColor);
    clipboard.setContents(stringSelection, null);
  }

}
