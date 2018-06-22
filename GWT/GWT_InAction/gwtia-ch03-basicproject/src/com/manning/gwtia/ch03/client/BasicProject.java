package com.manning.gwtia.ch03.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SimpleHtmlSanitizer;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ClosingEvent;
import com.google.gwt.user.client.Window.ClosingHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class BasicProject implements EntryPoint, ValueChangeHandler<String> {

	private static final String LOGO_IMAGE_NAME = "gwtia.png";
	
	static final int DECK_HOME = 0;
	static final int DECK_PRODUCTS = 1;
	static final int DECK_CONTACT = 2;
	
	static final String TOKEN_HOME = "Home";
	static final String TOKEN_PRODUCTS = "Products";
	static final String TOKEN_CONTACT = "Contact";
	
	Image logo; 

	FocusPanel feedback;
	PopupPanel searchRequest;
	HTMLPanel homePanel;
	HTMLPanel productsPanel;
	HTMLPanel contactPanel;
	TabLayoutPanel content;
	Button search;
 
	enum Pages {
		HOME(DECK_HOME, TOKEN_HOME),
		PRODUCTS(DECK_PRODUCTS, TOKEN_PRODUCTS),
		CONTACT(DECK_CONTACT, TOKEN_CONTACT);
		
		private int val;
		private String text;
		
		Pages(int val, String text) {
			this.val = val;
			this.text = text;
		}
		
		public int getVal() {
			return val;
		}
		
		public String getText() {
			return text;
		}
	}
	
	public void onModuleLoad() {
		setUpGui();
		startHistoryManagment();
		setUpEventhandling();
	}

	/**
	 * 
	 */
	private void startHistoryManagment() {
		History.addValueChangeHandler(this);
		
		History.fireCurrentHistoryState();
		
		Window.addWindowClosingHandler(new ClosingHandler() {
			
			@Override
			public void onWindowClosing(ClosingEvent event) {
				event.setMessage("Ran out of history.  Now leaving application, is that OK?");
				
			}
		});
	}

	/**
	 * 
	 */
	private void setUpEventhandling() {
		content.addSelectionHandler(new SelectionHandler<Integer>() {
			
			@Override
			public void onSelection(SelectionEvent<Integer> event) {
				Integer tabSelected = event.getSelectedItem();
				
				History.newItem(Pages.values()[tabSelected].getText());
				
			}
		});
		
		search.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				FlowPanel qAnswer;
				final TextBox searchTerm = new TextBox();
				
				if(searchRequest == null) {
					searchRequest = new PopupPanel();
					
					qAnswer = new FlowPanel();
					qAnswer.add(new Label("Search For:"));
					qAnswer.add(searchTerm);
					
					searchTerm.addChangeHandler(new ChangeHandler() {
						
						@Override
						public void onChange(ChangeEvent event) {
							searchRequest.hide();
							Window.alert("If implemented, now we would search for: " + searchTerm.getText());;
						}
					});
					
					searchRequest.add(qAnswer);
					
					searchRequest.setAnimationEnabled(true);
					searchRequest.showRelativeTo(search);
					searchRequest.setAutoHideEnabled(true);
					searchRequest.setAutoHideOnHistoryEventsEnabled(true);
				} else {
					searchTerm.setText("");
					searchRequest.show();
				}
				searchTerm.setFocus(true);
			}
		});
		
		feedback.addMouseOverHandler(new MouseOverHandler() {
			
			@Override
			public void onMouseOver(MouseOverEvent event) {
				feedback.removeStyleName("normal");
				feedback.addStyleName("active");
				RootPanel.getBodyElement().getStyle().setProperty("overflow", "hidden");				
			}
		});
		
		feedback.addMouseOutHandler(new MouseOutHandler() {
			
			@Override
			public void onMouseOut(MouseOutEvent event) {
				feedback.removeStyleName("active");
				feedback.addStyleName("normal");
				RootPanel.getBodyElement().getStyle().setProperty("overflow", "auto");
			}
		});
		
		feedback.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Window.alert("You could provide feedback if this was implemented");
				
			}
		});
	}

	private void setUpGui() {
		buildTabContent();
		wrapExistingSearchButton();
		insertLogo();
		createFeedbackTab();
		styleTabPanelUsingUIObject();
		styleButtonsUsingDOM();
		RootPanel.get().add(feedback);
		RootPanel logoSlot = RootPanel.get("logo");
		if(logoSlot != null) logoSlot.add(logo);
		RootPanel contentSlot = RootPanel.get("content");
		if(contentSlot != null) contentSlot.add(content);
		
	}

	private void styleTabPanelUsingUIObject() {
		homePanel.setHeight("400px");
		productsPanel.setHeight("400px");
		contactPanel.setHeight("400px");
		content.setHeight("420px");
	}

	private void styleButtonsUsingDOM() {
		search.getElement().getStyle().setProperty("backgroundColor", "#ff0000");
		search.getElement().getStyle().setProperty("border", "2px solid");
		search.getElement().getStyle().setOpacity(0.7);
		
	}

	private void createFeedbackTab() {
		feedback = new FocusPanel();
		feedback.setStyleName("feedback");
		feedback.addStyleName("normal");
		
		VerticalPanel text = new VerticalPanel();
		text.add(new Label("Feed"));
		text.add(new Label("Back"));
		
		feedback.add(text);
		
	}

	private void insertLogo() {
		logo = new Image(GWT.getModuleBaseURL() + "../" + LOGO_IMAGE_NAME) {
			
			public void onBrowserEvent(Event event) {
				event.preventDefault();
				
				super.onBrowserEvent(event);
			}
			
		};
	}

	private void buildTabContent() {
		homePanel = new HTMLPanel(getContent(Pages.HOME.getText()));
		productsPanel = new HTMLPanel(getContent(Pages.PRODUCTS.getText()));
		contactPanel = new HTMLPanel(getContent(Pages.CONTACT.getText()));
		
		homePanel.addStyleName("htmlPanel");
		productsPanel.addStyleName("htmlPanel");
		contactPanel.addStyleName("htmlPanel");
		
		content = new TabLayoutPanel(20, Unit.PX);
		
		content.add(homePanel, Pages.HOME.getText());
		content.add(productsPanel, Pages.PRODUCTS.getText());
		content.add(contactPanel, Pages.CONTACT.getText());
		
		content.selectTab(DECK_HOME);
	}
	
	private String getContent(String id) {
		String toReturn = "";
		Element element = DOM.getElementById(id);
		if(element != null) {
			toReturn = DOM.getInnerHTML(element);
			DOM.setInnerText(element, "");
			SafeHtml safeHtml = SimpleHtmlSanitizer.sanitizeHtml(toReturn);
			toReturn = safeHtml.asString();
		} else {
			toReturn = "Unable to find " + id + " content in HTML page";
		}
		return toReturn;
	}

	private void wrapExistingSearchButton() {
		Element element = DOM.getElementById("search");
		if(element != null) {
			search = Button.wrap(element);
		} else {
			GWT.log("The search button is missing in the underlying HTML page, so we can't wrap it...trying to create it instead");
			search = new Button("search");
			RootPanel.get().add(search);
		}
		
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		String page = event.getValue().trim();
		if((page == null) || page.equals(""))
			showHomePage();
		else if(page.equals(Pages.PRODUCTS.getText()))
			showProducts();
		else if(page.equals(Pages.CONTACT.getText()))
			showContact();
		else
			showHomePage();
	}

	private void showContact() {
		content.selectTab(Pages.CONTACT.getVal());
	}

	private void showProducts() {
		content.selectTab(Pages.PRODUCTS.getVal());
	}

	private void showHomePage() {
		content.selectTab(Pages.HOME.getVal());
	}

}
