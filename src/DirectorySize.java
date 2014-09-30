// Java Script to measure size of a directory including sub-directories
// Visit http://aztnan.com/?p=351 for more info 
//
// Copyright (c) Adnan Umer. All rights reserved. Follow me @aztnan
// Email: aztnan@outlook.com
//
// Permission is hereby granted, free of charge, to any person obtaining a copy of this
// software and associated documentation files (the "Software"), to deal in the Software
// without restriction, including without limitation the rights to use, copy, modify, merge,
// publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons
// to whom the Software is furnished to do so, subject to the following conditions:
// 
// The above copyright notice and this permission notice shall be included in all copies or
// substantial portions of the Software.
// 
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
// INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
// PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
// FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
// OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
// DEALINGS IN THE SOFTWARE.

import java.io.File;
import java.util.Scanner;

public class DirectorySize {
	
	static long calcDirectorySize(File dir) {
		return calcFilesSize(dir.listFiles());
	}
	
	static long calcFilesSize(File[] files) {
		long size = 0;
		
		// For each file in directory, get it's size
		for (File file : files) {
			if (file.isFile()) {
				size += file.length();
			} else {
				// Directory size can not be calculated directly
				size += calcDirectorySize(file);
			}
		}
		
		return size;
	}
	
	public static long getSize(String directoryName) {
		File dir = new File(directoryName);
		
		// Make sure that we have a valid directory
		if (!dir.isFile() && dir.exists()) {
			
			return calcFilesSize(dir.listFiles());
		}

		// Not applicable for invalid directories
		return -1;
	}
	
	public static void main(String[] args)	
	{
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			System.out.print("Enter Directory Name: ");
			String dirName = scanner.next();
			
			long dirSize = getSize(dirName);
			
			if (dirSize == -1)
				System.out.println("Specified path is either not a valid directory or does not exits");
			else
			{
				double kbs = dirSize / 1024.0;
				double mbs = kbs / 1024.0;
				double gbs = mbs / 1024.0;
				
				if (kbs < 1)
					System.out.println(String.valueOf(dirSize) + " bytes");
				else if (mbs < 1)
					System.out.println(String.valueOf(kbs) + " KB (" + String.valueOf(dirSize) + " bytes)");
				else if (gbs < 1) 
					System.out.println(String.valueOf(mbs) + " MB (" + String.valueOf(dirSize) + " bytes)");
				else
					System.out.println(String.valueOf(gbs) + " GB (" + String.valueOf(dirSize) + " bytes)");
			}	
		}
	}
}