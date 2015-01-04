package ir.rayacell.mahdaclient.model;

import apl.vada.lib.db.annotations.Column;
import apl.vada.lib.db.util.ColumnType;

public class StatusModel extends BaseModel {

	@Column(type = ColumnType.TEXT)
	private String IPaddress;

	public StatusModel(long commandid, String phonenumber, String commandtype,
			String ipaddress) {
		super(commandid, phonenumber, commandtype);
		this.IPaddress = ipaddress;
	}

	public String getIp_Address() {
		return IPaddress;
	}

	
}
