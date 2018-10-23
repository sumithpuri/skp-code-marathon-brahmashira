package me.sumithpuri.github.brahmashira.web.search;

import static me.sumithpuri.github.brahmashira.search.attributes.BrahmashiraAttributes.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;

import me.sumithpuri.github.brahmashira.search.BrahmashiraSearch;

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
public class SimpleWebSearch implements BrahmashiraSearch {

	@Override
	public void brahmashiraBySearchTerm(String searchString) throws IOException, ParseException {


		System.out.println("Brahmashira: Received from User Interface (Search String) - " + searchString);
		Directory directory = ramDirectory;
		
		// open the ram directory and assign to index reader
		IndexReader indexReader = DirectoryReader.open(directory);
		
		// search using the index searcher
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		
		// use standard analyzer
		Analyzer analyzer = new StandardAnalyzer();
		
		// query parser! - types of query parsers come here
		QueryParser queryParser = new QueryParser("contents", analyzer);
		
		// queries next - types of queries for a query parser to parse
		Query query = queryParser.parse(searchString);
		
		// hits to hold all matches - class name is scoredoc - from TopDocs to
		// ScoreDocs
		ScoreDoc[] hits = indexSearcher.search(query, 1000).scoreDocs;
		
		System.out.println("Brahmashira: The Result Set from the Rigveda.");
		
		List<String> searchResult = new ArrayList<String>();
		
		// this is the search result set - yayy!
		for (ScoreDoc hit : hits) {
		
			// convert the found document index to a lucene document object -
			// memory footprint #################
		
			Document hitDocument = indexSearcher.doc(hit.doc);
		
			System.out.println(hitDocument.get("path"));
			
			searchResult.add(hitDocument.get("path"));
		}
		
		// cleanup and close - what is ideal for web applications?
		indexReader.close();
		
		// directory.close();
		webSearchResults =  searchResult;
	}

}
