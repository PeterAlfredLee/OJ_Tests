public class Solution1 {
    public static void main(String[] args) {
        int[] productions = new int[]{3, 4, 5, 2, 3, 4, 5, 4};
        int[] plans = new int[]{1, 0, 1, 0, 1, 0, 1, 0};
        int window = 3;
        System.out.println(24 == getMaxQu(productions, plans, window));


//        int[] productions = new int[]{803, 804, 805, 802, 803, 804, 805, 804};
//        int[] plans = new int[]{1, 0, 1, 0, 1, 0, 1, 0};
//        int window = 3;

//        System.out.println(4824 == getMaxQu(productions, plans, 3));
       // System.out.println(getMaxQu(productions, plans, 3));

//        int[] productions = new int[]{980, 910, 1000, 940, 973};
//        int[] plans = new int[]{1, 1, 1, 1, 1};
//        int window = 3;
//
//        System.out.println(getMaxQu(productions, plans, 5));


//        用例二：
//        5
//        980 910 1000 940 973
//        1 1 1 1 1
//        5
//
//
//        输出
//        4803
    }


    private static int getMaxQu(int productions[], int[] plans, int window) {
        int[] preSum = new int[productions.length];
        preSum[0] = plans[0] == 0 ? productions[0] : 0;

        int result = plans[0] == 0 ? 0 : productions[0];
        for (int i = 1; i < productions.length; i++) {
            preSum[i] = preSum[i - 1];
            if (plans[i] == 0) {
                preSum[i] += productions[i];
                continue;
            }
            result += productions[i];
        }

        if (window == 0) {
            return result;
        }

        if (window >= productions.length) {
            return result + preSum[productions.length - 1];
        }

        int max = preSum[window - 1];
        int temp;

        for (int i = 1; i + window - 1 < productions.length; i++) {
            temp = preSum[i + window - 1] - preSum[i - 1];
            max = temp > max ? temp : max;
        }

        return max + result;


    }
}

