import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;

/**
 * A PhotoShop like application for filtering images
 * 
 * @author Richard Dunn, modified Barbara Goldner
 * @version April 2002
 */
public class SnapShop extends JFrame
{
    FileLoader fl;
    FilterButtons fb;
    ImagePanel ip;
    RenderingDialog rd;

    /**
     * Constructor for objects of class SnapShop
     */
    public SnapShop()
    {
        super("CSC 143 - SnapShop");

        // Create all the necessary components and add them to the frame
        ip = new ImagePanel(this);
        this.getContentPane().add(ip, BorderLayout.CENTER);

        fl = new FileLoader(this);
        this.getContentPane().add(fl, BorderLayout.NORTH);

        fb = new FilterButtons(this);
        this.getContentPane().add(fb, BorderLayout.WEST);

        rd = new RenderingDialog(this);

        SnapShopConfiguration.configure(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    /**
     * Add a filter to the SnapShop GUI.  Creates a button and
     * links it to the filter.
     * @param f The filter to apply
     * @param description English description of the filter
     */
    public void addFilter(Filter f, String description) {
        fb.addFilter(f, description);
    }

   /** 
     * Set the default filename to appear in the box
     * @param filename The filename
     */
    public void setDefaultFilename(String filename) {
        fl.setDefaultFilename(filename);
    }

    /**
     * Open a SnapShop
     */
    public static void main(String[] args) {
        SnapShop s = new SnapShop();
    }

////////////////////////////////////////////////////////////////////
//  EVERYTHING BELOW THIS POINT CAN BE INGORED.  FEEL FREE TO LOOK AT
//  IT IF YOU WISH.  YOU DO NOT NEED TO KNOW THIS IN ORDER TO SOLVE
//  THE GIVEN PROBLEM.  MOST OF THIS MATERIAL WILL BE COVERED IN THE
//  WEEKS AHEAD.
//
//  PROCEED WITH CAUTION...
////////////////////////////////////////////////////////////////////
    /**
     * Displays a wait/dialog frame
     */
    protected void showWaitDialog() {
        rd.pack();
        rd.setVisible(true);
    }

    /**
     * Hides the wait/dialog frame
     */
    protected void hideWaitDialog() {
        rd.setVisible(false);
    }

    /**
     * @returns the ImagePanel
     */
    protected ImagePanel getImagePanel() {
        return ip;
    }

    
    // Private Inner Class
    // Loads a file specified by user
    private class FileLoader extends JPanel implements ActionListener {
        private JTextArea filenameBox;
        private ImagePanel ip;
        private SnapShop s;

       /* create a new FileLoader
        * @param s the SnapShop we're working with
        */
        public FileLoader(SnapShop s) {
            super(new FlowLayout());

            this.s = s;
            this.ip = s.getImagePanel();
            
            add(new JLabel("Enter file name: "));
            
            filenameBox = new JTextArea(1, 50);
            add(filenameBox);

            JButton loadButton = new JButton("Load");
            loadButton.addActionListener(this);
            add(loadButton);
        }

        // what to do when the load button is clicked
        public void actionPerformed(ActionEvent e) {
            String filename = filenameBox.getText();
            try {
                ip.loadImage(filename);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(s,
                    "Could not open file",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }

        public void setDefaultFilename(String filename) {
            filenameBox.setText(filename);
        }
    }

    // Private Inner class
    // Creates buttons for the user specified filters
    // And handles event when user clicks on button
    private class FilterButtons extends JPanel {
        private SnapShop s;
        private ImagePanel ip;

        public FilterButtons(SnapShop s) {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.s = s;
            this.ip = s.getImagePanel();;
        }

        public void addFilter(Filter f, String description) {
            JButton filterButton = new JButton(description);
            filterButton.addActionListener(new FilterButtonListener(this, f));
            add(filterButton);
            s.pack();
        }

        public void applyFilter(Filter f) {
            try {
                ip.applyFilter(f);
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }

        // inner, inner class
        private class FilterButtonListener implements ActionListener {
            private FilterButtons fb;
            private Filter f;

            public FilterButtonListener(FilterButtons fb, Filter f) {
                this.fb = fb;
                this.f = f;
            }

            public void actionPerformed(ActionEvent e) {
                fb.applyFilter(f);
            }
        }                
    }

    // Inner class
    // Location for displaying the PixelImage
    private class ImagePanel extends JPanel {
        private BufferedImage bi;
        private SnapShop s;

        public ImagePanel(SnapShop s) {
            bi = null;
            this.s = s;
        }
         
        public void loadImage(String filename) {             
            Image img = Toolkit.getDefaultToolkit().getImage(filename);
            try {
                MediaTracker tracker = new MediaTracker(this);
                tracker.addImage(img, 0);
                tracker.waitForID(0);
            } catch (Exception e) {}
            int width = img.getWidth(this);
            int height = img.getHeight(this);
            bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D biContext = bi.createGraphics();
            biContext.drawImage(img, 0, 0, null);
            setPreferredSize(new Dimension(bi.getWidth(), bi.getHeight()));
            revalidate();
            s.pack();
            s.repaint();
        }
        
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (bi != null) {
                g.drawImage(bi, 0, 0, this);
            }
        }

        // Here's where the work gets done
        // apply the filter to the PixelImage
        public void applyFilter(Filter f) {
            if (bi == null) {
                return;
            }
            PixelImage newImage = new PixelImage(bi);
            s.showWaitDialog();
            f.filter(newImage);
            s.hideWaitDialog();
            bi = newImage.getImage();
            repaint();
        }
    }

    // Private inner class
    // a wait/dialog box to tell user that something is actually happening
    private class RenderingDialog extends JFrame {
        public RenderingDialog(JFrame parent) {
            super("Please Wait");
            Point p = parent.getLocation();
            setLocation((int)p.getX() + 100, (int)p.getY() + 100);
            this.getContentPane().add(new JLabel("Applying filter, please wait..."), BorderLayout.CENTER);
        }
    }

  
}
