import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Calculator {
    private static final String OPERATORS = "+-*/^";
    private static final int[] PRECEDENCE = {1, 1, 2, 2, 3};

    double calculate(String expression) {
        Queue<String> queue = convertToRPN(expression);
        Stack<Double> stack = new Stack<>();

        while (!queue.isEmpty()) {
            String token = queue.poll();

            if (OPERATORS.contains(token)) {
                double rightOperand = stack.pop();
                double leftOperand = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(leftOperand + rightOperand);
                        break;
                    case "-":
                        stack.push(leftOperand - rightOperand);
                        break;
                    case "*":
                        stack.push(leftOperand * rightOperand);
                        break;
                    case "/":
                        stack.push(leftOperand / rightOperand);
                        break;
                    case "^":
                        stack.push(Math.pow(leftOperand, rightOperand));
                        break;
                }
            } else {
                stack.push(Double.parseDouble(token));
            }
        }

        return stack.pop();
    }

    private Queue<String> convertToRPN(String expression) {
        Queue<String> outputQueue = new LinkedList<>();
        Stack<String> stack = new Stack<>();

        StringTokenizer tokenizer = new StringTokenizer(expression, OPERATORS + "()", true);

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();

            if (OPERATORS.contains(token)) {
                while (!stack.isEmpty() && isHigherPrecedence(token, stack.peek())) {
                    outputQueue.add(stack.pop());
                }
                stack.push(token);
            } else if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.peek().equals("(")) {
                    outputQueue.add(stack.pop());
                }
                stack.pop();
            } else {
                outputQueue.add(token);
            }
        }

        while (!stack.isEmpty()) {
            outputQueue.add(stack.pop());
        }

        return outputQueue;
    }

    private boolean isHigherPrecedence(String op1, String op2) {
        return (OPERATORS.contains(op2) && PRECEDENCE[OPERATORS.indexOf(op2)] >= PRECEDENCE[OPERATORS.indexOf(op1)]);
    }
}