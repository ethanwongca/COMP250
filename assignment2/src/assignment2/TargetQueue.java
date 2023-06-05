package assignment2;
//March 20 Ver
public class TargetQueue extends MyQueue<Position>{
    private MyStack<String> stack = new MyStack<>();
    public TargetQueue(){
        super();
    }
    public void clear(){
        super.clear();
        stack.clear();

    }
    public void addTargets(String s) {
        String num = "";g
        Boolean commaAllowed = false;
        Boolean rightAllowed = false;
        Boolean numAllowed = false;
        Boolean periodAllowed = true;
        Boolean leftAllowed = true;
        //form needed is "(1,2).(3,4).(5,6)"
        for (int i = 0; i < s.length(); i++) {
            if (leftAllowed) { //(
                if (s.charAt(i) == '(' && num.equals("") && stack.isEmpty()) { //condition one stack and num empty
                    stack.push("(");
                    numAllowed = true;
                    periodAllowed = false; 
                    leftAllowed = false;
                    continue;
                }
            }
            if (numAllowed) { //next is num, so as long as it is a number things can go through
                if (Character.isDigit(s.charAt(i))) { //checks if the next digits are nums and appends it
                    num += s.charAt(i);
                    commaAllowed = true;
                    continue;
                }
            }
            if (commaAllowed) {
                if (s.charAt(i) == ',') {
                    if (num.equals("")) {
                        throw new IllegalArgumentException();
                    }
                    stack.push(num);
                    stack.push(",");
                    num = "";
                    commaAllowed = false;
                    rightAllowed = true;
                    continue;
                }
            }
            if (rightAllowed) {
                if (s.charAt(i) == ')') {
                    if (num.equals("")) {
                        throw new IllegalArgumentException();
                    }
                    if (stack.getSize() != 3) {
                        throw new IllegalArgumentException();
                    }
                    if (!(stack.peek().equals(","))) {
                        throw new IllegalArgumentException();
                    }
                    //check all three elements
                    stack.pop(); 
                    this.enqueue(new Position(Integer.parseInt(stack.pop()), Integer.parseInt(num))); //removes num
                    stack.clear(); 
                    num = "";
                    numAllowed = false;
                    rightAllowed = false;
                    periodAllowed = true;
                    continue;
                }
            }
            if (periodAllowed) {
                if (s.charAt(i) == '.') {
                    leftAllowed = true;
                    commaAllowed = false;
                    numAllowed = false;
                    rightAllowed = false;
                    periodAllowed = false;
                    continue;
                }
            }



            throw new IllegalArgumentException("Wrong Character");

        }

        if (!(stack.isEmpty())){
            throw new IllegalArgumentException("Wrong Character");
        }

    }
}
