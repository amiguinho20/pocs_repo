package br.amiguinho.lucenepoc;


import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.phonetic.DoubleMetaphoneFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

/**
 * 
 * @author Amiguinho
 *
 * Fontes: http://www.lucenetutorial.com/lucene-in-5-minutes.html
 *         https://stackoverflow.com/questions/38599692/how-to-implement-a-phonetic-search-using-lucene?rq=1
 *
 */
public class LucenePocMemoria {
	
	public static final String CODIGO = "codigo";
	public static final String DESCRICAO = "descricao";
	
	private Directory index;
	private Analyzer analyzer;
	
	public LucenePocMemoria() throws IOException{
        index = new RAMDirectory();
        analyzer = new Analyzer() {
    	    @Override
    	    protected TokenStreamComponents createComponents(String fieldName) {
    	        Tokenizer tokenizer = new StandardTokenizer();
    	        TokenStream stream = new DoubleMetaphoneFilter(tokenizer, 6, false);
    	        return new TokenStreamComponents(tokenizer, stream);
    	    }
    	};
	}
	
	public void addAtIndex(Item item) throws IOException{
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter w = new IndexWriter(index, config);
        Document doc = new Document();
        doc.add(new StringField(CODIGO, item.getCodigo(), Field.Store.YES));
        doc.add(new TextField(DESCRICAO, item.getDescricao(), Field.Store.YES));
        w.addDocument(doc);
        w.close();
	}
	
	public Set<Item> pesquisarDescricao(String termo) {
		try{
	        Query q = new QueryParser(DESCRICAO, analyzer).parse(termo);
	        int hitsPerPage = 10;
	        IndexReader reader = DirectoryReader.open(index);
	        IndexSearcher searcher = new IndexSearcher(reader);
	        TopDocs docs = searcher.search(q, hitsPerPage);
	        ScoreDoc[] hits = docs.scoreDocs;
	        
	        Set<Item> itens = new TreeSet<Item>();
	        
	        for(int i=0;i<hits.length;++i) {
	            int docId = hits[i].doc;
	            Document doc = searcher.doc(docId);
	            itens.add(new Item(doc.get(CODIGO), doc.get(DESCRICAO)));
	        }
	        reader.close();
	        return itens;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
}