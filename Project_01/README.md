# Student Result Management System

## Project Overview

The **Student Result Management System** is a Java-based console application designed to manage student records and generate academic results.

The application allows users to add student details, display student results, search for students, calculate grades, identify the highest and lowest marks, calculate the class average, count pass/fail students, and display student rankings.

This project focuses on strengthening Java fundamentals, array manipulation, methods, searching, sorting, input validation, and logical problem-solving.

## Features

* Add student records
* Display all student results
* Calculate grades based on marks
* Check pass/fail status
* Search for a student using their ID
* Display the student with the highest marks
* Display the student with the lowest marks
* Calculate the class average
* Count passed students
* Count failed students
* Display students in descending order of marks
* Validate student IDs and marks
* Handle invalid menu input

## Technologies Used

* **Programming Language:** Java
* **Application Type:** Console-based application
* **Input Handling:** `Scanner`
* **Data Storage:** Arrays

## Java Concepts Practiced

* Variables and data types
* Operators
* Conditional statements
* Loops
* Methods
* `Scanner`
* One-dimensional arrays
* Parallel arrays
* Nested loops
* Searching
* Sorting
* Input validation
* Formatted output

## Input Validation

The application validates the following inputs:

* Student ID must be a positive integer.
* Student IDs must be unique.
* Student marks must be between `0` and `100`.
* The maximum number of students is limited to `50`.
* Invalid menu input is handled without terminating the application.
* Operations that require student records are handled when no students have been added.

## Application Menu

```text
1. Add Student
2. Display Students
3. Search Student
4. Display Highest Marks
5. Display Lowest Marks
6. Display Rankings
7. Display Class Average
8. Display Passed Student Count
9. Display Failed Student Count
10. Exit
```

## Project Limitations

* Data is stored only during program execution.
* Student records are lost when the application exits.
* The application supports only one subject per student.
* The maximum number of students is fixed at `50`.
* The application does not use a database.
* The application does not provide a graphical user interface.
* Ranking changes the order of the records in the arrays.

## Learning Outcome

This project provides practical experience in building a menu-driven Java application using fundamental programming concepts. It strengthens understanding of arrays, methods, loops, conditions, searching, sorting, validation, and structured program design.
