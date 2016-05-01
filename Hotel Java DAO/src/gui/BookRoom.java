package gui;
import java.sql.Date;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import businessLogic.*;

public class BookRoom {
	public BookRoom(final int room, final Date arrival, final Date departure){
	final Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("XY Hotel: Room Booking");
    
    Label explain = new Label(shell, SWT.NONE);
    explain.setText("Please enter your personal data!");
    GridData data = new GridData(GridData.FILL, 
            GridData.BEGINNING, true, false, 2, 1);
    explain.setLayoutData(data);
    
    Label fnameLabel = new Label(shell, SWT.NONE);
    fnameLabel.setText("First name: ");
    final Text firstName = new Text(shell, SWT.LEFT);   
    Label nameLabel = new Label(shell, SWT.NONE);
    nameLabel.setText("Last name: ");
    final Text lastName = new Text(shell, SWT.LEFT);
    Label phoneLabel = new Label(shell, SWT.NONE);
    phoneLabel.setText("Phone Number: ");
    final Text phoneNumber = new Text(shell, SWT.LEFT);
    
    Button reserve =  new Button(shell, SWT.PUSH);
    reserve.setText("reserve room");
   
    GridLayout layout = new GridLayout(2, false);
    // set the layout of the shell
    shell.setLayout(layout);
    
    reserve.addSelectionListener(
    		new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
	    String fn = firstName.getText();
	    String ln = lastName.getText();
	    String pn = phoneNumber.getText();

    	RoomManagement.reserveRoom(room,fn,ln,pn,arrival,departure);
        System.out.println("Called!");
        display.dispose();   
      }
    }); 

    // produce layout
    shell.pack();
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch())
        display.sleep();
    }
    display.dispose();
  } 
}
