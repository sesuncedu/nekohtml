/* 
 * Copyright Marc Guillemot
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.cyberneko.html.xercesbridge;

import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xni.XMLDocumentHandler;
import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.xni.parser.XMLDocumentFilter;
import org.apache.xerces.xni.parser.XMLDocumentSource;

/**
 * This class allows to transparently handle Xerces methods that have changed among versions.
 *
 * @author Marc Guillemot
 * @version $Id: $Id
 */
public abstract class XercesBridge 
{

	static private final XercesBridge instance = makeInstance();

	/**
	 * The access point for the bridge.
	 *
	 * @return the instance corresponding to the Xerces version being currently used.
	 */
	public static XercesBridge getInstance()
	{
		return instance;
	}
	
    private static XercesBridge makeInstance()
    {
        final String[] classNames = {
            "org.cyberneko.html.xercesbridge.XercesBridge_2_3",
            "org.cyberneko.html.xercesbridge.XercesBridge_2_2",
        };

        for (int i = 0; i != classNames.length; ++i) {
            final String className = classNames[i];
        	final XercesBridge bridge = newInstanceOrNull(className);
            if (bridge != null) {
                return bridge;
            }
        }
        throw new IllegalStateException("Failed to create XercesBridge instance");
    }

	private static XercesBridge newInstanceOrNull(final String className) {
		try {
			return (XercesBridge) Class.forName(className).newInstance();
	    } 
		catch (ClassNotFoundException ex) { }
		catch (SecurityException ex) { } 
		catch (LinkageError ex) { } 
		catch (IllegalArgumentException e) { }
		catch (IllegalAccessException e) { }
		catch (InstantiationException e) { }
		
		return null;
	}
	/**
	 * Default implementation does nothing
	 *
	 * @param namespaceContext a {@link org.apache.xerces.xni.NamespaceContext} object.
	 * @param ns a {@link java.lang.String} object.
	 * @param avalue a {@link java.lang.String} object.
	 */
	public void NamespaceContext_declarePrefix(NamespaceContext namespaceContext, String ns, String avalue) {
		// nothing
	}
	
	/**
	 * Gets the Xerces version used
	 *
	 * @return the version
	 */
	public abstract String getVersion();

	/**
	 * Calls startDocument on the {@link org.apache.xerces.xni.XMLDocumentHandler}.
	 *
	 * @param documentHandler a {@link org.apache.xerces.xni.XMLDocumentHandler} object.
	 * @param locator a {@link org.apache.xerces.xni.XMLLocator} object.
	 * @param encoding a {@link java.lang.String} object.
	 * @param nscontext a {@link org.apache.xerces.xni.NamespaceContext} object.
	 * @param augs a {@link org.apache.xerces.xni.Augmentations} object.
	 */
	public abstract void XMLDocumentHandler_startDocument(XMLDocumentHandler documentHandler, XMLLocator locator,
			String encoding, NamespaceContext nscontext, Augmentations augs);
	
	/**
	 * Calls startPrefixMapping on the {@link org.apache.xerces.xni.XMLDocumentHandler}.
	 *
	 * @param documentHandler a {@link org.apache.xerces.xni.XMLDocumentHandler} object.
	 * @param prefix a {@link java.lang.String} object.
	 * @param uri a {@link java.lang.String} object.
	 * @param augs a {@link org.apache.xerces.xni.Augmentations} object.
	 */
	public void XMLDocumentHandler_startPrefixMapping(
			XMLDocumentHandler documentHandler, String prefix, String uri,
			Augmentations augs) {
		// default does nothing
	}

	/**
	 * Calls endPrefixMapping on the {@link org.apache.xerces.xni.XMLDocumentHandler}.
	 *
	 * @param documentHandler a {@link org.apache.xerces.xni.XMLDocumentHandler} object.
	 * @param prefix a {@link java.lang.String} object.
	 * @param augs a {@link org.apache.xerces.xni.Augmentations} object.
	 */
	public void XMLDocumentHandler_endPrefixMapping(
			XMLDocumentHandler documentHandler, String prefix,
			Augmentations augs) {
		// default does nothing
	}

	/**
	 * Calls setDocumentSource (if available in the Xerces version used) on the {@link org.apache.xerces.xni.parser.XMLDocumentFilter}.
	 * This implementation does nothing.
	 *
	 * @param filter a {@link org.apache.xerces.xni.parser.XMLDocumentFilter} object.
	 * @param lastSource a {@link org.apache.xerces.xni.parser.XMLDocumentSource} object.
	 */
	public void XMLDocumentFilter_setDocumentSource(XMLDocumentFilter filter,
			XMLDocumentSource lastSource)
	{
		// nothing, it didn't exist on old Xerces versions
	}
}
