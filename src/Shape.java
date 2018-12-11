import java.awt.Color;
import java.awt.Graphics;
/**
 * This is an abstract class which defines a shape.
 *@author Kelvin
 *@version 2018-04-30
 */
public abstract class Shape {
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private Color color;
    
    
    //This constructor sets all the default values of a shape.  
    public Shape(int x1, int y1, int x2, int y2, Color color){
        setX1(x1);
        setY1(y1);
        setX2(x2);
        setY2(y2);
        setColor(color);
    }
    //This constructor sets all the default values of a shape. 
    public Shape(){
        this(0,0,0,0,Color.BLACK);
    }
    
    //Takes one argument as an integer. X1 is set to that integer value.
    public void setX1(int newX1){
        if (newX1 < 0){                
            System.err.println("X1 SET TO 0");
            this.x1 = 0;
        }
        else{
            this.x1 = newX1;
        }
    }
    //Takes one argument as an integer. Y1 is set to that integer value.
    public void setY1(int newY1){
        if (newY1 < 0){
            System.err.println("Y1 SET TO 0");
            this.y1 = 0;
        }
        else{
            this.y1 = newY1;
        }
    }
    //Takes one argument as an integer. X2 is set to that integer value.
    public void setX2(int newX2){
        if (newX2 < 0){
            System.err.println("X2 SET TO 0");
            this.x2 = 0;
        }
        else{
            this.x2 = newX2;
        }
    }
    //Takes one argument as an integer. Y2 is set to that integer value.
    public void setY2(int newY2){
        if (newY2 < 0){
            System.err.println("Y2 SET TO 0");
            this.y2 = 0;
        }
        else{
            this.y2 = newY2;
        }
    }
    //returns the instance variable y1(integer).
    public int getY1(){
        return y1;
    }
    //returns the instance variable x1(integer).
    public int getX1(){
        return x1;  
    }
    //returns the instance variable y2(integer).
    public int getY2(){
        return y2;   
    }
    //returns the instance variable x2(integer).
    public int getX2(){
        return x2; 
    }
    //returns the color instance variable(Color).
    public Color getColor(){
        return color;
    }
    //sets the color instance variable.
    public void setColor(Color color){
        this.color = color;
    }
    //abstract method that draws the shape
    public abstract void draw(Graphics g);
}