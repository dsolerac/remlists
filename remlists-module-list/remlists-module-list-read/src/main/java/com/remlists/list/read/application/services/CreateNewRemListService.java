package com.remlists.list.read.application.services;

import com.remlists.list.domain.valueObjects.RemListName;
import com.remlists.shared.domain.valueObjects.Id;

public interface CreateNewRemListService {

    void createNewRemList(Id id, RemListName name);


}
