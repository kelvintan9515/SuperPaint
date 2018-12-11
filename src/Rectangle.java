import java.awt.Color;
import java.awt.Graphics;
/** 
 * This is a class which defines a rectangle.
 *@author: Kelvin
 *@version: 2017-11-14
 */
public class Rectangle extends FillableShape{
    private static int instances;
    /*This constructor method takes 5 arguments. The first four are integers
     *which correspond to x and y coordinate values. The last parameter is 
     *a boolean value for whether the rectangle is filled or not. These arguments
     *are used to set the default values of each instance variable.
     */
    public Rectangle(int x1, int y1, int x2, int y2,Color color, boolean filled){
        super(x1,y1,x2,y2,color,filled);
        instances++;
    }
    /*A constructor method with no parameters. Sets all instance variables to 0 and the
     *boolean value to false.
     */
    public Rectangle(){
        super();
        instances++;
    }
    /*This method takes a graphics object as an argument, and uses it to draw a filled
     *or unfilled rectangle on the surface based on the given coordinates.
     */
    public void draw(Graphics g){
        g.setColor(getColor());
        g.drawRect(getUpperLeftX(),getUpperLeftY(),getWidth(),getHeight());
        if (getFilled()){
            g.fillRect(getUpperLeftX(),getUpperLeftY(),getWidth(),getHeight());
        }
    }

}