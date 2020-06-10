import java.util.LinkedList;

public class day0530 {
    public static void main(String[] args) {
        day0530 day0530 = new day0530();
        System.out.println(day0530.largestRectangleArea(new int[] {2,1,5,6,2,3}));
    }
    // 明确单调栈能够解决的问题
// 1.找右侧第一个更小 增栈
// 2.找右侧第一个更大 减栈
// 3.找左侧第一个更小 增栈
// 4.找左侧第一个更大 减栈
    public int largestRectangleArea(int[] heights) {
        LinkedList<Integer> stack = new LinkedList<>();
        int max = 0;
        for(int i = 0; i < heights.length; i++){
            while(!stack.isEmpty() && heights[stack.getLast()] > heights[i]){
                int j = stack.removeLast();
                max = Math.max(max, heights[j] * (stack.isEmpty()?i:(i-stack.getLast() - 1)));
            }
            stack.addLast(i);
        }
        while(!stack.isEmpty()){
            int j = stack.removeLast();
            max = Math.max(max, heights[j] * (stack.isEmpty()?heights.length:(heights.length-stack.getLast() - 1)));
        }
        return max;
    }
}
