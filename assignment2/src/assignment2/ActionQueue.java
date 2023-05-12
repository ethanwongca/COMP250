package assignment2;
//March 20 Ver
public class ActionQueue extends MyQueue<Direction> {
    //following my previous implementation
    //two stacks might come in handy like the art example in class
    //so keep in track of the num and brackets
    public ActionQueue() {
        super();
    }

    public void clear() {
        super.clear();
        //anything else lol
    }

    public void loadFromEncodedString(String s) {
        MyStack<Integer> stackNum = new MyStack<>();
        MyStack<String> brackets = new MyStack<>();
        MyStack<String> stackDirection = new MyStack<>();
        MyQueue<String> tempDirectionQueue = new MyQueue<>();
        MyQueue<String> finalQueue = new MyQueue<>();
        //maybe while is more effective
        String num = "";
        String direction = "";
        String temp;
        String finalDirection = "";
        int numOpenBracket = 0;
        int numClosedBracket = 0;
        int counterTemp = 0;
        int finalCounter = 0;

        for (int i = 0; i < s.length(); i++) {
            //remove the initial cases
            //forgot you note: Maybe change target Queue
            char c = s.charAt(i);
            //first puts num as normal
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
                    if(stackNum.isEmpty() || brackets.isEmpty()) { //for the case 2S[N] new edit
                        throw new IllegalArgumentException("Wrong");
                    }
                    direction += c;
                }
                continue;
            }
            // will finish tmrw has issue with direction logic.
            if (c == ']') { //have to count brackets somehow
                if(!(direction.equals(""))) {
                    stackDirection.push(direction);
                }//push whatever to trick if
                if(direction.equals("") && stackDirection.isEmpty() && tempDirectionQueue.isEmpty()){
                    throw new IllegalArgumentException("Missing Direction");
                } //check this
                numClosedBracket++;
                if (stackNum.isEmpty() || brackets.isEmpty()) {
                    throw new IllegalArgumentException("Something is wrong");
                }
                //two cases either it is in brackets or not
                //between the two we need to do this now continue with either or
                int coefficient = stackNum.pop();

                brackets.pop();
                if (!(direction.equals(""))) {
                    //right in essence

                    temp = stackDirection.pop();
                    for (int n = 0; n < coefficient - 1; n++) {
                        temp += direction; //fixed now not a multiple
                    } // this for loop is wrong

                    //the 0 case, clear the temp and clear the direction
                    if(coefficient == 0){
                        num = "";
                        direction = "";
                        tempDirectionQueue.clear();
                        continue;
                    }


                    //currently we have it for 3[NE]2[S] but need to combine
                    //put this in a queue instead so we can push out later
                    if(brackets.isEmpty()){ //two situations whether it is empty or not
                        finalQueue.enqueue(temp);
                        finalCounter ++;
                    } else if (coefficient != 0){ //changed to else if
                        tempDirectionQueue.enqueue(temp);
                        counterTemp++;
                    }
                    num = "";
                    direction = "";
                    continue;
                } else { //if direction.equals("")
                    String combine = "";
                    for(int l = 0; l < counterTemp; l++){ //issue with the counter
                        combine += tempDirectionQueue.dequeue();
                    }

                    temp = combine;
                    combine = "";

                    for (int n = 0; n < coefficient; n++) {
                        combine += temp; //fixed now not a multiple
                    } //error here
                    counterTemp = 0;
                    if(brackets.isEmpty()) {
                        finalQueue.enqueue(combine);
                        finalCounter++;
                        continue;
                    } else { //ADDED TODAY NEED TO CHECK
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



