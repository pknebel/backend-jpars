package br.edu.unochapeco.jpars.dto;

public class FirstFollowRowDTO {

	private String naoTerminal;
	private String first;
	private String follow;

	public FirstFollowRowDTO() {
	}
	
	public FirstFollowRowDTO(String naoTerminal, String first, String follow) {
		this.naoTerminal = naoTerminal;
		this.first = first;
		this.follow = follow;
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
