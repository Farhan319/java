// Java Program to Print list of Read-Only files in a given Directory
// For more detail visit http://aztnan.com/?p=
//
// Copyright 2014 Adnan Umer <aztnan@outlook.com> Follow me @aztnan
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
import java.util.*;

public class DisplayReadonly {

	// Prints list of read-only files from files array
	static void displayReadOnlyFiles(File[] files) {
		for (File file : files) {

			// Process only files
			if (file.isFile()) {
				
				// Read-Only files can only read.
				if (file.canRead() && !file.canWrite()) {
					
					System.out.println("Found Read Only File: " + file.getName());
				}
			}
		}
	}

	public static void main(String[] args) {

		Scanner scaner = new Scanner(System.in);
		System.out.print("Enter directory name: ");

		String directoryName = scaner.next();

		File directory = new File(directoryName);

		// Make sure directory exits on given path
		if (directory.exists() && !directory.isFile()) {
			displayReadOnlyFiles(directory.listFiles());
		} else {
			System.out.println("Invalid directory name");
		}
	}
}