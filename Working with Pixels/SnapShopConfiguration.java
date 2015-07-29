/**
 * A class to configure the SnapShop application
 * 
 * @author Richard Dunn
 * @version March 2002
 */

public class SnapShopConfiguration
{
    /**
     * Method to configure the SnapShop.  Call methods like addFilter
     * and setDefaultFilename here.
     * @param theShop The application to configure
     */

    public static void configure(SnapShop theShop)
    {
        theShop.setDefaultFilename("");
        theShop.addFilter(new FlipHorizontalFilter(), "Flip Horizontal");
        theShop.addFilter(new FlipVerticalFilter(), "Flip Vertical");
        theShop.addFilter(new GrayToneFilter(), "Graytone");
        theShop.addFilter(new Gaussian(), "Gaussian");
        theShop.addFilter(new Laplacian(), "Laplacian");
    }

}

