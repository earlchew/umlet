package com.baselet.gwt.client.copy.umlet.element.experimental;

public interface DrawHandlerInterface {

	void updateLayer(NewGridElement newGridElement);

	void updatePropertyPanel();

	float getZoomFactor();
	
	boolean displaceDrawingByOnePixel();

}