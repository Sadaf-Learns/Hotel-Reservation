public class Room {
    private int roomId;
    private int roomBed;
    private int roomPrice;
    private boolean isReserved;
    public Room(int roomId,int roomBed,int roomPrice,boolean isReserved){
        this.roomBed=roomBed;
        this.roomId=roomId;
        this.roomPrice=roomPrice;
        this.isReserved=false;
    }
    public int getRoomId(){
        return roomId;
    }
    public int getRoomBed(){
        return roomBed;
    }
    public int getRoomPrice(){
        return roomPrice;
    }
    public boolean getisReserved(){
        return isReserved;
    }
    public void setReserved(boolean reserved){
        isReserved=reserved;
    }

    @Override
    public String toString() {
        return("Room number:"+roomId+" Beds:"+roomBed+" Price:"+roomPrice+" Reservation state:"+isReserved);
    }
}
