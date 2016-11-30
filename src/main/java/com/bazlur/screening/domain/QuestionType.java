package com.bazlur.screening.domain;

/**
 * @author Bazlur Rahman Rokon
 * @since 12/1/16.
 */
public enum QuestionType {
	SUBJECTIVE("Subjective"),
	MULTIPLE_CHOICE("Multiple Choice");

	private String label;

	QuestionType(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
