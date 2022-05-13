package dev.licensex.server.utils.style;

import lombok.experimental.UtilityClass;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

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
