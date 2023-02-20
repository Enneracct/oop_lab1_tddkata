public class Main {
    public static void main(String[] args) throws NegativeNumberException {
        StringCalculator calc = new StringCalculator();
        int result = calc.add("//[???][%%%]\n1???8000,2???3\n4???5???2000%%%5");
        System.out.println(result);
    }
}
