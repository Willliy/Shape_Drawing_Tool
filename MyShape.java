
import java.awt.Color;
import java.awt.Shape;

public class MyShape {
	/**
	 * data fields
	 */
  private Shape shape;
  private Color color;
  
  /**
   * constructor
   * @param s shape of s
   * @param c shape of c
   */
  public MyShape(Shape s, Color c) { 
	  /**
	   * initialize data fields
	   */
     this.shape = s;
     this.color = c;
  }
  
  public Shape getShape() {
     return shape;
  }
  
  public Color getColor() {
     return color;
  }
}
