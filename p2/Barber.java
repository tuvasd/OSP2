package p2;
/**
 * This class implements the barber's part of the
 * Barbershop thread synchronization example.
 */
public class Barber extends Thread {
	/**
	 * Creates a new barber.
	 * @param queue		The customer queue.
	 * @param gui		The GUI.
	 * @param pos		The position of this barber's chair
	 */
	
	private CustomerQueue queue;
	private Gui gui; 
	private int pos;

	public Barber(CustomerQueue queue, Gui gui, int pos) { 
		this.queue = queue; 
		this.gui = gui; 
		this.pos = pos;
	}
	
	public void run(){
		while(true){
			Customer customer = queue.removeCustomer(pos);
			while(customer == null){
				customer = queue.removeCustomer(pos);
			}
			gui.println( Globals.navnListe[pos] + " gets customer number: " + customer.getCustomerID());
			System.out.println(customer.getCustomerID());
			gui.fillBarberChair(pos, customer);
			try {
				//gui.println( Globals.navnListe[pos] + " is working<3!");
				sleep(Globals.barberWork);
				gui.emptyBarberChair(pos);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				gui.barberIsSleeping(pos);
				//gui.println( Globals.navnListe[pos] + " is daydreaming, how cute!");
				sleep(Globals.barberSleep);
				gui.barberIsAwake(pos);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	/**
	 * Starts the barber running as a separate thread.
	 */
	public void startThread() {
		this.start();
	}

	/**
	 * Stops the barber thread.
	 */
	public void stopThread() {
		this.stop();
	}

	// Add more methods as needed
}

