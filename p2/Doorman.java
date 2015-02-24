package p2;
/**
 * This class implements the doorman's part of the
 * Barbershop thread synchronization example.
 */
public class Doorman extends Thread {
	
	private CustomerQueue queue;

	
	/**
	 * Creates a new doorman.
	 * @param queue		The customer queue.
	 * @param gui		A reference to the GUI interface.
	 */
	public Doorman(CustomerQueue queue, Gui gui) { 
		this.queue = queue;
		
		
	}
	

	@Override
	public void run() {
		while (true){
			Customer customer = new Customer();
			queue.addCustomer(customer);
			try {
				sleep(Globals.doormanSleep);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		
	}


	/**
	 * Starts the doorman running as a separate thread.
	 */
	public void startThread() {
		this.start();
		
	
	}

	/**
	 * Stops the doorman thread.
	 */
	public void stopThread() {
		this.stop();

	}

	// Add more methods as needed
}
