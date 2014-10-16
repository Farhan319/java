// Java Program to Delete files whose name starts with specific string
// WARNING : This program can delete your personal files. Use with Care
//
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

public class DeleteSpecificFiles {

    // Deletes files whose name starts with specified string
    static void deleteFiles(File files[], String startWith) {
        for (File file : files) {

            // process only files
            if (file.isFile()) {
                
                // File name are case in-sensitive in windows. So perform
                // Case In-Sensitive comparison
                String fileName = file.getName().toLowerCase();
                if (fileName.startsWith(startWith)) {

                    System.out.println("Deleting " + file.getName());                    

                    // Deletes the file
                    file.delete();
                }
            }
        }
    }

    public static void main(String args[]) {

        Scanner scaner = new Scanner(System.in);
        System.out.print("Enter directory name: ");
        String directoryName = scaner.next();

        System.out.print("Enter staring keyword of files to delete: ");
        String startWith = scaner.next().toLowerCase();

        File directory = new File(directoryName);

        // Process only if given path is a valid Directory
        if (directory.exists() && !directory.isFile()) {

            // If so do job for all files in specified directory
            deleteFiles(directory.listFiles(), startWith);
        } else {
            System.out.println("Invalid directory name");
        }
    }
}