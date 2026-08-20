import java.util.Scanner;

// Stack Implementation using Array to simulate Browser History
class BrowserStack {
    private String[] stack;
    private int top;
    private int capacity;

    // Constructor to initialize the stack
    public BrowserStack(int size) {
        this.capacity = size;
        this.stack = new String[capacity];
        this.top = -1; // -1 represents an empty stack
    }

    // Push Operation: Store a newly visited URL
    public void push(String url) {
        if (top == capacity - 1) {
            System.out.println("Error: Browser History is Full (Stack Overflow)!");
            return;
        }
        stack[++top] = url;
        System.out.println("Success -> Visited: " + url);
    }

    // Pop Operation: Go back to the previous webpage
    public String pop() {
        if (top == -1) {
            System.out.println("Error: No history to go back to (Stack Underflow)!");
            return null;
        }
        return stack[top--];
    }

    // Peek Operation: View current webpage
    public String peek() {
        if (top == -1) {
            System.out.println("Notification: You are on the Home Page (No active URL).");
            return null;
        }
        return stack[top];
    }

    // Display Operation: Show the complete visited history stack
    public void display() {
        if (top == -1) {
            System.out.println("Browser History is empty.");
            return;
        }
        System.out.println("\n--- Browser History (Recent -> Oldest) ---");
        for (int i = top; i >= 0; i--) {
            if (i == top) {
                System.out.println("[ " + i + " ] " + stack[i] + "  <-- Current Page");
            } else {
                System.out.println("[ " + i + " ] " + stack[i]);
            }
        }
        System.out.println("-------------------------------------------\n");
    }
}

// Main class providing a menu-driven simulation
public class BrowserHistorySimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter max size of Browser History Stack: ");
        int size = scanner.nextInt();
        scanner.nextLine(); // Clear scanner buffer

        BrowserStack browserHistory = new BrowserStack(size);
        int choice;

        do {
            System.out.println("\n====== BROWSER NAVIGATION SIMULATOR ======");
            System.out.println("1. Visit New Page (Push)");
            System.out.println("2. Click Back Button (Pop)");
            System.out.println("3. Peek Current Page (Peek)");
            System.out.println("4. Display History (Display)");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // Clear scanner buffer

            switch (choice) {
                case 1:
                    System.out.print("Enter URL to visit (e.g., google.com): ");
                    String url = scanner.nextLine();
                    browserHistory.push(url);
                    break;

                case 2:
                    String removedPage = browserHistory.pop();
                    if (removedPage != null) {
                        System.out.println("Action -> Left page: " + removedPage);
                        String currentPage = browserHistory.peek();
                        if (currentPage != null) {
                            System.out.println("Action -> Now on page: " + currentPage);
                        }
                    }
                    break;

                case 3:
                    String topPage = browserHistory.peek();
                    if (topPage != null) {
                        System.out.println("Current Page details: " + topPage);
                    }
                    break;

                case 4:
                    browserHistory.display();
                    break;

                case 5:
                    System.out.println("Closing simulation. History cleared!");
                    break;

                default:
                    System.out.println("Invalid choice. Please select between 1 and 5.");
            }
        } while (choice != 5);

        scanner.close();
    }
}