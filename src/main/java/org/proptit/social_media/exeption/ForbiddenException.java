package org.proptit.social_media.exeption;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ForbiddenException extends RuntimeException {
    private String message;
}
