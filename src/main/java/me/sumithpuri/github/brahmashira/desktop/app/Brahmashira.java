package me.sumithpuri.github.brahmashira.desktop.app;

import static me.sumithpuri.github.brahmashira.search.attributes.BrahmashiraAttributes.usageMode;

import java.io.IOException;

import org.apache.lucene.queryparser.classic.ParseException;

import me.sumithpuri.github.brahmashira.desktop.search.SimpleDesktopSearch;
import me.sumithpuri.github.brahmashira.search.BrahmashiraSearch;
import me.sumithpuri.github.brahmashira.search.index.BrahmashiraIndexer;

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
public class Brahmashira {
	
	public static void main(String[] args) {
		
		// TODO auto-generated method stub
		usageMode=true;
		
		try {
			
			System.out.println("Copyright (c) 2018-19,	Sumith Kumar Puri");
			System.out.println();
			System.out.println("Project Codename      Brahmashira");
			System.out.println("Project Description   Apache Lucene Demo Code");
			System.out.println("Technical Blog        http://www.techilashots.com");
			System.out.println("Technical Blog Post   https://goo.gl/nxD5VY");
			System.out.println("[Developer Notes]     [01] Use Java Version 9.0+ Compiler");
			System.out.println();
			
			BrahmashiraIndexer indexer = new BrahmashiraIndexer();
			indexer.init();
			
			BrahmashiraSearch brahmashira = new SimpleDesktopSearch();
			System.out.println();
			brahmashira.brahmashiraBySearchTerm("Son of Dyausu");
			System.out.println();
			brahmashira.brahmashiraBySearchTerm("O Far-seeing One");
			System.out.println();
			brahmashira.brahmashiraBySearchTerm("Maintain his Power Eternal");
			System.out.println();
			brahmashira.brahmashiraBySearchTerm("Maker of the Light");
			System.out.println();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (ParseException e) {

			e.printStackTrace();
		}
	}
	
	
}
