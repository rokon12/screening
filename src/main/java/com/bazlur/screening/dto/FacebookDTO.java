package com.bazlur.screening.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Bazlur Rahman Rokon
 * @since 11/28/16.
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
public class FacebookDTO {
	public static final String GRAPH_PIC_URL = "//graph.facebook.com/_PIC_URL_/picture?height=285&width=285";
	public static final String GRAPH_PIC_URL_REPLACE = "_PIC_URL_";

	private String email;
	private String id;
	private String displayName;
	private String imageURL;
	private String firstName;
	private String lastName;
	private String middleName;
	private String username;
}
