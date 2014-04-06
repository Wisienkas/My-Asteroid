package dk.sdu.mmmi.cbse.java;

import java.io.File;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class AsteroidsGameActivator implements BundleActivator{
	
	public AsteroidsGameActivator() {
		System.out.println(this.getClass().getName() + " Has been installed");
	}
	
	public void start(BundleContext arg0) throws Exception {
		AsteroidsGameService service = new AsteroidsJava();
		arg0.registerService(AsteroidsGameService.class.getName(), service, null);
		System.out.println("Service registrered: " + AsteroidsGameService.class.getName() 
				+ "\tImplementaion: " + AsteroidsJava.class.getName());
		File file = new File("lol");
	}

	public void stop(BundleContext arg0) throws Exception {
		System.out.println("Stopped Service: " + AsteroidsGameService.class.getName());
	}
	
	protected void finalize() throws Throwable {
		System.out.println(this.getClass().getName() + " Has been unistalled");
		super.finalize();
	}

}
