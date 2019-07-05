package com.remlists.entrypoint;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface BeanNames {


    Logger LOG = LoggerFactory.getLogger(BeanNames.class);


    public interface domain {

        public interface repository {

            String userRepository = "userRepository";


        }

        public interface service {

            String createRemlistUserDomainService = "createRemlistUserDomainService";

        }

    }

    public interface application {

        public interface service {

            String registryNewUserAccountService = "registryNewUserAccountService";
        }


    }


    public interface infrastructure {

        public interface spring {


            public interface security {


            }

            public interface component {

            }

        }
    }


}
