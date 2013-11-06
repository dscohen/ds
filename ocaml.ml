type 'a tree = Leaf | Node of 'a * 'a tree * 'a tree;;

let rec displayXML (tr, indent) = 
  for i = 0 to indent do print_string " " done;

