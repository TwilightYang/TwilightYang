package com.rank.calculate;

public class TitleAnswer {
    private String problemDescription;

    private String answer;

    public TitleAnswer(String problemDescription, String answer) {
        this.problemDescription = problemDescription;
        this.answer = answer;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "TitleAnswer{" +
                "problemDescription='" + problemDescription + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
