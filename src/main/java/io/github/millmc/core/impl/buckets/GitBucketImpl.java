package io.github.millmc.core.impl.buckets;

import java.io.File;
import javax.annotation.Nullable;

import io.github.millmc.core.api.buckets.Bucket;
import io.github.millmc.core.api.pkgs.InstallablePackage;

public class GitBucketImpl implements Bucket {

	public GitBucketImpl(String slug, File workingDirectory, String remoteUrl) {

	}
	@Override
	public String getSlug() {
		return null;
	}

	@Override
	public String getRemoteURL() {
		return null;
	}

	@Nullable
	@Override
	public InstallablePackage getPackage(String slug) {
		return null;
	}

	@Override
	public void sync() {

	}
}
