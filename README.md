# hotswap
Java Class Loading - Hot Swap

1. 运行StartUp类下的main，会加载test_data下的MyHotSwap.class, 执行hello方法，不断打印hello
2. 修改test_data下的MyHotSwap.java文件，将hello方法修改为打印hello world
3. 重新编译test_data下的java文件：javac HotSwap.java MyHotSwap.java
4. 会看到控制台的输出从hello变为hello world
