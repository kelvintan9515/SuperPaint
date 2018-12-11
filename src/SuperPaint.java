import javax.swing.JFrame;

/**
 * This main method starts the paint program.
 *@author Kelvin Tan
 *@version 2018-04-30
 */ 
  
class SuperPaint {
    
    public static void main(String[] args) {
        DrawFrame application = new DrawFrame();
        application.setSize( 480, 320 );
        application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        application.setVisible( true );
    }
}