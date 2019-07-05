package com.remlists.entrypoint.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface WebUrlMapper {

    Logger LOG = LoggerFactory.getLogger(WebUrlMapper.class);


    public interface Rest {

        final String API_ROOT = "api";
        final String USER_ROOT = "users";


        final String USER_ROOT_PATH = "/" + API_ROOT + "/" + USER_ROOT;

        public interface Public {

            public interface Users {

                final String USER_REGISTER = "register";
                final String USER_REGISTER_PATH = USER_ROOT_PATH + "/" + USER_REGISTER;

            }


            public interface Lists {

            }

        }

        public interface Private {

            public interface Users {

            }

            public interface Lists {

            }

        }

    }

}