package com.remlists.shared.application.queries;

import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Collection;

@Validated
public interface IterableQueryHandler<S, T extends Query> {

    Iterable<S> execute(@Valid T query);

}
