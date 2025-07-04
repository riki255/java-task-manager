import java.util.Scanner;//מייבא את המחלקה סקנר שמאפשרת לקבל קלט המהשתמש

public class Main 
{
    public static void main(String[] args)//זו השיטה שמפעילים כשמריצים את התוכנית 
    {
        Scanner scanner = new Scanner(System.in);//כדי שבהמשך המשתמש יוכל לקלוט
        TaskManager manager = new TaskManager();//יוצר אובייקט מהסוג של טסקמנגר שזה המנהל של כל המשימות
        manager.loadTasksFromFile();//טוען את כל המשימות השמורות מהקובץ (אם קיים) לתוך הזיכרון בתחילת הריצה
        boolean exit = false;//משנה בולאני עבור הלולאה שתפעל כל עוד הוא פולס

        System.out.println("Welcome to the Task Manager!");
        //הלולאה תפעל עד שיהיה טרו
        while (!exit) {//
            System.out.println("\nChoose an option:");
            System.out.println("1. Add task");
            System.out.println("2. Show all tasks");
            System.out.println("3. Mark task as done");
            System.out.println("4. Delete task");
            System.out.println("5. Exit");
            System.out.print("Your choice: ");

            int choice = scanner.nextInt();//קולט את choice שהמשתמש מכניס
            scanner.nextLine(); // Consume newline
            
           // מבצע פעולה לפי הבחירה
            switch (choice) {
                case 1:
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter due date (YYYY-MM-DD): ");
                    String dueDate = scanner.nextLine();
                    int priority = 0;
                    boolean validPriority = false;

                    while (!validPriority) 
                    {
                        System.out.print("Enter priority (1=High, 2=Medium, 3=Low): ");
                        if (scanner.hasNextInt())//בודק אם הקלט שהמשתמש הכניס הוא מספר שלם 
                        {
                            priority = scanner.nextInt();//שיקלוט אותו
                            scanner.nextLine(); // לניקוי שורה
                            if (priority >= 1 && priority <= 3)//אם הקלט גם בין 1-3
                            {
                                validPriority = true;
                            } 
                            else 
                            {
                                System.out.println("Invalid number. Please enter 1, 2 or 3.");
                            }
                        }
                        else
                        {
                            System.out.println("Invalid input. Please enter a number.");
                            scanner.nextLine(); // דילוג על השורה השגויה
                        }
                    }
                    manager.addTask(title, description, dueDate, priority);
                    break;
                case 2:
                    manager.showAllTasks();
                    break;
                case 3:
                    System.out.print("Enter task ID to mark as done: ");
                    int doneId = scanner.nextInt();
                    scanner.nextLine();
                    manager.markTaskAsDone(doneId);
                    break;
                case 4:
                    System.out.print("Enter task ID to delete: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    manager.deleteTask(deleteId);
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    exit = true;
                    break;
                default://אם המשתמש מקיש מספר לא חוקי (למשל 6 או אות)
                    System.out.println("Invalid choice. Try again.");
            }
        }

        scanner.close();
    }
}
