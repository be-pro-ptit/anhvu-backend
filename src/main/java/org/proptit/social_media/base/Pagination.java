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
public class Pagination<T> {
    private long page;
    private long totalElement;
    private long totalPage;
    List<T> elements;
}
