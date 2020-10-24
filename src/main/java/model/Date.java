package model;

import java.io.Serializable;

public class Date implements Serializable {

	private static final long serialVersionUID = 1L;
	private final int year;
	private final int month;
	private final int day;
	private final String userDate;

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
		return year == other.year;
	}

	@Override
	public String toString() {
		return userDate;
	}

	public static boolean validateDate(String userInput) {

		String[] userInputArray = userInput.split("-");

		if (userInputArray.length > 3 || userInputArray.length < 3) {
			return false;
		}

		String year = userInputArray[0];
		String month = userInputArray[1];
		String day = userInputArray[2];
		
		int yearNum = Integer.parseInt(year);
		int monthNum = Integer.parseInt(month);
		int dayNum = Integer.parseInt(day);

		if (year.length() > 4) {
			return false;
		} else if (month.length() > 2 || monthNum > 12 || monthNum < 1) {
			return false;
		} else if (day.length() > 2 || dayNum > 31 || dayNum < 1) {
			return false;
		}

		return validateDay(yearNum, monthNum, dayNum);
	}

	private static boolean validateDay(int yearNum, int monthNum, int dayNum) {
		
		//checking if the year is a lap year
		boolean isLapYear = false;
		int dayLimit = 0;
		
		if(yearNum % 4 == 0) {
			isLapYear = true;
		}
		
		if(monthNum == 2) {
			if(isLapYear)
				dayLimit = 29;
			else 
				dayLimit = 28;
		} else if (monthNum <= 7) {
			if(monthNum % 2 == 0) {
				dayLimit = 30;
			} else {
				dayLimit = 31;
			}
		} else if (monthNum > 7) {
			if(monthNum % 2 == 0) {
				dayLimit = 31;
			} else {
				dayLimit = 30;
			}
		}
		
		return dayNum <= dayLimit;
	}

}
