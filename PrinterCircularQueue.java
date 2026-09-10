import java.util.Scanner;

class CircularQueue {
    private String[] queue;
    private int front, rear, capacity;

    public CircularQueue(int size) {
        this.capacity = size;
        this.queue = new String[capacity];
        this.front = -1;
        this.rear = -1;
    }

    public void enqueue(String job) {
        if ((rear + 1) % capacity == front) {
            System.out.println("Error: Printer Queue Full (Overflow)!");
            return;
        }
        if (front == -1) front = 0;
        rear = (rear + 1) % capacity;
        queue[rear] = job;
        System.out.println("Success -> Job added: " + job);
    }

    public String dequeue() {
        if (front == -1) {
            System.out.println("Error: No jobs in queue (Underflow)!");
            return null;
        }
        String job = queue[front];
        if (front == rear) {
            front = -1;
            rear = -1;
        } else {
            front = (front + 1) % capacity;
        }
        return job;
    }

    public void display() {
        if (front == -1) {
            System.out.println("Queue is empty.");
            return;
        }
        System.out.print("Current Print Queue: ");
        int i = front;
        while (true) {
            System.out.print("[" + i + "]" + queue[i] + " ");
            if (i == rear) break;
            i = (i + 1) % capacity;
        }
        System.out.println();
    }
}

public class PrinterCircularQueue {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter printer queue capacity: ");
        int n = sc.nextInt();
        CircularQueue pq = new CircularQueue(n);
        int choice;
        do {
            System.out.println("\n--- PRINTER MANAGEMENT ---");
            System.out.println("1. Add Job\n2. Process Job\n3. Display\n4. Exit");
            choice = sc.nextInt();
            sc.nextLine(); 
            switch (choice) {
                case 1:
                    System.out.print("Job name: ");
                    pq.enqueue(sc.nextLine());
                    break;
                case 2:
                    String job = pq.dequeue();
                    if (job != null) System.out.println("Printing: " + job);
                    break;
                case 3: pq.display(); break;
            }
        } while (choice != 4);
        sc.close();
    }
}
