package lab;

class SortObj<Type extends Comparable<Type>> {
    Type[] arr;

    SortObj(Type[] o) {
        arr = o;
    }

    int less(Type a, Type a2) { // метод сравнивает два элемента
        return a.compareTo(a2);
    }

    void exch(int i, int j) { // метод обменивает два элемента
        Type t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    void compExch(int i, int j) { // метод сравнивает и обменивает два элемента
        if (less(arr[j], arr[i]) < 0)
            exch(i, j);
    }

    void sort(int l, int r) {
        for (int i = l + 1; i <= r; i++)
            for (int j = i; j > l; j--)
                compExch(j - 1, j);
    }

    void printArr(String sort) {
        String typeArr = arr[0].getClass().getName();
        String last = typeArr.substring(typeArr.lastIndexOf(".") + 1);
        System.out.println(sort + " массив " + last + ": ");
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    int getLength(int n) {
        return arr.length + n;
    }
}

public class ArraySort {
    public static void main(String[] args) {
        try {
            Byte byteArr[] = { 3, 1, 2, 5, 4 };
            Short shortArr[] = { 7, 8, 6, 9, 10 };
            Integer intArr[] = { 13, 12, 15, 11, 14 };
            Long longArr[] = { 20L, 18L, 16L, 17L, 19L };
            Float floatArr[] = { 22.15f, 21.64f, 24.84f, 23.49f, 25.67f };
            Double dblArr[] = { 29.254, 30.657, 26.489, 28.687, 27.326 };
            Character charArr[] = { 'ы', 1065, 'ъ', 's', 1020 };
            Boolean boolArr[] = { true, false, false, true, false };
            String strArr[] = { "you", "shall", "not", "pass", "!" };
            SortObj<?> arrOfArrs[] = {new SortObj<Byte>(byteArr),
                    new SortObj<Short>(shortArr),
                    new SortObj<Integer>(intArr),
                    new SortObj<Long>(longArr),
                    new SortObj<Float>(floatArr),
                    new SortObj<Double>(dblArr),
                    new SortObj<Character>(charArr),
                    new SortObj<Boolean>(boolArr),
                    new SortObj<String>(strArr)};
            for (SortObj<?> i : arrOfArrs) {
                i.printArr("Несортированный");
                i.sort(0, i.getLength(-1));
                i.printArr("Сортированный");
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.toString());
            e.printStackTrace();
        }
    }
}