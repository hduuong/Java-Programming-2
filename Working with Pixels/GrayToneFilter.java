/**
 * Filter turns the pixel's color to gray
 * @author Duong Chau
 * @version HomeWork 1
 *
 */
public class GrayToneFilter implements Filter
{
    /**
  * This transformation turns the color of images to gray
  * 
  * @param  theImage The image to modify
  */
    public void filter(PixelImage pi) {
        Pixel[][] data = pi.getData();

        //for each row, swap its contents from left to right
        for (int row = 0; row < pi.getHeight(); row++) {
            for (int col = 0; col < pi.getWidth(); col++) {
                // given a column: i, its pair is column: width() - i - 1
                // e.g. with a width of 10
                // column 0 is paired with column 9
                // column 1 is paired with column 8 etc.
                Pixel current = data[row][col];
                int red = current.red;
                int green = current.green;
                int blue = current.blue;
                if( red > green && red > blue) {
                  current.blue = red;
                  current.green = red;
                }
                if( green > red && green > blue) {
                  current.red = green;
                  current.blue = green;
                }
                if( blue > green && blue > red) {
                  current.red = blue;
                  current.green = blue;
                }
            }
        }
        pi.setData(data);
    }
}
