package homework4;

import java.math.BigDecimal;

/**
 * For debugging purposes.
 * @author DennisQiu
 */
public class Partition {
	private String printArray = "[";
	private int pivot, left, right;
	
	public Partition(BigDecimal[] array, int pivot, int left, int right) {
		this.pivot = pivot; this.left = left; this.right = right;
		for (int i = 0; i < array.length; i++) {
			printArray += array[i] + ((i == array.length-1) ? "" : ", ");
		}
		printArray += "]";
	}
	
	@Override
	public String toString() {
		return "Array: " + printArray + " -> Pivot: " + pivot + " -> Left index: " + left + ", Right index: " + right;
	}
	
	public int getPivot() {
		return pivot;
	}
}
