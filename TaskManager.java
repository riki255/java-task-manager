import java.util.ArrayList;//ממשק שמגדיר מה אפשר לעשטת עם רשימה
import java.util.List;//מימוש של רשימה
import java.time.format.DateTimeFormatter;//המרה בין תאריכים למחרוזות (ולהפך) לפי פורמט מותאם
import java.time.format.DateTimeParseException;//טיפול בשגיאות כאשר מחרוזת לא תואמת את הפורמט
import java.time.LocalDate;//מייבא את המחלקה לייצוג תאריך
/**
 *אלו המחלקות שנשתמש בהן לכתיבה וקריאה של קבצים. 
 */
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class TaskManager
{
    private List<Task> tasks;//רשימת כל המשימות
    private int nextId;//מוודא שכל רשימה תקבל מספר ייחודי
    
    public TaskManager()//    בנאי שיוצר רשימת משימות ה וכן מגדיר שמספר המשימה הבאה יהיה 1
    {
        tasks = new ArrayList<>();
        nextId = 1;
    }
    /**
     *מקבלת את פרטי המשימה יוצרת אובייקט task עם nextid מוסיפה אותו לרשימת המשימות מגדילה את המזהה הבא 
     
    public void addTask(String title, String description, String dueDateString, int priority)//
    {
        Task newTask = new Task(nextId, title, description, java.time.LocalDate.parse(dueDateString), priority);
        tasks.add(newTask);
        nextId++;
    }*/
    public void addTask(String title, String description, String dueDateString, int priority) 
    {
        try//יכנס לתנאי זה רק אם תקין
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");   // מגדירים את פורמט התאריך הנכון (שנה-חודש-יום)
            LocalDate dueDate = LocalDate.parse(dueDateString, formatter);// מנסים להמיר את המחרוזת לאובייקט LocalDate
            
            Task newTask = new Task(nextId, title, description, dueDate, priority); // יוצרים את המשימה ומוסיפים לרשימה
            tasks.add(newTask);//מוסיף את המשימה לרימת המשימות שיש במחלקת "טסק
            nextId++;//מגדילה את מספר המזהה הבא
            System.out.println("Task added successfully!");
            saveTasksToFile();// שומר את כל המשימות לקובץ טקסט (tasks.txt) כדי שהן לא יאבדו בסגירת התוכנית
        }
        catch (DateTimeParseException e)//אם המשתמש הכניס את התאריך לא נכון 
        {
            System.out.println("Invalid date format! Please use YYYY-MM-DD (e.g. 2025-06-20).");
        }
    }
        
    /**
     * הצגת כל המשימות 
     * אם הרשימה ריקה – מדפיס: "אין משימות כרגע"
     * אחרת – עובר על כל המשימות ומדפיס אותן
     */public void showAllTasks() 
    {
        if (tasks.isEmpty()) {
            System.out.println("No tasks at the moment.");
        } else {
            for (Task t : tasks) {
                System.out.println(t);
            }
        }
    }
    /**
     * סימון משימה כבוצעה
     * מקבלת מספר מזהה id
     * עוברת על הרשימה ומחפשת משימה עם אותו id
     * כשמוצאת – קוראת לפונקציה markAsDone() שמסמנת אותה כבוצעה
     * אם לא נמצאה – מציגה הודעה מתאימה
     */
    public void markTaskAsDone(int id) {
        for (Task t : tasks) {
            if (t.getId() == id) {
                t.markAsDone();
                System.out.println("המשימה סומנה כבוצעה.");
                saveTasksToFile();// שומר את כל המשימות לקובץ טקסט (tasks.txt) כדי שהן לא יאבדו בסגירת התוכנית
                return;
            }
        }
        System.out.println("לא נמצאה משימה עם מספר מזהה זה.");
    }
    /**
     *מחיקת משימה
     *מקבלת מספר מזהה (id)
     *מחפשת את המשימה המתאימה ברשימה
    *אם נמצאה – שומרת אותה במשתנה toRemove ומוחקת אותה מהרשימה אם לא נמצאה – מודיעה למשתמש
    */
    public void deleteTask(int id) {
        Task toRemove = null;
        for (Task t : tasks) {
            if (t.getId() == id) {
                toRemove = t;
                break;
            }
        }
        if (toRemove != null) {
            tasks.remove(toRemove);
            System.out.println("המשימה נמחקה.");
            saveTasksToFile();// שומר את כל המשימות לקובץ טקסט (tasks.txt) כדי שהן לא יאבדו בסגירת התוכנית
        } else {
            System.out.println("לא נמצאה משימה למחיקה.");
        }
    }
    
    /**
     * שומרת את רשימת המשימות לקובץ בשם tasks.txt 
     */
    public void saveTasksToFile() 
    {
        try 
        {
            FileWriter writer = new FileWriter("tasks.txt");//FileWriter יוצר קובץ (או מוחק וכותב מחדש אם קיים).
            for (Task t : tasks)//כל משימה נכתבת בשורה עם הפרטים מופרדים בפסיקים. 
            {
                writer.write(t.getId() + "," +
                             t.getTitle() + "," +
                             t.getDescription() + "," +
                             t.getDueDate() + "," +
                             t.getPriority() + "," +
                             t.isDone() + "\n");//מחזיר true או false
            }
            writer.close();
        } 
        catch (IOException e) 
        {
            System.out.println("X Error saving tasks to file.");
        }
    }
    
    /**
     
       * קוראת את הקובץ tasks.txt ומטעינה את כל המשימות חזרה לרשימה בתחילת האפליקציה.
       * קוראים כל שורה בקובץ
       * יוצרים משימה לפי הערכים
       * מוסיפים אותה לרשימה
       * בודקים אם isDone == true → מסמנים אותה כבוצעה
       * מעדכנים את nextId כדי שהמשימות הבאות לא יכפילו ID
     */
    public void loadTasksFromFile() 
    {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("tasks.txt"));
            String line;
            while ((line = reader.readLine()) != null) 
            {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String title = parts[1];
                String description = parts[2];
                LocalDate dueDate = LocalDate.parse(parts[3]);
                int priority = Integer.parseInt(parts[4]);
                boolean isDone = Boolean.parseBoolean(parts[5]);

                Task task = new Task(id, title, description, dueDate, priority);
                if (isDone) {
                task.markAsDone();
                }
                tasks.add(task);
                nextId = Math.max(nextId, id + 1); // מעדכן את ה-id הבא
            }
            reader.close();
        }
        catch (IOException e) 
        {
            System.out.println("No saved tasks found.");
        }
    }

}
