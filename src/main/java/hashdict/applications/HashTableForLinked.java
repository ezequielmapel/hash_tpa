package hashdict.applications;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class HashTableForLinked <T extends Object> {
	
	public static final int TAMANHO_HASH = 10000031;
	public static final int TAMANHO_RANDOM_PESO = 3000;
	private HashLinked<T>[] hashs = new HashLinked[TAMANHO_HASH];
	private List<Integer> pesos = new ArrayList<>();
	private int correctInsertCount = 0;
	private int colisaoCount = 0;
	public int colisaoAntesMod = 0;
	public List<Integer> colisaoAntes = new ArrayList<>();
	
	public HashTableForLinked() {		
	}
	
	public  <T extends Object> void create(String key, T value) {
		int hashKey = this.getNewHashKeyOrReturnExisting(key);
		if(this.hashs[hashKey] != null)
			this.hashs[hashKey].getRow().add(new HashElement<T>(key, value));
		else
			this.hashs[hashKey] = new HashLinked(new HashElement<T>(key, value));
		
	}
	
	public <T extends Object> T read(String key) {
		int hashKey = this.getNewHashKeyOrReturnExisting(key);
		HashElement<T> hashElement = (HashElement<T>) this.tryGetHashElement(hashKey, key);
		
		if(hashElement != null) {
			return (T) hashElement.getValue();
		}
		return null;
	}
	
	private HashElement<?> tryGetHashElement(int hashKey, String key) {
		HashElement<?> hashElement = null;
		try {
		hashElement = this.hashs[hashKey]
				.getRow()
				.stream()
				.filter(e -> e.getKey().equals(key))
				.collect(Collectors.toList()).get(0);
		} catch (Exception notFound) {
			System.out.println("Não foi possível encontrar essa key");
		}
		return hashElement;
	}
	
	private int getNewHashKeyOrReturnExisting(String key) {
		int value = 0;
		int index = 0;
		
		for (char letter : key.toCharArray()) {
			value += ((int) letter + index); //* this.getNewPesoOrReturnExisting(index);
			index++;
		}
		
		return value % TAMANHO_HASH;
	}
	
	private int getNewPesoOrReturnExisting(int index){
		if(this.pesos.size() > index) {
			return this.pesos.get(index);
		}
		
		int randomNumber = this.generateRandomNumber();
		pesos.add(randomNumber);
		
		return randomNumber;
	}
	
	private int generateRandomNumber() {
		Random random = new Random();
		int randomNumber = random.nextInt(TAMANHO_RANDOM_PESO);
		return randomNumber;
	}
	
	public HashLinked[] getHashs() {
		return this.hashs;
	}
	
	public int getColisaoCount() {
		return this.colisaoCount;
	}
	
	public void percentageInserts() {
		double perc = (((double) this.colisaoCount / this.TAMANHO_HASH))*100;
		System.out.printf("Correto: %d; Colis�o: %d; Porcentagem: %.2f%% %n", this.correctInsertCount, this.colisaoCount, perc);
	}
	
}
