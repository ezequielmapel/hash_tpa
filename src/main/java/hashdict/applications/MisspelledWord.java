package hashdict.applications;

import java.util.LinkedList;
import java.util.List;


public class MisspelledWord {
	
	private static final String LETTERS = "abcdefghijklmnopqrstuvwxyzáéíâêôúã";
	private String word;
	private List<String> suggetions = new LinkedList<>();
	private List<String> splittingWord = new LinkedList<>();
	
	private String beginWord;
	private String endWord;
	
	
	public List<String> findSuggetionsToWord(String word) {
		this.word = word;
		this.splittingWord.clear();
		this.suggetions.clear();
		
		this.splittingWord();
		this.manipulateSplittedWords();
	
		System.out.println(suggetions.size());
		System.out.println(suggetions);
		return this.suggetions;
	}
	
	private void splittingWord() {
		for(int indexLetter = 0; indexLetter <= this.word.length(); indexLetter++) {
			String wordBeginTo = this.word.substring(0, indexLetter);
			String subWord = this.word.substring(indexLetter);
			
			this.splittingWord.add(wordBeginTo);
			this.splittingWord.add(subWord);
		}
	}
	
	private void manipulateSplittedWords() {
		int untilTheIndex = this.splittingWord.size() - 1;
		for(int indexWord = 0; indexWord < untilTheIndex; indexWord += 2) {
			this.beginWord = this.splittingWord.get(indexWord);
			this.endWord = this.splittingWord.get(indexWord+1);
			
			this.findWordsByDeletions();
			this.findWordsByTransposes();
			this.findWordsByReplaces();
			this.findWordsByInserts();
			
		}
	}
	
	private void findWordsByDeletions() {
		if(!this.endWord.equals("")) {
			String wordToTry = this.beginWord.concat(this.endWord.substring(1));
			this.addToSuggetions(wordToTry);
		}
	}
	
	private void findWordsByTransposes() {
		if(this.endWord.length() > 1) {
			String wordToTry = beginWord + endWord.charAt(1);
			wordToTry = wordToTry + endWord.charAt(0);
			wordToTry = wordToTry.concat(endWord.substring(2));
			this.addToSuggetions(wordToTry);
		}
	}	
	
	private void findWordsByReplaces() {
		for(char letter : LETTERS.toCharArray()) {
			String wordToTry = this.beginWord + letter;
			if(!this.endWord.equals("")) {
				wordToTry = wordToTry.concat(this.endWord.substring(1));
				this.addToSuggetions(wordToTry);

			}
		}		
	}
	
	private void findWordsByInserts() {
		for(char letter : LETTERS.toCharArray()) {
			String wordToTry = (this.beginWord + letter).concat(this.endWord);
			this.addToSuggetions(wordToTry);
		}
	}
	
	private void addToSuggetions(String word) {
		if(word != null && !this.suggetions.contains(word)) {
			this.suggetions.add(word);
		}
	}
}
