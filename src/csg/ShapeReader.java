/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg;

import boofcv.alg.filter.binary.GThresholdImageOps;
import boofcv.alg.filter.binary.ThresholdImageOps;
import boofcv.alg.shapes.ellipse.BinaryEllipseDetector;
import boofcv.alg.shapes.polygon.DetectPolygonBinaryGrayRefine;
import boofcv.factory.shape.ConfigPolygonDetector;
import boofcv.factory.shape.FactoryShapeDetector;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.struct.image.GrayU8;
import georegression.struct.shapes.Polygon2D_F64;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import org.ddogleg.struct.FastQueue;

/**
 *
 * @author kiper
 */
public class ShapeReader {
    
    BinaryEllipseDetector<GrayU8> ellipseDetector;

    ConfigPolygonDetector config = new ConfigPolygonDetector(4,4);
    DetectPolygonBinaryGrayRefine<GrayU8> rectangleDetector;
    

    public ShapeReader(File f) {
        ellipseDetector = FactoryShapeDetector.ellipse(null, GrayU8.class);
        rectangleDetector = FactoryShapeDetector.polygon(config, GrayU8.class);
        BufferedImage image = readImage(f);
        GrayU8 input = ConvertBufferedImage.convertFromSingle(image, null, GrayU8.class);
        GrayU8 binary = new GrayU8(input.width,input.height);
        
        int threshold = GThresholdImageOps.computeOtsu(input, 0, 255);
        ThresholdImageOps.threshold(input, binary, threshold, true);
        
        ellipseDetector.process(input, binary);
//        FastQueue<BinaryEllipseDetector.EllipseInfo> foundEllipses = ellipseDetector.getFound();
        
        rectangleDetector.process(input, binary);
//        List<Polygon2D_F64> foundRectangles = rectangleDetector.getPolygons(null, null);
    }
    
    public BufferedImage readImage(File f){
        BufferedImage image = null;
        int width;    //width of the image
        int height;
        
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
        
        return image;
    }
    
}
