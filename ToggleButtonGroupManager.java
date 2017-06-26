package rsd.gui;

import java.util.List;
import java.util.ArrayList;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/** ToggleButtonGroupManager<br>
 * An Object of this class will manage a Collection of JToggleButtons.<br>
 * Only one JToggleButtons is allowed to be selected at a time.<br>
 * If no JToggleButton is selected in from the Collection, 
 * then any JToggleButton can be selected.<br>
 * If a JToggleButton in the Collection is already selected, 
 * then all the other JToggleButton in the Collection are disabled<p>
 * Usage Example:<br>
 * JToggleButton a = new JToggleButton("A");<br>
 * JToggleButton b = new JToggleButton("B");<br>
 * ToggleButtonGroupManager manager = new ToggleButtonGroupManager(a,b);<br>
 * <p>
 * 
 * @author Lance Dooley, Robotic Systems Design
 *
 */
public class ToggleButtonGroupManager implements ActionListener
{
	private List<JToggleButton> btnList = new ArrayList<>();
		
	/**
	 * Default constructor taking no arguments. If this constructor is
	 * used, then user must add JToggleButtons to this object by
	 * calling the addButton method.
	 */
	public ToggleButtonGroupManager() {}
	
	/**
	 * Constructor that takes JToggleButton varargs (Variable Argument).
	 * @param btns vararg of type JToggleButon
	 */
	public ToggleButtonGroupManager(JToggleButton... btns) 
	{
		this();
		
		if( btns != null )
		{
			for(JToggleButton btn: btns )
			{
				if( !btnList.contains(btn) )
				{
					btnList.add(btn);
					btn.addActionListener(this);
				}
			}
		}		
	}
	
	/**
	 * Adds a JToggleButton to this Object's collection.
	 * The order in which the JToggleButtons are added does not
	 * effect functionality or physical placement into a java.awt.Container.
	 * @param btn of type JToggleButton
	 */
	public void addButton(JToggleButton btn)
	{
		if( !btnList.contains(btn) )
		{
			btnList.add(btn);
			btn.addActionListener(this);
		}
	}
	
	/**
	 * Removes the referenced JToggleButton from this Object's
	 * collection.
	 * @param btn of type JToggleButton  
	 */
	public void removeButton(JToggleButton btn)
	{
		if( btnList.contains(btn) )
		{
			btnList.remove(btn);
			btn.removeActionListener(this);
		}
	}
	
	/**
	 * Implementation of ActionListener interface.
	 * @param event of type ActionEvent
	 */
	public void actionPerformed(ActionEvent event )
	{
		try {
			// get reference to the button
			JToggleButton b = (JToggleButton)event.getSource();
			if( b.isSelected() ) {
				disableAllExcept(b);
			} else {
				enableAll();
		}
		
		} catch( Exception ex ) { }
	}
	
	/**
	 * Called from the actionPerfomed method from the JToggleButton
	 * that was clicked. This method will disable every JToggleButton
	 * except the one which was just pressed down.
	 * @param btn
	 */
	private void disableAllExcept(JToggleButton btn)
	{
		for( JToggleButton b: btnList)
		{
			if( btn != b )
				b.setEnabled(false);
		}
	}
	
	/**
	 * Called from the actionPerfomed method from the JToggleButton
	 * that was clicked. 
	 * @param 
	 */
	private void enableAll()
	{
		// loop through the list of buttons and
		// enable all of them
		for( JToggleButton btn: btnList)
			btn.setEnabled(true);
	}

}