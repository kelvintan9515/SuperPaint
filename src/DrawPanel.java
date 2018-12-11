import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
/**
 * This is a class which defines the panel that is drawn on. It inherits from JPanel.
 *@author Kelvin
 *@version 2018-04-30
 */
public class DrawPanel extends JPanel {
    
    private LinkedList<Shape> shapes = new LinkedList<>();
    private LinkedList<Shape> undoShapes = new LinkedList<>();
    private LinkedList<Shape> redoShapes = new LinkedList<>();
    
    private final String[] SHAPES = {"Line", "Oval", "Rectangle", "FreeDraw"};
    private final String[] COLOURS = {"RED", "GREEN", "BLUE"};
    
    private Color whichColour = Color.RED;
    private String whichShape = SHAPES[0];
    private boolean isFilled = false;
    private Shape currentShape = null;
    private JLabel statusLabel;
    private int freeDrawX;
    private int freeDrawY;
    
    //This constructor creates a mouse listener object to detect mouse events and sets the panel to white.
    public DrawPanel() {
        super();
        
        // Create and register listener for mouse and mouse motion events
        MouseEventListener drawPanelListener = new MouseEventListener(); 
        addMouseListener( drawPanelListener ); 
        addMouseMotionListener( drawPanelListener );    
        
        setBackground( Color.WHITE );     
    } 
    //This method sets the whichColour instance variable.
    public void setColour(Color colour){
        whichColour = colour;
    }
    //This method sets the whichShape instance variable.
    public void setShape(String shape){
        whichShape = shape;
    }
    //This method sets the isFilled instance variable.
    public void setFilled(boolean filled){
        isFilled = filled;
    }
    //This method sets the statusLabel instance variable.
    public void setLabel(JLabel statusLabel){
        this.statusLabel = statusLabel;
    } 
    /*This method removes the last item from the shapes list and adds it to the undoShapes list. The 
     *redoShapes list also gets a copy of what was removed from the shapes list.
     */
    public void undo(){
        undoShapes.addFirst(shapes.removeLast());
        redoShapes.addFirst(undoShapes.peekFirst());
        repaint();
    }
    //This method adds the first item from the redo list to the end of the shapes list.
    public void redo(){
        shapes.addLast(redoShapes.removeFirst());
        repaint();
    }
    //This method clears all the lists and repaints the canvas
    public void clear(){
        shapes.clear();
        redoShapes.clear();
        undoShapes.clear();
        repaint();
    }
    
    // This method is called automatically by the JVM when the window needs to be (re)drawn.
    @Override
    public void paintComponent( Graphics g ) {
        super.paintComponent( g );
        // Call the draw() method for each shape object in the array
        for ( int i = 0; i < shapes.size(); i++ ) {
            Shape temp = shapes.removeFirst();
            temp.draw(g);
            shapes.addLast(temp);
        }
        
    } 
    /*This inner class defines the mouse listener used in the panel.
     */
    class MouseEventListener extends MouseAdapter {
        //When the mouse is pressed, currentShape is set to the corresponding shape and added to the list.
        @Override
        public void mousePressed( MouseEvent event ) {
            if (event.getButton() == MouseEvent.BUTTON1){
                if (whichShape == SHAPES[0]){
                    currentShape = new Line( event.getX(), event.getY(), event.getX(), event.getY(), Color.RED );
                    shapes.addLast(currentShape);
                }
                else if (whichShape == SHAPES[1]){
                    currentShape = new Oval( event.getX(), event.getY(), event.getX(), event.getY(), Color.RED, isFilled );
                    shapes.addLast(currentShape);
                }
                else if (whichShape == SHAPES[2]){
                    currentShape = new Rectangle( event.getX(), event.getY(), event.getX(), event.getY(), Color.RED, isFilled );
                    shapes.addLast(currentShape);
                }
                else{
                    //Free draw doesn't have a shape, it's composed of many small lines(dots), so the x and y coordinates are kept
                    freeDrawX = event.getX();
                    freeDrawY = event.getY();
                    
                }
                // Tell JVM to call paintComponent( g )
                repaint();
            }
        } 
        // Mouse release indicates the new line is finished
        @Override
        public void mouseReleased( MouseEvent event ) {
            if (event.getButton() == MouseEvent.BUTTON1){
                if (whichShape != SHAPES[3]){
                    currentShape.setColor( whichColour );
                }
                // Get ready for the next line to be drawn
                currentShape = null;
            }
            repaint();            
        } 
        // As mouse is dragged, update ending coordinates of currentLine and statusBar
        @Override
        public void mouseDragged( MouseEvent event ) {
            statusLabel.setText( String.format( "Mouse at (%d, %d)", 
                                               event.getX(), event.getY() ) );
            //prevents exception when both left and right click are held down.
            if (SwingUtilities.isLeftMouseButton(event)){
                //If free draw mode is on, new line objects will be added to the shapes list as the mouse is being dragged.
                if (whichShape == SHAPES[3]){
                    shapes.addLast(new Line( freeDrawX, freeDrawY, event.getX(), event.getY(), whichColour));
                    //save new x and y so that the drawing can keep up.
                    freeDrawX = event.getX();
                    freeDrawY = event.getY();
                }
                else{
                    currentShape.setX2( event.getX() );
                    currentShape.setY2( event.getY() );
                }
            }
            repaint();
        } 
        // As mouse is moved, just update the statusBar
        @Override
        public void mouseMoved( MouseEvent event ) {
            statusLabel.setText( String.format( "Mouse at (%d, %d)", 
                                               event.getX(), event.getY() ) );
            
        } 
    } 
} 