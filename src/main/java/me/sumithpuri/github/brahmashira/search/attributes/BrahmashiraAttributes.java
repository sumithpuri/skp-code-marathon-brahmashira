/**
 * 
 */
package me.sumithpuri.github.brahmashira.search.attributes;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

/**
 * MIT License
 *
 * Copyright (c) 2018-19,	Sumith Kumar Puri

 * GitHub URL 			https://github.com/sumithpuri
 * Blog Post URL		http://www.techilashots.com/2016/03/starting-search-with-apache-lucene.html	
 * Blog Short URL		https://goo.gl/nxD5VY
 * Package Prefix 		me.sumithpuri.github.brahmashira
 * Project Codename		brahmashira
 * Contact E-Mail		code@sumithpuri.me
 * Contact WhatsApp		+91 9591497974
 *
 *
 * Permission is hereby  granted,  free  of  charge, to  any person  obtaining a  copy of  this  software and associated 
 * documentation files (the "Software"), to deal in the  Software without  restriction, including without limitation the 
 * rights to use, copy, modify, merge, publish, distribute, sub-license and/or sell copies of the Software and to permit 
 * persons to whom the Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in  all copies or substantial portions of the 
 * Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS  OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE 
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES  OR  OTHER  LIABILITY, WHETHER IN AN ACTION  OF  CONTRACT, TORT OR 
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
public class BrahmashiraAttributes {

	// most preferred is java bean standards: accessors and mutators
	
	// not the most apt usage! false for web, true for standalone
	public static boolean usageMode = false; 
	
	// if usageMode is false, then a servlet context will also exist
	public static ServletContext servletContext = null;
	
	// not the most apt usage!
    public static final Directory ramDirectory = new RAMDirectory();
    
    // not the most apt usage!
    public static List<String> webSearchResults = new ArrayList<String>(); 
}
