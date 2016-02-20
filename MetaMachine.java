package meta;

import java.lang.reflect.*;
import java.util.Arrays;

class MetaMachine {
	public String execute(String ... args) throws Exception {
		try{
		Class c = Class.forName(args[0]);//find and load the class
		Object blob = c.newInstance();
		Method meth = c.getMethod(args[1], (new String[1]).getClass());
		int numArgs = args.length - 2;
		String methArgs[] = new String[numArgs];
		for(int i = 0; i < numArgs; i++){
			methArgs[i] = args[i + 2];
		}
			
			//System.out.println(Arrays.toString(methArgs));
			meth.invoke(blob, new Object[]{methArgs});

		
			//meth.invoke(c, args[i]);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args){
		MetaMachine mm = new MetaMachine();
		try {
			//mm.execute("meta.Greeter", "greetings", "Hello", "Jupiter");
			mm.execute("meta.MetaMachine", "execute", "meta.Greeter", "greetings", "Hello", "Jupiter");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
