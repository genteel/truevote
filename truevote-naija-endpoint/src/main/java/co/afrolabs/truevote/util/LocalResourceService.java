package co.afrolabs.truevote.util;

import java.util.ResourceBundle;

public  class LocalResourceService {
	public static String getResource(String resourceName){
	return ResourceBundle.getBundle("Language").getString(resourceName);
	}
	public static String getResource(String fileName,String resourceName){
		return  ResourceBundle.getBundle(fileName).getString(resourceName);
	}
}


