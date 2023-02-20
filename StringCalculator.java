import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringCalculator{
    public int add(String nums) throws NegativeNumberException {
        // Initializing lists
        List<String> delimiters = new ArrayList<>();
        List<String> input;
        List<String> numbers;

        // Delimiters assigned by default
        delimiters.add("\n");

        // User-defined delimiter parsing
        if(nums.startsWith("//")) {
            // Get first line
            input = Arrays.asList(nums.split("\n"));
            // Check if we have multiple delimiters / delimiters with multiple characters
            if(input.get(0).contains("[")) {
                // Putt 1 line with delimiters in allDelimiters var
                String allDelimiters = input.get(0);
                // Parsing it
                allDelimiters = allDelimiters.replaceAll("//", "");
                // Putt different delimiters in array
                String[] delPatterns = allDelimiters.split("]");
                for (String s : delPatterns) {
                    s = s.replaceAll("\\[", "");
                    // Get delimiter pattern
                    delimiters.add(Character.toString(s.charAt(1)));
                }
            }
            // Remove first line with custom delimiter
            nums = nums.substring(nums.indexOf('\n')+1);
        }

        // Replacing all delimiter variations with one ',' sign
        for(String d : delimiters) {
            nums = nums.trim().replaceAll(String.format("\\%s", d), ",");
            nums = nums.trim().replaceAll(",+", ",");
        }

        // Initializing output lists
        numbers = Arrays.asList(nums.split(","));
        List<Integer> resultList = new ArrayList<>();
        List<Integer> negativeNumbers = new ArrayList<>();

        // Putting numbers to output list
        for(String s : numbers) {
            int number = Integer.parseInt(s);
            if(number < 0) {
                //negative number going to negativeNumbers list
                negativeNumbers.add(number);
            }
            else if((number > 0) && (number < 1001)){
                resultList.add(Integer.parseInt(s));
            }
        }

        // Throwing exception is there are negative numbers
        if(!negativeNumbers.isEmpty()) {
            throw new NegativeNumberException("Negative numbers in the expression: " + negativeNumbers);
        } else {
            // Calculate the sum of all numbers in result list
            int result = 0;
            for (Integer i : resultList) {
                result += i;
            }
            return result;
        }
    }
}