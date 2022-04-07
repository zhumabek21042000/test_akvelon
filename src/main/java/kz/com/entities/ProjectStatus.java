package kz.com.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
/*
    ProjectStatus enum is designed to choose status by the code and make the project creating process more simple
*/
public enum ProjectStatus {
    NotStarted("e1"), // value is NotStarted and we can get that value by code "e1"
    Active("e2"),
    Completed("e3");


    private String statusCode;

    private ProjectStatus(String statusCode){
        this.statusCode = statusCode;
    }

    public String getStatus(){
        return this.statusCode;
    }

    @JsonCreator // To pass the enum value to front-end server and back
    // we will get value by the code in order to prevent from errors
    public static ProjectStatus getProjectStatusFromCode(String value) {

        for (ProjectStatus ps : ProjectStatus.values()) { // if one of the status equals to string value,
            // it will return ProjectStatus object

            if (ps.getStatus().equals(value)) {

                return ps;
            }
        }

        return null;
    }
}
