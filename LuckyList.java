package lab;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LuckyList {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int x = 0, sum;
        List<ArrayList<Integer>> tickets = new ArrayList<ArrayList<Integer>>();
        List<ArrayList<Integer>> sumArr = new ArrayList<ArrayList<Integer>>();
        sumArr = sumArrGen();
        for (int l1 = 0; l1 < 10; l1++) {
            for (int l2 = 0; l2 < 10; l2++) {
                for (int l3 = 0; l3 < 10; l3++) {
                    sum = l1 + l2 + l3;
                    tickets.add(sumArr.get(sum));
                    x += sumArr.get(sum).size();
                }
            }
        }
        long list = System.currentTimeMillis();
        long listTime = list - start;
        System.out.println(x + " результатов.\nВремя составления и подсчета полного списка счастливых билетов: "
                + listTime + " мс.\nНомера билетов, являющихся суммой трех первых разрядов в некой степени:");
        long pwrTimeStart = System.currentTimeMillis();
        pwrLucky(tickets);
        long pwrTime = System.currentTimeMillis() - pwrTimeStart;
        System.out.println("На поиск и вывод на экран билетов с этими номерами было затрачено " + pwrTime + " мс.");
        luckyPrint(sumArr);
    }

    private static void luckyPrint(List<ArrayList<Integer>> sumArr) {
        Scanner left = new Scanner(System.in);
        boolean leftCheck = false;
        System.out.println("Введите левую часть билета для вывода всех вариантов счастливых билетов для неё (число от 0 до 999): ");
        while (!leftCheck) {
            if (!left.hasNextInt()) {
                System.out.println("Введен неподходящий тип данных! Нужно ввести целое число:");
                left.next();
                continue;
            }
            int leftInt = left.nextInt();
            if (leftInt >= 0 && leftInt <= 999) {
                leftCheck = true;
                int t1 = leftInt/100;
                int t2 = (leftInt%100)/10;
                int t3 = leftInt%10;
                int n = 0;
                System.out.print("Для данной левой части существует " + sumArr.get(t1 + t2 + t3).size() + " вариантов счастливых билетов:");
                for (int ticket : sumArr.get(t1 + t2 + t3)) {
                    if (n % 10 == 0) System.out.println();
                    n++;
                    System.out.printf("%03d%03d ", leftInt, ticket);
                }
            } else {
                System.out.println("Нужно ввести целое число от 0 до 999:");
                continue;
            }
        }
        left.close();
    }

    private static void pwrLucky(List<ArrayList<Integer>> tickets) {
        for (int l1 = 0; l1 < 10; l1++) {
            for (int l2 = 0; l2 < 10; l2++) {
                for (int l3 = 0; l3 < 10; l3++) {
                    int left = l1 * 100 + l2 * 10 + l3;
                    int sum = l1 + l2 + l3;
                    for (int ticket : tickets.get(left)) {
                        int n = 1;
                        double pwr = -1;
                        int number = left * 1000 + ticket;
                        while (pwr <= number && n < 20) {
                            pwr = Math.pow(sum, n);
                            if (number == pwr) {
                                System.out.printf(sum + "^" + n + " = %06d\n", number);
                                break;
                            }
                            n++;
                        }
                    }
                }
            }
        }
    }

    private static List<ArrayList<Integer>> sumArrGen() {
        List<ArrayList<Integer>> sumArr = new ArrayList<ArrayList<Integer>>();
        for (int n = 0; n < 28; n++) {
            ArrayList<Integer> luckyRight = new ArrayList<Integer>();
            int[] min = new int[3];
            min = minimum(n);
            luckyRight.addAll(lucky(min[0], min[1], min[2], n));
            sumArr.add(luckyRight);
        }
        return sumArr;
    }

    private static ArrayList<Integer> lucky(int r1, int r2, int r3, int sum) {
        int w = -1;
        ArrayList<Integer> right = new ArrayList<Integer>();
        int max = r1 * 100 + r2 * 10 + r3;
        while (w < max) {
            w = r3 * 100 + r2 * 10 + r1;
            if (r1 + r2 + r3 == sum)
                right.add(w);
            if (r1 > 0) {
                r1--;
                if (r2 < 9)
                    r2++;
                else {
                    r2 = 0;
                    r3++;
                }
            } else
                r1 = 9;
        }
        return right;
    }

    private static int[] minimum(int i) {
        int[] minimum = new int[3];
        if (i < 10) {
            minimum[0] = i;
        } else if (i > 18) {
            minimum[0] = 9;
            minimum[1] = 9;
            minimum[2] = i - 18;
        } else {
            minimum[0] = 9;
            minimum[1] = i - 9;
        }
        return minimum;
    }
}