package com.joblister.joblisterapp.error;

public class ResultMessage {
    private final ResultType type;
    private final String message;

    public ResultMessage(ResultType type, String message){
        this.type = type;
        this.message = message;
    }

    public ResultMessage(ResultType type){
        this.type = type;
        this.message = "Ok!";
    }

    public ResultType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString(){
        return message;
    }
}
