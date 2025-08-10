package br.edu.unochapeco.jpars.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@Valid
public class FirstFollowRowDTO {

	@NotBlank(message = "Informe o não terminal")
	private String naoTerminal;
	@NotBlank(message = "Informe o não First")
	private String first;
	@NotBlank(message = "Informe o não Follow")
	private String follow;

	public FirstFollowRowDTO() {
	}
	

	public String getNaoTerminal() {
		return naoTerminal;
	}

	public void setNaoTerminal(String naoTerminal) {
		this.naoTerminal = naoTerminal;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getFollow() {
		return follow;
	}

	public void setFollow(String follow) {
		this.follow = follow;
	}

}
