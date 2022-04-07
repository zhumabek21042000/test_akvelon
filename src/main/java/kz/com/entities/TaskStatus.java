package kz.com.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TaskStatus {
    ToDo("e1"),
    InProgress("e2"),
    Done("e3");


    private String statusCode;

    private TaskStatus(String statusCode){
        this.statusCode = statusCode;
    }

    public String getStatus(){
        return this.statusCode;
    }

    @JsonCreator
    public static TaskStatus getTaskStatusFromCode(String value) {

        for (TaskStatus ts : TaskStatus.values()) {

            if (ts.getStatus().equals(value)) {

                return ts;
            }
        }

        return null;
    }
}
