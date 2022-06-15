package com.davidvelz.diagnosticsystem.objects;

import java.util.ArrayList;
import java.util.List;

public class FailureModel {
    private String system_id;
    private String key;
    private String failure_abstract;
    private String failure_id;
    private String failure_image;
    private List<String> solutions;

    public FailureModel() {
    }

    public FailureModel(String system_id, String key, String failure_abstract, String failure_id, String failure_image, List<String> solutions) {
        this.system_id = system_id;
        this.key = key;
        this.failure_abstract = failure_abstract;
        this.failure_id = failure_id;
        this.failure_image = failure_image;
        this.solutions = solutions;
    }

    public String getSystem_id() {
        return system_id;
    }

    public String getKey() {
        return key;
    }

    public String getFailure_abstract() {
        return failure_abstract;
    }

    public String getFailure_id() {
        return failure_id;
    }

    public String getFailure_image() {
        return failure_image;
    }

    public List<String> getSolutions() {
        return solutions;
    }

    public void setSystem_id(String system_id) {
        this.system_id = system_id;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setFailure_abstract(String failure_abstract) {
        this.failure_abstract = failure_abstract;
    }

    public void setFailure_id(String failure_id) {
        this.failure_id = failure_id;
    }

    public void setFailure_image(String failure_image) {
        this.failure_image = failure_image;
    }

    public void setSolutions(List<String> solutions) {
        this.solutions = solutions;
    }
}
