package org.docx4j.openpackaging.parts.SpreadsheetML;

import javax.xml.bind.JAXBElement;

import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.parts.JaxbXmlPart;
import org.docx4j.openpackaging.parts.PartName;
import org.docx4j.openpackaging.parts.relationships.Namespaces;
import org.xlsx4j.jaxb.Context;
import org.xlsx4j.sml.CTCalcChain;
import org.xlsx4j.sml.CTSst;

public class SharedStrings  extends JaxbSmlPart<JAXBElement<CTSst>> {
	
	public SharedStrings(PartName partName) throws InvalidFormatException {
		super(partName);
		init();
	}

	public SharedStrings() throws InvalidFormatException {
		super(new PartName("/xl/sharedStrings.xml"));
		init();
	}
	
	public void init() {	
				
		// Used if this Part is added to [Content_Types].xml 
		setContentType(new  org.docx4j.openpackaging.contenttype.ContentType( 
				org.docx4j.openpackaging.contenttype.ContentTypes.SPREADSHEETML_SHARED_STRINGS));

		// Used when this Part is added to a rels 
		setRelationshipType(Namespaces.SPREADSHEETML_SHARED_STRINGS);
		
	}

}