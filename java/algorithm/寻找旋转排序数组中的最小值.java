class Solution {
    public int findMin(int[] nums) {
        if(nums.length == 1) return nums[0];
        if(nums.length == 2) return Math.min(nums[0], nums[1]);

        int min = nums[0];
        for(int i = 1; i < nums.length-1; i++){
            if((nums[i] < nums[i-1]) && (nums[i] < nums[i+1])){
                min = nums[i];
                break;
            }else {
                min = Math.min(nums[i-1], min);
                min = Math.min(nums[i+1], min);
            }
        }
        return min;
    }
}