//import sun.reflect.generics.tree.Tree;

import javax.sound.midi.Soundbank;
import java.util.Stack;

public class Homework1 {
	public static String a ="";
	public static void main(String[] args) {
		// Begin of arguments input sample
		String pfix = "0";

		try{
			pfix = args[0] ;
			
		}
		catch(ArrayIndexOutOfBoundsException e){
			System.out.println("No args. !");
			
			pfix = "251-*32*+" ;
		}

		if (args.length > 0) {
			if (pfix.equalsIgnoreCase("251-*32*+")) {
				System.out.println("(2*(5-1))+(3*2)=14");
			}
		}

		Stack<Node> a = new Stack<Node>() ;
		Node r0 = new Node("a"), r1 = new Node("a")  , r2 = new Node("a");

		for(int i=0 ; i< pfix.length() ; i++){
			if( isDigit(pfix.charAt(i)) ){//�繵���Ţ
				r0 = new Node(pfix.charAt(i)) ;
				a.push(r0) ;
			}else{ // ����ͧ����
				r0 = new Node(pfix.charAt(i)) ;
				r1 = a.pop() ;
				r2 = a.pop() ;
				r0.left = r2 ; r2.dad = r0 ;
				r0.right = r1 ; r1.dad = r0;
				a.push(r0) ;
			}
		}
		infix(r0) ;
		TreeUI r = null;
		r.main(r0);
	}


	static String infix(Node n){
		
		a="";
		inorder(n) ;

		// To check if node is non-root so delete 1 pair bracelet ;
		if(n.dad != null){
			String temp = a ;
			a = temp.charAt(1)+"";
			for (int i=2 ; i < temp.length()-1 ; i++)
				a += temp.charAt(i) ;
		}

		System.out.println("=" + calculate(n) );
		a += "=" ;
		a += calculate(n) ;

		//System.out.println(a);
			return a;
	}

	static void inorder(Node n){
		if(!isDigit(n.item))
		{
			if(n.dad != null){
				System.out.print("("  );
				a += "(" ;
				inorder(n.left);
				System.out.print(n.item );
				a += n.item ;
				inorder(n.right);
				System.out.print(")"  );
				a += ")" ;
			}else{
				inorder(n.left);
				System.out.print(n.item );
				a += n.item ;
				inorder(n.right);
			}

		}else
		{
			System.out.print(n.item  );
			a += n.item ;
		}


//        if (n != null) {
//
//        	if(n.left == null && n.dad.left == n)System.out.print("(");
//
//           inorder(n.left);
//           System.out.print(n.item);
//            inorder(n.right);
//        if(n.right == null && n.dad.right == n) System.out.print(")");
//        }
	}

	static int calculate(Node n){

		
		if(	isDigit(n.item)	)return toDigit(n.item) ;
		else{
			switch(n.item){
				case '+' :  return calculate(n.left) + calculate(n.right) ;
				case '-' :  return calculate(n.left) - calculate(n.right) ;
				case '*' :  return calculate(n.left) * calculate(n.right) ;
				case '/' :  return calculate(n.left) / calculate(n.right) ;
			}
		}
		return 0;
	}

	static boolean isDigit(char item)
	{
		return item >= '0' && item <= '9';
	}
	static int toDigit(char item)
	{
		return item - '0';
	}
}
