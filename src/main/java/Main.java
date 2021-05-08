public class Main {
    public static void main(String[] args){
        Data data = new Data("src/main/resources/hypoport.txt");

        System.out.println(data.getMinPrice());
        System.out.println(data.getMaxPrice());
        System.out.println(data.getMaxDifference());
        System.out.println(data.getAverageClosePrice());
    }
}
