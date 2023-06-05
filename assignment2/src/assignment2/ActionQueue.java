package assignment2;
//March 20 Ver
public class ActionQueue extends MyQueue<Direction> {
    //following my previous implementation
    public ActionQueue() {
        super();
    }

    public void clear() {
        super.clear();
  
    }

    public void loadFromEncodedString(String s) {
        MyStack<Integer> stackNum = new MyStack<>();
        MyStack<String> brackets = new MyStack<>();
        MyStack<String> stackDirection = new MyStack<>();
        MyQueue<String> tempDirectionQueue = new MyQueue<>();
        MyQueue<String> finalQueue = new MyQueue<>();
  
        String num = "";
        String direction = "";
        String temp;
        String finalDirection = "";
        int numOpenBracket = 0;
        int numClosedBracket = 0;
        int counterTemp = 0;
        int finalCounter = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
  
            if ((Character.isDigit(c))) {
                num += c;
                if((!(direction.equals(""))) && (!(brackets.isEmpty()))) {
                    tempDirectionQueue.enqueue(direction);
                    counterTemp ++;
                    direction = "";
                }
                continue;
            }
            //then checks the num
            if (c == '[') {
                // checks for the k value fulfilment
                if (num.equals("") && direction.equals("")) {
                    throw new IllegalArgumentException("None");
                }
                stackNum.push(Integer.valueOf(num));
                num = "";
                brackets.push("[");
                numOpenBracket++;
                continue;

            }
            if (c == 'N' || c == 'E' || c == 'S' || c == 'W') { //NE works and NE5[NE]
                if(num.equals("") && brackets.isEmpty() && stackNum.isEmpty()){
                    finalQueue.enqueue("" + c);
                    finalCounter++;
                } else {
                    if(stackNum.isEmpty() || brackets.isEmpty()) { //for the case 2S[N] 
                        throw new IllegalArgumentException("Wrong");
                    }
                    direction += c;
                }
                continue;
            }
        
            if (c == ']') { 
                if(!(direction.equals(""))) {
                    stackDirection.push(direction);
                }
                if(direction.equals("") && stackDirection.isEmpty() && tempDirectionQueue.isEmpty()){
                    throw new IllegalArgumentException("Missing Direction");
                } 
                numClosedBracket++;
                if (stackNum.isEmpty() || brackets.isEmpty()) {
                    throw new IllegalArgumentException("Something is wrong");
                }
      
                int coefficient = stackNum.pop();

                brackets.pop();
                if (!(direction.equals(""))) {
        
        
                    temp = stackDirection.pop();
                    for (int n = 0; n < coefficient - 1; n++) {
                        temp += direction; 
                    } 

             
                    if(coefficient == 0){
                        num = "";
                        direction = "";
                        tempDirectionQueue.clear();
                        continue;
                    }


              
                    if(brackets.isEmpty()){ //two situations whether it is empty or not
                        finalQueue.enqueue(temp);
                        finalCounter ++;
                    } else if (coefficient != 0){ 
                        tempDirectionQueue.enqueue(temp);
                        counterTemp++;
                    }
                    num = "";
                    direction = "";
                    continue;
                } else {
                    String combine = "";
                    for(int l = 0; l < counterTemp; l++){
                        combine += tempDirectionQueue.dequeue();
                    }

                    temp = combine;
                    combine = "";

                    for (int n = 0; n < coefficient; n++) {
                        combine += temp; 
                    } 
                    counterTemp = 0;
                    if(brackets.isEmpty()) {
                        finalQueue.enqueue(combine);
                        finalCounter++;
                        continue;
                    } else { 
                        tempDirectionQueue.enqueue(combine);
                        counterTemp ++;
                        continue;
                    }
                }
            }
            throw new IllegalArgumentException("Not right char");
        }
        if (numOpenBracket != numClosedBracket) {
            throw new IllegalArgumentException("Brackets are wrong");
        }
        for(int m = 0; m < finalCounter; m++){
            finalDirection += finalQueue.dequeue();
        }
        for(int k = 0; k < finalDirection.length(); k++){
            char i = finalDirection.charAt(k);
            this.enqueue((Direction) getDirection(i));
        }
    }


    private Object getDirection(char c){
        switch (c){
            case 'N': return Direction.NORTH;
            case 'S': return Direction.SOUTH;
            case 'W': return Direction.WEST;
            case 'E': return Direction.EAST;
            default: return null;
        }
    }

}



