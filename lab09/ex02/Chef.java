package lab09.ex02;

abstract class Chef {
    private Chef successor = null;

    public void parse (String request) {
        if (successor != null) {
            successor.parse(request);
        } else {
            System.out.println("We're sorry but that request can't be satisfied by our service!");
        }
    }

    protected boolean canHandleRequest (String request, String foodType) {
        return (request == null) || (request.toLowerCase().contains(foodType.toLowerCase()));
    }

    public Chef setSuccessor(Chef successor) {
        this.successor = successor;
        return this;
    }

    protected int randomTime(){
        return (int) (Math.random()*20+10);
    }
}