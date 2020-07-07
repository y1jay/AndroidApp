package com.test.naverapi.data;

import java.io.Serializable;

public class Model implements Serializable {
    String message;
    String result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Model(String message, String result) {
        this.message = message;
        this.result = result;
    }
    public Model(){}
}
