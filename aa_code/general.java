import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

import java.util.Map;public class general {

    public static void main(String[] args){
        //Создаем таблицу соответствий триплетов и аминокислот
        Map<String, String> nucToAmino = new HashMap<String, String>();
        nucToAmino.put("ggg","G");
        nucToAmino.put("gga","G");
        nucToAmino.put("ggc","G");
        nucToAmino.put("ggt","G");

        nucToAmino.put("cgt","R");
        nucToAmino.put("cgc","R");
        nucToAmino.put("cga","R");
        nucToAmino.put("cgg","R");
        nucToAmino.put("agc","R");
        nucToAmino.put("agg","R");

        nucToAmino.put("ttt","F");
        nucToAmino.put("ttc","F");

        nucToAmino.put("tta","L");
        nucToAmino.put("ttg","L");
        nucToAmino.put("ctt","L");
        nucToAmino.put("ctc","L");
        nucToAmino.put("cta","L");
        nucToAmino.put("ctg","L");

        nucToAmino.put("att","I");
        nucToAmino.put("atc","I");
        nucToAmino.put("atg","I");
        nucToAmino.put("ata","I");

        nucToAmino.put("atg","M");

        nucToAmino.put("gtc","V");
        nucToAmino.put("gta","V");
        nucToAmino.put("gtg","V");
        nucToAmino.put("gtt","V");

        nucToAmino.put("agt","S");
        nucToAmino.put("agc","S");
        nucToAmino.put("tca","S");
        nucToAmino.put("tcg","S");
        nucToAmino.put("tcc","S");
        nucToAmino.put("tct","S");

        nucToAmino.put("cca","P");
        nucToAmino.put("cct","P");
        nucToAmino.put("ccg","P");
        nucToAmino.put("ccc","P");

        nucToAmino.put("aca","T");
        nucToAmino.put("acc","T");
        nucToAmino.put("act","T");
        nucToAmino.put("acg","T");

        nucToAmino.put("gca","A");
        nucToAmino.put("gcc","A");
        nucToAmino.put("gct","A");
        nucToAmino.put("gcg","A");

        nucToAmino.put("taa","STOP");
        nucToAmino.put("tag","STOP");
        nucToAmino.put("tga","STOP");

        nucToAmino.put("tat","Y");
        nucToAmino.put("tac","Y");

        nucToAmino.put("cat","H");
        nucToAmino.put("cac","H");

        nucToAmino.put("caa","Q");
        nucToAmino.put("cag","Q");

        nucToAmino.put("aat","N");
        nucToAmino.put("aac","N");

        nucToAmino.put("aaa","K");
        nucToAmino.put("aag","K");

        nucToAmino.put("gat","D");
        nucToAmino.put("gac","D");

        nucToAmino.put("gaa","E");
        nucToAmino.put("gag","E");

        nucToAmino.put("tgu","C");
        nucToAmino.put("tgc","C");

        nucToAmino.put("tgg","W");

        //Здесь используем дерево для последующей сортировки. Упростили себе жизнь.
        TreeSet<String> set = new TreeSet<String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1.length() - o2.length()) >= 0 ? 1 : -1;
            }
        });

        try{
            List<String> temp = new ArrayList();//Список для пептидов
            BufferedReader br1 = new BufferedReader(new FileReader("seq.txt"));//Читаем файл
            String str; // считываемая строка
            while ((str = br1.readLine()) != null){
                String sum = ""; //строка пептида(одного)
                if (str.length()%3 == 0){//проверяем, что мы вообще можем считать полностью, без потерь
                    List<String> aminoList = new ArrayList();//список для последовательности аминокислот
                    String[] test = str.split("(?<=\\G.{3})");//режем входную строку на куски по три буквы и пишем в строковый массив
                    for(String i: test) aminoList.add(nucToAmino.get(i));//пишем в массив для аминокислот ту, которую нашли по ключу (совпадению) из нашего считанного массива
                    for(String i: aminoList) sum+=i;//собираем отдельные буквы в строку
                    temp.add(sum);//добавляем в наш массив пептидов
                }
                else {
                    //Выводим на экран строку, которую не получилось перевести в пептид, в файл не пишем
                    System.out.print(":("+str);
                }
            }
            //Преобразование из списка в обычный строковый массив
            String[] t = temp.toArray(new String[temp.size()]);
            //Запись строкововго массива в наше дерево для сортировки
            for (String i: t) set.add(i.toString());

            //Вывод на экран
            for (String i: set) System.out.println(i.toString());

            //блок для записи нашего отсортированного массива пептидов в файл
            try(FileWriter writer = new FileWriter("seq_out.txt", false))
            {
               for (String i: set) writer.write(i.toString()+"\n");
                writer.flush();
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}

