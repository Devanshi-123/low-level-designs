public enum Note {
    Ten(10),
    Twenty(20),
    Fifty(50);

    private final int value;

    Note(int value){
        this.value = value;
    }

    public int getNoteVal(){
        return value;
    }
}
