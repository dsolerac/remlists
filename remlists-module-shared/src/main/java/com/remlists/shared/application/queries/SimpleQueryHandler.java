package com.remlists.shared.application.queries;

import javax.validation.Valid;

public interface SimpleQueryHandler<S, T extends Query> {

    S execute(@Valid T query);


}
