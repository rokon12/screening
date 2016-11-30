package com.bazlur.screening.domain;

/**
 * @author Bazlur Rahman Rokon
 * @since 11/30/16.
 */
public enum Difficulty {
	EASY("Easy"),
	MEDIUM("Medium"),
	HARD("Hard");

	private String label;

	Difficulty(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
