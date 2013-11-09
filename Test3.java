public class Test3 extends TestHarness {

    public Test3(String s) { super(s); }

    public boolean test() {
        FileSystem fs;
        try {
            fs = new FileSystem("sample");
        } catch (Exception e) {
            return false;
        }
        fs.display();

        // make sure 'sample/foo/bar' is in the file system
        //Make sure findChild works correctly

        Tree<FileNode> root = null;
        FileNode testNode = new FileNode(null, true);
        root = new Tree<FileNode>(new FileNode("Apartment", true));

        Tree<FileNode> Kitchen = new Tree<FileNode>(new FileNode("Kitchen", true));
        root.addChild(Kitchen);
        try {
        if (root.findChild(null) != null) {return false;} 
        } catch (Exception e) {return false;}
        try {
        if (root.findChild(testNode) != null) {return false;}
        } catch (Exception e) {return false;}


        Tree<FileNode> Refrigerator = new Tree<FileNode>(new FileNode("Refrigerator", true));
        root.addChild(Refrigerator);
        Tree<FileNode> a = new Tree<FileNode>(new FileNode("a", true));
        Tree<FileNode> b = new Tree<FileNode>(new FileNode("b", false));
        Tree<FileNode> c = new Tree<FileNode>(new FileNode("c", false));
        Tree<FileNode> d = new Tree<FileNode>(new FileNode("d", false));
        root.addChild(a);
        a.addChild(b);
        a.addChild(c);
        a.addChild(d);
        if (a.findChild(Kitchen.getLabel()) != null) {return false;}
        if (a.findChild(root.getLabel()) != null) {return false;}
        //displayxml if label is null
        //displayxml on empty tree (no children)
        //displayxml
        //path constructor on empty directory
        //path constructor with really long directory 
        //pathconstructor with empty string
        //builds EXACT match...meanming no double folders/files
        //findchild for null label, also checks to see if node in tree has null label and see if it crashes
        //findChild for only immediate children
        //findchild if label is null

        //Test for double folders by checking for siblings
        Tree<FileNode> rt = fs.getTree();
        if(!rt.getLabel().getName().equals("CWD"))
            return false;
        Tree<FileNode> sampleT = rt.findChild(new FileNode("sample",true));
        if(sampleT == null) return false;
        if (sampleT.getNextSibling() != null) {return false;}
        Tree<FileNode> fooT    = sampleT.findChild(new FileNode("foo",true));
        if(fooT == null) return false;
        Tree<FileNode> barT    = fooT.findChild(new FileNode("bar",true));
        if(fooT == null) return false;
        return true;

    }

}
