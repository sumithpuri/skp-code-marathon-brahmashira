package me.sumithpuri.github.brahmashira.search.index;

import static me.sumithpuri.github.brahmashira.search.attributes.BrahmashiraAttributes.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;

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
public class BrahmashiraIndexer {

	public void init() throws IOException {

		// demonstration using the standard analzyer
		Analyzer analyzer = new StandardAnalyzer();

		// use ram directory for indexing in this example
		Directory directory = ramDirectory;

		// two objects for index writing - io exception here
		IndexWriterConfig idxWriterConfig = new IndexWriterConfig(analyzer);
		IndexWriter idxWriter = null;

		try {

			idxWriter = new IndexWriter(directory, idxWriterConfig);
		} catch (IOException e) {

			System.out.println("Brahmashira: Fire in the Mountain - Run! Run! Run!");
			e.printStackTrace();
		}

		System.out.println("\nBrahmashira: Done with Initialization...");
		
		index(idxWriter);
	}

	private void index(IndexWriter idxWriter) throws IOException {
		// lucene document - created through text files
		String[] files = getFilesByUsageMode();

		for (String fileStr : files) {
			try {

				// try using joiner from google guava, it is very elegant
				if (usageMode) {

					char x = File.separatorChar;
					fileStr = "." + x + "resources" + x + fileStr;
				} else {

					String realPath = servletContext.getRealPath(File.separator);
					fileStr = realPath + fileStr; // !
				}

				File file = new File(fileStr);
				System.out.println(fileStr);

				String path = file.getCanonicalPath();
				Document document = new Document();

				// the file path is added to the document
				document.add(new Field("path", path, TextField.TYPE_STORED));
				idxWriter.addDocument(document);

				// open a file reader!
				Reader reader = new FileReader(file);

				document.add(new TextField("contents", reader));

				// add the current file as document to the index writer 
				// thereby, to the index
				idxWriter.addDocument(document);
			} catch (IOException e) {

				System.out.println("Brahmashira: Sky has Fallen our Heads!");
				e.printStackTrace();
			}
		}
		idxWriter.close();

		System.out.println("\nBrahmashira: Done with Indexing...");
	}
	
	public String[] getFilesByUsageMode() {

		String[] files = new String[0];

		if (usageMode) {

			char x = File.separatorChar;
			File dir = new File("." + x + "resources" + x);
			System.out.println(dir.getAbsolutePath());
			files = dir.list();
		} else {

			String realPath = servletContext.getRealPath(File.separator);
			Set<String> fileSet = servletContext.getResourcePaths("/WEB-INF/classes/resources/");
			files = (fileSet.toArray(files));
		}
		return files;
	}

}
