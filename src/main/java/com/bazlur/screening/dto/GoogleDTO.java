package com.bazlur.screening.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author Bazlur Rahman Rokon
 * @since 11/28/16.
 */
@Data
@Builder
public class GoogleDTO {
    private String email;
    private String id;
    private String displayName;
    private String imageURL;
    private String firstName;
    private String lastName;
    private String middleName;
    private String username;
}
