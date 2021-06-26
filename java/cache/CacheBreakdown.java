public class CacheBreakdown {
	
	public Object getData(String key) {
		Object data = getDataFromCache(key);
		if (data == null) {
			if (getLock(key)) {
				try {
					data = getDataFromDb(key);
					updateDataToCache(key, data);
				} catch (Exception e) {
					releaseLock(key);
				} finally {
					releaseLock(key);
				}
			} else {
				// failed to grap lock , waiting a moment to reacquire data
				Thread.sleep(500);
				getData(key);
			}
		}
	}

	public Obejct getDataFromCache(String key) {
		return "data from cache";
	}

	public void updateDataToCache(String key, Object obj) {
		return;
	}

	public Object getDataFromDb(String key) {
		return "data from db";
	}

	public boolean getLock(String key) {
		// simulation get the distribution lock or jvm lock
		return true;
	}

	public boolean releaseLock(String key) {
		return true;
	}
}
