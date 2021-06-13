class LongestConsecutive {
    public int longestConsecutive(int[] nums) {
        if(nums.length == 0) return 0;
        int longest = 1;
        Set<Integer> cache = new HashSet<Integer>(nums.length);
        for(int i : nums)
             cache.add(i);
        for(int i : nums){
            if(cache.remove(i)){
                int currentLongest = 1;
                // find left neiber
                int left = i - 1;
                while(cache.remove(left--))
                    currentLongest++;
                // find ringht neiber
                int ringht = i + 1;
                while(cache.remove(ringht++))
                    currentLongest++;
                longest = Math.max(longest, currentLongest);
            }
        }
        return longest;
    }
}