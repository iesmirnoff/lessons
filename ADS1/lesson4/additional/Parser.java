package com.iesmirnoff;

import java.util.Arrays;
import java.util.LinkedList;

public class Parser {

    static boolean checkBrackets(String string) {
        Stack<Character> brackets = new Stack<>();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c == ')' && brackets.size() == 0) {
                return false;
            }
            if (c == '(') {
                brackets.push(c);
            } else if (c == ')') {
                brackets.pop();
            }
        }
        return brackets.size() == 0;
    }

    static Integer calculateExpression(String expressionString) {
        Stack<String> expression = new Stack<>(expressionString.split(" "));
        Stack<Integer> result = new Stack<>();
        while (expression.size() > 0) {
            String itm = expression.pop();
            try {
                result.push(Integer.parseInt(itm));
            } catch (NumberFormatException e) {
                switch (itm) {
                    case "+" -> sum(result);
                    case "*" -> multiply(result);
                    case "=" -> {
                        return result.pop();
                    }
                }
            }
        }
        return null;
    }

    private static void sum(Stack<Integer> result) {
        int val1 = result.pop();
        int val2 = result.pop();
        result.push(val1 + val2);
    }

    private static void multiply(Stack<Integer> result) {
        int val1 = result.pop();
        int val2 = result.pop();
        result.push(val1 * val2);
    }

    public static class Stack<T> {
        private final LinkedList<T> buffer;

        public Stack() {
            // инициализация внутреннего хранилища стека
            buffer = new LinkedList<>();
        }

        public Stack(T[] array) {
            // инициализация внутреннего хранилища стека
            buffer = new LinkedList<>(Arrays.asList(array));
        }

        public int size() {
            // размер текущего стека
            return buffer.size();
        }

        public T pop() {
            // ваш код
            if (buffer.isEmpty()) {
                return null;
            }
            return buffer.removeFirst();
        }

        public void push(T val) {
            // ваш код
            buffer.push(val);
        }

        public T peek() {
            // ваш код
            return buffer.peek();
        }
    }
}
