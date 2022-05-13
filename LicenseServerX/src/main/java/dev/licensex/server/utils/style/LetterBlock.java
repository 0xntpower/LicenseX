package dev.licensex.server.utils.style;

public class LetterBlock {

	
	int len;
	char[][] myArray;
	
	public LetterBlock(int n) {
		this.len = n+1;
		myArray = new char[len][len];
		for(int i=0;i<len;i++) {
			for(int j=0;j<len;j++) {
				myArray[i][j] = ' ';
			}
		}
	}
	
	
	public void printLine(int n) {
		for(int i=0;i<len;i++) {
			System.out.print(myArray[n][i]);
		}
	}
	
	public void putLine(String s, int n) {
		if(AABanner.debug) 
			System.out.println(s +"(end of String of len "+s.length()+") line number : " + n + " expected Length " + len);
		for(int i=0;i<len && i < s.length();i++) {
				myArray[n][i] = s.charAt(i);
		}
	}
	
	
}
