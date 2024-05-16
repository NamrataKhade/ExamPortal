package com.exam.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private QuizService quizService;

	// Post question
	@PostMapping("/")
	public ResponseEntity<Question> add(@RequestBody Question question) {
		return ResponseEntity.ok(this.questionService.addQuestion(question));
	}

	// update question
	@PutMapping("/")
	public ResponseEntity<Question> update(@RequestBody Question question) {
		return ResponseEntity.ok(this.questionService.updateQuestion(question));
	}

	// get All Question using quizId
	@GetMapping("/quiz/{quizId}")
	public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable("quizId") Long quizId) {
//	   Quiz quiz = new Quiz();
//	   quiz.setQuizId(quizId);
//	   Set<Question> questionOfQuiz = this.questionService.getQuestionOfQuiz(quiz);
//	   return ResponseEntity.ok(questionOfQuiz);

		Quiz quiz = this.quizService.getQuiz(quizId);
		Set<Question> questions = quiz.getQuestions();
		List<Question> list= new ArrayList(questions);
		if (list.size() > Integer.parseInt(quiz.getNumberOfQuestion())) {
			list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestion() + 1));
		}
		
		list.forEach((q)->{
			q.setAnswer("");
		});
		Collections.shuffle(list);
		return ResponseEntity.ok(list);

	}

	// get All Question using quizId
	@GetMapping("/quiz/all/{quizId}")
	public ResponseEntity<?> getQuestionsOfQuizAdmin(@PathVariable("quizId") Long quizId) {
		Quiz quiz = new Quiz();
		quiz.setQuizId(quizId);
		Set<Question> questionOfQuiz = this.questionService.getQuestionOfQuiz(quiz);
		return ResponseEntity.ok(questionOfQuiz);

		// return ResponseEntity.ok(list);

	}

	// get single question
	@GetMapping("/{questionId}")
	public Question get(@PathVariable("questionId") Long questionId) {
		return this.questionService.getQuestion(questionId);
	}

	@DeleteMapping("/{questionId}")
	public void delete(@PathVariable("questionId") Long questionId) {
		this.questionService.deleteQuestion(questionId);
	}

	// evaluate quiz
	@PostMapping("/eval-quiz")
	public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions) {

		System.out.println(questions);
		double marksGot = 0;
		int correctAnswers = 0;
		int attempted = 0;

		for (Question q : questions) {
			// System.out.println(q.getGivenAnswer());

			// single question
			// q.getQuestionId();

			Question question = this.questionService.get(q.getQuestionId());
			if (question.getAnswer().equals(q.getGivenAnswer())) {
				// correct
				correctAnswers++;
				double marksSingle = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks()) / questions.size();
				marksGot += marksSingle;

			}
			if (q.getGivenAnswer() != null) {
				attempted++;
			}

		}

		Map<String, Object> map = Map.of("marksGot", marksGot, "correctAnswers", correctAnswers, "attempted",
				attempted);
		return ResponseEntity.ok(map);

	}

}
