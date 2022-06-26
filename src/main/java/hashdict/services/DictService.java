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

import hashdict.applications.HashLinked;
import hashdict.applications.HashTableForLinked;
import hashdict.applications.MisspelledWord;

@Service
public class DictService {
	
	HashTableForLinked<String> hashTable = new HashTableForLinked<>();
	MisspelledWord misspelledWord = new MisspelledWord();
	
	@PostConstruct
	public void loadDictOnInitApplication(){
		this.readFileAndCreatePessoas();
	}
	
	public void readFileAndCreatePessoas() {
		try {
			String path = "src/main/resources/assets/pt.dic";
			BufferedReader reader  = new BufferedReader(new FileReader("src/main/resources/assets/pt.dic", Charset.forName("UTF-16")));
			int numberLine = 0;
			System.out.println("Teste");

			for (String line : reader.lines().toList()){
				// Ignorar letras do alfabeto
				if(numberLine > 22) {
					this.hashTable.create(line, line);
				}else {
					numberLine++;
				}
				
			}
			
			reader.close();
			
			
		} catch(IOException err) {
			err.printStackTrace();
		}
		
	}

	public boolean containsTheWord(String word) {
		return this.hashTable.read(word) != null;
	}
	
	public List<String> getSuggetions(String wordToTry) {
		List<String> suggetionsWord = new LinkedList<>();
		List<String> possiblesWords = this.misspelledWord.findSuggetionsToWord(wordToTry);
		
		for (String possibleWord : possiblesWords) {
			if(this.containsTheWord(possibleWord)) {
				suggetionsWord.add(possibleWord);
			}
		}
		return suggetionsWord;
	}
}
