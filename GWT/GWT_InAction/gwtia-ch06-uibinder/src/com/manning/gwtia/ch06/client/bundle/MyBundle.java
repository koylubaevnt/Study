package com.manning.gwtia.ch06.client.bundle;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;

public interface MyBundle extends ClientBundle {

	public static final MyBundle INSTANCE = GWT.create(MyBundle.class);
	
	public interface MyCssResource extends CssResource {

		String bold();

		String bigText();

	}
	
	@Source("MyCss.css")
	MyCssResource style();

	@Source("MyLogo.jpg")
	ImageResource logo();

}
