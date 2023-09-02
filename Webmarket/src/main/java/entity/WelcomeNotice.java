package entity;

import java.util.Date;

public class WelcomeNotice {
	private String greeting  ;
	private String tagline  ;
	private Date date;
	
	public WelcomeNotice() {
	}
	
	public WelcomeNotice(String greeting, String tagline, Date date) {
		this.greeting = greeting;
		this.tagline = tagline;
		this.date = date;
	}

	public String getGreeting() {
		return greeting;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}

	public String getTagline() {
		return tagline;
	}

	public void setTagline(String tagline) {
		this.tagline = tagline;
	}

	
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "WelcomeNotice [greeting=" + greeting + ", tagline=" + tagline + ", date=" + date + "]";
	}

	
	
	
}
