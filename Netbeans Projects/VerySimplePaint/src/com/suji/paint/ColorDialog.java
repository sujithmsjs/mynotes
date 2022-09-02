package com.suji.paint;

/**
 * Dialog Demo Example
 * with an simple color setter
 * ****************/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
public class ColorDialog extends JFrame {
  
  private JButton setBg;
  private JPanel  flowPanel;
  private JPanel  colorPan;
  
  public ColorDialog() {
    setFrame();
    initComponents();
    
  }
  private void setFrame(){
    setTitle("Dialog Demo");
    setSize(400,300);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    
  }
  
  private void initComponents() {
    
    flowPanel = new JPanel();
    setBg = new JButton("Set Back Ground");
    colorPan = new JPanel();
    
    
    
    setBg.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        setColor();
      }
    });
    
    flowPanel.add(setBg);
    getContentPane().add(flowPanel, BorderLayout.PAGE_START);
    getContentPane().add(colorPan, BorderLayout.CENTER);
  }                                                             
  private void setColor(){
    ColorSetter c = new ColorSetter(this,true);
    if(c.showColorSetter()==1)
      colorPan.setBackground(c.getColor());
  }
  
  public static void main (String args[]) {
    new ColorDialog().setVisible(true);
  }         
}

/**
 * ***
 * Color Setter Class
 * ****************/
class ColorSetter extends JDialog {
  
  private JLabel sliderTypeLabel;
  private JRadioButton bri;
  private ButtonGroup buttonGroup1;
  private JButton cancle;
  private JPanel center;
  private JPanel colorPanel;
  private JRadioButton hue;
  private JButton ok;
  private JRadioButton sat;
  private JSlider slider;
  private JPanel southFlow;
  private JSpinner spinner;  
  
  private int userClicked=0;
  private int h=50,s=100,b=100;
  
  
  public ColorSetter(JFrame parent, boolean modal) {
    super(parent, modal);
    setFrame(parent);
    initComponents();
    addEvents();
  }
  
  private void setFrame(JFrame f){
    setTitle("Color Chooser");
    setSize(new Dimension(400, 170));
    setLocationRelativeTo(f);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    
  }
  
  private void initComponents() {
    
    buttonGroup1 = new ButtonGroup();
    southFlow    = new JPanel(new FlowLayout(FlowLayout.TRAILING, 10, 5));
    hue          = new JRadioButton("Hue",true);
    sat          = new JRadioButton("Saturation");
    bri          = new JRadioButton("Brightness");
    ok           = new JButton("Ok");
    cancle       = new JButton("Cancle");
    center       = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 30));
    sliderTypeLabel       = new JLabel("Hue :");
    slider       =  new JSlider();
    spinner      = new JSpinner(new SpinnerNumberModel(10,  0,  100,  1));
    colorPanel   = new JPanel();   
    
    southFlow.setBackground(new Color(204, 204, 204));
    
    buttonGroup1.add(hue);
    buttonGroup1.add(sat);
    buttonGroup1.add(bri);
    
    hue.setContentAreaFilled(false);
    sat.setContentAreaFilled(false);
    bri.setContentAreaFilled(false);
    
    slider.setValue(h);
    spinner.setValue(slider.getValue());
    setColor();
    
    spinner.setPreferredSize(new Dimension(75, 25));
    
    colorPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
    colorPanel.setPreferredSize(new Dimension(50, 50));
    
    
    southFlow.add(hue);
    southFlow.add(sat);
    southFlow.add(bri);
    southFlow.add(ok);
    southFlow.add(cancle);
    
    center.add(sliderTypeLabel);
    center.add(slider);
    center.add(spinner);
    center.add(colorPanel);
    
    getContentPane().add(southFlow, BorderLayout.PAGE_END);
    getContentPane().add(center, BorderLayout.CENTER);
  }  
  
  
  
  private void addEvents(){
    
    hue.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        sliderTypeLabel.setText("Hue :");
        slider.setValue(h);
      }
    });
    
    sat.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        sliderTypeLabel.setText("Sat :");
        slider.setValue(h);
      }
    });
    
    bri.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        sliderTypeLabel.setText("Bri :");
        slider.setValue(h);
      }
    });
    
    ok.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        userClicked = 1;
        dispose();
      }
    });
    
    cancle.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        dispose();
      }
    });
    
    slider.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent evt) {
        sliderStateChanged();
      }
    });
    
    spinner.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent evt) {
        spinnerStateChanged();
      }
    });
  }
  
  private void sliderStateChanged() {                                    
    spinner.setValue(slider.getValue());
    setColor();
  }                                   
  
  private void spinnerStateChanged() {                                     
    slider.setValue((int)spinner.getValue());
    setColor();
  }                                    
  
  private void setColor(){
    
    if(hue.isSelected())
      h = slider.getValue();
    else if(sat.isSelected())
      s = slider.getValue();
    else
      b = slider.getValue();
    colorPanel.setBackground(Color.getHSBColor(h/(float)100,s/(float)100, b/(float)100));
  }
  
  public int showColorSetter(){
    setVisible(true);
    return userClicked;
  }
  
  public Color getColor(){
    return Color.getHSBColor(h/(float)100,s/(float)100, b/(float)100);
  }     
}

