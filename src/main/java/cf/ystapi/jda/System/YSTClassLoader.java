package cf.ystapi.jda.System;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static cf.ystapi.jda.System.ClassData.AddedClass;
import static cf.ystapi.jda.System.ClassData.ReplacedClass;

public class YSTClassLoader extends ClassLoader {

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if(ReplacedClass.containsKey(name)){
            try {
                InputStream is = new FileInputStream(ReplacedClass.get(name));
                byte[] buf = new byte[10000];
                int len = is.read(buf);
                return defineClass(name, buf, 0, len);
            } catch (IOException e) {
                throw new ClassNotFoundException("Error while finding class file", e);
            }
        }else if (AddedClass.containsKey(name)){
            try {
                InputStream is = new FileInputStream(AddedClass.get(name));
                byte[] buf = new byte[10000];
                int len = is.read(buf);
                return defineClass(name, buf, 0, len);
            } catch (IOException e) {
                throw new ClassNotFoundException("Error while finding class file", e);
            }
        }else
            return super.loadClass(name);
    }
}
