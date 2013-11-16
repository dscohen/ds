public class BSTException extends Exception {
  public BSTException(String msg) {super(msg);}
}
public class BST<T extends Comparable<T>>{
  class BSTNode<T extends Comparable<T>>
  {
    protected T value;
    protected BSTNode<T> left;
    protected BSTNode<T> right;
    protected BSTNode<T> parent;//null for the tree root
    public BSTNode(T v, BSTNode<T> l, BSTNode<T> r, BSTNode<T> p)
    { value = v; left = l; right = r; parent = p;
      //main class ensures v non null
    }
    protected boolean isLeaf(){return (left == null) && (right == null);}
    protected boolean hasOneChild(){
      return (left==null && right != null)
        || (left!=null && right == null);
    }
    protected boolean isRoot(){return parent == null;}
    protected BSTNode<T> getRightmostDecendant(){
      if(right == null) return this;
      else return right.getRightmostDecendant();
    }
    protected String makeString(int depth){
      int increase = 2;
      String rval = "";
      for(int i = 0; i < depth; ++i){
        rval += " ";
      }
      rval+= "|";
      rval += value + "\n";
      if(left != null)
        rval += left.makeString(depth+increase);
      if(right != null)
        rval += right.makeString(depth + increase);
      return rval;
    }
  }
  private BSTNode<T> root;
  public BST() {root = null;}
  public boolean search(T toFind) throws BSTException{
    if (toFind == null) throw new BSTException("Cannot work with null values");
    return search(toFind, root);
  }
  private boolean search(T toFind, BSTNode<T> N) throws BSTException
  { // search for value V in the subtree with root N;
    if (N==null) return false;
    int ct = toFind.compareTo(N.value);
    if(ct == 0)
      return true;
    else if (ct > 0)
      return search(toFind,N.right);
    else
      return search(toFind,N.left);
  }
  public boolean add(T toAdd) throws BSTException{
    if (toAdd == null) throw new BSTException("Cannot work with null values");
    if (root == null){
      root = new BSTNode<T>(toAdd, null,null,null);
      return true;
    } else {
      return add(toAdd, root);
    }
  }
  public boolean add(T toAdd, BSTNode<T> N) throws BSTException{
    int ct = toAdd.compareTo(N.value);
    if(ct == 0) {
      return false;
    } else if (ct > 0){
      if (N.right == null){
        N.right = new BSTNode<T>(toAdd,null,null,N);
        return true;
      } else {
        return add(toAdd, N.right);
      }
    } else {
      if (N.left == null){
        N.left = new BSTNode<T>(toAdd,null,null,N);
        return true;
      } else {
        return add(toAdd, N.left);
      }            
    }
  }
  public boolean remove(T toRemove) throws BSTException {
    if (toRemove == null) throw new BSTException("Cannot work with null values");
    if(root.value.equals(toRemove) && root.isLeaf()){
      root = null;
      return true;
    } else {
      return remove(toRemove, root);
    }
  }
  private boolean remove(T toRemove, BSTNode<T> N) throws BSTException{
    //Case 1, reached the end without finding it
    if (N == null) return false;
    int ct = toRemove.compareTo(N.value);
    if (ct > 0) {
      return remove(toRemove,N.right);
    } else if (ct < 0) {
      return remove (toRemove,N.left);
    } else {
      //we found the element, now to remove it!
      if(N.isLeaf()){
        //case 2:
        deleteLeaf(N);
      } else if(N.left == null){
        //case 3
        shiftNodeUp(N.right);
      } else if(N.right == null){
        //case 3
        shiftNodeUp(N.left);
      } else {
        //case 4
        //We know that there is some rightmost decendent
        BSTNode<T> rightmost = N.left.getRightmostDecendant();
        //updating in place makes it easier to handle the case of the root
        N.value = rightmost.value;
        //now we need to deal with the rightmost node
        //and its children
        if(rightmost.isLeaf()){
          //case 4a
          deleteLeaf(rightmost);
        } else {
          //case 4b
          shiftNodeUp(rightmost.left);
        }
      }
      return true;
    }
  }

  private void deleteLeaf(BSTNode<T> L) throws BSTException {
    if(!L.isLeaf()) throw new BSTException("L is not a leaf");
    if(L == L.parent.left){
      L.parent.left = null;
    } else {
      L.parent.right = null;
    }
  }

  private void shiftNodeUp(BSTNode<T> N) throws BSTException
  {
    if(N.parent == null) throw new BSTException("Can't shift up root node");
    if(!N.parent.hasOneChild())  throw new BSTException("Can't shift into full parent");
    //Move the values and the children
    N.parent.value = N.value;
    N.parent.left = N.left;
    N.parent.right = N.right;
    //patch up the parents
    //of the subtrees
    if(N.parent.left != null){
      N.parent.left.parent = N.parent;
    }
    if(N.parent.right != null){
      N.parent.right.parent = N.parent;
    }
  }
  public String toString(){
    if(root != null){
      return root.makeString(0);
    }
    else return "(null)";
  }

  public static void main(String[] args){
    BST<Integer> bst = new BST<Integer>();
    try{
      bst.add(10);
      bst.add(7);
      bst.add(18);
      bst.add(3);
      bst.add(15);
      bst.add(25);
      bst.add(1);
      bst.add(6);
      bst.add(14);
      //14 in most examples;
      //or 16 in case 4a example
      bst.add(22);
      bst.add(28);
      bst.add(4);
      bst.remove(10);
      System.out.println(bst.toString());
    }
    catch (Exception e){e.printStackTrace();}
  }
}
