package com.manning.gwtia.ch05.client.dataresource;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;

public class DataExample extends Composite {

	
	public DataExample() {
		// Same Layout using UIBinder
		initWidget(new DataResourceUI());
        //RootPanel.get().add();
/*
		final DataResources res = DataResources.IMPL;
		
		Grid grid = new Grid(4, 3);
		
		grid.setWidget(1, 0, new Label(res.slashdot().getName()));
		grid.setWidget(1, 1, new Label(res.slashdot().getSafeUri().asString()));
		grid.setWidget(1, 2, new Image(res.slashdot().getSafeUri()));
		
		grid.setWidget(2, 0, new Label(res.bigPhoto().getName()));
		grid.setWidget(2, 1, new Label(res.bigPhoto().getSafeUri().asString()));
		grid.setWidget(2, 2, new Anchor(res.bigPhoto().getName(), res.bigPhoto().getSafeUri().asString()));
		
		grid.setWidget(3, 0, new Label(res.document().getName()));
		grid.setWidget(3, 1, new Label(res.document().getSafeUri().asString()));
		grid.setWidget(3, 2, new Button("Load Resource" , new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Window.open(res.document().getSafeUri().asString(), "_blank", "");
				
			}
		}));
		
		initWidget(grid);
*/		
	}
	
}
