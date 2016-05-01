package gui;
import java.sql.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

public class ReservationRequest {
	
	  public static void main(String[] args) {
		  new ReservationRequest();
	  }
	  
	  public ReservationRequest(){
	    final Display display = new Display();
	    Shell shell = new Shell(display);
	    shell.setText("Welcome at XY Hotel!");
	    
	    Label request = new Label(shell, SWT.NONE);
	    request.setText("Please enter the requested period of your stay!");
	    GridData data = new GridData(GridData.FILL, 
	            					 GridData.BEGINNING, true, false, 4, 1);
	    request.setLayoutData(data);
	    
	    Label fromLabel = new Label(shell, SWT.NONE);
	    fromLabel.setText("from: ");
	    fromLabel.setToolTipText("enter start of reservation");
	    final Text fromDay = new Text(shell, SWT.LEFT);   
	    final Text fromMonth = new Text(shell, SWT.LEFT);
	    final Text fromYear = new Text(shell, SWT.LEFT);
	    fromYear.setText("2012");
	    
	    Label toLabel = new Label(shell, SWT.NONE);
	    toLabel.setText("to:   ");
	    toLabel.setToolTipText("enter start of reservation");
	    final Text toDay = new Text(shell, SWT.LEFT);
	    final Text toMonth = new Text(shell, SWT.LEFT);
	    final Text toYear = new Text(shell, SWT.LEFT);
	    toYear.setText("2012");
	    
	    Button proceed =  new Button(shell, SWT.PUSH);
	    proceed.setText("proceed");
	   
	    GridLayout layout = new GridLayout(4, false);
	    // set the layout of the shell
	    shell.setLayout(layout);
	    
	    proceed.addSelectionListener(
	    		new SelectionAdapter() {
	      @Override
	      public void widgetSelected(SelectionEvent e) {
		    int d1 = Integer.parseInt(fromDay.getText());
		    int m1 = Integer.parseInt(fromMonth.getText());
		    int y1 = Integer.parseInt(fromYear.getText());
		    
		    // how not to handle warnings
		    @SuppressWarnings(value = { "deprecation" })
		    Date arrival = new Date(y1-1900,m1-1,d1);
		    int d2 = Integer.parseInt(toDay.getText());
		    int m2 = Integer.parseInt(toMonth.getText());
		    int y2 = Integer.parseInt(toYear.getText());

		    @SuppressWarnings(value = { "deprecation" })
		    Date departure = new Date(y2-1900,m2-1,d2);   
		 
	    	
	    	int room = businessLogic.RoomManagement.findRoom(arrival,departure);
	        System.out.println(room);
	        System.out.println("Called!");
	        display.dispose();
	        new BookRoom(room,arrival,departure);        
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
