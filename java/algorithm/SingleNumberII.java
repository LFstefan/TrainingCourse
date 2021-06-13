class SingleNumberII {
    public int singleNumber(int[] nums) {
        int result = 0;
        int k = 3;
        for(int i = 0; i < 32; i++){
            int count = 0;
            int mask = 1 << i;
            for(int j : nums){
                if((j & mask) != 0)
                    count++;
            }
            if((count % k) != 0)
                result |= mask;
        }
        return result;
    }
}