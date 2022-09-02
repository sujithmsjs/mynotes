package com.suji.generics;

/**
 * Amazing Colors Graphic in Applet
 **/
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Event;
import javax.swing.*;

public class Amazing_Colors extends Applet {
  int x,y;
  int i;
  int dx,dy;
  int c =50;
  boolean click;
  boolean keypressed;
  Color[] cols= new Color[c];
  
  public void init(){
    for(i=0;i<cols.length;i++){
      cols[i] = new Color( Color.HSBtoRGB(i/(float )c,1,1));  
    }
    i=0;
  }
  public boolean mouseDown(Event e,int x,int y){
    this.x=x;
    this.y=y;
    click=true;
    return true;
  }
  
  
  public boolean mouseDrag(Event e,int x,int y){
    dx=x;
    dy=y;
    repaint();
    return true;
  }
  
  
  
  public boolean keyDown(Event e,int key){
    keypressed=true;      
    repaint();
    return true;
  }
  
  public boolean mouseUp(Event e,int x,int y){
    click=false;
    return true;
  }
  
  public void update(Graphics g){
    if(keypressed){
      g.clearRect(0, 0,getSize().width,getSize().width);
      keypressed=false;
    }
    i++;
    if(i>=c)
      i=0;
    g.setColor(cols[i]);
    paint(g);
  }
  
  public void paint(Graphics g){
    setBackground(Color.black);
    if(click){
      g.drawLine(x,y,dx,dy);
    }
  }
}