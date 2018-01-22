/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg;

import boofcv.alg.filter.binary.GThresholdImageOps;
import boofcv.alg.filter.binary.ThresholdImageOps;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;

import boofcv.alg.shapes.ellipse.BinaryEllipseDetector;
import boofcv.alg.shapes.polygon.DetectPolygonBinaryGrayRefine;
import boofcv.factory.shape.ConfigPolygonDetector;
import boofcv.factory.shape.FactoryShapeDetector;
import boofcv.gui.ListDisplayPanel;
import boofcv.gui.feature.VisualizeShapes;
import boofcv.gui.image.ShowImages;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.struct.image.GrayU8;
import georegression.struct.point.Point2D_F64;
import georegression.struct.shapes.Polygon2D_F64;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
//        shapes.add(new Rectangle2D.Double(3.0, 3.0, 5.0, 2.0));
//        System.out.println(shapes.get(0).contains(0, 0));
//        System.out.println(shapes.get(0).contains(4, 4));
//        shapes.add(new Rectangle2D.Double(2.0, 2.0, 2.0, 4.0));
//        System.out.println(shapes.get(0).intersects((Rectangle2D) shapes.get(1)));
        
        BinaryEllipseDetector<GrayU8> ellipseDetector;
        ellipseDetector = FactoryShapeDetector.ellipse(null, GrayU8.class);
        
        ConfigPolygonDetector config = new ConfigPolygonDetector(4,4);
	DetectPolygonBinaryGrayRefine<GrayU8> rectangleDetector;
        rectangleDetector = FactoryShapeDetector.polygon(config, GrayU8.class);

        //read image
        try{
          f = new File("resources/input.jpg"); //image file path
//          image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
          image = ImageIO.read(f);
          width = image.getWidth();
          height = image.getHeight();
          System.out.println("Reading complete.");
//          System.out.println(image.getHeight() + " " + image.getWidth());
        }catch(IOException e){
          System.out.println("Error: "+e);
        }
        
        GrayU8 input = ConvertBufferedImage.convertFromSingle(image, null, GrayU8.class);
        GrayU8 binary = new GrayU8(input.width,input.height);
        
        int threshold = GThresholdImageOps.computeOtsu(input, 0, 255);
        ThresholdImageOps.threshold(input, binary, threshold, true);
        
        ellipseDetector.process(input, binary);
        FastQueue<BinaryEllipseDetector.EllipseInfo> foundEllipses = ellipseDetector.getFound();
        Graphics2D g2 = image.createGraphics();
        g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.RED);
        for (int i=0; i<foundEllipses.size; i++) {
            VisualizeShapes.drawEllipse(foundEllipses.get(i).ellipse, g2);
        }
        
        rectangleDetector.process(input, binary);
        List<Polygon2D_F64> foundRectangles = rectangleDetector.getPolygons(null, null);
        g2.setColor(Color.BLUE);
        for (int i=0; i<foundRectangles.size(); i++){
            VisualizeShapes.drawPolygon(foundRectangles.get(0), true, g2);
        }
        
//        ListDisplayPanel panel = new ListDisplayPanel();
//        panel.addImage(image,new File("figures").getName());
//        ShowImages.showWindow(panel,"Detected Shapes",true);
        
        width = image.getWidth();
        height = image.getHeight();

        //write image
        try{
          f = new File("resources/output.jpg");  //output file path
//          image = new BufferedImage(width, height);
          ImageIO.write(image, "jpg", f);
          System.out.println("Writing complete.");
        }catch(IOException e){
          System.out.println("Error: "+e);
        }
        
        
        // create java.awt rectangles
        Point2D_F64 p1, p2, p3, p4;
        for (int i=0; i<foundRectangles.size(); i++){
            System.out.println(foundRectangles.get(i).getClass());
                p1 = foundRectangles.get(i).vertexes.get(0);
                p2 = foundRectangles.get(i).vertexes.get(1);
                p3 = foundRectangles.get(i).vertexes.get(2);
                p4 = foundRectangles.get(i).vertexes.get(3);
                System.out.println(p1);
                System.out.println(p2);
                System.out.println(p3);
                System.out.println(p4);
                System.out.println(p1.distance(p2));
                System.out.println(p2.distance(p3));
                
                System.out.println(foundRectangles.get(i).vertexes.get(0).getClass());
                System.out.println(foundRectangles.get(i).vertexes.get(0).y);
                System.out.println(foundRectangles.get(i).getSideLength(i));
                
                shapes.add(new Rectangle2D.Double(p2.x, p2.y, p2.distance(p1), p2.distance(p3)));
        }
    }
    
}
