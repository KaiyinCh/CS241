import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FruitStand {
    private class Fruit {
        private int id;
        private String name;
        private double unitPrice;
        public Fruit(int id, String name, double price) {
            this.id = id;
            this.name = name;
            this.unitPrice = price;
        }
    }
    MyHashTableKV<Integer, Fruit> t = new MyHashTableKV<Integer, Fruit>();
    public FruitStand(String path) {
        //TODO: your code here
        try{
            File f = new File(path);
            Scanner scan = new Scanner (f);
            while(scan.hasNext()){
                String l = scan.nextLine();
                String[] data = l.split(",");
                Integer id = Integer.parseInt(data[0]);
                String name = data[1];
                double price = Double.parseDouble(data[2]);

                Fruit a = new Fruit(id, name, price);
                t.put(id, a);
            }
            scan.close();
        }catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
    }

    public double lookup_price(int key) {
        //TODO: your code here
        if (t.contains(key)){
            return t.get(key).unitPrice;
        }else{
            throw new NoSuchElementException();
        }

    }

}
