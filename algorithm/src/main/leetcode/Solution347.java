import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Solution347 {

    public int[] topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return nums;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            //小顶堆
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int num = entry.getKey();
            int count = entry.getValue();
            if (priorityQueue.size() < k) {
                priorityQueue.offer(new int[]{num, count});
            } else if (priorityQueue.size() == k) {
                if (count > priorityQueue.peek()[1]) {
                    priorityQueue.poll();
                    priorityQueue.offer(new int[]{num, count});
                }
            }
        }

        int[] ret = new int[k];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = priorityQueue.poll()[0];
        }
        return ret;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
        int k = 2;
        Solution347 solution347 = new Solution347();
        int[] res = solution347.topKFrequent(nums, k);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }
}
