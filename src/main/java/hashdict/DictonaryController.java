package hashdict;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hashdict.services.DictService;

@RestController
@RequestMapping("api/dictionary")
public class DictonaryController {
	
	@Autowired
	private DictService dictService;

	@PostMapping("check-text")
	public List<String> getCheckedWords(@RequestBody List<String> wordsToCheck) {
		//wordsToCheck.forEach(w -> System.out.println(w));
		List<String> wordsWithProblem = new ArrayList<>();
		for(String word : wordsToCheck) {
			if(!this.dictService.containsTheWord(word)) {
				wordsWithProblem.add(word);
			}
		}
		return wordsWithProblem;
	}
}
