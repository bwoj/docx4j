/*
 *  Copyright 2007, Plutext Pty Ltd.
 *   
 *  This file is part of Docx4J.

    Docx4J is free software: you can redistribute it and/or modify
    it under the terms of version 3 of the GNU General Public License 
    as published by the Free Software Foundation.

    Docx4J is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License   
    along with Docx4J.  If not, see <http://www.gnu.org/licenses/>.
    
 */


package org.docx4j.openpackaging.parts;

import org.docx4j.openpackaging.Base;
import org.docx4j.openpackaging.contenttype.ContentType;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.Package;
import org.docx4j.openpackaging.parts.relationships.RelationshipsPart;

import org.dom4j.Document;

import org.apache.log4j.Logger;


/**
 * An abstraction of an Open Packaging Convention (OPC) Part.
 * To instantiate a Part use (or create) an appropriate subclass.
 * When an existing document is being loaded, ContentTypeManager.getPart
 * will instantiate the appropriate subclass. 
 */
public abstract class Part extends Base {
	
	/**
	 * Logger.
	 */
	protected static Logger log = Logger.getLogger(Part.class);

	
	/**
	 * This part's XML contents.  Not guaranteed to be up to date.
	 * Whether it is or not will depend on how the class which extends
	 * Part chooses to treat it.  It may be that the class uses some
	 * other internal representation for its data. 
	 */
	public Document document;
	
	public abstract void setDocument(Document document);
	
	public abstract Document getDocument();
	
	
	protected Package pack;
	
	/** Every part is the target of some relationship,
	 * specified in a RelationshipsPart. Every part can also 
	 * have its own RelationshipsPart - for that, see Base 
	 * (since Package has one as well). 
	 */
	private RelationshipsPart owningRelationshipPart;
		
	public RelationshipsPart getOwningRelationshipPart() {
		return owningRelationshipPart;
	}

	public void setOwningRelationshipPart(
			RelationshipsPart owningRelationshipPart) {
		this.owningRelationshipPart = owningRelationshipPart;
	}
	
	public Part() {
		
	}

	/**
	 * Constructor.
	 * 
	 * @param pack
	 *            Parent package.
	 * @param partName
	 *            The part name, relative to the parent Package root.
	 * @throws InvalidFormatException
	 *             If the specified URI is not valid.
	 */
	public Part(PartName partName)
			throws InvalidFormatException {
		this.partName = partName;
	}
	

	/**
	 * Constructor.
	 * 
	 * @param partName
	 *            The part name, relative to the parent Package root.
	 * @param contents
	 *            The XML Document contents of the part.
	 * @throws InvalidFormatException
	 *             If the specified URI is not valid.
	 */
	public Part(PartName partName, Document document)
			throws InvalidFormatException {
		this.partName = partName;
		this.document = document;
	}
	
	/**
	 * Constructor.
	 * 
	 * @param pack
	 *            Parent package.
	 * @param partName
	 *            The part name, relative to the parent Package root.
	 * @param contentType
	 *            The Multipurpose Internet Mail Extensions (MIME) content type
	 *            of the part's data stream.
	 */
	public Part(PartName partName,
			String contentType) throws InvalidFormatException {
		this(partName);
		this.contentType = new ContentType(contentType);
	}
	
//	public Document getDocument() {
//		return document;
//	}


	public Package getPackage() {
		if (pack==null) {
			log.error("Package field null for this Part!");
		}
		return pack;
	}
	public void setPackage( Package pack) {
		this.pack = pack;
	}


//	public void setDocument(Document document) {
//		this.document = document;
//	}

	@Override
	public boolean setPartShortcut(Part part, String relationshipType) {
		return false;
	}

}