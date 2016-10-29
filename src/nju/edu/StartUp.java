package nju.edu;

import nju.edu.hotswap.HotSwap;
import nju.edu.hotswap.HotSwapFactory;

/**
 * @author luxiangfan
 */
public class StartUp implements Runnable {

	public static final String MY_HOT_SWAP = "nju.edu.hotswap.MyHotSwap";

	public static void main(String[] args) {
		new Thread(new StartUp()).start();
	}

	@Override
	public void run() {
		while (true) {
			HotSwap hotSwap = HotSwapFactory.getHotSwap(MY_HOT_SWAP);
			System.out.println(hotSwap.getClass().getClassLoader());
			hotSwap.hello();

//			try {
//				Method m = hotSwap.getClass().getMethod("hello", new Class[]{});
//				m.invoke(hotSwap, new Object[]{});
//			} catch (Exception e) {
//				e.printStackTrace();
//			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
