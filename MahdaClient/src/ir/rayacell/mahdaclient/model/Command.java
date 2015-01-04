package ir.rayacell.mahdaclient.model;

import apl.vada.lib.db.annotations.Column;
import apl.vada.lib.db.util.ColumnType;

public class Command extends BaseModel {

	@Column(type = ColumnType.INTEGER)
	private int duration;

	@Column(type = ColumnType.INTEGER)
	private int repetition_count;

	@Column(type = ColumnType.INTEGER)
	private int delay;

	@Column(type = ColumnType.TEXT)
	private String date_and_time;

	public Command(long commandid, String phonenumber, String commandtype,
			String datetime, int duration, int repetition, int delay) {
		super(commandid, phonenumber, commandtype);
		this.date_and_time = datetime;
		this.duration = duration;
		this.repetition_count = repetition;
		this.delay = delay;
	}

	public String getDate_and_time() {
		return date_and_time;
	}

	public int getDuration() {
		return duration;
	}

	public int getRepetition_count() {
		return repetition_count;
	}

	public int getDelay() {
		return delay;
	}

}
