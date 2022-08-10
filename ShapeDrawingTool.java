/**
 * import utilities
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;

public class ShapeDrawingTool { 
	/**
	 * constants
	 */
  private static final int MAX_SHAPE = 5; // maximum number of shapes that can be drawn
  private static final int WIDTH = 800; // width of GUI window
  private static final int HEIGHT = 600; // height of GUI window
  private static final String[] menuItems = {"Big", "Medium", "Small"};
  private static final String filename = "myDrawingTool.dat";
         
  /**
   * data fields
   */
  private JFrame frame;
  private MyShape[] shapes; // array to hold 5 shapes
  private int shapeIndex; // index of next shape to draw
  private Color color;
  private JComboBox<String> sizeMenu;
  private JRadioButton circle;
  private JRadioButton rectangle;
  
  /**
   * constructor
   */
  public ShapeDrawingTool() {
     // initialize data fields
     shapes = new MyShape[MAX_SHAPE];
     // initially there is no shapes so set them to null
     for(int i=0; i<MAX_SHAPE; i++) {
        shapes[i] = null;
     }
     shapeIndex = 0;
     // set GUI
     createDrawingWindow();
  }
  
  private void createDrawingWindow() {
     // create a JFrame object for window to display
     frame = new JFrame("Shape Drawing Tool");
     // set size of frame
     frame.setSize(WIDTH, HEIGHT);
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     // create a menu bar
     JMenuBar menu = createMenuBar();
     // create selection area
     JPanel selection = createSelectionArea();
     // create drawing area
     JPanel drawing = createDrawingArea();
     // add components to frame
     frame.setJMenuBar(menu);
     frame.add(selection, BorderLayout.NORTH);
     frame.add(drawing, BorderLayout.CENTER);
     frame.setVisible(true);     
  }

  /**
   * private string creatMenuBar
   * @return return menu bar 
   */
  private JMenuBar createMenuBar() {
     // create a new menu bar
     JMenuBar menu = new JMenuBar();
     // create and add file menu
     JMenu file = new JMenu("File");
     menu.add(file);
     // create and add Save and Load menus
     JMenuItem save = new JMenuItem("Save");
     JMenuItem load = new JMenuItem("Load"); 
     file.add(save);
     file.add(load);
     return menu;
  }
  
  /**
   * create a new JPanel for selection area
   * @return return selection
   */
  private JPanel createSelectionArea() {
     JPanel selection = new JPanel();
     /**
      * create and set boarder
      */
     Border title = BorderFactory.createTitledBorder("Selection Area");
     selection.setBorder(title);
     
     /**
      * create radio buttons for shape selection
      */
     circle = new JRadioButton("circle");
     rectangle = new JRadioButton("rectangle");
     ButtonGroup shapeType = new ButtonGroup();
     shapeType.add(circle);
     shapeType.add(rectangle);
     circle.setSelected(true); // default selection
     JLabel shapeText = new JLabel("Shape");
     JPanel shapePanel = new JPanel();
     shapePanel.add(shapeText);
     shapePanel.add(circle);
     shapePanel.add(rectangle);
     
     /**
      * create Labels for color selection
      */
     JLabel colorText = new JLabel("Color");
     JLabel red = new JLabel("RED");
     red.setOpaque(true);
     red.setBackground(Color.RED);
     red.setBorder(BorderFactory.createLineBorder(Color.BLACK));
     JLabel yellow = new JLabel("YELLOW");
     yellow.setOpaque(true);
     yellow.setBackground(Color.YELLOW);
     yellow.setBorder(BorderFactory.createLineBorder(Color.BLACK));
     JLabel blue = new JLabel("BLUE");
     blue.setOpaque(true);
     blue.setBackground(Color.BLUE);
     blue.setBorder(BorderFactory.createLineBorder(Color.BLACK));
     JPanel colorPanel = new JPanel();
     colorPanel.add(colorText);
     colorPanel.add(red);
     colorPanel.add(yellow);
     colorPanel.add(blue);
     color = red.getBackground(); // default selection
     // set action listeners
     red.addMouseListener(new MouseListener() {
        @Override
        public void mousePressed(MouseEvent e) {
           color = red.getBackground();
        }
        public void mouseExited(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseClicked(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
     });
     yellow.addMouseListener(new MouseListener() {
        @Override
        public void mousePressed(MouseEvent e) {
           color = yellow.getBackground();
        }
        public void mouseExited(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseClicked(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
     });
     blue.addMouseListener(new MouseListener() {
        @Override
        public void mousePressed(MouseEvent e) {
           color = blue.getBackground();
        }
        public void mouseExited(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseClicked(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
     });
     
     /**
      * create size menu
      */
     JLabel sizeText = new JLabel("Size");
     sizeMenu = new JComboBox<String>(menuItems);
     JPanel sizePanel = new JPanel();
     sizePanel.add(sizeText);
     sizePanel.add(sizeMenu);
      
     /**
      * add component to selection
      */
     selection.setLayout(new BoxLayout(selection, BoxLayout.Y_AXIS));
     selection.add(shapePanel);
     selection.add(colorPanel);
     selection.add(sizePanel);
     return selection;
  }
  
  /**
   * create a new JPanel for drawing area
   * @return return draw
   */
  private JPanel createDrawingArea() {
     JPanel draw = new JPanel() {
        @Override
        public void paintComponent(Graphics g) {
           Graphics2D g2 = (Graphics2D) g;
           /**
            * draw shapes
            */
           for(int i=0; i<MAX_SHAPE; i++) {
              if(shapes[i] != null) {
                 g2.setColor(shapes[i].getColor());
                 g2.fill(shapes[i].getShape());
              }
           }
        }
     };
     /**
      * create and set boarder
      */
     Border title = BorderFactory.createTitledBorder("Draw Area");
     draw.setBorder(title);
     
     /**
      * set action listener
      */
     draw.addMouseListener(new MouseListener() {
        @Override
        public void mousePressed(MouseEvent e) {
           // get selection data and create new object
           // get sizes
           int shapeWidth;
           int shapeHeight;
           if(sizeMenu.getSelectedItem().equals(menuItems[0])) {
              shapeWidth = WIDTH / 4;
              shapeHeight = HEIGHT / 4;
           }
           else if(sizeMenu.getSelectedItem().equals(menuItems[1])) {
              shapeWidth = WIDTH / 8;
              shapeHeight = HEIGHT / 8;
           }
           else {
              shapeWidth = WIDTH / 12;
              shapeHeight = HEIGHT / 12;
           }
          
           /**
            * set shape
            */
           if(rectangle.isSelected()) {
              shapes[shapeIndex] = new MyShape(new Rectangle(e.getX(), e.getY(), shapeWidth, shapeHeight), color);
           }
           else {
              shapes[shapeIndex] = new MyShape(new Ellipse2D.Double(e.getX(), e.getY(), shapeWidth, shapeHeight), color);  
           }
          
           /**
            * update index
            */
           shapeIndex++;
           if(shapeIndex >= MAX_SHAPE) {
              shapeIndex = shapeIndex - MAX_SHAPE;
           }
           frame.repaint();
        }
        public void mouseExited(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseClicked(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
     }); 
     return draw;
  }
}
