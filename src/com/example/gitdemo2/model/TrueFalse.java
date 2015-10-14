package com.example.gitdemo2.model;

public class TrueFalse {
	private int mQuestion;
	private boolean isTrueQuestion;
	public TrueFalse(int question ,boolean isTrueQuestion){
		this.mQuestion = question;
		this.isTrueQuestion = isTrueQuestion;
	}
	
	public int getQuestion() {
		return mQuestion;
	}
	public void setQuestion(int mQuestion) {
		this.mQuestion = mQuestion;
	}
	public boolean isTrueQuestion() {
		return isTrueQuestion;
	}
	public void setTrueQuestion(boolean isTrueQuestion) {
		this.isTrueQuestion = isTrueQuestion;
	}
	
}
