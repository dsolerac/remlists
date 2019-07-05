package com.remlists.list.write.infrastructure.spring;


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

            String CreateNewRemListService = "createNewRemListServiceWrite";

        }


    }


    public interface Infrastructure {

        public interface Spring {

            String entityManagerListWrite = "entityManagerListWrite";
            String dataSourceListWrite = "dataSourceListWrite";
            String transactionManagerListWrite = "transactionManagerListWrite";

            public interface Repository {


                String remListDataRepository = "remListDataRepository";
                String remListDataCustomRepositoryImpl = "remListDataCustomRepositoryImpl";

                String listWriteRepositoryJPA = "listWriteRepositoryJPA";
            }

            public interface Security {


            }

            public interface Component {

                public interface CommandHandler{

                    String createNewRemlistCommandHandlerWrite = "createNewRemlistCommandHandlerWrite";

                }

                public interface Mapper{

                    String remListMapperWrite = "remListMapperWrite";

                }

                public interface Publisher{


                    public interface Kafka {

                        String listWriteKafkaMessagePublisher ="listWriteKafkaMessagePublisher";

                    }

                }

            }

        }
    }


}
