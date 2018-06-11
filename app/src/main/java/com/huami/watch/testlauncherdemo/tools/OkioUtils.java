//package com.huami.watch.testlauncherdemo.tools;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//
//import okio.BufferedSource;
//import okio.ByteString;
//import okio.Okio;
//import okio.Source;
//
///**
// *  okio 处理的工具类
// */
//public class OkioUtils {
//
//    private static OkioUtils instance;
//
//
//    private Okio okio;
//
//    public static OkioUtils getInstance() {
//        if (instance == null) {
//            synchronized (OkioUtils.class) {
//                if (instance == null) {
//                    instance = new OkioUtils();
//                }
//            }
//        }
//        return instance;
//    }
//
//    public OkioUtils() {
//
//
//    }
//
//
//    /**
//     * 读取文件内容
//     * @param file
//     * @throws IOException
//     */
//    public String readLines(File file) throws Exception {
//
//        StringBuffer stringBuffer =new StringBuffer() ;
//        Source fileSource = null;
//        fileSource = Okio.source(file);
//        BufferedSource bufferedSource = Okio.buffer(fileSource) ;
//        while (true) {
//            String line = bufferedSource.readUtf8Line();
//            if (line == null) {
//                break;
//            }else{
//                stringBuffer.append(line);
//            }
//        }
//        return  stringBuffer.toString();
//    }
//
//
//    /**
//     * 文件从 file copy 到 file
//     */
//    public void copyFileToFile(File srcFile, File destFile){
//        try {
//            Okio.buffer(Okio.sink(destFile)).writeAll(Okio.buffer(Okio.source(srcFile)));
//        }catch (FileNotFoundException e){
//            e.printStackTrace();
//        }catch (IOException ex){
//            ex.printStackTrace();
//        }
//    }
//
//    /**
//     * sink :
//     *
//     * source :
//     */
//    public void test(){
//
//
//        try {
//            Okio.sink(new File(""));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        ByteString.encodeUtf8("");
//
//    }
//}
