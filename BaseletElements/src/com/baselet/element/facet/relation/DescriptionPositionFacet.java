package com.baselet.element.facet.relation;

import java.util.HashMap;
import java.util.Map;

import com.baselet.control.geom.Point;
import com.baselet.diagram.draw.DrawHandler;
import com.baselet.diagram.draw.helper.StyleException;
import com.baselet.element.facet.KeyValueFacet;
import com.baselet.element.facet.PropertiesParserState;

public class DescriptionPositionFacet extends KeyValueFacet {

	private static final int MAX_DISP = 200;

	public static final String POS = "pos";

	public static final DescriptionPositionFacet INSTANCE_MESSAGE_START = new DescriptionPositionFacet(LineDescriptionEnum.MESSAGE_START);
	public static final DescriptionPositionFacet INSTANCE_MESSAGE_END = new DescriptionPositionFacet(LineDescriptionEnum.MESSAGE_END);
	public static final DescriptionPositionFacet INSTANCE_ROLE_START = new DescriptionPositionFacet(LineDescriptionEnum.ROLE_START);
	public static final DescriptionPositionFacet INSTANCE_ROLE_END = new DescriptionPositionFacet(LineDescriptionEnum.ROLE_END);

	private final LineDescriptionEnum lineDesc;

	public DescriptionPositionFacet(LineDescriptionEnum lineDesc) {
		super();
		this.lineDesc = lineDesc;
	}

	@Override
	public KeyValue getKeyValue() {
		return new KeyValue(lineDesc.getKey() + POS, false, "-5,7", "comma separated integers as displacement of " + lineDesc + " text (first=horizontal, second=vertical)");
	}

	@Override
	public void handleValue(String value, DrawHandler drawer, PropertiesParserState state) {
		try {
			Map<String, Point> displacements = state.getOrInitFacetResponse(DescriptionPositionFacet.class, new HashMap<String, Point>());
			String[] split = value.split(",");
			int x = Integer.parseInt(split[0]);
			int y = Integer.parseInt(split[1]);
			if (x > MAX_DISP || y > MAX_DISP) {
				throw new StyleException("max allowed displacement value is " + MAX_DISP);
			}
			displacements.put(lineDesc.getKey(), new Point(x, y));
		} catch (Exception e) {
			if (e instanceof StyleException) {
				throw (StyleException) e;
			}
			throw new StyleException("value must be <integer>,<integer>");
		}

	}

}