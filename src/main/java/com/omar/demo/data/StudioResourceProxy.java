package com.omar.demo.data;

import com.omar.demo.cache.Cache;
import com.omar.demo.cache.CacheObject;
import com.omar.demo.objects.Studio;
import org.springframework.stereotype.Component;
import java.util.Set;

@Component
public class StudioResourceProxy implements Proxy {
  private final Resource referenceResource = new ReferenceResource(Studio.class);
  private final Cache cacheObject = CacheObject.getNewInstance();
  private static double overallRecordsCounter = 0;

  @Override
  public Class<?> getOutputClass() {
    return referenceResource.getOutputClass();
  }

  @Override
  public Object access(long id) {
    return referenceResource.access(id);
  }

  @Override
  public void add(long id, String reference) {
    synchronized (referenceResource){
      referenceResource.add(id, reference);
      ++overallRecordsCounter;
    }
  }

  @Override
  public Set<Long> getKeySet() {
    return referenceResource.getKeySet();
  }

  @Override
  public Cache getCache() {
    return cacheObject;
  }

  @Override
  public double getOverallRecordsCount() {
    return overallRecordsCounter;
  }

  @Override
  public Class<?> getObjectClass() {
    return Studio.class;
  }
}

