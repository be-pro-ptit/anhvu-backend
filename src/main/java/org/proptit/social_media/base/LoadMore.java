package org.proptit.social_media.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class LoadMore<T> {
    private Long lastId;
    private int limit;
    private List<T> elements;
}
