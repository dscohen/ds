public class Test3 extends TestHarness {

  public Test3(String s) { super(s); }

  public boolean test() {
    boolean pass = true;
    FileSystem fs;
    try {
      fs = new FileSystem("sample");
    } catch (Exception e) {
      return false;
    }

    try {

      Tree<FileNode> root = fs.getTree();
      root.displayXML();
      //Checking to make sure folder were created
      Tree<FileNode> sampleT = root.findChild(new FileNode("sample",true));
      if (sampleT == null) {pass = false;}
      //Checking to make sure no double copies were created
      if (sampleT.getNextSibling() != null) {pass = false;}
      Tree<FileNode> bazT = sampleT.findChild(new FileNode("baz",true));
      if (bazT == null) {pass = false;}
      Tree<FileNode> fooT = bazT.getNextSibling();
      if (fooT == null) {pass = false;} 

      Tree<FileNode> blueT = sampleT.findChild(new FileNode("blue",true));
      blueT = blueT.findChild(new FileNode("blue1",true));
      blueT = blueT.findChild(new FileNode("green",true));
      blueT = blueT.findChild(new FileNode("green1",true));
      blueT = blueT.findChild(new FileNode("green3",true));
      blueT = blueT.findChild(new FileNode("green4",true));
      blueT = blueT.findChild(new FileNode("deepgreen.txt",false));
      //Shouln't do anything differently because of recursion, but 
      //checking for a long directory
      if(blueT == null) {pass = false;}
      if (!blueT.Leaf()) {pass = false;}
      if (sampleT.Leaf()) {pass = false;}

    } catch (Exception e) {return false;}
    return pass;


  }
}
