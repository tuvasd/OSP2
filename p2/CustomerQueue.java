package p2;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

/**
 * This class implements a queue of customers as a circular buffer.
 */
public class CustomerQueue {
	/**
	 * Creates a new customer queue.
	 * @param queueLength	The maximum length of the queue.
	 * @param gui			A reference to the GUI interface.
	 */
	private int queueLength;
	private Gui gui;
	private Customer[] queue;
	private int first, last;
	
	
    public CustomerQueue(int queueLength, Gui gui) {
    	queueLength = queueLength;
    	gui = gui;
    	Customer[] queue = new Customer[18];
 
	}
    
    public void addCustomer(Customer customer){
    	last = (last +1) % 18;
    	queue[last] = customer;
    	if((last +1) % 18== first){
    		//NOTIFY DOORMAN
    	}
   	
    }
    
    
    
    

	// Add more methods as needed
}
