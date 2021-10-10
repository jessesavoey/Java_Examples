public class ArrayFrontBackCappedList<T> implements FrontBackCappedListInterface<T> {
	
	private T[] list;
	private int numberOfElements;

	public ArrayFrontBackCappedList(int maximumSize) {
		
		@SuppressWarnings("unchecked")
		T[] tempList = (T[]) new Object[maximumSize]; //Default in how java says to write arrays with generics
		list = tempList;
		numberOfElements = 0;
		
	}


	@Override
	public boolean addFront(T newEntry) {
			while(!isFull()) {
				makeRoom(0);
				list[0] = newEntry;
				numberOfElements++;
				return true;
			}
		return false;
	}
	

	@Override
	public boolean addBack(T newEntry) {
		if(!isFull()) {
		int newPosition = numberOfElements;
			list[newPosition] = newEntry;
			numberOfElements++;
		return true;
		}
		return false;
	}

	@Override
	public T removeFront() {
		if(!isEmpty()) {			
			T elementRemoved = getEntry(0);
			list[0] = null;
			removeGap(0);
			numberOfElements--;
			return elementRemoved;
		}
		return null;
	}

	@Override
	public T removeBack() {
		if (!isEmpty()) {
			int removePosition = numberOfElements - 1;
			T elementRemoved = getEntry(removePosition);
			list[removePosition] = null;
			numberOfElements--;
			return elementRemoved;
		}
		return null;
	}

	@Override
	public void clear() {
		for (int i = 0; i < numberOfElements; i++) {
			list[i] = null;
		}
		numberOfElements = 0;
	}

	@Override
	public T getEntry(int givenPosition) {
		if(givenPosition > list.length || givenPosition < 0) {
			return null;
		}
		return list[givenPosition];
	}

	@Override
	public int indexOf(T anEntry) {	
		for(int i = 0; i<= numberOfElements - 1; i++) {
			if(list[i] == anEntry || list[i].equals(anEntry)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(T anEntry) {
		
		for(int i = numberOfElements - 1; i >= 0; i--) {
			if(list[i] == anEntry || list[i].equals(anEntry)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public boolean contains(T anEntry) {
		
		for(int i = 0; i <= numberOfElements - 1; i++) {
			if(list[i] == anEntry || list[i].equals(anEntry)) {
				return true;
			}
		}			
		return false;
	}

	@Override
	public int size() {
		return numberOfElements;
	}

	@Override
	public boolean isEmpty() {
		if (numberOfElements != 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isFull() {
		if(size() >= list.length) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
			return "size=" + numberOfElements + " capacity =" + list.length + ";  [" + printAll() + "]";
	}

	
	private void makeRoom(int newPosition) {
		assert (newPosition >= 1) && (newPosition <= numberOfElements + 1);

		int newIndex = newPosition;
		int lastIndex = numberOfElements - 1;

		for (int index = lastIndex; index >= newIndex; index--) {
			list[index + 1] = list[index];
		}
	} 
	
	private void removeGap(int givenPosition) {
		assert (givenPosition >= 1) && (givenPosition < numberOfElements);

		int removedIndex = givenPosition;
		int lastIndex = numberOfElements - 1;

		for (int index = removedIndex; index < lastIndex; index++)
			list[index] = list[index + 1];
	}
	
	private String printAll() {
			String printedArray = "";
		for(int i = 0; i<=numberOfElements-1;i++) {
			printedArray +=  list[i] + ", ";
		
		}
		return printedArray;
	}
}
