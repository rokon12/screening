package com.bazlur.screening.domain;

import com.fasterxml.jackson.annotation.JsonValue;

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

	@JsonValue
	public String getLabel() {
		return label;
	}
}
