import java.awt.Color;
import java.awt.Graphics;
/**
 * This is a class which defines a fillable shape that extends shape.
 *@author Kelvin
 *@version 2018-04-30
 */
public abstract class FillableShape extends Shape{
    
    private boolean filled;
    
    //This constructor sets all the default values of a fillable shape.
    public FillableShape(int x1, int y1, int x2, int y2, Color color, boolean filled){
        super(x1,y1,x2,y2,color);
        setFilled(filled);
    }
     //This constructor sets all the default values of a shape.
    public FillableShape(){
        this(0,0,0,0,Color.BLACK,false);
    }
    //This method returns a boolean depending whether the shape is filled or not.
    public boolean getFilled(){
        return filled;
    }
    //This method sets the filled instance variable.
    public void setFilled(boolean filled){
        this.filled = filled;
    }
    //This method returns the upper left x coordinate as an int.
    public int getUpperLeftX(){
        return Math.min(getX1(),getX2());
    }
    //This method returns the upper left y coordinate as an int.
    public int getUpperLeftY(){
        return Math.min(getY1(),getY2());
    }
    //This method returns the width of the shape as an int.
    public int getWidth(){
        return Math.abs(getX1()-getX2());
    }
    //This method returns the height of the shape as an int.
    public int getHeight(){
        return Math.abs(getY1()-getY2());
    }
    //abstract method that draws the shape.
    public abstract void draw(Graphics g);
}