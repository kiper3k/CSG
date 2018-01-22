import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PixelBlack {

    public static Color[][] loadPixelsFromImage(File file) throws IOException {

        BufferedImage image = ImageIO.read(file);
        Color[][] colors = new Color[image.getWidth()][image.getHeight()];

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                colors[x][y] = new Color(image.getRGB(x, y));
                System.out.println(" Współrzędne x=" + x+ " Współrzędne y= " +y  );
                Color myBlack = new Color(0, 0, 0);
                 System.out.println("Czarne piksele " + myBlack);
                 
                
            }
          
            
        }

        return colors;
    }
    

//    public static void main(String[] args) throws IOException {
//        Color[][] colors = loadPixelsFromImage(new File("C:\\Users\\abc\\Desktop\\blackandwhite.jpg"));
//       
//    }
  
}
