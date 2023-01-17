package dev.licensex.server.utils.style;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AsciiUtil {

	public static void printBanner(String line) {
		AABanner.debug = false;
		AABanner.myFontName = "Verdana";
		AABanner.myFontStyle = 0;
		AABanner.percent = 32;
		AABanner.clarity = 8;
		AABanner.myChar = "*";

		AABanner.print(line);
	}
	
}
