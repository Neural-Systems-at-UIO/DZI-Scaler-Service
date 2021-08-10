package no.uio.nesys.dziss;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}
	
	@GetMapping("/xxx")
	public String xxx() {
		return "Greetings from XXX";
	}

}