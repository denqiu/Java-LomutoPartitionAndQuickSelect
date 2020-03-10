package homework4;

public abstract class TimeAlgorithm {
	public abstract Object algorithm();
	public abstract String algorithmName();
	private Object result;
	private long timeElapsed;
	
	public TimeAlgorithm() {
		long startTime = System.nanoTime();
		result = algorithm();
		timeElapsed = System.nanoTime() - startTime;
	}
	
	@Override
	public String toString() {
		return algorithmName() + " -> " + timeElapsed + " nanoseconds";
	}
	
	public Object getResult() {
		return result;
	} 
	
	public String printResult() {
		Object printResult = result;
		if (printResult instanceof int[]) {
			int[] getResult = (int[]) printResult;
			Integer[] setResult = new Integer[getResult.length];
			for (int i = 0; i < getResult.length; i++) {
				setResult[i] = getResult[i];
			}
			printResult = setResult;
		} 
		String r = "[";
		if (printResult instanceof Object[]) {
			Object[] getResult = (Object[]) printResult;
			for (int i = 0; i < getResult.length; i++) {
				r += getResult[i] + ((i == getResult.length-1) ? "" : ", ");
			}
		}
		return r + "]"; 
	}
		
	public long getTime() {
		return timeElapsed;
	}
}
