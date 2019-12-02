package lab;

import java.util.Scanner;

public class Point {
    public static void main(String[] args) {
        String[] form = {"y = [A]x + b", "y = ax + [B]"};
        double[] xy = new double[2];
        Scanner num = new Scanner(System.in);
        System.out.println("Введите формулу первой прямой:");
        double[] first = numScan(form, num);
        System.out.println("Введите формулу второй прямой:");
        double[] second = numScan(form, num);
        num.close();
        try {
            xy = crossPoint(first, second);
        }
        catch(Exception i) {
            System.out.println(i);
            return;
        }
        System.out.println("Точка пересечения прямых имеет координаты [" + xy[0] + ", " + xy[1] + "].");
    }

    private static double[] crossPoint(double[] first, double[] second) throws Exception {
        if (first[0] == second[0]) {
            if (first[1] == second[1])
                throw new Exception("Прямые совпадают!");
            throw new Exception("Прямые параллельны!");
        }
        double[] point = new double[2];
        point[0] = (second[1] - first[1]) / (first[0] - second[0]);
        point[1] = first[0] * point[0] + first[1];
        return point;
    }

    private static double[] numScan(String[] form, Scanner num) {
        double[] numbers = new double[2];
        int n = 0;
        for (String s : form) {
            System.out.println(s);
            while (true) {
                try {
                    numbers[n] = numCheck(num);
                    break;
                }
                catch(Exception i) {
                    System.out.println(i + " Введите число:");
                    num.next();
                }
            }
            n++;
        }
        return numbers;
    }

    private static double numCheck(Scanner num) throws Exception {
        if (!num.hasNextInt() && !num.hasNextDouble())
            throw new Exception("Введено не число.");
        return num.nextDouble();
    }
}