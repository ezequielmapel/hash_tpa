package hashdict.services;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import hashdict.applications.HashTableForLinked;

@Service
public class DictService {
	
	HashTableForLinked<String> hashTable = new HashTableForLinked<>();
	
	@PostConstruct
	public void loadDictOnInitApplication(){
		this.readFileAndCreatePessoas();
	}
	
	public void readFileAndCreatePessoas() {
		try {
			String path = "src/main/resources/assets/pt.dic";
			BufferedReader reader  = new BufferedReader(new FileReader("src/main/resources/assets/pt.dic", Charset.forName("UTF-16")));
			//new BufferedReader(new InputStreamReader(new FileInputStream(path), Charset.forName("UTF-8")));//
			int numberLine = 0;
			System.out.println("Teste");

			for (String line : reader.lines().toList()){
				
				if(numberLine > 22) {
					this.hashTable.create(line, line);
				}else {
					numberLine++;
				}
				
			}
			
			System.out.println(this.hashTable.getHashs().length);
			System.out.println((String)this.hashTable.read("Ah"));
			
			
			reader.close();
			
			
		} catch(IOException err) {
			err.printStackTrace();
		}
		
	}

	public boolean containsTheWord(String word) {
		return this.hashTable.read(word) != null;
	}
}
