package com.remlists.list.read.application.services;

import com.remlists.list.domain.entities.RemList;

public interface GetAllRemListsService {

    Iterable<RemList> getAllRemLists();
}
