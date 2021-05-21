package org.revo.jsonser;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

public class JsonPage<T> extends PageImpl<T> {

    public JsonPage(final List<T> content) {
        super(content);
    }


    @JsonView(View.Default.class)
    public List<T> getContent() {
        return new ArrayList<>(super.getContent());
    }
}
