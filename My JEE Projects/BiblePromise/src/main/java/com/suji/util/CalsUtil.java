package com.suji.util;

import java.time.LocalDate;

public class CalsUtil {
    public static int getNumber(String name, int month, int day) {
        name = name.toUpperCase().trim();
        LocalDate dob = LocalDate.of(1994, month, day);
        LocalDate now = LocalDate.of(2022, 1, 1);
        int doy = dob.getDayOfYear();
        int ny = now.getDayOfYear();

        int sum = 0;
        for (int i = 0; i < name.length(); i++) {

            sum += name.charAt(i) - 64;

        }
        int t = (sum * month) + doy + ny;//sum+m+d;
        return ((t % 335) + 1);
    }

	public static int getNumber(String name, String date) {
		
		LocalDate ld = DateUtil.toLocalDate(date, "yyyy-MM-dd");
		return getNumber(name, ld.getMonthValue(), ld.getDayOfMonth());
	}
}
