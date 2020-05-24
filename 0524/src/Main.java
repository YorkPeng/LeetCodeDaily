public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.findMedianSortedArrays(new int[]{1,3},new int[]{2}));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m =  nums2.length;
        int sum = n + m;
        if((sum & 1) == 0){
            return (helper(nums1,0,nums2,0,sum/2) + helper(nums1,0,nums2,0,sum/2 + 1)) / 2.0;
        }else{
            return helper(nums1,0,nums2,0,sum/2+1);
        }
    }

    private double helper(int[] nums1, int i, int[] nums2, int j, int k){
        if(i >= nums1.length){
            return nums2[j + k - 1];
        }
        if(j >= nums2.length){
            return nums1[i + k - 1];
        }
        if( k == 1){
            return Math.min(nums1[i],nums2[j]);
        }
        int midValue = (k/2 + i - 1>= nums1.length)?Integer.MAX_VALUE:nums1[k/2 + i - 1];
        int midValue2 = (k/2 + j - 1 >= nums2.length)?Integer.MAX_VALUE:nums2[k/2 + j - 1];
        if(midValue > midValue2){
            return helper(nums1,i,nums2,j+k/2,k-k/2);
        }else{
            return helper(nums1,i+k/2,nums2,j,k-k/2);
        }
    }
}
