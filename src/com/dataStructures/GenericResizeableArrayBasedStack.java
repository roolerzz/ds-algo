package com.dataStructures;


public class GenericResizeableArrayBasedStack<Item>{
	// Earlier we needed to provide the size of the array before hand, to initialize, which is kind of the restriction in cases, where client
	// does not know what is number of elements its gonno insert in the stack. To work with that limitation, we need to resize the array.
	// Everytime in case of push operation, if the stack is full, create a new array with double the size, copy the existing contents onto the new
	// and return the array. In case of pop, operation, to optimize that alot of space is not wasted, everytime the array is 1/4 of its size full,
	// create a new array with half the size and move elements to that.
	
	private Item[] arr;

	private int top=0;

	public GenericResizeableArrayBasedStack(){
		arr = (Item[])new Object[1];
	}
	
	public void push(Item obj){
		int length = arr.length;
		if(top == length){
		resize(2*length);
		}
		arr[top++] = obj;
		
	}
	
	private void resize(int newlen){
		System.out.println("Resizing the array from : " + arr.length + " to :" + newlen);
		Item[] newArray = (Item[])new Object[newlen];
		for(int i=0; i<top ; i++){
			newArray[i]=arr[i];
		}
		arr = newArray;
	}

	public void pop(){
		if(isEmpty()) {System.out.println("Stack Underflow"); return;}
		if(top<=arr.length/4){
		resize(arr.length/2);
		}
		System.out.print(arr[--top] + " ");
		arr[top] = null;
	}
	
	public boolean isEmpty(){
		return top==0;
	}

}


/*public class ClientProgram {
	public static void main(String[] args){
	ResizeArrayStackOfStrings stack= new ResizeArrayStackOfStrings();
	stack.push("to");
	stack.push("be");
	stack.push("or");
	stack.push("not");
	stack.push("to");
	stack.pop();
	stack.push("be");
	stack.pop();
	stack.pop();
	stack.push("that");
	stack.pop();
	stack.pop();
	stack.pop();
	stack.push("is");
	}
}
}*/