package com.example.demo.answer;

import com.example.demo.question.DataNotFoundException;
import com.example.demo.question.Question;
import com.example.demo.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    public Answer create(Question question, String content, SiteUser siteUser){
        Answer answer = new Answer();
        answer.setCreateDate(LocalDateTime.now());
        answer.setContent(content);
        answer.setQuestion(question);
        answer.setSiteUser(siteUser);
        answerRepository.save(answer);
        return answer;
    }
    public Page<Answer> getList(int page, Question question){
        List<Sort.Order> sort = new ArrayList<>();
        sort.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 3, Sort.by(sort));
        return answerRepository.findByQuestion(question, pageable);
    }
    public Answer getAnswer(Integer id){
        Optional<Answer> answer = answerRepository.findById(id);
        if (answer.isPresent()){
            return answer.get();
        }else {
            throw new DataNotFoundException("question not found");
        }
    }
    public void modify(Answer answer,String content){
        answer.setContent(content);
        answer.setModifyDate(LocalDateTime.now());
        answerRepository.save(answer);
    }
    public void delete(Answer answer){
        answerRepository.delete(answer);
    }
    public void vote(Answer answer, SiteUser siteUser){
        answer.getVoter().add(siteUser);
        answerRepository.save(answer);
    }
}
