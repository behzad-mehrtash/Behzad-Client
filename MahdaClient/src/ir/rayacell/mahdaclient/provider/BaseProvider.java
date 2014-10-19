package ir.rayacell.mahdaclient.provider;

import ir.rayacell.mahdaclient.param.BaseParam;


public interface BaseProvider {
	public boolean connect();

	public boolean send(BaseParam param);

	public void recieve(BaseParam param);
}
