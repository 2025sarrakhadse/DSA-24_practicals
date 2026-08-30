import java.util.Scanner;

class CharStack {
    private char[] stack;
    private int top;
    private int capacity;

    public CharStack(int size) {
        this.capacity = size;
        this.stack = new char[capacity];
        this.top = -1;
    }

    public void push(char c) {
        if (top == capacity - 1) {
            System.out.println("Stack Overflow Error!");
            return;
        }
        stack[++top] = c;
    }

    public char pop() {
        if (top == -1) {
            return '\0';
        }
        return stack[top--];
    }

    public char peek() {
        if (top == -1) {
            return '\0';
        }
        return stack[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }
}

public class InfixToPostfix {

    private static int getPrecedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    public static String convert(String infix) {
        StringBuilder postfix = new StringBuilder();
        CharStack stack = new CharStack(infix.length());

        for (int i = 0; i < infix.length(); i++) {
            char ch = infix.charAt(i);

            if (Character.isLetterOrDigit(ch)) {
                postfix.append(ch);
            }
            else if (ch == '(') {
                stack.push(ch);
            }
            else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                stack.pop(); 
            }
            else if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^') {
                while (!stack.isEmpty() && getPrecedence(ch) <= getPrecedence(stack.peek())) {
                    postfix.append(stack.pop());
                }
                stack.push(ch);
            }
        }

        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }

        return postfix.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("====== INFIX TO POSTFIX CONVERTER ======");
        System.out.print("Enter an Infix Expression (e.g., A+B*C or (A+B)*C): ");
        String infix = scanner.nextLine();

        String formattedInfix = infix.replaceAll("\\s+", "");
        String postfix = convert(formattedInfix);

        System.out.println("\n--- Conversion Summary ---");
        System.out.println("Infix Notation:   " + infix);
        System.out.println("Postfix Notation: " + postfix);
        System.out.println("--------------------------");

        scanner.close();
    }
}
