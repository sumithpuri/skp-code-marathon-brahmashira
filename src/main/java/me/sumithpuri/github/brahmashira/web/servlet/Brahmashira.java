package me.sumithpuri.github.brahmashira.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.lucene.queryparser.classic.ParseException;

import me.sumithpuri.github.brahmashira.search.BrahmashiraSearch;
import me.sumithpuri.github.brahmashira.search.attributes.BrahmashiraAttributes;
import me.sumithpuri.github.brahmashira.search.index.BrahmashiraIndexer;
import me.sumithpuri.github.brahmashira.web.search.SimpleWebSearch;

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
public class Brahmashira extends HttpServlet {
	
	private BrahmashiraSearch brahmashira = new SimpleWebSearch();
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		
		BrahmashiraAttributes.servletContext=getServletContext();
		try {
			
			BrahmashiraIndexer indexer = new BrahmashiraIndexer();
			indexer.init();
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.append("Brahmashira: Search is Unavailable Due To  " + e.getMessage());
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		
		// TODO Auto-generated method stub
		String searchTerm = req.getParameter("searchTerm");
		
		System.out.append("Brahmashira: Invoking With Search Term - " + searchTerm);
		
		List<String> resultString = null;
		try {
			
			brahmashira.brahmashiraBySearchTerm(searchTerm);
			resultString=BrahmashiraAttributes.webSearchResults;
			
		} catch (ParseException e) {
			
			e.printStackTrace();
			System.out.append("Brahmashira:  Unable to Search Due To   " + e.getMessage());
		}
		
		StringBuffer out = new StringBuffer();
		
		
		
		webFooter(resp, searchTerm, resultString, out);
		return;
	}

	public void webFooter(HttpServletResponse resp, String searchTerm, List<String> resultString, StringBuffer out)
			throws IOException {
		
		int x = resultString.size() ;
		
		out.append("Brahmashira: Fetched - " + x + " Documents Matching Search Term : " + searchTerm);
		out.append("<br/><br/><br/>");
		
		for(String result: resultString){
			out.append("<b>"+result+ "</b>");
			out.append("<br/><br/>");
			
		}
		
		webFunFact(out);
		
		resp.setContentType("text/html");
	    resp.setCharacterEncoding("UTF-8");
	    PrintWriter outP = resp.getWriter();
	    
	    System.out.println(out.toString());
	    System.out.println("------------------------------------------");
	    
	    out=new StringBuffer(StringEscapeUtils.escapeJava(out.toString()));
	    System.out.println(out.toString());
	    
	    outP.write(out.toString());
		outP.flush();
		outP.close();
	}

	public void webFunFact(StringBuffer out) {

		out.append("<br/><br/><br/><center><span style='color:blue'>Fact: "
				+ "Brahmashira is 4x Times the Power of Brahmastra! Ancient Nuclear Weapon - Unused Till Date."
				+ "</span>");
		
		out.append("<br/><span style='color:blue'>Fact: "
				+ "Only Arjuna and Aswathama knew how to Invoke/Use the Brahmashira - It Remained Un-Invoked.."
				+ "</span></center>");
	}
}
