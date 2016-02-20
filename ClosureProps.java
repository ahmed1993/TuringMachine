package meta;

import java.util.ArrayList;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class ClosureProps {
	
	public static Predicate<Integer> union(Predicate<Integer> memA, Predicate<Integer> memB) {
		return (n) -> memA.test(n) || memB.test(n);
	}
	
	public static Predicate<Integer> intersection(Predicate<Integer>memA, Predicate<Integer>memB){
		return (n) -> memA.test(n) && memB.test(n);
	}
	
	//A-B
	public static Predicate<Integer> diff(Predicate<Integer>memA, Predicate<Integer>memB){
		return (n) -> memA.test(n) && !memB.test(n);
	}
	//AXB
	public static BiPredicate<Integer, Integer> cartesian(Predicate<Integer>memA, Predicate<Integer>memB){
		return (m,n) -> memA.test(m) && memB.test(n);
	}
	
	// assumes f & g are enumerators for A and B, resp.
	   public static UnaryOperator<Integer> union(UnaryOperator<Integer> f, UnaryOperator<Integer> g) {
	      return x->(x % 2 == 0)?f.apply(x):g.apply(x);
	   }
	   // assumes f & g are recognizers for A and B, resp.
	   public static UnaryOperator<Integer> intersection(UnaryOperator<Integer> f, UnaryOperator<Integer> g) {
	      return x->f.apply(x) + g.apply(x); // +, *, /, whatever
	   }
	   // assumes f & g are recognizers for A and B, resp.
	   public static BinaryOperator<Integer> product(UnaryOperator<Integer> f, UnaryOperator<Integer> g) {
	      return (x, y)->f.apply(x) + g.apply(y);
	   }

	// tests
	
	public static boolean isSmall(Integer n) { return n < 10; }
	public static boolean divBy5(Integer n) { return n % 5 == 0; }
	
	
	public static void main(String args[]) {
		Predicate<Integer> mem = union(ClosureProps::isSmall, ClosureProps::divBy5);
		for(int i = 0; i < 21; i++) {
			System.out.println("union(" + i + ") = " + mem.test(i));
		}
		Predicate<Integer> mem1 = intersection(ClosureProps::isSmall, ClosureProps::divBy5);
		for(int i = 0; i < 21; i++){
			System.out.println("Intersection(" + i + ") = " + mem1.test(i));
		}
		Predicate<Integer> mem2 = diff(ClosureProps::isSmall, ClosureProps::divBy5);
		for(int i = 0; i < 21; i++){
			System.out.println("diff(" + i + ") = " + mem2.test(i));
		}
		BiPredicate<Integer,Integer> mem3 = cartesian(ClosureProps::isSmall, ClosureProps::divBy5);
		for(int i = 0; i < 21; i++){
			System.out.println("cartesian(" + i + ") = " + mem3.test(i,i));
		}
	}

}
