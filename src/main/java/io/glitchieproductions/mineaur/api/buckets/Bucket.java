package io.glitchieproductions.mineaur.api.buckets;

import javax.annotation.Nullable;

import io.glitchieproductions.mineaur.api.pkgs.InstallablePackage;
import io.glitchieproductions.mineaur.impl.MineAurImpl;

import com.patchworkmc.logging.Logger;

public interface Bucket {
	String getSlug();
	String getDownloadURL();
	@Nullable
	InstallablePackage getPackage(String slug);
	default Logger getLogger() {
		return MineAurImpl.LOGGER;
	}
}
