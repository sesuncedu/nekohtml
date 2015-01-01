/* 
 * Copyright 2004-2008 Andy Clark, Marc Guillemot
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

package org.cyberneko.html;

import org.apache.xerces.xni.Augmentations;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * This class is here to overcome the XNI changes to the
 * {@code Augmentations} interface. In early versions of XNI, the
 * augmentations interface contained a {@code clear()} method to
 * remove all of the items from the augmentations instance. A later
 * version of XNI changed this method to {@code removeAllItems()}.
 * Therefore, this class extends the augmentations interface and
 * explicitly implements both of these methods.
 * <p>
 * <strong>Note:</strong>
 * This code is inspired by performance enhancements submitted by
 * Marc-André Morissette.
 *
 * @author Andy Clark
 * @version $Id: $Id
 */
public class HTMLAugmentations implements Augmentations {

    //
    // Data
    //

    /** Augmentation items. */
    protected final Hashtable fItems = new Hashtable();

    //
    // Public methods
    //
    /**
     * <p>Constructor for HTMLAugmentations.</p>
     */
    public HTMLAugmentations() {
    	// nothing
    }

    /**
     * Copy constructor
     * @param augs the object to copy
     */
    HTMLAugmentations(final Augmentations augs) {
    	for (final Enumeration keys=augs.keys(); keys.hasMoreElements(); ) {
    		final String key = (String) keys.nextElement();
    		Object value = augs.getItem(key);
    		if (value instanceof HTMLScanner.LocationItem) {
    			value = new HTMLScanner.LocationItem((HTMLScanner.LocationItem) value);
    		}
        	fItems.put(key, value);
    	}
    }

    // since Xerces 2.3.0

    /**
     * Removes all of the elements in this augmentations object.
     */
    public void removeAllItems() {
        fItems.clear();
    } // removeAllItems()

    // from Xerces 2.0.0 (beta4) until 2.3.0

    /**
     * Removes all of the elements in this augmentations object.
     */
    public void clear() {
        fItems.clear();
    } // clear()

    //
    // Augmentations methods
    //

    /**
     * {@inheritDoc}
     *
     * Add additional information identified by a key to the Augmentations
     * structure.
     */
    public Object putItem(String key, Object item) {
        return fItems.put(key, item);
    } // putItem(String, Object):Object


    /**
     * {@inheritDoc}
     *
     * Get information identified by a key from the Augmentations structure.
     */
    public Object getItem(String key) {
        return fItems.get(key);
    } // getItem(String):Object
    
    /**
     * {@inheritDoc}
     *
     * Remove additional info from the Augmentations structure
     */
    public Object removeItem(String key) {
        return fItems.remove(key);
    } // removeItem(String):Object

    /**
     * Returns an enumeration of the keys in the Augmentations structure.
     *
     * @return a {@link java.util.Enumeration} object.
     */
    public Enumeration keys() {
        return fItems.keys();
    } // keys():Enumeration

} // class HTMLAugmentations
