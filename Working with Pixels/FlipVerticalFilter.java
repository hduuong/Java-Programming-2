/**
 * Filter that flips the image vertically.
 * @author Duong Chau
 * @version HomeWork 1
 *
 */
public class FlipVerticalFilter implements Filter
{
    /**
  * Flip the image horizontally ( over the horizontal axis)
  * 
  * @param  theImage The image to modify
  */
    public void filter(PixelImage pi) {
        Pixel[][] data = pi.getData();

        //for each row, swap its contents from left to right
        for (int col = 0; col < pi.getWidth(); col++) {
            for (int row = 0; row < pi.getHeight() / 2; row++) {
                // given a row: i, its pair is column: height() - i - 1
                // e.g. with a width of 10
                // row 0 is paired with row 9
                // row 1 is paired with row 8 etc.
                Pixel temp = data[row][col];
                data[row][col] = data[pi.getHeight() - row - 1][col];
                data[pi.getHeight() - row - 1][col] = temp;
            }
        }
        pi.setData(data);
    }
}
