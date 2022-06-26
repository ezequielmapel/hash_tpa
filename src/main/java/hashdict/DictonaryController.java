package hashdict;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hashdict.services.DictService;

@RestController
@RequestMapping("api/dictionary")
public class DictonaryController {
	
	@Autowired
	private DictService dictService;

	@PostMapping("check-text")
	public List<String> getCheckedWords(@RequestBody List<String> wordsToCheck) {
		List<String> wordsWithProblem = new ArrayList<>();		
		
		for(String word : wordsToCheck) {
			if(!this.dictService.containsTheWord(word)) {
				wordsWithProblem.add(word);
			}
		}
		return wordsWithProblem;
	}
	
	@GetMapping("suggetions")
	public List<String> getSuggetions(@RequestParam String key) {
		return this.dictService.getSuggetions(key.trim())
				.stream()
				.limit(10)
				.collect(Collectors.toList());
	}
}
