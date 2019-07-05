package com.remlists.list.read.application.services;

import com.remlists.list.domain.entities.RemList;
import com.remlists.shared.domain.valueObjects.Id;

import java.util.Optional;

public interface GetRemListService {

    Optional<RemList> getRemList(Id id);


}
