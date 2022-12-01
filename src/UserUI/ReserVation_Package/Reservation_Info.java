package UserUI.ReserVation_Package;

public class Reservation_Info {
	private String name;
	private int Reservation_state;
	public Reservation_Info(String name, int State) {
		this.name = name;
		this.Reservation_state = State;
	}
	
	public String Get_name() {
		return this.name;
	}
	public int Get_State() {
		return this.Reservation_state;
	}
	public void changeState(int state) {
		this.Reservation_state = state;
	}
}
