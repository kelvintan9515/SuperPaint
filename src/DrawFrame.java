import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JCheckBox;
/**
 * This is a class which defines the frame. It inherits from JFrame.
 *@author Kelvin
 *@version 2018-04-30
 */
public class DrawFrame extends JFrame{
   
    private final String[] SHAPES = {"Line", "Oval", "Rectangle","FreeDraw"};
    private final String[] COLOURS = {"RED", "GREEN", "BLUE"};
    
    private DrawPanel drawPanel = new DrawPanel();
    private JLabel statusLabel = new JLabel();
    private JPanel buttonPanel = new JPanel();
    private JCheckBox filledCheckBox= new JCheckBox("Filled");
    private JButton undoButton = new JButton("Undo");
    private JButton redoButton = new JButton("Redo");
    private JButton clearButton = new JButton("Clear");
    
    private JComboBox<String> shapeComboBox;
    private JComboBox<String> colourComboBox;
    
    /*This constructor sets the JLabel for the draw panel. It creates the listeners for the
     *combo box, button, and checkbox. It then adds all the widgets to the button panel and adds
     *the rest of the components to the frame.
     */
    public DrawFrame(){
        
        super();
        
        drawPanel.setLabel(statusLabel);
        
        shapeComboBox = new JComboBox<>(SHAPES);
        colourComboBox = new JComboBox<>(COLOURS);
        
        ComboListener comboBoxListener = new ComboListener();
        shapeComboBox.addItemListener( comboBoxListener );
        colourComboBox.addItemListener( comboBoxListener );
        
        ActionListener eventListener = new ButtonEventListener();
        undoButton.addActionListener( eventListener );
        redoButton.addActionListener( eventListener );
        clearButton.addActionListener( eventListener );
        
        ItemListener checkBoxListener = new CheckBoxEventListener();
        filledCheckBox.addItemListener( checkBoxListener );
        
        buttonPanel.add(undoButton);
        buttonPanel.add(redoButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(shapeComboBox);
        buttonPanel.add(colourComboBox);
        buttonPanel.add(filledCheckBox);
        
        add(buttonPanel, BorderLayout.NORTH);
        add(statusLabel,BorderLayout.SOUTH);
        add(drawPanel,BorderLayout.CENTER);
        
        
    }
    /*This inner class defines the combo box listener.
     */
    class ComboListener implements ItemListener{
        @Override 
        //When an item is changed from the combo box, it will change the corresponding colour/shape.
        public void itemStateChanged( ItemEvent e ) {
            if (e.getSource() == shapeComboBox){
                if (shapeComboBox.getSelectedItem() == "Oval"){
                    drawPanel.setShape(SHAPES[1]);        
                }
                else if (shapeComboBox.getSelectedItem() == "Rectangle"){
                    drawPanel.setShape(SHAPES[2]); 
                }
                else if (shapeComboBox.getSelectedItem() == "Line"){
                    drawPanel.setShape(SHAPES[0]); 
                }
                else{
                    drawPanel.setShape(SHAPES[3]); 
  
                }
            }
            else{
                if (colourComboBox.getSelectedItem() == "RED"){
                    drawPanel.setColour(Color.RED);
                }
                else if (colourComboBox.getSelectedItem() == "GREEN"){
                    drawPanel.setColour(Color.GREEN);
                }
                else{
                    drawPanel.setColour(Color.BLUE);
                }
            }
        }
    }
    /*This inner class defines the check box listener.
     */
    class CheckBoxEventListener implements ItemListener {
        
        @Override 
        public void itemStateChanged( ItemEvent e ) {
            // When the user clicks the check box, it will change the fill accordingly.
            if ( filledCheckBox.isSelected() ) {
                drawPanel.setFilled(true);
            } 
            else {
                drawPanel.setFilled(false);
            }
        }         
    }
    /*This inner class defines the button listener.
     */
    class ButtonEventListener implements ActionListener {
        //When a button is clicked, an action will be performed according to the button pressed.
        @Override 
        public void actionPerformed( ActionEvent e ) {
            if ( e.getSource() == undoButton ) {
                drawPanel.undo();
            }
            else if ( e.getSource() == redoButton ) {
                drawPanel.redo();
            }
            else{
                drawPanel.clear();
            }
        }
    }
}