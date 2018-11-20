package com.mvp.cifer.wanandroid.utils.glide;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class LoaderFactory {

  public static Map<Class, ILoader> map = new LinkedHashMap<Class, ILoader>();

  public static ILoader getLoader(Class<? extends ILoader> cls) throws InstantiationException,
          IllegalAccessException {
    ILoader loader = map.get(cls);
    if (loader == null) {
      regit(cls.newInstance());
    }
    return map.get(cls);
  }

  private static void regit(ILoader loder) {
    map.put(loder.getClass(), loder);
  }

  public static Collection<ILoader> getAll() {
    return map.values();
  }
}
