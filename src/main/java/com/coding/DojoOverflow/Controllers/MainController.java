package com.coding.DojoOverflow.Controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coding.DojoOverflow.Models.Answer;
import com.coding.DojoOverflow.Models.Question;
import com.coding.DojoOverflow.Services.MainService;

@Controller
public class MainController {
	private final MainService mainService;
	public MainController(MainService mainService) {this.mainService = mainService;}

//	Routes
	@RequestMapping(value={"/","question"})
	public String redir(){
			return "redirect:/questions";
	}
	@RequestMapping("/questions")
	public String index(Model model) {
			model.addAttribute("questions", mainService.findAllQuestion() );
			return "index";
	}
	
	@RequestMapping("/questions/{id}")
	public String showquestion(@PathVariable("id") Long id  , Model model, @ModelAttribute("ans") Answer answer) {
			model.addAttribute("question", mainService.findQuestion(id));
			return "showquestion";
	}
	
	@RequestMapping("/questions/new")
	public String newquestion(@ModelAttribute("q") Question question) {
			return "newquestion";
	}

// ~~~~~~~~~Operations~~~~~~~~~~~//
	@RequestMapping(value="/questions/new", method=RequestMethod.POST)
	public String createQuestion(@Valid @ModelAttribute("q") Question question, BindingResult result, RedirectAttributes ra, @RequestParam("tags") String tags ) {
				System.out.println("user tag input is  :  "+ tags);
		if(StringUtils.countOccurrencesOf(tags, ",")  > 2 ) {
			ra.addFlashAttribute("tag_error_space", "You can at most have 3 tags");
		}
		if (!tags.equals(tags.toLowerCase())) {
			ra.addFlashAttribute("tag_error_case", "characters should be all lower case ");		
		}
		if(ra.getFlashAttributes().size()>0){
				System.out.println("errors found.. escaping");
				return "redirect:/questions/new";
		}
		 if(result.hasErrors()) {
		 		return "newquestion";
		 }
		Question newQ = mainService.createQuestion(question.getQuestion(), tags);
				System.out.println("~~~~~FINAL RESULT~~~~~:");
				System.out.println("Tags :"+newQ.getTag());
				System.out.println("Question :" +newQ.getQuestion());
				System.out.println("Answers : "+newQ.getAnswers());
				return "redirect:/questions/"+newQ.getId();
		}

	@RequestMapping(value={"/questions/{id}"}, method=RequestMethod.POST)
	public String showquestion( @Valid @ModelAttribute("ans") Answer answer , BindingResult result, Model model, @PathVariable("id") Long id ) {
		if(result.hasErrors()) 
	 		{
		 		System.out.println("errors found, escaping ");
			 	return "showquestion";
	 		}
		mainService.createAnswer(id, answer.getAnswer());
				System.out.println("create hit no errors");
				return "redirect:/questions/{id}";
	}

}
