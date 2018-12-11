import java.awt.Color;
import java.awt.Graphics;
/** 
 * This is a class which defines an Oval.
 *@author: Kelvin
 *@version: 2017-11-14
 */
public class Oval extends FillableShape {
    private static int instances;
    /*This constructor method takes 5 arguments. The first four are integers
     *which correspond to x and y coordinate values. The last parameter is 
     *a boolean value for whether the rectangle is filled or not. These arguments
     *are used to set the default values of each instance variable.
     */
    public Oval(int x1, int y1, int x2, int y2, Color color, boolean filled){
        super(x1,y1,x2,y2,color,filled);
        instances++;
    }
    /*A constructor method with no parameters. Sets all instance variables to 0 and the
     *boolean value to false.
     */
    public Oval(){
        super();
        instances++;
    }
    /*This method takes a graphics object as an argument, and uses it to draw a filled
     *or unfilled oval on the surface based on the given coordinates.
     */
    public void draw(Graphics g){
        g.setColor(getColor());
        g.drawOval(getUpperLeftX(),getUpperLeftY(),getWidth(),getHeight());
        if (getFilled()){
            g.fillOval(getUpperLeftX(),getUpperLeftY(),getWidth(),getHeight());
        }
    }

}