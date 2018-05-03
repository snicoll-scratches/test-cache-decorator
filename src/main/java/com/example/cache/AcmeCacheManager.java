package com.example.cache;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.NoOpCache;

/**
 * A {@link CacheManager} that allows to change the enablement of a cache at runtime.
 *
 * @author Stephane Nicoll
 */
public class AcmeCacheManager implements CacheManager {

	private final CacheManager delegate;

	private final ConcurrentMap<String, Boolean> cachesEnablement = new ConcurrentHashMap<>();

	private final ConcurrentMap<String, Cache> disabledCaches = new ConcurrentHashMap<>();

	public AcmeCacheManager(CacheManager delegate) {
		this.delegate = delegate;
	}

	public void enableCache(String name) {
		this.cachesEnablement.put(name, true);
	}

	public void disableCache(String name) {
		this.cachesEnablement.put(name, false);
	}

	@Override
	public Cache getCache(String name) {
		return (cachesEnablement.getOrDefault(name, true)
				? this.delegate.getCache(name)
				: this.disabledCaches.computeIfAbsent(name, NoOpCache::new));
	}

	@Override
	public Collection<String> getCacheNames() {
		return this.delegate.getCacheNames();
	}

}
