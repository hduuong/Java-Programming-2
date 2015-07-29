/**
 * Filter that detects and highlights edges, all except the edge ones
 * @author Duong Chau
 * @version HomeWork 1
 *
 */
public class Laplacian implements Filter{
  
 /**
  * This transformation detects and highlights edges.
  * 
  * @param  theImage The image to modify
  */
    public void filter(PixelImage pi) {
      Pixel[][] data = pi.getData();
      //create the 3x3 array that has the weight values
      int[][] laplacian = {{-1,-1,-1},{-1,8,-1},{-1,-1,-1}};
      
      //going through every pixels except for the edges
      for (int row = 1; row < pi.getHeight()-1; row++) {
        for (int col = 1; col < pi.getWidth()-1; col++) {
          Pixel newcolor = data[row][col];
          
          //call the method to calculate the sum of the weights 
          newcolor = PixelImage.calculation(data, row, col, laplacian);
          //fix the colors to be within the range of 0 - 255
          if(newcolor.red < 0) {
            newcolor.red = 0;
          } else if(newcolor.red > 255) {
            newcolor.red = 255;
          }
          
          if(newcolor.blue < 0) {
            newcolor.blue = 0;
          } else if(newcolor.blue > 255) {
            newcolor.blue = 255;
          }
          
          if(newcolor.green < 0) {
            newcolor.green = 0;
          } else if(newcolor.green > 255) {
            newcolor.green = 255;
          } 
        }
      }
      pi.setData(data);
    }
}
