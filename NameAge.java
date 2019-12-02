package lab;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameAge {
    public static void main(String[] args) throws IOException {
        System.out.println("Введите имя и дату рождения в формате ДД.ММ.ГГГГ");
        wNameAge();
        rNameAge();
    }

    private static void rNameAge() throws IOException {
        BufferedReader nameAge = new BufferedReader(new FileReader("name.txt"));
        String[] arrNameAge = nameAge.readLine().split(" ", 4);
        nameAge.close();
        int day = Integer.parseInt(arrNameAge[1]);
        int mon = Integer.parseInt(arrNameAge[2]);
        int year = Integer.parseInt(arrNameAge[3]);
        Calendar cal = Calendar.getInstance();
        int nowYear = cal.get(Calendar.YEAR);
        int nowMon = cal.get(Calendar.MONTH);
        int nowDay = cal.get(Calendar.DAY_OF_MONTH);
        int age = nowYear - year;
        if (nowMon < mon) age--;
        if (nowMon == mon)
            if (nowDay < day) age--;
        System.out.print("Имя: " + arrNameAge[0] + "\nВозраст: " + age);
    }

    private static void wNameAge() throws FileNotFoundException {
        String bDay = null;
        String name = null;
        Pattern bDate = Pattern.compile("^\\d{2}[/,.]\\d{2}[/,.]\\d{4}$");
        Pattern yName = Pattern.compile("^\\D*$");
        Scanner input = new Scanner(System.in);
        while ((bDay == null) || (name == null)) {
            String str = input.nextLine();
            for (String val : str.split(" ")) {
                Matcher match = bDate.matcher(val);
                if (match.find()) bDay = val;
                match = yName.matcher(val);
                if (match.find()) name = val;
            }
        }
        input.close();
        String[] arrDate = bDay.split("[/,.]", 3);
        PrintWriter out = new PrintWriter("name.txt");
        out.print(name + " " + arrDate[0] + " " + arrDate[1] + " " + arrDate[2]);
        out.close();
    }
}