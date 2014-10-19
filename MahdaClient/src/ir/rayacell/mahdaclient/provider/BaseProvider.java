package ir.rayacell.mahdaclient.provider;

import ir.rayacell.mahdaclient.model.BaseModel;
import ir.rayacell.mahdaclient.param.BaseParam;

public abstract class BaseProvider {
	public ProviderManager mProviderManager;
	public BaseProvider(ProviderManager providerManager) {
		// TODO Auto-generated constructor stub
		this.mProviderManager = providerManager;
	}
	public abstract boolean connect();

	public abstract boolean send(BaseParam param);

	public abstract void recieve(BaseModel model);
}