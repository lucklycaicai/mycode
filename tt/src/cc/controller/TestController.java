package cc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {


	@RequestMapping(value="good")
	public String aaa(){
		return "index.html";
		
	}
}
