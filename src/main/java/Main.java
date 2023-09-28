import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        List<Word> words = List.of(
                Word.of("large", -3),
                Word.of("phone", -6),
                Word.of("newspaper", 2),
                Word.of("chocolate", -10),
                Word.of("connection", 13),
                Word.of("engineering", 10)
        );

        System.out.println(
                words.stream()
                        .filter(Predicate.not(Word::isBad))
                        .max(Comparator.comparing(Word::getPriority))
                        .map(Word::getValue)
                        .orElse("Nothing Found")
        );

        System.out.println(calculate(10, 9, 5));
    }
    @AllArgsConstructor
    @Getter
    public static class Word {

        private final String value;
        private final int priority;

        static Word of(String value, int priority){
            return new Word(value, priority);
        }

        public boolean isBad(){
            return this.value.contains("la");
        }
    }

    public static int calculate(int n, int left, int right) {

        int antDown = 0;
        if(left < right)
            antDown = NoIntersections(n, left, right);
        else if(left - right == 1)
            antDown = NoIntersections(n, right, left);
        else if(left - right == 2)
            antDown = NoIntersections(n, right - 1, left - 1) + 1;
        else {
            if((left - right) % 2 != 0) {
                antDown = NoIntersections(n, right, left) + left - right - 1;
            } else {
                antDown = NoIntersections(n, right, left) + parityCheck(n, right, left);
            }
        }

        return antDown;
    }
    public static int NoIntersections(int n, int left, int right){

        int antDown;
        if (n - right <= left)
            antDown = (n - right) + 1;
        else
            antDown = left + 1;
        return antDown;
    }

    public static int parityCheck(int n, int left, int right){

        int summ = right - left;

        if(left <= n - right + (right - left)/2)
            return summ-2;
        else
            return summ;
    }
}


