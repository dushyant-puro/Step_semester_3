package week_11.stacks_queues.assigment_problems;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Problem 3: The Quiz Scoring Formula
 *
 * Evaluates an infix expression in one left-to-right scan using an operand
 * stack and an operator stack. Operators of equal precedence are applied
 * left-to-right by reducing while the stack precedence is greater than or equal.
 */
public class QuizScoringFormula {

    public static int evaluate(String expr) {
        if (expr == null) {
            throw new IllegalArgumentException("Expression cannot be null.");
        }

        Deque<Integer> operands = new ArrayDeque<>();
        Deque<Character> operators = new ArrayDeque<>();

        int i = 0;

        while (i < expr.length()) {
            char ch = expr.charAt(i);

            if (Character.isWhitespace(ch)) {
                i++;
            } else if (ch >= '0' && ch <= '9') {
                // Read a complete non-negative, possibly multi-digit integer.
                int number = 0;
                while (i < expr.length()
                        && expr.charAt(i) >= '0'
                        && expr.charAt(i) <= '9') {
                    number = number * 10 + (expr.charAt(i) - '0');
                    i++;
                }
                operands.push(number);
            } else if (ch == '(') {
                operators.push(ch);
                i++;
            } else if (ch == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    applyTopOperator(operands, operators);
                }

                if (operators.isEmpty()) {
                    throw new IllegalArgumentException("Unmatched closing parenthesis.");
                }

                operators.pop(); // Discard the matching '('.
                i++;
            } else if (isOperator(ch)) {
                // Reduce operators of higher or equal precedence first.
                while (!operators.isEmpty()
                        && operators.peek() != '('
                        && precedence(operators.peek()) >= precedence(ch)) {
                    applyTopOperator(operands, operators);
                }

                operators.push(ch);
                i++;
            } else {
                throw new IllegalArgumentException("Invalid character: " + ch);
            }
        }

        while (!operators.isEmpty()) {
            if (operators.peek() == '(') {
                throw new IllegalArgumentException("Unmatched opening parenthesis.");
            }
            applyTopOperator(operands, operators);
        }

        if (operands.size() != 1) {
            throw new IllegalArgumentException("Invalid expression.");
        }

        return operands.pop();
    }

    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    private static int precedence(char operator) {
        if (operator == '*' || operator == '/') {
            return 2;
        }
        return 1;
    }

    private static void applyTopOperator(
            Deque<Integer> operands, Deque<Character> operators) {

        if (operands.size() < 2 || operators.isEmpty()) {
            throw new IllegalArgumentException("Invalid expression.");
        }

        char operator = operators.pop();
        int right = operands.pop();
        int left = operands.pop();
        int result;

        switch (operator) {
            case '+':
                result = left + right;
                break;
            case '-':
                result = left - right;
                break;
            case '*':
                result = left * right;
                break;
            case '/':
                // Java integer division truncates toward zero.
                result = left / right;
                break;
            default:
                throw new IllegalArgumentException("Unknown operator: " + operator);
        }

        operands.push(result);
    }

    public static void main(String[] args) {
        System.out.println(evaluate("2 * (3 + 4) - 10 / 5"));
        System.out.println(evaluate("(8 - 2) * (1 + 2) / 4"));
    }
}
