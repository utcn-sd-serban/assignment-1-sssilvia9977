package ro.utcn.spet.example.a1.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.spet.example.a1.entity.Question;
import ro.utcn.spet.example.a1.exception.QuestionNotFoundException;
import ro.utcn.spet.example.a1.repository.QuestionRepository;
import ro.utcn.spet.example.a1.repository.RepositoryFactory;

import javax.swing.text.html.Option;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.reverse;

@Service
@RequiredArgsConstructor
public class QuestionManagementService {

    private final RepositoryFactory repositoryFactory;

    @Transactional
    public void addQuestion(Integer userId, String title, String text){
         repositoryFactory.createQuestionRepository().save(new Question(userId, title, text, new Date()));
    }

    @Transactional
    public List<Question> findAllQuestions(){
        return (repositoryFactory.createQuestionRepository().findAll());
    }

    @Transactional
    public Optional<Question> filterTitle(String title){
        return repositoryFactory.createQuestionRepository().filterByTitle(title);

    }

    public Optional<Question> findById(int id)
    {
        return repositoryFactory.createQuestionRepository().findById(id);
    }

    @Transactional
    public void remove(String title) {
        QuestionRepository repository = repositoryFactory.createQuestionRepository();
        Question question = repository.filterByTitle(title).orElseThrow(QuestionNotFoundException::new);
        repository.remove(question);
    }

}
