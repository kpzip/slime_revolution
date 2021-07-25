package io.github.kpzip.slimerevolution.core.util;

public final class MathHelper {
	
	private MathHelper() {
        throw new IllegalAccessError("Class only uses static methods");
    }
	
	public static int min(int... nums) {
		if (nums.length == 2) {
			return min(nums[0], nums[1], "");
		}
		if (nums.length == 1) {
			return nums[0];
		}
		if (nums.length == 0) {
			return 0;
		}
		int[] newarr = new int[nums.length - 1];
		newarr[0] = min(nums[0], nums[1], "");
		for (int i = 1; i < newarr.length; i++) {
			newarr[i] = nums[i + 1];
		}
		return min(newarr);
	}
	
	private static int min(int num1, int num2, String unused) {
		return num1 < num2 ? num1 : num2;
	}
	
	

}
