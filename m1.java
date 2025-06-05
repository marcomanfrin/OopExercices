public class m1 {

    public static void main(String[] args) {
        // 1. Type declarations and conversion
        int i = 42;
        double d = i; // implicit conversion
        String s = "Hello";
        boolean b = true;
        System.out.println("int: " + i + ", double: " + d + ", String: " + s + ", boolean: " + b);

        // 2. Float operations
        float f1 = 5.5f, f2 = 2.0f;
        System.out.printf("Sum: %.2f, Diff: %.2f, Prod: %.2f, Quot: %.2f\n",
                f1 + f2, f1 - f2, f1 * f2, f1 / f2);

        // 3. Largest of three
        System.out.println("Largest: " + largestOfThree(5, 9, 3));

        // 4. Quadratic roots
        quadraticRoots(1, -3, 2);

        // 5. Prime check
        System.out.println("Is prime: " + isPrime(13));

        // 6. Rectangle perimeter
        System.out.println("Perimeter: " + perimeterRectangle(5, 3));

        // 7. Odd check
        System.out.println("Is odd (1=odd): " + isOdd(7));

        // 8. Triangle area
        System.out.println("Area: " + triangleArea(3, 4, 5));

        // 9. String concatenation
        concatStrings("One", "Two", "Three");

        // 10. Word count
        countWords("Java is fun and powerful");

        // 11. Substring extraction
        substringFrom("Programming", 3);

        // 12. Palindrome check
        System.out.println("Is palindrome: " + isPalindrome("A man a plan a canal Panama"));

        // 13. Char count
        System.out.println("Occurrences: " + countChar("banana", 'a'));

        // 14. Vowel count
        System.out.println("Vowel count: " + countVowels("Hello World"));

        // 15. First occurrence index
        findFirstIndex("Hello", 'e');

        // 16. Factorial
        System.out.println("Factorial: " + factorial(5));

        // 17. Fibonacci
        fibonacci(10);

        // 18. GCD
        System.out.println("GCD: " + gcd(20, 8));

        // 19. Multiplication table
        multiplicationTable(5);

        // 20. Numeric pattern
        numberPattern(5);

        // 21. N-th prime
        System.out.println("Nth prime: " + nthPrime(10));

        // 22. Max subarray sum
        System.out.println("Max subarray sum: " + maxSubarraySum(new int[]{-2,1,-3,4,-1,2,1,-5,4}));

        // 23. Rotate array
        int[] arr = {1, 2, 3, 4, 5};
        rotateArray(arr, 2);
        for (int num : arr) System.out.print(num + " ");
        System.out.println();

        // 24. Balanced brackets
        System.out.println("Balanced: " + isBalanced("{[()]}"));
    }

    static int largestOfThree(int a, int b, int c) {
        if (a >= b && a >= c) return a;
        else if (b >= c) return b;
        return c;
    }

    static void quadraticRoots(double a, double b, double c) {
        double discriminant = b * b - 4 * a * c;
        if (discriminant < 0) System.out.println("No real roots");
        else {
            double r1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double r2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            System.out.println("Roots: " + r1 + ", " + r2);
        }
    }

    static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) if (n % i == 0) return false;
        return true;
    }

    static double perimeterRectangle(double l, double w) {
        return 2 * (l + w);
    }

    static int isOdd(int num) {
        return num % 2 != 0 ? 1 : 0;
    }

    static double triangleArea(double a, double b, double c) {
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    static void concatStrings(String a, String b, String c) {
        String normal = a + b + c;
        String reverse = c + b + a;
        System.out.println("Normal: " + normal);
        System.out.println("Reverse: " + reverse);
    }

    static void countWords(String sentence) {
        String[] words = sentence.trim().split("\\s+");
        System.out.println("Word count: " + words.length);
    }

    static void substringFrom(String s, int index) {
        if (index < s.length())
            System.out.println("Substring: " + s.substring(index));
        else
            System.out.println("Index out of bounds");
    }

    static boolean isPalindrome(String s) {
        s = s.replaceAll("\\s+", "").toLowerCase();
        return s.equals(new StringBuilder(s).reverse().toString());
    }

    static int countChar(String s, char c) {
        int count = 0;
        for (char ch : s.toCharArray()) if (ch == c) count++;
        return count;
    }

    static int countVowels(String s) {
        int count = 0;
        for (char c : s.toLowerCase().toCharArray())
            if ("aeiou".indexOf(c) != -1) count++;
        return count;
    }

    static void findFirstIndex(String s, char c) {
        int idx = s.indexOf(c);
        System.out.println(idx != -1 ? "Index: " + idx : "Character not found");
    }

    static int factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }

    static void fibonacci(int n) {
        int a = 0, b = 1;
        for (int i = 0; i < n; i++) {
            System.out.print(a + " ");
            int next = a + b;
            a = b;
            b = next;
        }
        System.out.println();
    }

    static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    static void multiplicationTable(int n) {
        for (int i = 1; i <= 10; i++)
            System.out.println(n + " x " + i + " = " + (n * i));
    }

    static void numberPattern(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++)
                System.out.print(j + " ");
            System.out.println();
        }
    }

    static int nthPrime(int n) {
        int count = 0, num = 1;
        while (count < n) {
            num++;
            if (isPrime(num)) count++;
        }
        return num;
    }

    static int maxSubarraySum(int[] arr) {
        int max = arr[0], curr = arr[0];
        for (int i = 1; i < arr.length; i++) {
            curr = Math.max(arr[i], curr + arr[i]);
            max = Math.max(max, curr);
        }
        return max;
    }

    static void rotateArray(int[] arr, int k) {
        int n = arr.length;
        k = k % n;
        reverse(arr, 0, n - 1);
        reverse(arr, 0, k - 1);
        reverse(arr, k, n - 1);
    }

    static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    static boolean isBalanced(String s) {
        java.util.Stack<Character> stack = new java.util.Stack<>();
        for (char c : s.toCharArray()) {
            if ("({[".indexOf(c) != -1) stack.push(c);
            else if ("])}".indexOf(c) != -1) {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                    (c == ']' && top != '[') ||
                    (c == '}' && top != '{')) return false;
            }
        }
        return stack.isEmpty();
    }
}
