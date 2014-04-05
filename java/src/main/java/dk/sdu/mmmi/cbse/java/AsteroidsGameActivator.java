package dk.sdu.mmmi.cbse.java;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class AsteroidsGameActivator implements BundleActivator{
	
	public AsteroidsGameActivator() {
		System.out.println(this.getClass().getName() + " Has been installed");
	}
	
	@Override
	public void start(BundleContext bc) throws Exception {
		AsteroidsGameService service = new AsteroidsJava();
		bc.registerService(AsteroidsGameService.class, service, null);
		System.out.println("Service registrered: " + AsteroidsGameService.class.getName() 
				+ "\tImplementaion: " + AsteroidsJava.class.getName());
	}

	@Override
	public void stop(BundleContext bc) throws Exception {
		System.out.println("Stopped Service: " + AsteroidsGameService.class.getName());
	}
	
	@Override
	protected void finalize() throws Throwable {
		System.out.println(this.getClass().getName() + " Has been unistalled");
		super.finalize();
	}

}
