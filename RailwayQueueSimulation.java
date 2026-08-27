import java.util.Scanner;

// Linear Queue Implementation using Array
class LinearQueue {
    private String[] queue;
    private int front;
    private int rear;
    private int capacity;

    // Constructor to initialize the queue
    public LinearQueue(int size) {
        this.capacity = size;
        this.queue = new String[capacity];
        this.front = -1; // -1 represents an empty queue state
        this.rear = -1;
    }

    // Enqueue Operation: Add customer to the back of the line
    public void enqueue(String customerName) {
        if (rear == capacity - 1) {
            System.out.println("Error: Ticket line is full (Queue Overflow)!");
            return;
        }
        if (front == -1) {
            front = 0; // Initialize front to 0 when the first element joins
        }
        queue[++rear] = customerName;
        System.out.println("Success -> " + customerName + " joined the queue.");
    }

    // Dequeue Operation: Serve the customer at the front
    public String dequeue() {
        if (front == -1) {
            System.out.println("Error: No customers in line to serve (Queue Underflow)!");
            return null;
        }
        String servedCustomer = queue[front];
        
        // If there was only one customer in line, reset the queue after serving
        if (front == rear) {
            front = -1;
            rear = -1;
        } else {
            front++; // Move front forward to the next customer
        }
        return servedCustomer;
    }

    // Peek Operation: View who is currently next in line
    public String peek() {
        if (front == -1) {
            System.out.println("Notification: The ticket line is currently empty.");
            return null;
        }
        return queue[front];
    }

    // Display Operation: Show the complete line from Front to Rear
    public void display() {
        if (front == -1) {
            System.out.println("The ticket line is empty.");
            return;
        }
        System.out.println("\n--- Current Ticket Line (Front -> Rear) ---");
        for (int i = front; i <= rear; i++) {
            if (i == front) {
                System.out.println("[ " + i + " ] " + queue[i] + "  <-- Next to be served");
            } else if (i == rear) {
                System.out.println("[ " + i + " ] " + queue[i] + "  <-- End of line");
            } else {
                System.out.println("[ " + i + " ] " + queue[i]);
            }
        }
        System.out.println("-------------------------------------------\n");
    }
}

// Main class providing a menu-driven simulation
public class RailwayQueueSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter max capacity of the Railway Counter line: ");
        int size = scanner.nextInt();
        scanner.nextLine(); // Clear scanner buffer

        LinearQueue counterLine = new LinearQueue(size);
        int choice;

        do {
            System.out.println("\n====== RAILWAY TICKET COUNTER SIMULATOR ======");
            System.out.println("1. Customer Arrives (Enqueue)");
            System.out.println("2. Serve Customer (Dequeue)");
            System.out.println("3. Peek Next Customer (Peek)");
            System.out.println("4. Display Current Queue (Display)");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number (1-5).");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // Clear scanner buffer

            switch (choice) {
                case 1:
                    System.out.print("Enter customer name: ");
                    String name = scanner.nextLine();
                    counterLine.enqueue(name);
                    break;

                case 2:
                    String served = counterLine.dequeue();
                    if (served != null) {
                        System.out.println("Action -> Served: " + served + " (Ticket Issued)");
                    }
                    break;

                case 3:
                    String nextInLine = counterLine.peek();
                    if (nextInLine != null) {
                        System.out.println("Next up for service: " + nextInLine);
                    }
                    break;

                case 4:
                    counterLine.display();
                    break;

                case 5:
                    System.out.println("Closing counter. Remaining queue dismissed!");
                    break;

                default:
                    System.out.println("Invalid choice. Please select between 1 and 5.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
