package lab;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

public class Base {
    public static void main(String[] args) throws IOException {
        fileCreate("base.txt");
        Vector<HashMap> base = baseCreate("base.txt");
        System.out.println("Полное содержимое базы:");
        printbase(base);
        System.out.println("Поиск по базе:");
        HashMap input = inputFilter();
        Vector<HashMap> result = baseFilter(base, input);
        printbase(result);
    }

    private static Vector<HashMap> baseFilter(Vector<HashMap> base, HashMap input) {
        Vector<HashMap> result = new Vector<HashMap>();
        Iterator it = base.iterator();
        while(it.hasNext()) {
            boolean checkMin = true, checkMax = true, checkNameArt = true, checkQuan = true;
            HashMap tmp = (HashMap) it.next();
            if (input.containsKey("quan")) {
                int quan = (Integer) tmp.get("quan");
                if (quan <= 0) checkQuan = false;
            }
            if (input.containsKey("maxPrice")) {
                int price = (Integer) tmp.get("price");
                int maxPrice = (Integer) input.get("maxPrice");
                if (price > maxPrice - 1) checkMax = false;
            }
            if (input.containsKey("minPrice")) {
                int price = (Integer) tmp.get("price");
                int minPrice = (Integer) input.get("minPrice");
                if (price < minPrice + 1) checkMin = false;
            }
            if (input.containsKey("search")) {
                String prod = ((String) tmp.get("prod")).toLowerCase();
                String art = ((String) tmp.get("art")).toLowerCase();
                String search = ((String) input.get("search"));
                StringTokenizer searchToken = new StringTokenizer(search, " ");
                while(searchToken.hasMoreTokens()){
                    checkNameArt = true;
                    String tmpToken = searchToken.nextToken();
                    if ((!prod.contains(tmpToken)) && (!art.contains(tmpToken)))
                        checkNameArt = false;
                    if (checkMin && checkMax && checkNameArt && checkQuan) {
                        if (!result.contains(tmp))
                            result.add(tmp);
                    }
                }
            }
            if (checkMin && checkMax && checkNameArt && checkQuan && !input.isEmpty()) {
                if (!result.contains(tmp))
                    result.add(tmp);
            }
            if (input.containsKey("sort")) {
                String sort = ((String) input.get("sort"));
                Collections.sort(result, new Comparator<HashMap>(){
                    public int compare(HashMap one, HashMap two) {
                        return one.get(sort).toString().compareTo(two.get(sort).toString());
                    }
                });
            }
        }
        return result;
    }

    private static HashMap inputFilter() {
        Scanner input = new Scanner(System.in);
        HashMap inputFiltered = new HashMap();
        while (inputFiltered.isEmpty()) {
            String str = input.nextLine();
            StringTokenizer st = new StringTokenizer(str, " ");
            String search = "";
            String keyList = "дешевле дороже в наличии сортировка по";
            while(st.hasMoreTokens()){
                String token = st.nextToken().toLowerCase();
                if (token.equals("дешевле") && st.hasMoreTokens()) {
                    String token2 = st.nextToken().toLowerCase();
                    String strMax = token2.replaceAll("[^0-9]", "");
                    if (strMax.isEmpty()) {
                        token = token2;
                    }
                    else inputFiltered.put("maxPrice", Integer.parseInt(strMax));
                }
                if (token.equals("дороже") && st.hasMoreTokens()) {
                    String token2 = st.nextToken().toLowerCase();
                    String strMin = token2.replaceAll("[^0-9]", "");
                    if (strMin.isEmpty()) {
                        token = token2;
                    }
                    else inputFiltered.put("minPrice", Integer.parseInt(strMin));
                }
                if (token.equals("наличии")) inputFiltered.put("quan", 1);
                if (token.equals("сортировка") && st.hasMoreTokens()) {
                    String token2 = st.nextToken().toLowerCase();
                    if (token2.equals("по") && st.hasMoreTokens()) {
                        String token3 = st.nextToken().toLowerCase();
                        if (token3.equals("названию"))
                            inputFiltered.put("sort", "prod");
                        else if (token3.equals("артикулу"))
                            inputFiltered.put("sort", "art");
                        else if (token3.equals("цене"))
                            inputFiltered.put("sort", "price");
                        else token = token3;
                    }
                    else token = token2;
                }
                if (!keyList.contains(token)){
                    search = search + " " + token;
                }
            }
            if (!search.isEmpty()) {
                inputFiltered.put("search", search.trim());
            }
        }
        input.close();
        return inputFiltered;
    }

    private static void printbase(Vector<HashMap> base) {
        System.out.println("---------------------------------");
        System.out.println("|    Название    |Артикул| Цена |");
        System.out.println("---------------------------------");
        if (base.isEmpty())
            System.out.println("|       Ничего не найдено!      |");
        else {
            Iterator<HashMap> it = base.iterator();
            while(it.hasNext()) {
                HashMap tmp = it.next();
                String prod = (String) tmp.get("prod");
                String art = (String) tmp.get("art");
                int price = (Integer) tmp.get("price");
                System.out.printf("|%15s |%6s |%5s |\n", prod, art, price);
            }
        }
        System.out.println("---------------------------------");
    }

    private static Vector baseCreate(String string) throws IOException {
        String str;
        Vector<HashMap> base = new Vector<HashMap>();
        FileReader fin = new FileReader(string);
        Scanner src = new Scanner(fin);
        while (src.hasNextLine()) {
            str = src.nextLine();
            StringTokenizer st = new StringTokenizer(str, ";\n");
            while(st.hasMoreTokens()){
                HashMap h = new HashMap();
                h.put("prod", st.nextToken());
                h.put("art", st.nextToken());
                h.put("quan", Integer.parseInt(st.nextToken()));
                h.put("price", Integer.parseInt(st.nextToken()));
                base.add(h);
            }
        }
        fin.close();
        src.close();
        return base;
    }

    private static void fileCreate(String string) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(string);
        out.print("Монитор;757nf;12;5000\n" +
                "Сканер;960cx;5;2500\n" +
                "Монитор;568kf;23;3700\n" +
                "Системный блок;897sy;12;4600\n" +
                "Сканер;156df;9;3000\n" +
                "Системный блок;845lh;14;7000\n" +
                "Системный блок;862df;0;4000\n" +
                "Сканер;148gh;0;2300\n" +
                "Монитор;554kl;0;7000\n");
        out.close();
    }
}