import java.awt.image.*;

/**
 * Provides access to an image as an array of Pixels
 * 
 * @author Richard Dunn, modified by Donald Chinn, Duong Chau
 * @version HomeWork 1
 */

public class PixelImage
{
    private BufferedImage myImage;
    private int width;
    private int height;
  
   /**
   * Method that calculate the new values of RGB for new picture
   * @param data an array of Pixels
   * @param row the row position of the pixel
   * @param col the colum position of the pixel
   * @param effect the double array that represents the weights of the color
   */ 
  public static Pixel calculation(Pixel[][] data, int row, int col, int[][] effect) {
    Pixel newcolor = data[row][col];
    
    int r = 0;
    int g = 0;
    int b = 0;
    
    //nested loop that go through all 9 pixels correspond to the 3x3 weight
    for (int m = -1; m < 2; m++){
      for (int n = -1; n < 2; n++){
        Pixel temp = data[row + m][col + n];
        int red = temp.red;
        int green = temp.green;
        int blue = temp.blue;
        
        //multiply the color to the weight
        red = red * effect[m+1][n+1];
        green = green * effect[m+1][n+1];
        blue = blue * effect[m+1][n+1];
        
        //add all the values together
        r += red;
        g += green;
        b += blue;
      }
    }
    //store the values to the new pixel
    newcolor.red = r;
    newcolor.green = g;
    newcolor.blue = b;
    
    return newcolor;
  }
  /**
   * Construct a PixelImage from a Java BufferedImage
     * @param bi The image
     */
  public PixelImage(BufferedImage bi)
  {
    // initialise instance variables
    this.myImage = bi;
    this.width = bi.getWidth();
    this.height = bi.getHeight();
  }
  
  /**
   * Return the width of the image
   */
  public int getWidth() {
    return this.width;
  }
  
  /**
   * Return the height of the image
   */
  public int getHeight() {
    return this.height;
  }
  
  /**
   * Return the image's pixel data as an array of Pixels.  
   * @return The array of pixels
   */
  public Pixel[][] getData() {
    
    /* The Raster has origin 0,0 , so the size of the 
     * array is [width][height], where width and height are the 
     * dimensions of the Raster
     */
    Raster r = this.myImage.getRaster();
    Pixel[][] data = new Pixel[r.getHeight()][r.getWidth()];
    int[] samples = new int[3];
    
    // Translates from java image data to Pixel data
    for (int row = 0; row < r.getHeight(); row++) {
      for (int col = 0; col < r.getWidth(); col++) {
        samples = r.getPixel(col, row, samples);
        Pixel ourPixel = new Pixel(samples[0], samples[1], samples[2]);
        data[row][col] = ourPixel;
      }
    }
    return data;
  }
  
  /**
   * Set the image's pixel data from an array.  This array must match
   * that returned by getData() in terms of dimensions. 
   * @param data The array to pull from
   * @throws IllegalArgumentException if the passed in array does not match
   *   the dimensions of the PixelImage or has pixels with invalid values
   */
  
  public void setData(Pixel[][] data) throws IllegalArgumentException {
    int[] pixelValues = new int[3];     // a temporary array to hold r,g,b values
    WritableRaster wr = this.myImage.getRaster();
    
    if (data.length != wr.getHeight()) {
      throw new IllegalArgumentException("Array size does not match");
    } else if (data[0].length != wr.getWidth()) {
      throw new IllegalArgumentException("Array size does not match");
    }
    
    // Translates from an array of Pixel data to java image data
    for (int row = 0; row < wr.getHeight(); row++) {
      for (int col = 0; col < wr.getWidth(); col++) {
        pixelValues[0] = data[row][col].red;
        pixelValues[1] = data[row][col].green;
        pixelValues[2] = data[row][col].blue;
        wr.setPixel(col, row, pixelValues);
      }
    }
  }
  
  /** 
   * IGNORE THIS METHOD -- you won't need it
   */
  public BufferedImage getImage() {
    return this.myImage;
  }
}

