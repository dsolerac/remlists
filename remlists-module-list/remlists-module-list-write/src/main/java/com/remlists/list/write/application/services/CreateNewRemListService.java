package com.remlists.list.write.application.services;

import com.remlists.list.domain.valueObjects.RemListName;
import com.remlists.shared.domain.valueObjects.Id;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/*
Primary port
 */
@Validated
public interface CreateNewRemListService {

    void createNewRemList(@Valid Id id, @Valid RemListName name);

}
