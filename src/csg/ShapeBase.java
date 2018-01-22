import java.util.List;

public class ShapeBase implements Shape
{

    @Override
    public boolean contains(float x, float y) {return false;}

   
    public void visit(Shape shape) {}

    @Override
    public List getShapes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
