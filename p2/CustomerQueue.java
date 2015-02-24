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
    	this.queueLength = queueLength;
    	this.gui = gui;
    	this.first = 0;
    	this.last = 17;
    	this.queue = new Customer[queueLength];

 
	}
    
    public synchronized void addCustomer(Customer customer){
    	
    	updateLast();
    	queue[last] = customer;
    	gui.fillLoungeChair(last, customer);
    	// If the queue is full, the doorman should sleep until a customer is removed
    	if(queueFull()){
    		//NOTIFY DOORMAN
    		try {
    			gui.println("The queue is full, Norman the doorman has to wait.");
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	// If a new customer is added to an empty queue
    	else if(first==last){
    		notifyAll();
    	}
   	
    }
    
    public synchronized Customer removeCustomer(int pos){
    	if(queue[first] != null) {
    		if (queueFull()){
    			notifyAll();
    		}
    		Customer customer = queue[first];
    		queue[first] = null;
    		gui.emptyLoungeChair(first);
    		updateFirst();
 
    		
    		return customer;
    	}
    	else {
    		try {
				wait();
				gui.println(Globals.navnListe[pos] + " has to wait");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return null;
    	}
    	
    	
    }
    
    private void updateLast(){
    	
    	last = (last +1) % 18;
    }
    
    private void updateFirst(){
    	first = (first +1) % 18;
    }
    
    private boolean queueEmpty() {
    	return first == (last+1)%18 && queue[first] == null;
    }
    
    private boolean queueFull() {
    	return first == (last+1)%18 && queue[first] != null;
    }
    

	// Add more methods as needed
}
