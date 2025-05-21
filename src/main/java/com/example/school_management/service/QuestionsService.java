package com.example.school_management.service;

import com.example.school_management.dto.ResponseDTO;
import com.example.school_management.entity.Questions;
import com.example.school_management.exception.UserNotFoundException;
import com.example.school_management.repository.QuestionsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import utilities.Constants;

import java.util.List;

@Service
public class QuestionsService {
    private final QuestionsRepository questionsRepository;

    public QuestionsService(final QuestionsRepository questionsRepository) {
        this.questionsRepository = questionsRepository;
    }

    public ResponseDTO createQuestion(final Questions question) {
        String correctAnswer = question.getCorrect_answer();
        String option1 = question.getOption1();
        String option2 = question.getOption2();
        String option3 = question.getOption3();
        if (!correctAnswer.contains(option1) && !correctAnswer.contains(option2) && !correctAnswer.contains(option3)) {
            throw new UserNotFoundException("does not match any options");
        } else {
            final Questions createQuestion = this.questionsRepository.save(question);
            return ResponseDTO.builder().message(Constants.CREATED).data(createQuestion).statusCode(HttpStatus.CREATED.value()).build();
        }
    }

    public ResponseDTO retrieveQuestionById(final String id) {
        final Questions question = this.questionsRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Question Id not found:" + id));
        return ResponseDTO.builder().message(Constants.RETRIEVED).data(question).statusCode(HttpStatus.OK.value()).build();
    }

    public ResponseDTO retrieveAllQuestion() {
        final List<Questions> getAllQuestion = this.questionsRepository.findAll();
        return ResponseDTO.builder().message(Constants.RETRIEVED).data(getAllQuestion).statusCode(HttpStatus.OK.value()).build();
    }

//    public QuestionDTO retrieveQuestionsById(final String id) {
//        final Questions getQuestionsById = this.questionsRepository.findById(id)
//                .orElseThrow(() -> new UserNotFoundException("Question Id not found:" + id));
//        return QuestionDTO.builder().id(getQuestionsById.getId()).question(getQuestionsById.getQuestion()).option1(getQuestionsById.getOption1()).option2(getQuestionsById.getOption2()).option3(getQuestionsById.getOption3()).build();
//    }
//
//    public List<QuestionDTO> retrieveAllQuestions() {
//        final List<Questions> getAllQuestions = this.questionsRepository.findAll();
//        return getAllQuestions.stream()
//                .map(question -> QuestionDTO.builder().id(question.getId()).question(question.getQuestion()).option1(question.getOption1()).option2(question.getOption2()).option3(question.getOption3()).build())
//                .collect(Collectors.toList());
//    }

    public ResponseDTO updateQuestion(final String id, final Questions question) {
        final Questions updateQuestion = this.questionsRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Question Id not found:" + id));
        updateQuestion.setQuestion(question.getQuestion());
        updateQuestion.setOption1(question.getOption1());
        updateQuestion.setOption2(question.getOption2());
        updateQuestion.setOption3(question.getOption3());
        updateQuestion.setCorrect_answer(question.getCorrect_answer());
        updateQuestion.setTutor(question.getTutor());
        this.questionsRepository.save(updateQuestion);
        return ResponseDTO.builder().message(Constants.MODIFIED).data(updateQuestion).statusCode(HttpStatus.OK.value()).build();
    }

    public ResponseDTO removeQuestion(final String id) {
        final Questions deleteQuestion = this.questionsRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Question Id not found:" + id));
        this.questionsRepository.delete(deleteQuestion);
        return ResponseDTO.builder().message(Constants.REMOVED).data(deleteQuestion).statusCode(HttpStatus.OK.value()).build();
    }

    //    public ResponseDTO getQuestionByPage(int index,int size,String field){
//        Sort sort = Sort.by(Sort.Direction.ASC,field);
//        Pageable page = PageRequest.of(index,size,sort);
//        Page<Questions> question = questionsRepository.findAll(page);
//        return ResponseDTO.builder().message(Constants.RETRIEVED).data(question).statusCode(HttpStatus.OK.value()).build();
//    }
    public ResponseDTO getQuestionByPage(final int pageNumber, final int pageSize, final boolean sort, final String field,final String search) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sort ? Sort.Direction.ASC : Sort.Direction.DESC, field));
        Page<Questions> questions = this.questionsRepository.findBYQuestions(search,pageable);
        return ResponseDTO.builder().message(Constants.RETRIEVED).data(questions).statusCode(HttpStatus.OK.value()).build();
    }

}