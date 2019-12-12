package ru.avalon;

import ru.avalon.calculator.Calculator;
import ru.avalon.colorPicker.Colorpicker;

public class Application {


  public  static void main (String [] args) {
    //Calculator calc = new Calculator();
    //calc.setVisible(true);

    Colorpicker picker = new Colorpicker();
    picker.setVisible(true);
  }
}
