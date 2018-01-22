/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg;

import java.awt.geom.Ellipse2D;

/**
 *
 * @author kiper
 */
import java.awt.geom.Rectangle2D;/**
 *
 * @author kiper
 */
public class Ellipse extends Ellipse2D.Double{
    
//    Ellipse(){
//        super();
//    }

    Ellipse(double d, double d0, double width, double height) {
        super();
    }

    public boolean contains(float x, float y) {
        return super.contains(x,y);
    }

    @Override
    public double getX() {
        return super.getX();
    }

    @Override
    public double getY() {
        return super.getY();
    }

    @Override
    public double getWidth() {
        return super.getWidth();
    }

    @Override
    public double getHeight() {
        return super.getHeight();
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    public void setFrame(double d, double d1, double d2, double d3) {
        super.setFrame(d, d1, d2, d3);
    }

    @Override
    public Rectangle2D getBounds2D() {
        return super.getBounds2D();
    }
    
    public Ellipse translate(double x, double y){
        return new Ellipse(this.getX()+x, this.getY()+y, this.getWidth(), this.getHeight());
    }
    
}
