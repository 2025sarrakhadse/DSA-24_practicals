import java.util.Scanner;

// Custom character stack implementation using an array
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
            return '\0'; // Return null char if stack is empty
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

    // Helper method to return precedence of operators
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
        return -1; // For operands and parenthesis
    }

    // Conversion method using the custom CharStack
    public static String convert(String infix) {
        StringBuilder postfix = new StringBuilder();
        CharStack stack = new CharStack(infix.length());

        for (int i = 0; i < infix.length(); i++) {
            char ch = infix.charAt(i);

            // Case 1: If character is alphanumeric (operand), add to output
            if (Character.isLetterOrDigit(ch)) {
                postfix.append(ch);
            }
            // Case 2: If open parenthesis, push to stack
            else if (ch == '(') {
                stack.push(ch);
            }
            // Case 3: If closing parenthesis, pop until '(' is found
            else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                stack.pop(); // Pop out and discard '('
            }
            // Case 4: If an operator is encountered
            else if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^') {
                while (!stack.isEmpty() && getPrecedence(ch) <= getPrecedence(stack.peek())) {
                    postfix.append(stack.pop());
                }
                stack.push(ch);
            }
        }

        // Pop all remaining operators from the stack
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

        // Strip spaces for clean processing
        String formattedInfix = infix.replaceAll("\\s+", "");
        String postfix = convert(formattedInfix);

        System.out.println("\n--- Conversion Summary ---");
        System.out.println("Infix Notation:   " + infix);
        System.out.println("Postfix Notation: " + postfix);
        System.out.println("--------------------------");

        scanner.close();
    }
}
