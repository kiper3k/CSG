/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.awt.image.BufferedImage;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;

import boofcv.alg.shapes.ellipse.BinaryEllipseDetector;
import georegression.struct.point.Point2D_F64;
import georegression.struct.shapes.Polygon2D_F64;
import org.ddogleg.struct.FastQueue;

/**
 *
 * @author kiper
 */
public class CSG {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        File f = null;
        BufferedImage image = null;
        int width;    //width of the image
        int height;   //height of the image
        
        List<Shape> shapes = new ArrayList<>();
        
        f = new File("resources/input.jpg");
        ShapeReader sr = new ShapeReader(f);
        
        FastQueue<BinaryEllipseDetector.EllipseInfo> foundEllipses;
        List<Polygon2D_F64> foundRectangles;
        
        foundEllipses = sr.ellipseDetector.getFound();
        foundRectangles = sr.rectangleDetector.getPolygons(null, null);
       
        // create java.awt rectangles
        Point2D_F64 p1, p2, p3, p4;
        for (int i=0; i<foundRectangles.size(); i++){
            System.out.println(foundRectangles.get(i).getClass());
                p1 = foundRectangles.get(i).vertexes.get(0);
                p2 = foundRectangles.get(i).vertexes.get(1);
                p3 = foundRectangles.get(i).vertexes.get(2);
                p4 = foundRectangles.get(i).vertexes.get(3);
                
                shapes.add(new Rectangle2D.Double(p2.x, p2.y, p2.distance(p1), p2.distance(p3)));
        }
        
        // create java.awt ellipses
        Point2D_F64 center;
        double a, b, phi;
        for (int i=0; i<foundRectangles.size(); i++){
            System.out.println(foundEllipses.get(i).getClass());
                center = foundEllipses.get(i).ellipse.center;
                a = foundEllipses.get(i).ellipse.a;
                b = foundEllipses.get(i).ellipse.b;
                
                shapes.add(new Ellipse2D.Double(center.x-a, center.y-b, 2*a, 2*b));
        }
        
        for (int i=0; i<shapes.size(); i++){
            System.out.println(shapes.get(i).toString());
        }

        // check for shapes position
        for (int i=0; i<shapes.size(); i++){
            for(int j=i+1; j<shapes.size(); j++){
                if (shapes.get(i).contains(shapes.get(j).getBounds2D())){
                    System.out.println(i+" zawiera "+j);
                } else if (shapes.get(j).contains(shapes.get(i).getBounds2D())){
                    System.out.println(j+" zawiera "+i);
                } else {
                    System.out.println(i+" jest rozłączne z "+j);
                }
            }
        }
    }
    
}
