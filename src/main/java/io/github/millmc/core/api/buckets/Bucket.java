package io.github.millmc.core.api.buckets;

import javax.annotation.Nullable;

import io.github.millmc.core.api.pkgs.InstallablePackage;
import io.github.millmc.core.impl.MillImpl;

import com.patchworkmc.logging.Logger;

public interface Bucket {
	String getSlug();
	String getRemoteURL();
	@Nullable
	InstallablePackage getPackage(String slug);
	void sync();
	default Logger getLogger() {
		return MillImpl.LOGGER;
	}
}
