import java.io.*;
import java.util.*;

public class levelOrderLineWise {
  private static class Node {
    int data;
    ArrayList<Node> children = new ArrayList<>();
    
    Node(){
    	
    }
    Node(int data){
    	this.data = data;
    }
    
  }

  public static void display(Node node) {
    String str = node.data + " -> ";
    for (Node child : node.children) {
      str += child.data + ", ";
    }
    str += ".";
    System.out.println(str);

    for (Node child : node.children) {
      display(child);
    }
  }

  public static Node construct(int[] arr) {
    Node root = null;

    Stack<Node> st = new Stack<>();
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == -1) {
        st.pop();
      } else {
        Node t = new Node();
        t.data = arr[i];

        if (st.size() > 0) {
          st.peek().children.add(t);
        } else {
          root = t;
        }

        st.push(t);
      }
    }

    return root;
  }

  public static int size(Node node) {
    int s = 0;

    for (Node child : node.children) {
      s += size(child);
    }
    s += 1;

    return s;
  }

  public static int max(Node node) {
    int m = Integer.MIN_VALUE;

    for (Node child : node.children) {
      int cm = max(child);
      m = Math.max(m, cm);
    }
    m = Math.max(m, node.data);

    return m;
  }

  public static int height(Node node) {
    int h = -1;

    for (Node child : node.children) {
      int ch = height(child);
      h = Math.max(h, ch);
    }
    h += 1;

    return h;
  }

  public static void traversals(Node node){
    System.out.println("Node Pre " + node.data);

    for(Node child: node.children){
      System.out.println("Edge Pre " + node.data + "--" + child.data);
      traversals(child);
      System.out.println("Edge Post " + node.data + "--" + child.data);
    }

    System.out.println("Node Post " + node.data);
  }

  public static void levelOrderLinewise1(Node node){
    Queue<Node> mainq = new ArrayDeque<>();
    mainq.add(node);
    Queue<Node> childq = new ArrayDeque<>();
    while(mainq.size()>0){
        node = mainq.remove();
        System.out.print(node.data+" ");
        
        for(Node child: node.children){
            childq.add(child);
        }
        if(mainq.size()==0){
            mainq = childq;
            childq = new ArrayDeque<>();
            System.out.println();
        }
    } 
  }
  
//  ----------------Approach 2-----------------------Approach 2-------------------Approach 2----------------------------
  public static void levelOrderLinewise2(Node node){
	    Queue<Node> mainq = new ArrayDeque<>();
	    mainq.add(node);
	    mainq.add(new Node(-1));
	    
	    while(mainq.size()>0){
	        node = mainq.remove();
	        if(node.data != -1) {
	        	System.out.print(node.data+" ");
	        	
		        for(Node child: node.children){
		            mainq.add(child);
		        }	
	        }else {
	        	if(mainq.size()>0) {
	        		mainq.add(new Node(-1));
	        		System.out.println();
	        	}
	        }  
	    } 
	  }
//  -------------Approach 3(easiest)-----------Approach 3-----------Approach 3 -----------
  public static void levelOrderLinewise3(Node node){
	    Queue<Node> mainq = new ArrayDeque<>();
	    mainq.add(node);
	    
	    while(mainq.size()>0){
	    	//cicl = child in current level
	         int cicl = mainq.size();
	         for(int i=0;i<cicl;i++) {
	        	 node = mainq.remove();
	        	 System.out.print(node.data+" ");
	        	 
	        	 for(Node child: node.children) {
	        		 mainq.add(child);
	        	 }
	         }
	         System.out.println();
	    }
	  }
  
//-----------Approach 4-----------Approach 4--------------Approach 4-------  
  private static class Pair{
	  Node node;
	  int level;
	  
	  Pair(Node node, int level){
		  this.node = node;
		  this.level = level;
	  }
  }
  public static void levelOrderLinewise4(Node node){
	    Queue<Pair> mainq = new ArrayDeque<>();
	    mainq.add(new Pair(node, 1));
	    int level = 1;
	    while(mainq.size()>0) {
	    	Pair p = mainq.remove();
	    	if(p.level > level) {
	    		level = p.level;
	    		System.out.println();
	    	}
	    	System.out.println(p.node.data+" ");
	    	for(Node child: p.node.children) {
	    		Pair childPair = new Pair(child,p.level+1);
	    		mainq.add(childPair);
	    	}
	    }
	    
	  }
  
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];
    String[] values = br.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(values[i]);
    }

    Node root = construct(arr);
    levelOrderLinewise1(root);
    levelOrderLinewise2(root);
    levelOrderLinewise3(root);
    levelOrderLinewise4(root);
  }

}