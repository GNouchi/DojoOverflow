package com.coding.DojoOverflow.Services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.coding.DojoOverflow.Models.Answer;
import com.coding.DojoOverflow.Models.Question;
import com.coding.DojoOverflow.Models.Tag;
import com.coding.DojoOverflow.Repositories.AnswerRepository;
import com.coding.DojoOverflow.Repositories.QuestionRepository;
import com.coding.DojoOverflow.Repositories.TagRepository;

@Service
public class MainService {
	public final QuestionRepository questionRepository;
	public final AnswerRepository answerRepository;
	public final TagRepository tagRepository;

	public MainService(QuestionRepository questionRepository, AnswerRepository answerRepository, TagRepository tagRepository) {
		this.questionRepository = questionRepository;
		this.answerRepository = answerRepository;
		this.tagRepository = tagRepository;
	}
	
// generate tags usually used for create questions only
	public List<Tag> generateTags(String str){
		List<String> temptags =  Arrays.asList(str.split(","));
		List<Tag> retTags = new ArrayList<>();
				System.out.println("initital array is  : "+ temptags);
		for(int i = 0; i < temptags.size();i++) {
            Tag x = new Tag(StringUtils.trimWhitespace(temptags.get(i)));
            retTags.add(x);
            tagRepository.save(x);            
//			retTags.add(tagRepository.save(new Tag(StringUtils.trimWhitespace(temptags.get(i)))));  // function over form?
		}
				System.out.println("Returning : "+ retTags);
		return retTags;		
	}
	
// create question and calls generateTags()	
	public Question createQuestion(String question, String tags) {
		Question newQ= new Question(question, generateTags(tags));
		
		questionRepository.save(newQ);
				System.out.println("created id: "+ newQ.getId() + " "+ newQ);
		return newQ;
	}
	
// find one Question
	public Question findQuestion(Long id) {
		Optional<Question> temp = questionRepository.findById(id);
		if(temp.isPresent()) {
			return temp.get();
		}
		return null;
	}
	
// find all Question
public List<Question> findAllQuestion(){
	return questionRepository.findAll();
}	
// do not need find all answers/tags right now

// create answer
	public void createAnswer(Long id, String answer) {
				System.out.println("received input is : "+ answer);
		Question question = findQuestion(id);
		List<Answer> temp = question.getAnswers();
		Answer newAnswer = new Answer(question, answer);
		temp.add(newAnswer);
		question.setAnswers(temp);
				System.out.println("intermediate logging : " + question.getAnswers());
		answerRepository.save(newAnswer);
		questionRepository.save(question);
				System.out.println("end result : " + temp);
	}
}
