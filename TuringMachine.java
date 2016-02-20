package meta;

import java.util.*;

public class TuringMachine {
	   private Tape tape;
	   private Map<Trigger, Action> program;//trigger is (curstate,curBit). Action
	   //is (newState,newBit,direction)
	   private static Set<Integer> finalStates; // run halts when state is a member
	   private int state; // the current state
	   private int index = 0; // the current position of the R/W head
	   int counter = 0;
	   //constructor
	   public TuringMachine(){
		   state = 0;
		   tape = new Tape("");
		   program = new HashMap<Trigger, Action>();
		   finalStates = new HashSet<Integer>();
	   }
	   
	   //add tape
	   public void addTape(String x){
		  for(int i = 0; i < x.length(); i++){
			  tape.setString(i, x.charAt(i));
		  }
	   }
	   
	   //gets the number of 1s in the string
	   public int getCounter(String x){
		   int i = 0;
		   while(x.charAt(i) == '1'){
			   counter++;
			   i++;
		   }
		   return counter;
	   }
	   //resets and clears previous data
	   public void clear(){
		   state = 0;
		   index = 0;
		   tape = new Tape("");
		   finalStates.clear();
		   program.clear();
	   }
	   //the run method. Modifies the tape by getting info from the map<trigger,action>
	  public void run() { 
		  state = 0;
		  index = 0;
		  System.out.println("state:"+state);
		  System.out.println("index:"+index);
		  System.out.println(tape.toString());
		  while(!finalStates.contains(state) && index>=0){
		   //while(!program.containsKey(finalStates)){
			   Trigger trig = new Trigger(state, tape.getCells(index));
			   Action act = program.get(trig);
			   state = act.getState();

			   char nextBit = act.getNextBit();
			   if(tape.getCells(index)!=(nextBit)){
				   tape.setString(index, nextBit);
			   }
			   if(act.getDirection()>0){
				   index++;
			   }
			   if(act.getDirection()<0){
				   index--; 
			   }
			   else if(act.getDirection()== 0){
				   index = index + 0;
			   }
			   System.out.println("state: " + state);
			   System.out.println("index:" + index);
			   System.out.println(tape.toString());
			   
		   }
	  }
	  public int getState(){
		  return state;
	  }

	  //adds info to the hashmap
	   public void addTransition(int curState, char curBit, int newState, char newBit, int direction){
		   Trigger trigger = new Trigger(curState, curBit);
		   Action action = new Action(newState, newBit, direction);
		   program.put(trigger,action);
		   //System.out.println(program);
		   //System.out.println(action.getNextBit());
		   	   }
	   //class trigger created to be used in the hashmap
	 class Trigger{
			public char curBit;
			public int curState;
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + curBit;
			result = prime * result + curState;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Trigger other = (Trigger) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (curBit != other.curBit)
				return false;
			if (curState != other.curState)
				return false;
			return true;
		}
		private TuringMachine getOuterType() {
			return TuringMachine.this;
		}
		public Trigger(int x, char y){
			curState = x;
			curBit = y;
		}
		//returns the current state
		public int getState(){
			return curState;
		}
		@Override
		public String toString(){
			return "|" + curState + ", " + curBit + "|";
		}
	   }
	 //class action created to be used in the hashmap
	   class Action{
		   public int nextState;
		   public char newBit;
		   public int direction;
		   
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + direction;
			result = prime * result + newBit;
			result = prime * result + nextState;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Action other = (Action) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (direction != other.direction)
				return false;
			if (newBit != other.newBit)
				return false;
			if (nextState != other.nextState)
				return false;
			return true;
		}
		public Action(int x, char y, int z){
			nextState = x;
			newBit = y;
			direction = z;
		}
		//returns the next state
		public int getState(){
			return nextState;
		}
		//returns the direction
		public int getDirection(){
			return direction;
	   }
		//returns the nextbit
		public char getNextBit(){
			return newBit;
		}
		public String toString(){
		return "(" + nextState + ", " + newBit + ", " + direction + ")";
		}
		private TuringMachine getOuterType() {
			return TuringMachine.this;
		}

	   
	// etc.
	   
	
	}
	   public static void main(String[] args){
		   TuringMachine a = new TuringMachine();
		   //for adding m+n
		   a.addTape("1111101111110");
		   a.addTransition(0,'1',0,'1',1);
		   a.addTransition(0,'0',1,'0',1);
		   a.addTransition(1,'0',3,'0',0);
		   a.addTransition(1,'1',2,'0',-1);
		   a.addTransition(2,'0',0,'1',1);
		   a.addTransition(2,'1',4,'1',0);
		   finalStates.add(3);
		   System.out.println(finalStates);
		   a.run();
		   a.clear();
		   System.out.println("****************");
		   //for 2n
	
		   a.addTape("1110");
		   a.addTransition(0,'1',0,'$',1);
		   a.addTransition(0,'0',1,'0',-1);
		   a.addTransition(1,'1',1,'1',-1);
		   a.addTransition(1,'$',2,'1',1);
		   a.addTransition(2,'1',2,'1',1);
		   a.addTransition(2,'0',1,'1',-1);
		   a.addTransition(1,'0',3,'0',0);
		   finalStates.add(3);
		   a.run();
		   a.clear();
		   System.out.println("*****************");
		   
		   //for n squared
		   a.addTape("11100");
		   a.addTransition(0,'1',0,'1',1);
		   a.addTransition(0,'0',1,'1',1);
		   a.addTransition(1,'0',2,'1',1);
		   a.addTransition(2,'0',3,'1',1);
		   a.addTransition(3,'0',4,'1',1);
		   a.addTransition(4,'0',5,'1',1);
		   a.addTransition(5,'0',6,'1',1); 
		   finalStates.add(6);
		   a.run();
		   a.clear();
		   System.out.println("******************");
		   
		   //for 101
		   a.addTape("111101111");
		   a.addTransition(0,'1',0,'1',1);
		   a.addTransition(0,'0',1,'0',1);
		   a.addTransition(1,'1', 0, '1', 1);
		   a.addTransition(1,'0',2,'0',1);
		   a.addTransition(2,'0',3,'0',0);
		   a.addTransition(2,'1',3,'0',0);
		   finalStates.add(3);
		   a.run();
		   a.clear();
		   System.out.println("******************");
	   }
}
