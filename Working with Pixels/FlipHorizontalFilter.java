/**
 * Filter that flips the image horizontally.
 * @author Richard Dunn, modified Barbara Goldner
 * @version April 2002
 *
 */
public class FlipHorizontalFilter implements Filter
{
    /**
	 * Flip the image horizontally ( over the vertical axis)
	 * 
	 * @param  theImage The image to modify
	 */
    public void filter(PixelImage pi) {
        Pixel[][] data = pi.getData();

        //for each row, swap its contents from left to right
        for (int row = 0; row < pi.getHeight(); row++) {
            for (int col = 0; col < pi.getWidth() / 2; col++) {
                // given a column: i, its pair is column: width() - i - 1
                // e.g. with a width of 10
                // column 0 is paired with column 9
                // column 1 is paired with column 8 etc.
                Pixel temp = data[row][col];
                data[row][col] = data[row][pi.getWidth() - col - 1];
                data[row][pi.getWidth() - col - 1] = temp;
            }
        }
        pi.setData(data);
    }
}
