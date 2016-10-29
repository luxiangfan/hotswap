package nju.edu.hotswap;

import nju.edu.classloader.MyClassLoader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author luxiangfan
 */
public class HotSwapFactory {
	/**
	 * 记录热加载类的加载信息
	 */
	private static final Map<String, LoadInfo> loadInfoMap = new HashMap<>();

	private static final String CLASS_PATH = "/Users/luxiangfan/tech/hotswap/test_data/";

	public static HotSwap getHotSwap(String className) {
		File loadFile = new File(CLASS_PATH + className.replaceAll("\\.", "/") + ".class");
		long lastModified = loadFile.lastModified();

		/**
		 *  1. 查看是否被加载过, 如果没有被加载过则加载
		 *  2. 如果被加载过, 查看加载时间, 如果该类已经被修改, 则重新加载
		 */
		if (loadInfoMap.get(className) == null || loadInfoMap.get(className).getLoadTime() != lastModified) {
			load(className, lastModified);
		}

		return loadInfoMap.get(className).getHotSwap();
	}

	private static void load(String className, long lastModified) {

		/**
		 * 每次都需要new一个新的自定义的类加载器实例, 这样才能加载同一个class文件
		 * 否则同一个类加载器只能对同一个class对象加载一次, 发现有重复加载, 只会拿之前已经加载过的缓存
		 */
		MyClassLoader myLoader = new MyClassLoader(CLASS_PATH);
		Class<?> loadClass = null;
		try {
			loadClass = myLoader.loadClass(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		HotSwap hotSwap = newInstance(loadClass);
		LoadInfo loadInfo = new LoadInfo(myLoader, lastModified);
		loadInfo.setHotSwap(hotSwap);
		loadInfoMap.put(className, loadInfo);
	}

	private static HotSwap newInstance(Class<?> cls) {
		try {
			return (HotSwap) cls.newInstance();
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}

}
