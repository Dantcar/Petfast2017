/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicProgressBarUI;

/**
 *
 * @author Dac
 */
public class JProgressModelo extends BasicProgressBarUI {
public Color cor1;
    JProgressModelo(Color cor) {
       cor1 = cor;
    }
    public void JProgressModelo(Color cor){
        
    }
Rectangle r = new Rectangle();
  @Override
  protected void paintIndeterminate(Graphics g, JComponent c) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
    r = getBox(r);
        
    //g.setColor(progressBar.getForeground());
    g.setColor(cor1);
    g.fillOval(r.x, r.y, r.width, r.height);
  }
    /*
    class MyProgressUI extends BasicProgressBarUI {
  Rectangle r = new Rectangle();
  @Override
  protected void paintIndeterminate(Graphics g, JComponent c) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
    r = getBox(r);
    g.setColor(progressBar.getForeground());
    g.fillOval(r.x, r.y, r.width, r.height);
  }
}
    */  
    
}
