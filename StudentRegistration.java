
import java.util.Scanner;
class Student {
   int rollNo;
   String name;
   String course;
   Student next;
   Student(int rollNo, String name, String course) {
       this.rollNo = rollNo;
       this.name = name;
       this.course = course;
       this.next = null;
   }
}


class StudentLinkedList {
   Student head = null;
   void insert(int rollNo, String name, String course) {
       Student newStudent = new Student(rollNo, name, course);


       if (head == null) {
           head = newStudent;
       } else {
           Student temp = head;


           while (temp.next != null) {
               temp = temp.next;
           }


           temp.next = newStudent;
       }


       System.out.println("Student registered successfully.");
   }


   // Delete student using roll number
   void delete(int rollNo) {
       if (head == null) {
           System.out.println("List is empty.");
           return;
       }


       if (head.rollNo == rollNo) {
           head = head.next;
           System.out.println("Student deleted successfully.");
           return;
       }


       Student temp = head;


       while (temp.next != null && temp.next.rollNo != rollNo) {
           temp = temp.next;
       }


       if (temp.next == null) {
           System.out.println("Student not found.");
       } else {
           temp.next = temp.next.next;
           System.out.println("Student deleted successfully.");
       }
   }


   // Search student using roll number
   void search(int rollNo) {
       Student temp = head;


       while (temp != null) {
           if (temp.rollNo == rollNo) {
               System.out.println("Student Found!");
               System.out.println("Roll No: " + temp.rollNo);
               System.out.println("Name: " + temp.name);
               System.out.println("Course: " + temp.course);
               return;
           }


           temp = temp.next;
       }


       System.out.println("Student not found.");
   }


   // Update student details
   void update(int rollNo, String name, String course) {
       Student temp = head;


       while (temp != null) {
           if (temp.rollNo == rollNo) {
               temp.name = name;
               temp.course = course;


               System.out.println("Student details updated successfully.");
               return;
           }
           temp = temp.next;
       }


       System.out.println("Student not found.");
   }


   // Display all students
   void display() {
       if (head == null) {
           System.out.println("No students registered.");
           return;
       }


       Student temp = head;


       System.out.println("\n--- Student Registration List ---");


       while (temp != null) {
           System.out.println("Roll No: " + temp.rollNo);
           System.out.println("Name: " + temp.name);
           System.out.println("Course: " + temp.course);
           System.out.println("-------------------------");


           temp = temp.next;
       }
   }
}
public class StudentRegistration {
   public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       StudentLinkedList list = new StudentLinkedList();


       int choice;
       do {
           System.out.println("\n===== Student Registration System =====");
           System.out.println("1. Insert Student");
           System.out.println("2. Delete Student");
           System.out.println("3. Search Student");
           System.out.println("4. Update Student");
           System.out.println("5. Display Students");
           System.out.println("6. Exit");
           System.out.print("Enter your choice: ");


           choice = sc.nextInt();
           sc.nextLine();
           switch (choice) {
               case 1:
                   System.out.print("Enter Roll No: ");
                   int rollNo = sc.nextInt();
                   sc.nextLine();


                   System.out.print("Enter Name: ");
                   String name = sc.nextLine();


                   System.out.print("Enter Course: ");
                   String course = sc.nextLine();
                   list.insert(rollNo, name, course);
                   break;
               case 2:
                   System.out.print("Enter Roll No to delete: ");
                   rollNo = sc.nextInt()
                   list.delete(rollNo);
                   break;


               case 3:
                   System.out.print("Enter Roll No to search: ");
                   rollNo = sc.nextInt();
                   list.search(rollNo);
                   break;
               case 4:
                   System.out.print("Enter Roll No to update: ");
                   rollNo = sc.nextInt();
                   sc.nextLine();
                   System.out.print("Enter new Name: ");
                   name = sc.nextLine();
                   System.out.print("Enter new Course: ");
                   course = sc.nextLine();
                   list.update(rollNo, name, course);
                   break;


               case 5:
                   list.display();
                   break;


               case 6:
                   System.out.println("Exiting program...");
                   break;


               default:
                   System.out.println("Invalid choice!");
           }
       } while (choice != 6);
       sc.close();
   }
}
