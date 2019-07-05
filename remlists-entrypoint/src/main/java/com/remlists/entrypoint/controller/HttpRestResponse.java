package com.remlists.entrypoint.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;


public class HttpRestResponse {

    private static final Logger LOG = LoggerFactory.getLogger(HttpRestResponse.class);


    private HttpStatus status;
    private String error;
    private int code;
    private String message;
    private String path;
    private LocalDateTime timestamp;
    private String developerMessage;
    private String moreInfo;
    private Object value;
    private boolean success;
    private String[] fieldsAffected;
    private String exceptionType;


    private HttpRestResponse(BuilderError builder) {

        this.status = builder.status;
        this.error = builder.error;
        this.code = builder.code;
        this.message = builder.message;
        this.path = builder.path;
        this.timestamp = builder.timestamp;
        this.developerMessage = builder.developerMessage;
        this.moreInfo = builder.moreInfo;
        this.success = builder.success;
        this.fieldsAffected = builder.fieldsAffected;
        this.exceptionType = builder.exceptionType;


    }

    private HttpRestResponse(BuilderSuccess builder) {

        this.status = builder.status;
        this.message = builder.message;
        this.path = builder.path;
        this.timestamp = builder.timestamp;
        this.developerMessage = builder.developerMessage;
        this.moreInfo = builder.moreInfo;
        this.value = builder.value;
        this.success = builder.success;

    }

    public <T> T createSuccessResponse() {

        Map<String, Object> map = new LinkedHashMap<>();

        map.put("status", status.value() + " - " + status.name());
        map.put("message", message);
        map.put("path", path);
        map.put("timestamp", timestamp);
        map.put("moreInfo", moreInfo);
        map.put("value", value);
        map.put("success", success);

        return (T) map;


    }

    public <T> T createErrorResponse() {

        Map<String, Object> map = new LinkedHashMap<>();

        map.put("status", status.value() + " - " + status.name());
        map.put("error", error);
        map.put("code", code);
        map.put("message", message);
        map.put("path", path);
        map.put("timestamp", timestamp);
        map.put("developerMessage", developerMessage);
        map.put("moreInfo", moreInfo);
        map.put("success", success);
        map.put("fieldsAffected", Arrays.toString(fieldsAffected));
        map.put("exceptionType", exceptionType);


        return (T) map;


    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    public String[] getFieldsAffected() {
        return fieldsAffected;
    }

    public void setFieldsAffected(String[] fieldsAffected) {
        this.fieldsAffected = fieldsAffected;
    }

    @Override
    public String toString() {
        return "HttpRestResponse{" +
                "status=" + status +
                ", error='" + error + '\'' +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", path='" + path + '\'' +
                ", timestamp=" + timestamp +
                ", developerMessage='" + developerMessage + '\'' +
                ", moreInfo='" + moreInfo + '\'' +
                ", value=" + value +
                ", success=" + success +
                ", fieldsAffected=" + Arrays.toString(fieldsAffected) +
                ", exceptionType='" + exceptionType + '\'' +
                '}';
    }

//------ static nested Builders class

    public static class BuilderError {


        private HttpStatus status;
        private String error;
        private int code;
        private String message;
        private String path;
        private LocalDateTime timestamp;
        private String developerMessage;
        private String moreInfo;
        private boolean success;
        private String[] fieldsAffected;
        private String exceptionType;


        private Throwable exception;


        public BuilderError(Throwable ex) {

            this.exception = ex;

            this.message = ex.getMessage();
            this.timestamp = LocalDateTime.now();
            this.success = false;
            this.exceptionType = exception.getClass().getCanonicalName();
        }

        public BuilderError withHttpStatus(HttpStatus status) {
            this.status = status;
            return this;
        }

        public BuilderError withError(String error) {
            this.error = error;
            return this;
        }

        public BuilderError withCode(int code) {
            this.code = code;
            return this;
        }

        public BuilderError withDeveloperMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public BuilderError withMoreInfo(String moreInfo) {
            this.moreInfo = moreInfo;
            return this;
        }

        public BuilderError withPath(String path) {
            this.path = path;
            return this;
        }

        public BuilderError withFieldAffected(String... fieldsAffected) {
            this.fieldsAffected = fieldsAffected;
            return this;
        }


        public HttpRestResponse build() {

            return new HttpRestResponse(this);

        }


    }

    public static class BuilderSuccess {


        private HttpStatus status;
        private String message;
        private String path;
        private LocalDateTime timestamp;
        private String developerMessage;
        private String moreInfo;
        private Object value;
        private boolean success;


        private Throwable exception;

        public BuilderSuccess(String message) {

            this.message = message;
            this.timestamp = LocalDateTime.now();
            this.success = true;
        }

        public BuilderSuccess(String message, Object obj) {

            this.message = message;
            this.timestamp = LocalDateTime.now();
            this.value = obj.toString();
            this.success = true;
        }


        public BuilderSuccess withHttpStatus(HttpStatus status) {
            this.status = status;
            return this;
        }

        public BuilderSuccess withDeveloperMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public BuilderSuccess withMoreInfo(String moreInfo) {
            this.moreInfo = moreInfo;
            return this;
        }

        public BuilderSuccess withPath(String path) {
            this.path = path;
            return this;
        }

        public BuilderSuccess withValue(Object value) {
            this.value = value;
            return this;
        }


        public HttpRestResponse build() {

            return new HttpRestResponse(this);

        }


    }

}
