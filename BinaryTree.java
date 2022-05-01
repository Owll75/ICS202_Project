//package labProject;

import java.util.Queue;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class BinaryTree<T extends Comparable<T>> {
    WordPair root;
    
    public BinaryTree() { 
        root = null; 
    }

    public BinaryTree(WordPair root) {
        this.root = root; 
    }
    
   public void purge(){
        root = null;
   }
    
    public boolean isEmpty(){
       return root == null;
    }
    
    public void insert(T key, T Mean){
        if (root == null) {
            root = new WordPair(key, Mean);
            return;
        }

        WordPair temp;
        Queue<WordPair> q = new LinkedList<WordPair>();
         q.add(root);
 
        // Do level order traversal until we find the first empty left or right child.
        while (!q.isEmpty()) {
            temp = q.poll();
 
            if (temp.left == null) {
                temp.left = new WordPair(key, Mean);
                break;
            }
            else
                q.add(temp.left);
 
            if (temp.right == null) {
                temp.right = new WordPair(key, Mean);
                break;
            }
            else
                q.add(temp.right);
        }
    }
    
    // delete by copying last node
    public void deleteByCopying(T data){
         if(root == null)
            throw new UnsupportedOperationException("Tree is empty!");
         else if(root.left == null && root.right == null){
            if(root.word.equals(data))
                root = null;
            else
               throw new NoSuchElementException(data + " not in the tree.");
         return;
       }
       
       Queue<WordPair> queue = new LinkedList<WordPair>();
       queue.add(root);
        WordPair keyNode = null;
        WordPair currentNode = null;
        WordPair parentNode = root;
       boolean found = false;
       while(! queue.isEmpty()){
          currentNode = queue.poll();
          
          if(currentNode.word.equals(data)){
             if(! found){
                keyNode = currentNode;
                found = true;
             }
          }
          
          if(currentNode.left != null){
             queue.add(currentNode.left);
             parentNode = currentNode;
           }
          if(currentNode.right != null){
             queue.add(currentNode.right);
             parentNode = currentNode;
           }
       }
       
       if(! found)
          throw new NoSuchElementException(data + " not in tree.");
            
       while(! queue.isEmpty()){
            currentNode = queue.poll();
            System.out.print(currentNode.word + "  ");
            if(currentNode.left != null){
             queue.add(currentNode.left);
             parentNode = currentNode;
           }
          if(currentNode.right != null){
             queue.add(currentNode.right);
             parentNode = currentNode;
           }
        }
        
        keyNode.word = currentNode.word;
        if(parentNode.left == currentNode)
            parentNode.left = null;
        else if(parentNode.right == currentNode)
            parentNode.right = null;
    } 
  
    public void levelOrderTraversal(){ // BreadthFirstTraversal
      levelOrderTraversal(root);
    } 

    // BreadthFirst Traversal
    protected void levelOrderTraversal(WordPair root){
       if(root == null)
          return;
       Queue<WordPair> queue = new LinkedList<WordPair>();
        WordPair node = root;
       queue.add(node); 
       while(! queue.isEmpty()){
          node = queue.poll();
          visit(node);
          if(node.left != null)
             queue.add(node.left);
          if(node.right != null)
             queue.add(node.right);
       } 
    }

    public void levelOrderTraversalByLevels(){
          levelOrderTraversalByLevels(root);
    }

    protected void levelOrderTraversalByLevels(WordPair<T> root){
       Queue q = new LinkedList();
       int levelNodes = 0; 
       if(root==null) 
         return;
         
       q.add(root);
       while(!q.isEmpty()){
          levelNodes = q.size();
          while(levelNodes>0){
              WordPair n = (WordPair)q.remove();
             System.out.print(" " + n.word);
             if(n.left!=null) 
                q.add(n.left);
             if(n.right!=null) 
               q.add(n.right);
             levelNodes--;
          }
          System.out.println("");
       }
    }
   
    protected void visit(WordPair<T> p) {
        System.out.print(p.word + " "+ p.meaning+"\n");
    }

    public void inorderTraversal(){     
       inorderTraversal(root);   
     } 

    protected void inorderTraversal(WordPair node) {
        if (node == null) 
            return; 
  
        inorderTraversal(node.left); 
        visit(node); 
        inorderTraversal(node.right); 
    }
    public void postorderTraversal(){     
       postorderTraversal(root);  
    }
 
    protected void postorderTraversal(WordPair node){
        if (node == null) 
            return; 
  
        postorderTraversal(node.left); 
        postorderTraversal(node.right); 
        visit(node); 
    }  
  
    public void preorderTraversal(){     
        preorderTraversal(root);  
    } 
 
    protected void preorderTraversal(WordPair node){
        if (node == null) 
            return; 
  
        visit(node); 
        preorderTraversal(node.left); 
        preorderTraversal(node.right); 
    } 
  
    public boolean search(T key){
       if(root == null)
          return false;
       Queue<WordPair> queue = new LinkedList<WordPair>();
        WordPair node = root;
       queue.add(node); 
       while(! queue.isEmpty()){
          node = queue.poll();
          if(key.equals(node.word))
              return true;
          if(node.left != null)
             queue.add(node.left);
          if(node.right != null)
             queue.add(node.right);
       } 
       return false;
    }

   public void printTree(){
	  if(root == null){
		  System.out.println("[ ]");
		  return;
	  }
      printTree(root, "", true);
   }

     // Print the binary tree
  protected void printTree(WordPair currPtr, String indent, boolean last) {
    if(indent.equals(""))
      System.out.print("Root");
    if (currPtr != null) {
      System.out.print(indent);
      if (last) {
		 if(indent.equals("")) 
           System.out.print("--");
		 else
           System.out.print("R----");
        indent += "   ";
      } else {
        System.out.print("L----");
        indent += "|  ";
      }
      System.out.println(currPtr.word);
      printTree(currPtr.left, indent, false);
      printTree(currPtr.right, indent, true);
    }
  } 
 }    