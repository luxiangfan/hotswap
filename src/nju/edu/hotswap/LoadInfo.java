package nju.edu.hotswap;

import nju.edu.classloader.MyClassLoader;

/**
 * @author luxiangfan
 */
public class LoadInfo {
	private MyClassLoader myLoader;
	private long          loadTime;
	private HotSwap       hotSwap;

	public LoadInfo(MyClassLoader myLoader, long loadTime) {
		this.myLoader = myLoader;
		this.loadTime = loadTime;
	}

	public MyClassLoader getMyLoader() {
		return myLoader;
	}

	public void setMyLoader(MyClassLoader myLoader) {
		this.myLoader = myLoader;
	}

	public long getLoadTime() {
		return loadTime;
	}

	public void setLoadTime(long loadTime) {
		this.loadTime = loadTime;
	}

	public HotSwap getHotSwap() {
		return hotSwap;
	}

	public void setHotSwap(HotSwap hotSwap) {
		this.hotSwap = hotSwap;
	}

}
