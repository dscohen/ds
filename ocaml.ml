type 'a tree = | Leaf | Node of 'a * 'a tree * 'a tree;;

let rec displayXML (t : string tree) (indent : int) :unit = 
    for i = 0 to indent do print_string " " done;
    (*  if tree = Leaf then print_string ""
    if tree = Node then print_string string_of_'a *)
    match t with
    | Leaf ->  print_string ""
    | Node (vl, Leaf, tR) -> begin print_string ("<Leaf>"^vl^"</Leaf>\n");
        displayXML tR indent
    end
    | Node (vl, tL, Leaf) -> begin print_string ("<Node>"^vl^"\n");
        displayXML tL (indent+3);
         for i = 0 to indent do print_string " " done;
         print_string "</Node>\n"
    end
    | Node (vl, tL, tR) -> begin print_string ("<Node>"^vl^"\n");
        displayXML tL (indent+3);
        displayXML tR indent;
        for i = 0 to indent do print_string " " done;
        print_string "</Node>\n"
    end

;; 

(* 
 *
 * let t = Node(Leafe, "test", Lead) in 
* displayXML t 10
* *)
