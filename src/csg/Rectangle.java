/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg;

import java.awt.geom.Rectangle2D;

/**
 *
 * @author kiper
 */
public class Rectangle extends Rectangle2D.Double{
    
    public Rectangle(){
        super();
    }

    private Rectangle(double d, double d0, double width, double height) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean contains(double x, double y) {
        return super.contains(x,y);
    }

    @Override
    public void setRect(double d, double d1, double d2, double d3) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int outcode(double d, double d1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rectangle2D createIntersection(Rectangle2D rd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rectangle2D createUnion(Rectangle2D rd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
    public Rectangle translate(){
        return new Rectangle(this.getX()+x, this.getY()+y, this.getWidth(), this.getHeight());
    }
    
}
