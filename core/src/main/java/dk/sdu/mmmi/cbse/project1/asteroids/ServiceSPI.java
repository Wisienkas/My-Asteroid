package dk.sdu.mmmi.cbse.project1.asteroids;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

public class ServiceSPI {
	
	@SuppressWarnings("rawtypes")
	private static Map<Class, ServiceLoader> loaderMap = new HashMap<Class, ServiceLoader>();
	
	private ServiceSPI(){}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> locateAll(Class<T> service){
		ServiceLoader<T> loader = loaderMap.get(service);
		
		if(loader == null){
			loader = ServiceLoader.load(service);
			loaderMap.put(service, loader);
		}
		
		List<T> list = new ArrayList<T>();
		
		if(loader != null){
			
			try{
				for(T instance : loader){
					list.add(instance);
				}
			}catch(ServiceConfigurationError sce){
					sce.printStackTrace();
			}
		}
		System.out.println("Found: " + list.size() + " results of implementation for interface: " + service.getName());
		
		return list;
	}
	
}