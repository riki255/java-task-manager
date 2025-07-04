import java.time.LocalDate;// LocalDateב כדי שנוכל להשתמש
/**
 * Write a description of class Task here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Task
{
    // instance variables - replace the example below with your own
    
    private int id; //מספר המשימה
    private String title; //כותרת למשימה
    private String description; // תיאור מפורט של המשימה
    private LocalDate dueDate; //תאריך היעד של המשימה
    private boolean isDone; //האם המשימה בוצעה כן /לא
    private int priority; //דרגת עדיפות של המשימה 1 גבוה, 2בינונית , 3 נמוכה

  
    public Task(int id, String title, String description, LocalDate dueDate, int priority)
    {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.isDone = false;
    }
    public void markAsDone() //כאשר השיטה בזאת רצה היא משנה את המשימה למשימה שנעשתה
    {
        isDone = true;
    }

    public String toString() //מדפיסה את המשימה ומסמנת  V/X אם השימה נעשתה או לא
    {
        return id + ". " + title + " (" + (isDone ? "V" : "X") + ") - Due: " + dueDate;
    }

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public LocalDate getDueDate() { return dueDate; }
    public boolean isDone() { return isDone; }
    public int getPriority() { return priority; }
    //Setters
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public void setPriority(int priority) { this.priority = priority; }
}

    
