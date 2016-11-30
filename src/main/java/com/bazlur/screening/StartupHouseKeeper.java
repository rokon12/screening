package com.bazlur.screening;

import com.bazlur.screening.domain.Difficulty;
import com.bazlur.screening.domain.SubjectiveQuestion;
import com.bazlur.screening.domain.Tag;
import com.bazlur.screening.repository.SubjectiveQuestionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Bazlur Rahman Rokon
 * @since 12/1/16.
 */
@Component
public class StartupHouseKeeper implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger log = LoggerFactory.getLogger(StartupHouseKeeper.class);

	@Inject
	private SubjectiveQuestionRepository subjectiveQuestionRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		log.info("onApplicationEvent() :{}", event);

		SubjectiveQuestion subjectiveQuestion = new SubjectiveQuestion();
		subjectiveQuestion.setName("Which Javascript framework do you think is the best? Why? Compare at least 2");
		subjectiveQuestion.setDescription("Simple Description");
		subjectiveQuestion.setAdditionalNotes("None");
		subjectiveQuestion.setAllocatedTime(120);
		subjectiveQuestion.setDifficulty(Difficulty.EASY);
		subjectiveQuestion.setMaxScore(20);
		subjectiveQuestion.setCreatedBy("Bazlur Rahman");
		subjectiveQuestion.setLastUpdatedBy("Bazlur Rahman");

		Tag javaScript = new Tag("JavaScript");
		javaScript.setCreatedBy("Bazlur Rahman");
		javaScript.setLastUpdatedBy("Bazlur Rahman");

		Tag webApplication = new Tag("Web Application");
		webApplication.setCreatedBy("Bazlur Rahman");
		webApplication.setLastUpdatedBy("Bazlur Rahman");

		Set<Tag> tags = Stream.of(javaScript, webApplication).collect(Collectors.toSet());

		subjectiveQuestion.getTags().addAll(tags);

		subjectiveQuestionRepository.saveAndFlush(subjectiveQuestion);

		SubjectiveQuestion subjectiveQuestion2 = new SubjectiveQuestion();
		subjectiveQuestion2.setName("Admittedly, I stole this question from somewhere else (can't remember where I read it any more) but thought it was funny:\n" );
		subjectiveQuestion2.setDescription("Simple Description");
		subjectiveQuestion2.setAdditionalNotes("None");
		subjectiveQuestion2.setAllocatedTime(120);
		subjectiveQuestion2.setDifficulty(Difficulty.EASY);
		subjectiveQuestion2.setMaxScore(20);
		subjectiveQuestion2.setCreatedBy("Bazlur Rahman");
		subjectiveQuestion2.setLastUpdatedBy("Bazlur Rahman");

		Tag java = new Tag("Java");
		javaScript.setCreatedBy("Bazlur Rahman");
		javaScript.setLastUpdatedBy("Bazlur Rahman");

		Tag c = new Tag("C#");
		webApplication.setCreatedBy("Bazlur Rahman");
		webApplication.setLastUpdatedBy("Bazlur Rahman");

		Set<Tag> tags2 = Stream.of(java, c).collect(Collectors.toSet());

		subjectiveQuestion.getTags().addAll(tags2);
		subjectiveQuestionRepository.saveAndFlush(subjectiveQuestion2);
	}
}
