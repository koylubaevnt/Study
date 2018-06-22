/**
 * 
 */
package com.manning.gwtia.ch05.client.dataresource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.DataResource;
import com.google.gwt.resources.client.DataResource.DoNotEmbed;
import com.google.gwt.resources.client.DataResource.MimeType;
import com.google.gwt.resources.client.ImageResource;

/**
 * @author KojlubaevNT
 *
 */
public interface DataResources extends ClientBundle {

	DataResources IMPL = (DataResources) GWT.create(DataResources.class);
	
	@Source("BigPhoto.jpg")
	ImageResource bigPhoto();

	@DoNotEmbed
	@Source("Document.pdf")
	DataResource document();

	@MimeType("image/png")
	@Source("slashdot.png")
	ImageResource slashdot();

}
