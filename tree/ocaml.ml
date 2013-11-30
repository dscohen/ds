type 'a tree = | Leaf | Node of 'a * 'a tree * 'a tree;;

let rec displayXML (t : string tree) (indent : int) :unit = 
    for i = 0 to indent do print_string " " done;
    match t with
    | Leaf ->  print_string ""
    | Node (vl, Leaf, Lead) -> begin print_string("<Leaf>"^vl^"</Leaf>\n");
    end
    | Node (vl, Leaf, sib) -> begin print_string ("<Leaf>"^vl^"</Leaf>\n");
        displayXML sib indent
    end
    | Node (vl, chld, Leaf) -> begin print_string ("<Node>"^vl^"\n");
        displayXML chld (indent+3);
         for i = 0 to indent do print_string " " done;
         print_string "</Node>\n"
    end
    | Node (vl, chld, sib) -> begin print_string ("<Node>"^vl^"\n");
        displayXML chld (indent+3);
        displayXML sib indent;
        for i = 0 to indent do print_string " " done;
        print_string "</Node>\n"
    end

;; 
