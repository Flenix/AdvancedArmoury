package co.uk.silvania.advancedarmoury;

import co.uk.silvania.advancedarmoury.items.assets.ComponentFrontEnd;
import co.uk.silvania.advancedarmoury.items.assets.ComponentReceiver;
import co.uk.silvania.advancedarmoury.items.assets.ComponentStock;
import co.uk.silvania.advancedarmoury.items.assets.ComponentType;

public enum EnumComponentName {
	
	receiver ("receiver"),
	frontend("frontend"),
	stock("stock");
	
	public String folderName;
	
	private EnumComponentName(String s) {
		folderName = s;
	}

	public static EnumComponentName get(String s) {
		for(EnumComponentName e : values()) {
			if (e.folderName.equalsIgnoreCase(s)) {
				return e;
			}
		}
		return null;
	}
	
	public Class<? extends ComponentType> getTypeClass() {
		switch(this) {
		case receiver : return ComponentReceiver.class;
		case frontend : return ComponentFrontEnd.class;
		case stock : return ComponentStock.class;
		default : return ComponentType.class;
		}
	}
	
	public static EnumComponentName getFromObject(Object o) {
		if (o instanceof ComponentReceiver) return receiver;
		if (o instanceof ComponentFrontEnd) return frontend;
		if (o instanceof ComponentStock) return stock;
		return null;
	}
}
