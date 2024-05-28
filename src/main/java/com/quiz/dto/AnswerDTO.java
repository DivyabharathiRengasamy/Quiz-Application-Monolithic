package com.quiz.dto;

public class AnswerDTO {
	private Long id;
	private String response;
	
	
	public AnswerDTO() {
		super();
	}


	public AnswerDTO(Long id, String response) {
		super();
		this.id = id;
		this.response = response;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getResponse() {
		return response;
	}


	public void setResponse(String response) {
		this.response = response;
	}
	
	
}
