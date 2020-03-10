package homework4;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Random;

public class Problem4LomutoPartitionQuickSelect {	
	private int lomutoComparisons, quickComparisons;
	
	public static void main(String[] args) {
		new Problem4LomutoPartitionQuickSelect();
	}

	public Problem4LomutoPartitionQuickSelect() {
		Data data = new Data(getClass());
		int[] testArray = new int[] {5, 2, 7, 8, 1, 4, 3, 9}; 
		testArray = new int[] {4, 1, 10, 8, 7, 12, 9, 2, 15};
		testArray = new int[] {5,1,8,7,4,2};
		//testArray = new int[] {8, 10, 2, 3, 7, 12};
		BigDecimal[] bigArray = new BigDecimal[testArray.length];
		for (int i = 0; i < bigArray.length; i++) {
			bigArray[i] = new BigDecimal(testArray[i]);
		}
		System.out.println(lomutoPartition(bigArray));
		int testK = randomIndex(1, bigArray.length);
		testK = 6;
		System.out.println("K: " + testK);
		System.out.println("Kth smallest: " + quickSelectRecursion(bigArray, testK));
		final String lom = "Lomuto Partition", q = "Quick Select";
		data.write("Size of N\t" + lom + "\t" + q + "\tTotal Comparisons\n", true);
		long start = System.nanoTime();
		for (int n = 2; n <= 5000; n++) {
			BigDecimal[] array = createArray(n), sortedArray = Arrays.copyOf(array, n); Arrays.sort(sortedArray);
			int k = randomIndex(1, n);
			TimeAlgorithm lomutoPartition = new TimeAlgorithm() {
				@Override
				public String algorithmName() {
					return lom;
				}
				@Override
				public Object algorithm() {
					return lomutoPartition(array);
				}
			}, quickSelect = new TimeAlgorithm() {
				@Override
				public String algorithmName() {
					return q;
				}
				@Override
				public Object algorithm() {
					return quickSelectRecursion(array, k);
				}
			};
			if (sortedArray[k-1].compareTo((BigDecimal) quickSelect.getResult()) == 0) {
				data.write(n + "\t" + lomutoPartition.getTime() + "\t" + quickSelect.getTime() + "\t" + quickComparisons + "\n", true);
				data.write(n + " -> Total comparisons: " + quickComparisons + "\n" + lomutoPartition.toString() + " -> " + lomutoPartition.getResult() + "\n" + quickSelect.toString() + " -> K = " + k + " -> Kth smallest = " + quickSelect.getResult() + ((n == 3000) ? "" : "\n\n"), false);
				System.out.println(n + " -> " + ((System.nanoTime() - start) / 60000000000L) + " minutes");
				quickComparisons = 0;	
			} else {
				System.out.println("Something went wrong with Lomuto Partition and/or Quick Select algorithms."); System.exit(0);
			}
		}
	}
	
	public Partition lomutoPartition(BigDecimal[] array) {
		lomutoComparisons = 0;
		int left = 0, right = array.length;
		BigDecimal p = array[left], swap;
		int s = left;
		for (int i = left; i < right; i++) {
			if (array[i].compareTo(p) == -1) {
				s++;
				swap = array[s];
				array[s] = array[i];
				array[i] = swap;
				lomutoComparisons++;
			}
		}
		swap = array[left];
		array[left] = array[s];
		array[s] = swap;
		return new Partition(array, s, left, right);
	}
	
	public BigDecimal quickSelectRecursion(BigDecimal[] array, int k) {
		int left = 0, right = array.length, s = lomutoPartition(array).getPivot();
		quickComparisons += lomutoComparisons;
		if (s == k - 1) {
			return array[s];
		} else if (s > left + k - 1) {
			return quickSelectRecursion(Arrays.copyOfRange(array, left, s), k);
		} else {
			return quickSelectRecursion(Arrays.copyOfRange(array, s+1, right), k-1-s);
		}
	}
	
	private BigDecimal[] createArray(int n) {
		BigDecimal[] array = new BigDecimal[n];
		for (int i = 0; i < array.length; i++) {
			array[i] = new BigDecimal(Math.random()).multiply(new BigDecimal("5000000000000000000000")).setScale(0, RoundingMode.HALF_EVEN);
		}
		return array;
	}
	
	private int randomIndex(int min, int max) {
		return new Random().nextInt((max - min) + 1) + min;
	}
}