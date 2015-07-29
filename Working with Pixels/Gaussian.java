/**
 * Filter that blur the pixels' color, all except the edge ones
 * @author Duong Chau
 * @version HomeWork 1
 *
 */
public class Gaussian implements Filter{
  
 /**
  * This transformation blurs the images
  * 
  * @param  theImage The image to modify
  */
    public void filter(PixelImage pi) {
      Pixel[][] data = pi.getData();
      //create the 3x3 array that has the weight values
      int[][] gaussian = {{1,2,1},{2,4,2},{1,2,1}};
      
      //going through every pixels except for the edges
      for (int row = 1; row < pi.getHeight()-1; row++) {
        for (int col = 1; col < pi.getWidth()-1; col++) {
          Pixel newcolor = data[row][col];
          
          //call the method to calculate the sum of the weight
          newcolor = PixelImage.calculation(data, row, col, gaussian);
          //get the calculated colors from the new pixel
          int red = newcolor.red;
          int green = newcolor.green;
          int blue = newcolor.blue;
          
          //devide the RGB by 16
          red = red/16;
          green = green/16;
          blue = blue/16;
          
          //store the RGB back to the pixel
          newcolor.red = red;
          newcolor.green = green;
          newcolor.blue = blue;
          
        }
      }
      pi.setData(data);
    }
}
