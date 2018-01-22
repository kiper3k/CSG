package csg;

import java.util.List;

public class ShapeBase implements Shape
{

    public boolean contains(float x, float y) {return false;}

   
    public void visit(Shape shape) {}

    public List getShapes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
