package task;

import java.util.Comparator;

public class TaskIsCompleteComparator implements Comparator<Task>{

	@Override
	public int compare(Task t1, Task t2){
		return Boolean.compare(t1.getIsComplete().getValue(), t2.getIsComplete().getValue());
	}
}
