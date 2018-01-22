
public class Node {

    public Character item ;
    
    Node left;
    Node right;
    Node dad ;
    
    Node(char ch) {
        item = ch;
        left = right = dad = null;
    }
    
    Node(String st) {
        item = st.charAt(0);
        left = right = dad = null;
    }
    
    String tostring(char a){
    	return "" + a ;
    }
    public String toString()
    {
    	return item.toString();
    }
}
