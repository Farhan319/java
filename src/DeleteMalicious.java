// Java Program to Delete files ending with files that can be malicious
// WARNING : This program can delete your personal files. Use with Care
//
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
import java.util.Scanner;
import java.util.regex.*;

public class DeleteMalicious {

    // Lower cased file extension that can be malicious or potentialy unwanted
    static String[] MaliciousFileExtensions = new String[] { ".exe", ".dll", ".hta", ".vbs", ".bat", "o.tmp" };

    // Returns extension of a file
    // For more detail visit http://aztnan.com/?p=353
    static String getExtension(String fileName) {
        // NOTE: Instead of traditional string spliting technique, I'm going to use
        // Regular Expressions that can be more efficient way
        // Using \.[^.\. ]+ pattern we can match any string starting with period (.)
        // to end of source string excluding period (.) inside of matched string

        Matcher m = Pattern.compile("\\.[^.\\. ]+").matcher(fileName);

        String extension = "";

        // For example a file with double extension like myfile.tar.gzip actual file
        // extension is .gzip. Above pattern will gives two results and last one is 
        // the actual file extension so preserve current result and find next match
        while (m.find())
             extension = m.group(0);

        // If no match was found this will be empty string else contains last match
        return extension;
    }

    // Searches for files having any of extension from MaliciousFileExtensions
    // and deletes that files
    static void deleteMaliciousFiles(File[] files) {
        for (File file : files) {

            // Make sure to process obly for files
            if (file.isFile()) {

                // Get the extension of file and convert to lower case to
                // perform case in-sensitive search
                String extension = getExtension(file.getName()).toLowerCase();

                // Iterate over all extensions in MaliciousFileExtensions
                for (String ext : MaliciousFileExtensions) {

                    // and check current file extension is in MaliciousFileExtensions
                    // NOTE: Equal (=) not works for string So, use
                    // String.equals to check strings are equal or not
                    if (ext.equals(extension)) {

                        System.out.println("Found Virus in " + file.getName());

                        // Delete the file
                        file.delete();

                        // Breaks the loop to stop searching MaliciousFileExtensions array
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        Scanner scaner = new Scanner(System.in);
        System.out.print("Enter directory name: ");

        String directoryName = scaner.next();

        File directory = new File(directoryName);

        // Make sure give path is for a valid directory
        if (directory.exists() && !directory.isFile()) {

            // If so do the job
            deleteMaliciousFiles(directory.listFiles());
        } else {
            System.out.println("Invalid directory name");
        }
    }
}