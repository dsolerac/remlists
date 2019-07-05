package com.remlists.list.read.infrastructure.spring;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface BeanNames {


    Logger LOG = LoggerFactory.getLogger(BeanNames.class);


    public interface Domain {

        public interface Repository {


        }

        public interface Service {



        }

    }

    public interface Application {

        public interface Service {

            String CreateNewRemListService = "createNewRemListServiceRead";
        }


    }


    public interface Infrastructure {

        public interface Spring {

            String entityManagerListRead = "entityManagerListRead";
            String dataSourceListRead = "dataSourceListRead";
            String transactionManagerListRead = "transactionManagerListRead";

            public interface Repository {

                String remListCommandDataRepository = "remListCommandDataRepository";
                String remListCommandDataCustomRepositoryImpl = "remListCommandDataCustomRepositoryImpl";

                String remListQueryDataRepository = "remListQueryDataRepository";
                String remListQueryDataCustomRepositoryImpl = "remListQueryDataCustomRepositoryImpl";

                String listReadCommandRepositoryJPA = "listReadCommandRepositoryJPA";
                String listReadQueryRepositoryJPA = "listReadQueryRepositoryJPA";
            }

            public interface Security {


            }

            public interface Component {

                public interface CommandHandler{

                    String createNewRemlistCommandHandlerRead = "createNewRemlistCommandHandlerRead";

                }

                public interface Mapper{

                    String remListMapperRead = "remListMapperRead";

                }

            }

        }
    }


}
