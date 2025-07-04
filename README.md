# Java Console Task Manager

A simple Java-based console application for managing personal tasks via a text-based menu.

## Features
- Add new tasks with title, description, due date, and priority
- Delete tasks by ID
- Mark tasks as completed
- View all tasks in a clear, numbered list
- Task persistence across sessions via local file storage

## Technologies Used
- Java (standard libraries)
- Object-Oriented Programming (OOP)
- `LocalDate` for date management
- File I/O (`FileWriter`, `BufferedReader`)

## File Structure
- `Main.java` – Main menu and user interaction
- `Task.java` – Task model with attributes and logic
- `TaskManager.java` – Task list management and file operations
- `tasks.txt` – Automatically generated file for storing tasks

## Sample Output
```
1. Study (X) - Due: 2025-08-01
2. Study (V) - Due: 2025-08-01
3. Buy groceries (X) - Due: 2025-07-01 ```

## Author
Riki Oyerbach
