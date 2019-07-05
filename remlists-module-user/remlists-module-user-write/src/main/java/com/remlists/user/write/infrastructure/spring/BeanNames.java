package com.remlists.user.write.infrastructure.spring;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface BeanNames {


    Logger LOG = LoggerFactory.getLogger(BeanNames.class);


    public interface Domain {

        public interface Repository {


        }

        public interface Service {

            String createUserDomainService = "createUserDomainService";
            String passwordEncoderDomainService = "passwordEncoderDomainService";

        }

    }

    public interface Application {

        public interface Service {

            String registryNewUserAccountService = "registryNewUserAccountService";
        }


    }


    public interface Infrastructure {

        public interface Spring {

            String entityManagerUserWrite = "entityManagerUserWrite";
            String dataSourceUserWrite = "dataSourceUserWrite";
            String transactionManagerUserWrite = "transactionManagerUserWrite";

            public interface Repository {

                String userWriteDataRepository = "userWriteDataRepository";
                String roleWriteDataRepository = "roleWriteDataRepository";

                String userWriteDataCustomRepositoryImpl = "userWriteDataCustomRepositoryImpl";
                String roleWriteDataCustomRepositoryImpl = "roleWriteDataCustomRepositoryImpl";

                String userWriteRepositoryJPA = "userWriteRepositoryJPA";
                String roleWriteRepositoryJPA = "roleWriteRepositoryJPA";

            }

            public interface Security {


            }

            public interface Component {


                public interface CommandHandler{

                    String createNewUserCommandHandlerWrite = "createNewUserCommandHandlerWrite";

                }

                public interface Mapper{

                    String userMapperWrite = "userMapperWrite";
                    String roleMapperWrite = "roleMapperWrite";

                }

                public interface Publisher{


                    public interface Kafka {

                        String userWriteKafkaMessagePublisher ="userWriteKafkaMessagePublisher";

                    }

                }


            }

        }

    }


}
