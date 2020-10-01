package model;

import java.io.Serializable;

public class Date implements Serializable {

	private static final long serialVersionUID = 1L;
	private int year;
	private int month;
	private int day;
	private String userDate;

	public Date(String userDate) {
		this.userDate = userDate;
		String[] userDateArray = userDate.split("-");
		this.year = Integer.parseInt(userDateArray[0]);
		this.month = Integer.parseInt(userDateArray[1]);
		this.day = Integer.parseInt(userDateArray[2]);
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + day;
		result = prime * result + month;
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Date other = (Date) obj;
		if (day != other.day)
			return false;
		if (month != other.month)
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return userDate;
	}

}
